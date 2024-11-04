package tn.esprit.spring.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.spring.entities.Instructor;
import tn.esprit.spring.services.IInstructorServices;
import tn.esprit.spring.tdo.InstructorDTO;

import java.util.List;

@Tag(name = "\uD83D\uDC69\u200D\uD83C\uDFEB Instructor Management")
@RestController
@RequestMapping("/instructor")
@RequiredArgsConstructor
public class InstructorRestController {

    private final IInstructorServices instructorServices;

    @Operation(description = "Add Instructor")
    @PostMapping(value = "/add")
    public Instructor addInstructor(@RequestBody Instructor instructorDTO){
        return  instructorServices.addInstructor(instructorDTO);
    }
    @Operation(description = "Add Instructor and Assign To Course")
    @PutMapping(value = "/addAndAssignToCourse/{numCourse}")
    public Instructor addAndAssignToInstructor(@RequestBody Instructor instructor, @PathVariable("numCourse")Long numCourse){
        return  instructorServices.addInstructorAndAssignToCourse(instructor,numCourse);
    }
    @Operation(description = "Retrieve all Instructors")
    @GetMapping(value = "/all")
    public List<Instructor> getAllInstructors(){
        return instructorServices.retrieveAllInstructors();
    }

    @Operation(description = "Update Instructor ")
    @PutMapping(value = "/update")
    public Instructor updateInstructor(@RequestBody Instructor instructor){
        return  instructorServices.updateInstructor(instructor);
    }

    @Operation(description = "Retrieve Instructor by Id")
    @GetMapping(value = "/get/{id-instructor}")
    public Instructor getById(@PathVariable("id-instructor") Long numInstructor){
        return instructorServices.retrieveInstructor(numInstructor);
    }

}
