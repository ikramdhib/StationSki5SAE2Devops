package tn.esprit.spring.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.spring.dto.InstructorDTO;
import tn.esprit.spring.entities.Instructor;
import tn.esprit.spring.entities.InstructorMapper;
import tn.esprit.spring.services.IInstructorServices;

import java.util.List;

@Tag(name = "\uD83D\uDC69\u200D\uD83C\uDFEB Instructor Management")
@RestController
@RequestMapping("/instructor")
@RequiredArgsConstructor
public class InstructorRestController {

    private final IInstructorServices instructorServices;
    private final InstructorMapper instructorMapper;

    @Operation(description = "Add Instructor")
    @PostMapping(value = "/add")
    public ResponseEntity<InstructorDTO> addInstructor( @RequestBody InstructorDTO instructorDTO) {
        // Convertir le DTO en entité
        Instructor instructor = instructorMapper.toEntity(instructorDTO);

        // Appeler le service pour ajouter l'instructeur
        Instructor savedInstructor = instructorServices.addInstructor(instructor);

        // Convertir l'instructeur sauvegardé en DTO et retourner la réponse
        InstructorDTO savedInstructorDTO = instructorMapper.toDTO(savedInstructor);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedInstructorDTO);
    }

    @Operation(description = "Add Instructor and Assign To Course")
    @PutMapping(value = "/addAndAssignToCourse/{numCourse}")
    public ResponseEntity<InstructorDTO> addAndAssignToInstructor(@RequestBody InstructorDTO instructorDTO, @PathVariable("numCourse") Long numCourse) {

        Instructor instructor = instructorMapper.toEntity(instructorDTO);
        Instructor savedInstructor = instructorServices.addInstructorAndAssignToCourse(instructor, numCourse);
        InstructorDTO savedInstructorDTO = instructorMapper.toDTO(savedInstructor);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedInstructorDTO);
    }

    @Operation(description = "Retrieve all Instructors")
    @GetMapping(value = "/all")
    public List<Instructor> getAllInstructors(){
        return instructorServices.retrieveAllInstructors();
    }

    @Operation(description = "Update Instructor")
    @PutMapping(value = "/update")
    public ResponseEntity<InstructorDTO> updateInstructor( @RequestBody InstructorDTO instructorDTO) {
        Instructor instructor = instructorMapper.toEntity(instructorDTO);
        Instructor updatedInstructor = instructorServices.updateInstructor(instructor);
        InstructorDTO updatedInstructorDTO = instructorMapper.toDTO(updatedInstructor);
        return ResponseEntity.ok(updatedInstructorDTO);
    }


    @Operation(description = "Retrieve Instructor by Id")
    @GetMapping(value = "/get/{id-instructor}")
    public Instructor getById(@PathVariable("id-instructor") Long numInstructor){
        return instructorServices.retrieveInstructor(numInstructor);
    }
}
