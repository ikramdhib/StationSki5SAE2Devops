package tn.esprit.spring.services;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import tn.esprit.spring.entities.Subscription;
import tn.esprit.spring.entities.TypeSubscription;
import tn.esprit.spring.tdo.SubscriptionDTO;

public interface ISubscriptionServices {

	Subscription addSubscription(SubscriptionDTO subscription);

	Subscription updateSubscription(SubscriptionDTO subscription);

	Subscription retrieveSubscriptionById(Long numSubscription);

	Set<Subscription> getSubscriptionByType(TypeSubscription type);

	List<Subscription> retrieveSubscriptionsByDates(LocalDate startDate, LocalDate endDate);


}
