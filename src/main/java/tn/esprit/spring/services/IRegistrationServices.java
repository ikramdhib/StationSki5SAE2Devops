package tn.esprit.spring.services;

import tn.esprit.spring.entities.*;
import tn.esprit.spring.tdo.RegistrationDTO;

import java.util.List;

public interface IRegistrationServices {

	Registration addRegistrationAndAssignToSkier(RegistrationDTO registration, Long numSkier);
	Registration assignRegistrationToCourse(Long numRegistration, Long numCourse);
	Registration addRegistrationAndAssignToSkierAndCourse(RegistrationDTO registration, Long numSkieur, Long numCours);
	List<Integer> numWeeksCourseOfInstructorBySupport(Long numInstructor, Support support);
}

