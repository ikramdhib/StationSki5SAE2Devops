package tn.esprit.spring.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.spring.tdo.CourseDTO;
import tn.esprit.spring.entities.Course;
import tn.esprit.spring.entities.TypeCourse;
import tn.esprit.spring.services.ICourseServices;
import java.util.List;

@Tag(name = "\uD83D\uDCDA Course Management")
@RestController
@RequestMapping("/course")
@RequiredArgsConstructor
public class CourseRestController {
    
    private final ICourseServices courseServices;

    @Operation(description = "Add Course")
    @PostMapping("/add")
    public Course addCourse(@RequestBody CourseDTO course) {
        return courseServices.addCourse(course);
    }
    @Operation(description = "Retrieve all Courses")
    @GetMapping("/all")
    public List<Course> getAllCourses() {
        return courseServices.retrieveAllCourses();
    }

    @Operation(description = "Update Course")
    @PutMapping("/update")
    public Course updateCourse(@RequestBody CourseDTO course) {
        return courseServices.updateCourse(course);
    }

    @Operation(description = "Retrieve Course by Id")
    @GetMapping("/get/{id-course}")
    public Course getById(@PathVariable("id-course") Long numCourse) {
        return courseServices.retrieveCourse(numCourse);
    }

    @Operation(description = "Delete Course by Id")
    @DeleteMapping("/deleteCourse/{numCourse}")
    public void deleteCourse(@PathVariable Long numCourse) {
        courseServices.deleteCourse(numCourse);
    }

    @Operation(description = "Search Courses based on criteria")
    @GetMapping("/search")
    public List<Course> searchCourses(
            @RequestParam(required = false) Integer level,
            @RequestParam(required = false) TypeCourse typeCourse,
            @RequestParam(required = false) Float minPrice,
            @RequestParam(required = false) Float maxPrice) {
        return courseServices.searchCourses(level, typeCourse, minPrice, maxPrice);
    }

    @Operation(description = "Recommend Courses based on type")
    @GetMapping("/recommend")
    public ResponseEntity<List<Course>> recommendCourses(@RequestParam TypeCourse typeCourse) {
        List<Course> recommendedCourses = courseServices.recommendCourses(typeCourse);
        return new ResponseEntity<>(recommendedCourses, HttpStatus.OK);
    }
}
