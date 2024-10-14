package tn.esprit.spring.services;

import tn.esprit.spring.entities.*;

import java.util.List;

public interface IRegistrationServices {

	RegistrationTDO addRegistrationAndAssignToSkier(RegistrationTDO registration, Long numSkier);
	RegistrationTDO assignRegistrationToCourse(Long numRegistration, Long numCourse);
	RegistrationTDO addRegistrationAndAssignToSkierAndCourse(RegistrationTDO registration, Long numSkieur, Long numCours);
	List<Integer> numWeeksCourseOfInstructorBySupport(Long numInstructor, Support support);
}

