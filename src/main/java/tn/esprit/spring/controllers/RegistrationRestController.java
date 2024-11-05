package tn.esprit.spring.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.spring.entities.Registration;
import tn.esprit.spring.entities.Support;
import tn.esprit.spring.services.IRegistrationServices;
import tn.esprit.spring.dto.RegistrationDTO;

import java.util.List;

@Tag(name = "\uD83D\uDDD3️Registration Management")
@RestController
@RequestMapping("/registration")
@RequiredArgsConstructor
public class RegistrationRestController {

    private static final String ADD_AND_ASSIGN_TO_SKIER_URL = "/addAndAssignToSkier/{numSkieur}";
    private static final String ASSIGN_TO_COURSE_URL = "/assignToCourse/{numRegis}/{numCourse}";
    private static final String ADD_AND_ASSIGN_TO_SKIER_AND_COURSE_URL = "/addAndAssignToSkierAndCourse/{numSkieur}/{numCourse}";
    private static final String NUM_WEEKS_COURSE_INSTRUCTOR_URL = "/numWeeks/{numInstructor}/{support}";

    private final IRegistrationServices registrationServices;

    // Méthode privée pour convertir RegistrationDTO en Registration
    private Registration convertToRegistration(RegistrationDTO registrationDTO) {
        return Registration.builder()
                .numWeek(registrationDTO.getNumWeek())
                .build();
    }

    @Operation(description = "Add Registration and Assign to Skier")
    @PutMapping(ADD_AND_ASSIGN_TO_SKIER_URL)
    public ResponseEntity<Registration> addAndAssignToSkier(@RequestBody RegistrationDTO registrationDTO,
                                                            @PathVariable("numSkieur") Long numSkieur) {
        Registration registration = convertToRegistration(registrationDTO);
        Registration result = registrationServices.addRegistrationAndAssignToSkier(registration, numSkieur);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @Operation(description = "Assign Registration to Course")
    @PutMapping(ASSIGN_TO_COURSE_URL)
    public ResponseEntity<Registration> assignToCourse(@PathVariable("numRegis") Long numRegistration,
                                                       @PathVariable("numCourse") Long numCourse) {
        Registration result = registrationServices.assignRegistrationToCourse(numRegistration, numCourse);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @Operation(description = "Add Registration and Assign to Skier and Course")
    @PutMapping(ADD_AND_ASSIGN_TO_SKIER_AND_COURSE_URL)
    public ResponseEntity<Registration> addAndAssignToSkierAndCourse(@RequestBody RegistrationDTO registrationDTO,
                                                                     @PathVariable("numSkieur") Long numSkieur,
                                                                     @PathVariable("numCourse") Long numCourse) {
        Registration registration = convertToRegistration(registrationDTO);
        Registration result = registrationServices.addRegistrationAndAssignToSkierAndCourse(registration, numSkieur, numCourse);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @Operation(description = "Numbers of the weeks when an instructor has given lessons in a given support")
    @GetMapping(NUM_WEEKS_COURSE_INSTRUCTOR_URL)
    public ResponseEntity<List<Integer>> numWeeksCourseOfInstructorBySupport(@PathVariable("numInstructor") Long numInstructor,
                                                                             @PathVariable("support") Support support) {
        List<Integer> weeks = registrationServices.numWeeksCourseOfInstructorBySupport(numInstructor, support);
        return new ResponseEntity<>(weeks, HttpStatus.OK);
    }
}
