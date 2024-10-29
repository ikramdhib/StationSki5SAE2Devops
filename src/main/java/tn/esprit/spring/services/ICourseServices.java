package tn.esprit.spring.services;

import tn.esprit.spring.entities.Course;
import tn.esprit.spring.entities.CourseDTO;
import tn.esprit.spring.entities.TypeCourse;

import java.util.List;

public interface ICourseServices {

    List<Course> retrieveAllCourses();

    Course  addCourse(Course  course);

    Course updateCourse(Course course);
    public void deleteCourse(Long numCourse);

    Course retrieveCourse(Long numCourse);
    List<Course> searchCourses(Integer level, TypeCourse typeCourse, Float minPrice, Float maxPrice);
    public List<Course> recommendCourses(TypeCourse typeCourse) ;
    public Course toCourse(CourseDTO courseDTO);


}
