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
    @PostMapping(value = "/add",produces = "text/plain")
    public Instructor addInstructor(@RequestBody InstructorDTO instructorDTO){
        return  instructorServices.addInstructor(instructorDTO);
    }
    @Operation(description = "Add Instructor and Assign To Course")
    @PutMapping(value = "/addAndAssignToCourse/{numCourse}",produces = "text/plain")
    public Instructor addAndAssignToInstructor(@RequestBody InstructorDTO instructor, @PathVariable("numCourse")Long numCourse){
        return  instructorServices.addInstructorAndAssignToCourse(instructor,numCourse);
    }
    @Operation(description = "Retrieve all Instructors")
    @GetMapping(value = "/all",produces = "text/plain")
    public List<Instructor> getAllInstructors(){
        return instructorServices.retrieveAllInstructors();
    }

    @Operation(description = "Update Instructor ")
    @PutMapping(value = "/update",produces = "text/plain")
    public Instructor updateInstructor(@RequestBody InstructorDTO instructorTDO){
        return  instructorServices.updateInstructor(instructorTDO);
    }

    @Operation(description = "Retrieve Instructor by Id")
    @GetMapping(value = "/get/{id-instructor}",produces = "text/plain")
    public Instructor getById(@PathVariable("id-instructor") Long numInstructor){
        return instructorServices.retrieveInstructor(numInstructor);
    }

}
