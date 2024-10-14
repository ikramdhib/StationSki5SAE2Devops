package tn.esprit.spring.services;

import tn.esprit.spring.entities.SkierDTO;
import tn.esprit.spring.entities.TypeSubscription;

import java.util.List;

public interface ISkierServices {

	List<SkierDTO> retrieveAllSkiers();

	SkierDTO  addSkier(SkierDTO  skier);

	SkierDTO assignSkierToSubscription(Long numSkier, Long numSubscription);

	SkierDTO addSkierAndAssignToCourse(SkierDTO skier, Long numCourse);

	void removeSkier (Long numSkier);

	SkierDTO retrieveSkier (Long numSkier);


	SkierDTO assignSkierToPiste(Long numSkieur, Long numPiste);

	List<SkierDTO> retrieveSkiersBySubscriptionType(TypeSubscription typeSubscription);

}
