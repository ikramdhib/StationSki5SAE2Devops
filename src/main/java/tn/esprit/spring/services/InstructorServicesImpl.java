package tn.esprit.spring.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.spring.entities.CourseDTO;
import tn.esprit.spring.entities.InstructorDTO;
import tn.esprit.spring.repositories.ICourseRepository;
import tn.esprit.spring.repositories.IInstructorRepository;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@AllArgsConstructor
@Service
public class InstructorServicesImpl implements IInstructorServices{

    private IInstructorRepository instructorRepository;
    private ICourseRepository courseRepository;

    @Override
    public InstructorDTO addInstructor(InstructorDTO instructor) {
        return instructorRepository.save(instructor);
    }

    @Override
    public List<InstructorDTO> retrieveAllInstructors() {
        return instructorRepository.findAll();
    }

    @Override
    public InstructorDTO updateInstructor(InstructorDTO instructor) {
        return instructorRepository.save(instructor);
    }

    @Override
    public InstructorDTO retrieveInstructor(Long numInstructor) {
        return instructorRepository.findById(numInstructor).orElse(null);
    }

    @Override
    public InstructorDTO addInstructorAndAssignToCourse(InstructorDTO instructor, Long numCourse) {
        CourseDTO course = courseRepository.findById(numCourse).orElse(null);
        Set<CourseDTO> courseSet = new HashSet<>();
        courseSet.add(course);
        instructor.setCourses(courseSet);
        return instructorRepository.save(instructor);
    }


}
