package tn.esprit.spring.services;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import tn.esprit.spring.entities.SubscriptionDTO;
import tn.esprit.spring.entities.TypeSubscription;

public interface ISubscriptionServices {

	SubscriptionDTO addSubscription(SubscriptionDTO subscription);

	SubscriptionDTO updateSubscription(SubscriptionDTO subscription);

	SubscriptionDTO retrieveSubscriptionById(Long numSubscription);

	Set<SubscriptionDTO> getSubscriptionByType(TypeSubscription type);

	List<SubscriptionDTO> retrieveSubscriptionsByDates(LocalDate startDate, LocalDate endDate);


}
