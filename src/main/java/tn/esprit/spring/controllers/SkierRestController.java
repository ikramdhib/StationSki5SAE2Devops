package tn.esprit.spring.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.spring.dto.SkierDTO;
import tn.esprit.spring.entities.Skier;
import tn.esprit.spring.entities.TypeSubscription;
import tn.esprit.spring.services.ISkierServices;

import java.util.List;

@Tag(name = "\uD83C\uDFC2 Skier Management")
@RestController
@RequestMapping("/skier")
@RequiredArgsConstructor
public class SkierRestController {

    private static final String ADD_SKIER_URL = "/add";
    private static final String ADD_ASSIGN_SKIER_TO_COURSE_URL = "/addAndAssign/{numCourse}";
    private static final String ASSIGN_TO_SUBSCRIPTION_URL = "/assignToSub/{numSkier}/{numSub}";
    private static final String ASSIGN_TO_PISTE_URL = "/assignToPiste/{numSkier}/{numPiste}";
    private static final String GET_SKIERS_BY_SUBSCRIPTION_URL = "/getSkiersBySubscription";
    private static final String GET_SKIER_BY_ID_URL = "/get/{id-skier}";
    private static final String DELETE_SKIER_URL = "/delete/{id-skier}";
    private static final String GET_ALL_SKIERS_URL = "/all";

    private final ISkierServices skierServices;

    // Méthode privée pour convertir SkierDTO en Skier
    private Skier convertToSkier(SkierDTO skierDTO) {
        return Skier.builder()
                .firstName(skierDTO.getFirstName())
                .lastName(skierDTO.getLastName())
                .dateOfBirth(skierDTO.getDateOfBirth())
                .city(skierDTO.getCity())
                .build();
    }

    @Operation(description = "Add Skier")
    @PostMapping(ADD_SKIER_URL)
    public ResponseEntity<Skier> addSkier(@RequestBody SkierDTO skierDTO) {
        Skier skier = convertToSkier(skierDTO);
        Skier addedSkier = skierServices.addSkier(skier);
        return new ResponseEntity<>(addedSkier, HttpStatus.CREATED);
    }

    @Operation(description = "Add Skier And Assign To Course")
    @PostMapping(ADD_ASSIGN_SKIER_TO_COURSE_URL)
    public ResponseEntity<Skier> addSkierAndAssignToCourse(@RequestBody SkierDTO skierDTO,
                                                           @PathVariable("numCourse") Long numCourse) {
        Skier skier = convertToSkier(skierDTO);
        Skier addedSkier = skierServices.addSkierAndAssignToCourse(skier, numCourse);
        return new ResponseEntity<>(addedSkier, HttpStatus.OK);
    }

    @Operation(description = "Assign Skier To Subscription")
    @PutMapping(ASSIGN_TO_SUBSCRIPTION_URL)
    public ResponseEntity<Skier> assignToSubscription(@PathVariable("numSkier") Long numSkier,
                                                      @PathVariable("numSub") Long numSub) {
        Skier updatedSkier = skierServices.assignSkierToSubscription(numSkier, numSub);
        return new ResponseEntity<>(updatedSkier, HttpStatus.OK);
    }

    @Operation(description = "Assign Skier To Piste")
    @PutMapping(ASSIGN_TO_PISTE_URL)
    public ResponseEntity<Skier> assignToPiste(@PathVariable("numSkier") Long numSkier,
                                               @PathVariable("numPiste") Long numPiste) {
        Skier updatedSkier = skierServices.assignSkierToPiste(numSkier, numPiste);
        return new ResponseEntity<>(updatedSkier, HttpStatus.OK);
    }

    @Operation(description = "Retrieve Skiers By Subscription Type")
    @GetMapping(GET_SKIERS_BY_SUBSCRIPTION_URL)
    public ResponseEntity<List<Skier>> retrieveSkiersBySubscriptionType(TypeSubscription typeSubscription) {
        List<Skier> skiers = skierServices.retrieveSkiersBySubscriptionType(typeSubscription);
        return new ResponseEntity<>(skiers, HttpStatus.OK);
    }

    @Operation(description = "Retrieve Skier by Id")
    @GetMapping(GET_SKIER_BY_ID_URL)
    public ResponseEntity<Skier> getById(@PathVariable("id-skier") Long numSkier) {
        Skier skier = skierServices.retrieveSkier(numSkier);
        if (skier == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(skier, HttpStatus.OK);
    }

    @Operation(description = "Delete Skier by Id")
    @DeleteMapping(DELETE_SKIER_URL)
    public ResponseEntity<Void> deleteById(@PathVariable("id-skier") Long numSkier) {
        skierServices.removeSkier(numSkier);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Operation(description = "Retrieve all Skiers")
    @GetMapping(GET_ALL_SKIERS_URL)
    public ResponseEntity<List<Skier>> getAllSkiers() {
        List<Skier> skiers = skierServices.retrieveAllSkiers();
        return new ResponseEntity<>(skiers, HttpStatus.OK);
    }
}
