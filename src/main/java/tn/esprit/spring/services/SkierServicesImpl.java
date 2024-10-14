package tn.esprit.spring.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.spring.entities.*;
import tn.esprit.spring.repositories.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@AllArgsConstructor
@Service
public class SkierServicesImpl implements ISkierServices {

    private ISkierRepository skierRepository;

    private IPisteRepository pisteRepository;

    private ICourseRepository courseRepository;

    private IRegistrationRepository registrationRepository;

    private ISubscriptionRepository subscriptionRepository;


    @Override
    public List<SkierDTO> retrieveAllSkiers() {
        return skierRepository.findAll();
    }

    @Override
    public SkierDTO addSkier(SkierDTO skier) {
        switch (skier.getSubscription().getTypeSub()) {
            case ANNUAL:
                skier.getSubscription().setEndDate(skier.getSubscription().getStartDate().plusYears(1));
                break;
            case SEMESTRIEL:
                skier.getSubscription().setEndDate(skier.getSubscription().getStartDate().plusMonths(6));
                break;
            case MONTHLY:
                skier.getSubscription().setEndDate(skier.getSubscription().getStartDate().plusMonths(1));
                break;
        }
        return skierRepository.save(skier);
    }

    @Override
    public SkierDTO assignSkierToSubscription(Long numSkier, Long numSubscription) {
        SkierDTO skier = skierRepository.findById(numSkier).orElse(null);
        SubscriptionDTO subscription = subscriptionRepository.findById(numSubscription).orElse(null);
        skier.setSubscription(subscription);
        return skierRepository.save(skier);
    }

    @Override
    public SkierDTO addSkierAndAssignToCourse(SkierDTO skier, Long numCourse) {
        SkierDTO savedSkier = skierRepository.save(skier);
        CourseDTO course = courseRepository.getById(numCourse);
        Set<RegistrationDTO> registrations = savedSkier.getRegistrations();
        for (RegistrationDTO r : registrations) {
            r.setSkier(savedSkier);
            r.setCourse(course);
            registrationRepository.save(r);
        }
        return savedSkier;
    }

    @Override
    public void removeSkier(Long numSkier) {
        skierRepository.deleteById(numSkier);
    }

    @Override
    public SkierDTO retrieveSkier(Long numSkier) {
        return skierRepository.findById(numSkier).orElse(null);
    }

    @Override
    public SkierDTO assignSkierToPiste(Long numSkieur, Long numPiste) {
        SkierDTO skier = skierRepository.findById(numSkieur).orElse(null);
        PisteDTO piste = pisteRepository.findById(numPiste).orElse(null);
        try {
            skier.getPistes().add(piste);
        } catch (NullPointerException exception) {
            Set<PisteDTO> pisteList = new HashSet<>();
            pisteList.add(piste);
            skier.setPistes(pisteList);
        }

        return skierRepository.save(skier);
    }

    @Override
    public List<SkierDTO> retrieveSkiersBySubscriptionType(TypeSubscription typeSubscription) {
        return skierRepository.findBySubscription_TypeSub(typeSubscription);
    }
}
