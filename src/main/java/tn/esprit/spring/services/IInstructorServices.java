package tn.esprit.spring.services;

import tn.esprit.spring.entities.InstructorTDO;

import java.util.List;

public interface IInstructorServices {

    InstructorTDO addInstructor(InstructorTDO instructor);

    List<InstructorTDO> retrieveAllInstructors();

    InstructorTDO updateInstructor(InstructorTDO instructor);

    InstructorTDO retrieveInstructor(Long numInstructor);

    InstructorTDO addInstructorAndAssignToCourse(InstructorTDO instructor, Long numCourse);

}
