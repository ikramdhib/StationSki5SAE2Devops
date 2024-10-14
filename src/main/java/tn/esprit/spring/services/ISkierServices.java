package tn.esprit.spring.services;

import tn.esprit.spring.entities.SkierTDO;
import tn.esprit.spring.entities.TypeSubscription;

import java.util.List;

public interface ISkierServices {

	List<SkierTDO> retrieveAllSkiers();

	SkierTDO  addSkier(SkierTDO  skier);

	SkierTDO assignSkierToSubscription(Long numSkier, Long numSubscription);

	SkierTDO addSkierAndAssignToCourse(SkierTDO skier, Long numCourse);

	void removeSkier (Long numSkier);

	SkierTDO retrieveSkier (Long numSkier);


	SkierTDO assignSkierToPiste(Long numSkieur, Long numPiste);

	List<SkierTDO> retrieveSkiersBySubscriptionType(TypeSubscription typeSubscription);

}
