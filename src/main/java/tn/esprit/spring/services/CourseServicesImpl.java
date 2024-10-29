package tn.esprit.spring.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.spring.entities.Course;
import tn.esprit.spring.entities.TypeCourse;
import tn.esprit.spring.repositories.ICourseRepository;

import java.util.List;
import java.util.NoSuchElementException;

@AllArgsConstructor
@Service
public class CourseServicesImpl implements  ICourseServices{

    private ICourseRepository courseRepository;

    @Override
    public List<Course> retrieveAllCourses() {
        return courseRepository.findAll();
    }

    @Override
    public Course addCourse(Course course) {
        return courseRepository.save(course);
    }

    @Override
    public Course updateCourse(Course course) {
        return courseRepository.save(course);
    }

    @Override
    public void deleteCourse(Long numCourse) {
        courseRepository.deleteById(numCourse);
    }

    @Override
    public Course retrieveCourse(Long numCourse) {

        return courseRepository.findById(numCourse).orElseThrow(() -> new NoSuchElementException("Course not found with id: " + numCourse));
    }
    @Override
    public List<Course> searchCourses(Integer level, TypeCourse typeCourse, Float minPrice, Float maxPrice) {
        return courseRepository.findAllByCriteria(level, typeCourse, minPrice, maxPrice);
    }

    @Override
    public List<Course> recommendCourses(TypeCourse typeCourse) {
        return courseRepository.findByTypeCourse(typeCourse);
    }

    }

