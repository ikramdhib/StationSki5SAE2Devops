package tn.esprit.spring.services;

import tn.esprit.spring.entities.Course;
import tn.esprit.spring.tdo.CourseDTO;

import java.util.List;

public interface ICourseServices {

    List<Course> retrieveAllCourses();

    Course  addCourse(CourseDTO course);

    Course updateCourse(CourseDTO course);

    Course retrieveCourse(Long numCourse);


}
