package tn.esprit.spring.services;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import tn.esprit.spring.entities.*;
import tn.esprit.spring.repositories.ICourseRepository;
import tn.esprit.spring.repositories.IRegistrationRepository;
import tn.esprit.spring.repositories.ISkierRepository;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.Period;
import java.util.List;
@Slf4j
@AllArgsConstructor
@Service
public class RegistrationServicesImpl implements  IRegistrationServices{

    private IRegistrationRepository registrationRepository;
    private ISkierRepository skierRepository;
    private ICourseRepository courseRepository;


    @Override
    public RegistrationDTO addRegistrationAndAssignToSkier(RegistrationDTO registration, Long numSkier) {
        SkierDTO skier = skierRepository.findById(numSkier).orElse(null);
        registration.setSkier(skier);
        return registrationRepository.save(registration);
    }

    @Override
    public RegistrationDTO assignRegistrationToCourse(Long numRegistration, Long numCourse) {
        RegistrationDTO registration = registrationRepository.findById(numRegistration).orElse(null);
        CourseDTO course = courseRepository.findById(numCourse).orElse(null);
        registration.setCourse(course);
        return registrationRepository.save(registration);
    }

    @Transactional
    @Override
    public RegistrationDTO addRegistrationAndAssignToSkierAndCourse(RegistrationDTO registration, Long numSkieur, Long numCours) {
        SkierDTO skier = skierRepository.findById(numSkieur).orElse(null);
        CourseDTO course = courseRepository.findById(numCours).orElse(null);

        if (skier == null || course == null) {
            return null;
        }

        if (isAlreadyRegisteredForCourse(registration, skier, course)) {
            log.info("Sorry, you're already registered to this course for the week: " + registration.getNumWeek());
            return null;
        }

        int ageSkieur = calculateAge(skier.getDateOfBirth());
        log.info("Age " + ageSkieur);

        return processCourseRegistration(course, registration, skier, ageSkieur);
    }

// Helper Methods

    private boolean isAlreadyRegisteredForCourse(RegistrationDTO registration, SkierDTO skier, CourseDTO course) {
        return registrationRepository.countDistinctByNumWeekAndSkier_NumSkierAndCourse_NumCourse(
                registration.getNumWeek(), skier.getNumSkier(), course.getNumCourse()) >= 1;
    }

    private int calculateAge(LocalDate dateOfBirth) {
        return Period.between(dateOfBirth, LocalDate.now()).getYears();
    }

    private RegistrationDTO processCourseRegistration(CourseDTO course, RegistrationDTO registration, SkierDTO skier, int ageSkieur) {
        switch (course.getTypeCourse()) {
            case INDIVIDUAL:
                log.info("Adding individual course without checks.");
                return assignRegistration(registration, skier, course);

            case COLLECTIVE_CHILDREN:
                return handleChildCourseRegistration(registration, skier, course, ageSkieur);

            default:
                return handleAdultCourseRegistration(registration, skier, course, ageSkieur);
        }
    }

    private RegistrationDTO handleChildCourseRegistration(RegistrationDTO registration, SkierDTO skier, CourseDTO course, int ageSkieur) {
        if (ageSkieur < 16) {
            log.info("Ok CHILD !");
            if (isCourseAvailableForWeek(course, registration.getNumWeek())) {
                log.info("Course successfully added !");
                return assignRegistration(registration, skier, course);
            } else {
                log.info("Full Course ! Please choose another week to register !");
                return null;
            }
        } else {
            log.info("Sorry, your age doesn't allow you to register for this course! Try a Collective Adult Course...");
            return null;
        }
    }

    private RegistrationDTO handleAdultCourseRegistration(RegistrationDTO registration, SkierDTO skier, CourseDTO course, int ageSkieur) {
        if (ageSkieur >= 16) {
            log.info("Ok ADULT !");
            if (isCourseAvailableForWeek(course, registration.getNumWeek())) {
                log.info("Course successfully added !");
                return assignRegistration(registration, skier, course);
            } else {
                log.info("Full Course ! Please choose another week to register !");
                return null;
            }
        } else {
            log.info("Sorry, your age doesn't allow you to register for this course! Try a Collective Child Course...");
            return null;
        }
    }

    private boolean isCourseAvailableForWeek(CourseDTO course, int numWeek) {
        return registrationRepository.countByCourseAndNumWeek(course, numWeek) < 6;
    }

    private RegistrationDTO assignRegistration (RegistrationDTO registration, SkierDTO skier, CourseDTO course){
        registration.setSkier(skier);
        registration.setCourse(course);
        return registrationRepository.save(registration);
    }

    @Override
    public List<Integer> numWeeksCourseOfInstructorBySupport(Long numInstructor, Support support) {
        return registrationRepository.numWeeksCourseOfInstructorBySupport(numInstructor, support);
    }

}
