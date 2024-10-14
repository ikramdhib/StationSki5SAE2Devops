package tn.esprit.spring.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.spring.entities.InstructorDTO;
import tn.esprit.spring.services.IInstructorServices;

import java.util.List;

@Tag(name = "\uD83D\uDC69\u200D\uD83C\uDFEB Instructor Management")
@RestController
@RequestMapping("/instructor")
@RequiredArgsConstructor
public class InstructorRestController {

    private final IInstructorServices instructorServices;

    @Operation(description = "Add Instructor")
    @PostMapping("/add")
    public InstructorDTO addInstructor(@RequestBody InstructorDTO instructor){
        return  instructorServices.addInstructor(instructor);
    }
    @Operation(description = "Add Instructor and Assign To Course")
    @PutMapping("/addAndAssignToCourse/{numCourse}")
    public InstructorDTO addAndAssignToInstructor(@RequestBody InstructorDTO instructor, @PathVariable("numCourse")Long numCourse){
        return  instructorServices.addInstructorAndAssignToCourse(instructor,numCourse);
    }
    @Operation(description = "Retrieve all Instructors")
    @GetMapping("/all")
    public List<InstructorDTO> getAllInstructors(){
        return instructorServices.retrieveAllInstructors();
    }

    @Operation(description = "Update Instructor ")
    @PutMapping("/update")
    public InstructorDTO updateInstructor(@RequestBody InstructorDTO instructorTDO){
        return  instructorServices.updateInstructor(instructorTDO);
    }

    @Operation(description = "Retrieve Instructor by Id")
    @GetMapping("/get/{id-instructor}")
    public InstructorDTO getById(@PathVariable("id-instructor") Long numInstructor){
        return instructorServices.retrieveInstructor(numInstructor);
    }

}
