package tn.esprit.spring.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.spring.entities.Course;
import tn.esprit.spring.services.ICourseServices;
import tn.esprit.spring.tdo.CourseDTO;

import java.util.List;

@Tag(name = "\uD83D\uDCDA Course Management")
@RestController
@RequestMapping("/course")
@RequiredArgsConstructor

public class CourseRestController {
    
    private final ICourseServices courseServices;

    @Operation(description = "Add Course")
    @PostMapping(value = "/add",produces = "text/plain")

    public Course addCourse(@RequestBody CourseDTO course){
        return  courseServices.addCourse(course);
    }

    @Operation(description = "Retrieve all Courses")
    @GetMapping(value = "/all",produces = "text/plain")
    public List<Course> getAllCourses(){
        return courseServices.retrieveAllCourses();
    }

    @Operation(description = "Update Course ")
    @PutMapping(value = "/update",produces = "text/plain")
    public Course updateCourse(@RequestBody CourseDTO courseDTO){
        return  courseServices.updateCourse(courseDTO);
    }

    @Operation(description = "Retrieve Course by Id")
    @GetMapping(value = "/get/{id-course}",produces = "text/plain")
    public Course getById(@PathVariable("id-course") Long numCourse){
        return courseServices.retrieveCourse(numCourse);
    }

}
