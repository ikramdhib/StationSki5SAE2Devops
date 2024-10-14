package tn.esprit.spring.services;

import tn.esprit.spring.entities.InstructorDTO;

import java.util.List;

public interface IInstructorServices {

    InstructorDTO addInstructor(InstructorDTO instructor);

    List<InstructorDTO> retrieveAllInstructors();

    InstructorDTO updateInstructor(InstructorDTO instructor);

    InstructorDTO retrieveInstructor(Long numInstructor);

    InstructorDTO addInstructorAndAssignToCourse(InstructorDTO instructor, Long numCourse);

}
