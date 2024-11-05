package tn.esprit.spring.services;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import tn.esprit.spring.entities.Skier;
import tn.esprit.spring.entities.Subscription;
import tn.esprit.spring.entities.TypeSubscription;
import tn.esprit.spring.repositories.ISkierRepository;
import tn.esprit.spring.repositories.ISubscriptionRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Slf4j
@AllArgsConstructor
@Service
public class SubscriptionServicesImpl implements ISubscriptionServices{

    private ISubscriptionRepository subscriptionRepository;

    private ISkierRepository skierRepository;

    @Override
    public Subscription addSubscription(Subscription subscription) {
        if (subscription.getTypeSub() == null) {
            throw new IllegalArgumentException("TypeSubscription cannot be null");
        }

        switch (subscription.getTypeSub()) {
            case ANNUAL:
                subscription.setEndDate(subscription.getStartDate().plusYears(1));
                break;
            case SEMESTRIEL:
                subscription.setEndDate(subscription.getStartDate().plusMonths(6));
                break;
            case MONTHLY:
                subscription.setEndDate(subscription.getStartDate().plusMonths(1));
                break;
            default:
                throw new IllegalArgumentException("Invalid TypeSubscription");
        }

        return subscriptionRepository.save(subscription);
    }


    @Override
    public Subscription updateSubscription(Subscription subscription) {
        return subscriptionRepository.save(subscription);
    }

    @Override
    public Subscription retrieveSubscriptionById(Long numSubscription) {
        return subscriptionRepository.findById(numSubscription).orElse(null);
    }

    @Override
    public Set<Subscription> getSubscriptionByType(TypeSubscription type) {
        return subscriptionRepository.findByTypeSubOrderByStartDateAsc(type);
    }

    @Override
    public List<Subscription> retrieveSubscriptionsByDates(LocalDate startDate, LocalDate endDate) {
        return subscriptionRepository.getSubscriptionsByStartDateBetween(startDate, endDate);
    }

    @Override
    @Scheduled(cron = "*/30 * * * * *") /* Cron expression to run a job every 30 secondes */
    public List<Subscription> retrieveSubscriptions() {
        for (Subscription sub: subscriptionRepository.findDistinctOrderByEndDateAsc()) {
            Skier   aSkier = skierRepository.findBySubscription(sub);
            log.info(sub.getNumSub().toString() + " | "+ sub.getEndDate().toString()
                    + " | "+ aSkier.getFirstName() + " " + aSkier.getLastName());
        }
        return null;
    }

    @Scheduled(cron = "*/30 * * * * *") // Cron expression to run a job every 30 seconds
    public void showMonthlyRecurringRevenue() {
        // Calculate monthly recurring revenue from different subscription types
        Float monthlyRevenue = subscriptionRepository.recurringRevenueByTypeSubEquals(TypeSubscription.MONTHLY);
        Float semiAnnualRevenue = subscriptionRepository.recurringRevenueByTypeSubEquals(TypeSubscription.SEMESTRIEL);
        Float annualRevenue = subscriptionRepository.recurringRevenueByTypeSubEquals(TypeSubscription.ANNUAL);

        // Handle null values, defaulting to 0 if necessary
        monthlyRevenue = (monthlyRevenue != null) ? monthlyRevenue : 0.0f;
        semiAnnualRevenue = (semiAnnualRevenue != null) ? semiAnnualRevenue : 0.0f;
        annualRevenue = (annualRevenue != null) ? annualRevenue : 0.0f;

        // Calculate total monthly revenue
        Float revenue = monthlyRevenue + (semiAnnualRevenue / 6) + (annualRevenue / 12);

        // Log the monthly revenue
        log.info("Monthly Revenue = " + revenue);
    }

}
