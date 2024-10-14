package tn.esprit.spring.services;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import tn.esprit.spring.entities.SubscriptionTDO;
import tn.esprit.spring.entities.TypeSubscription;

public interface ISubscriptionServices {

	SubscriptionTDO addSubscription(SubscriptionTDO subscription);

	SubscriptionTDO updateSubscription(SubscriptionTDO subscription);

	SubscriptionTDO retrieveSubscriptionById(Long numSubscription);

	Set<SubscriptionTDO> getSubscriptionByType(TypeSubscription type);

	List<SubscriptionTDO> retrieveSubscriptionsByDates(LocalDate startDate, LocalDate endDate);


}
