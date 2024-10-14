package tn.esprit.spring.services;

import tn.esprit.spring.entities.CourseDTO;
import tn.esprit.spring.entities.TypeCourse;

import java.util.List;

public interface ICourseServices {

    List<CourseDTO> retrieveAllCourses();

    CourseDTO  addCourse(CourseDTO  course);

    CourseDTO updateCourse(CourseDTO course);

    CourseDTO retrieveCourse(Long numCourse);


}
