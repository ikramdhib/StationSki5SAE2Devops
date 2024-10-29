package tn.esprit.spring.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.spring.entities.Instructor;
import tn.esprit.spring.entities.InstructorDTO;
import tn.esprit.spring.services.IInstructorServices;

import java.util.List;

@Tag(name = "\uD83D\uDC69\u200D\uD83C\uDFEB Instructor Management")
@RestController
@RequestMapping("/instructor")
@RequiredArgsConstructor
public class InstructorRestController {

    private static final String ADD_INSTRUCTOR_URL = "/add";
    private static final String ADD_ASSIGN_INSTRUCTOR_URL = "/addAndAssignToCourse/{numCourse}";
    private static final String GET_ALL_INSTRUCTORS_URL = "/all";
    private static final String UPDATE_INSTRUCTOR_URL = "/update";
    private static final String GET_INSTRUCTOR_BY_ID_URL = "/get/{id-instructor}";

    private final IInstructorServices instructorServices;

    // Méthode pour convertir InstructorDTO en Instructor
    private Instructor convertToInstructor(InstructorDTO instructorDTO) {
        return Instructor.builder()
                .firstName(instructorDTO.getFirstName())
                .lastName(instructorDTO.getLastName())
                .dateOfHire(instructorDTO.getDateOfHire())
                .build();
    }

    @Operation(description = "Add Instructor")
    @PostMapping(ADD_INSTRUCTOR_URL)
    public ResponseEntity<Instructor> addInstructor(@RequestBody InstructorDTO instructorDTO) {
        Instructor instructor = convertToInstructor(instructorDTO);
        Instructor addedInstructor = instructorServices.addInstructor(instructor);
        return new ResponseEntity<>(addedInstructor, HttpStatus.CREATED);
    }

    @Operation(description = "Add Instructor and Assign To Course")
    @PutMapping(ADD_ASSIGN_INSTRUCTOR_URL)
    public ResponseEntity<Instructor> addAndAssignToInstructor(@RequestBody InstructorDTO instructorDTO,
                                                               @PathVariable("numCourse") Long numCourse) {
        Instructor instructor = convertToInstructor(instructorDTO);
        Instructor addedInstructor = instructorServices.addInstructorAndAssignToCourse(instructor, numCourse);
        return new ResponseEntity<>(addedInstructor, HttpStatus.OK);
    }

    @Operation(description = "Retrieve all Instructors")
    @GetMapping(GET_ALL_INSTRUCTORS_URL)
    public ResponseEntity<List<Instructor>> getAllInstructors() {
        List<Instructor> instructors = instructorServices.retrieveAllInstructors();
        return new ResponseEntity<>(instructors, HttpStatus.OK);
    }

    @Operation(description = "Update Instructor")
    @PutMapping(UPDATE_INSTRUCTOR_URL)
    public ResponseEntity<Instructor> updateInstructor(@RequestBody InstructorDTO instructorDTO) {
        Instructor instructor = convertToInstructor(instructorDTO);
        Instructor updatedInstructor = instructorServices.updateInstructor(instructor);
        return new ResponseEntity<>(updatedInstructor, HttpStatus.OK);
    }

    @Operation(description = "Retrieve Instructor by Id")
    @GetMapping(GET_INSTRUCTOR_BY_ID_URL)
    public ResponseEntity<Instructor> getById(@PathVariable("id-instructor") Long numInstructor) {
        Instructor instructor = instructorServices.retrieveInstructor(numInstructor);
        if (instructor == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(instructor, HttpStatus.OK);
    }
}
