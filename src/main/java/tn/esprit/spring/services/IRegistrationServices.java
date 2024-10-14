package tn.esprit.spring.services;

import tn.esprit.spring.entities.*;

import java.util.List;

public interface IRegistrationServices {

	RegistrationDTO addRegistrationAndAssignToSkier(RegistrationDTO registration, Long numSkier);
	RegistrationDTO assignRegistrationToCourse(Long numRegistration, Long numCourse);
	RegistrationDTO addRegistrationAndAssignToSkierAndCourse(RegistrationDTO registration, Long numSkieur, Long numCours);
	List<Integer> numWeeksCourseOfInstructorBySupport(Long numInstructor, Support support);
}

