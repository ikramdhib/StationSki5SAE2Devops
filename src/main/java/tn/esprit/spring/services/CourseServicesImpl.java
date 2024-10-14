package tn.esprit.spring.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.spring.entities.CourseDTO;
import tn.esprit.spring.repositories.ICourseRepository;

import java.util.List;
@AllArgsConstructor
@Service
public class CourseServicesImpl implements  ICourseServices{

    private ICourseRepository courseRepository;

    @Override
    public List<CourseDTO> retrieveAllCourses() {
        return courseRepository.findAll();
    }

    @Override
    public CourseDTO addCourse(CourseDTO course) {
        return courseRepository.save(course);
    }

    @Override
    public CourseDTO updateCourse(CourseDTO course) {
        return courseRepository.save(course);
    }

    @Override
    public CourseDTO retrieveCourse(Long numCourse) {
        return courseRepository.findById(numCourse).orElse(null);
    }


}
