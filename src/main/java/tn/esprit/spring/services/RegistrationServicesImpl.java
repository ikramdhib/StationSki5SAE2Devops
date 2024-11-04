package tn.esprit.spring.services;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import tn.esprit.spring.entities.*;
import tn.esprit.spring.repositories.ICourseRepository;
import tn.esprit.spring.repositories.IRegistrationRepository;
import tn.esprit.spring.repositories.ISkierRepository;
import tn.esprit.spring.tdo.RegistrationDTO;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.Period;
import java.util.List;
@Slf4j
@AllArgsConstructor
@Service
public class RegistrationServicesImpl implements IRegistrationServices {

    private IRegistrationRepository registrationRepository;
    private ISkierRepository skierRepository;
    private ICourseRepository courseRepository;

    @Override
    public Registration addRegistrationAndAssignToSkier(Registration registration, Long numSkier) {
        Skier skier = skierRepository.findById(numSkier).orElse(null);
        registration.setSkier(skier);
        return registrationRepository.save(registration);
    }

    @Override
    public Registration assignRegistrationToCourse(Long numRegistration, Long numCourse) {
        return assignCourseToRegistration(numRegistration, numCourse);
    }

    @Transactional
    @Override
    public Registration addRegistrationAndAssignToSkierAndCourse(Registration registration, Long numSkieur, Long numCours) {
        Skier skier = skierRepository.findById(numSkieur).orElse(null);
        Course course = courseRepository.findById(numCours).orElse(null);

        if (skier == null || course == null || isAlreadyRegisteredForCourse(registration, skier, course)) {
            return null;
        }

        int ageSkieur = calculateAge(skier.getDateOfBirth());
        log.info("Age: " + ageSkieur);

        return processCourseRegistration(course, registration, skier, ageSkieur);
    }

    // Helper Methods
    private Registration assignCourseToRegistration(Long numRegistration, Long numCourse) {
        Registration registration = registrationRepository.findById(numRegistration).orElse(null);
        Course course = courseRepository.findById(numCourse).orElse(null);
        if (course == null || registration == null) {
            return null;
        }
        registration.setCourse(course);
        return registrationRepository.save(registration);
    }

    private boolean isAlreadyRegisteredForCourse(Registration registration, Skier skier, Course course) {
        return registrationRepository.countDistinctByNumWeekAndSkier_NumSkierAndCourse_NumCourse(
                registration.getNumWeek(), skier.getNumSkier(), course.getNumCourse()) >= 1;
    }

    private int calculateAge(LocalDate dateOfBirth) {
        return Period.between(dateOfBirth, LocalDate.now()).getYears();
    }

    private Registration processCourseRegistration(Course course, Registration registration, Skier skier, int ageSkieur) {
        if (isAgeAppropriateForCourse(course, ageSkieur)) {
            if (isCourseAvailableForWeek(course, registration.getNumWeek())) {
                return assignRegistration(registration, skier, course);
            } else {
                logCourseFullMessage();
                return null;
            }
        } else {
            logInvalidAgeMessage(course);
            return null;
        }
    }

    private boolean isAgeAppropriateForCourse(Course course, int ageSkieur) {
        switch (course.getTypeCourse()) {
            case INDIVIDUAL:
                log.info("Adding individual course without checks.");
                return true;
            case COLLECTIVE_CHILDREN:
                return ageSkieur < 16;
            default:
                return ageSkieur >= 16;
        }
    }

    private void logCourseFullMessage() {
        log.info("Full Course! Please choose another week to register.");
    }

    private void logInvalidAgeMessage(Course course) {
        if (course.getTypeCourse() == TypeCourse.COLLECTIVE_CHILDREN) {
            log.info("Sorry, your age doesn't allow you to register for this course! Try a Collective Adult Course...");
        } else {
            log.info("Sorry, your age doesn't allow you to register for this course! Try a Collective Child Course...");
        }
    }

    private boolean isCourseAvailableForWeek(Course course, int numWeek) {
        return registrationRepository.countByCourseAndNumWeek(course, numWeek) < 6;
    }

    private Registration assignRegistration(Registration registration, Skier skier, Course course) {
        registration.setSkier(skier);
        registration.setCourse(course);
        return registrationRepository.save(registration);
    }

    @Override
    public List<Integer> numWeeksCourseOfInstructorBySupport(Long numInstructor, Support support) {
        return registrationRepository.numWeeksCourseOfInstructorBySupport(numInstructor, support);
    }
}

