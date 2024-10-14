package tn.esprit.spring.services;


import tn.esprit.spring.entities.Instructor;
import tn.esprit.spring.tdo.InstructorDTO;

import java.util.List;

public interface IInstructorServices {

    Instructor addInstructor(InstructorDTO instructor);

    List<Instructor> retrieveAllInstructors();

    Instructor updateInstructor(InstructorDTO instructor);

    Instructor retrieveInstructor(Long numInstructor);

    Instructor addInstructorAndAssignToCourse(InstructorDTO instructor, Long numCourse);

}
