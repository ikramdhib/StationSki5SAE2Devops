package tn.esprit.spring.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.spring.entities.SkierDTO;
import tn.esprit.spring.entities.TypeSubscription;
import tn.esprit.spring.services.ISkierServices;

import java.util.List;

@Tag(name = "\uD83C\uDFC2 Skier Management")
@RestController
@RequestMapping("/skier")
@RequiredArgsConstructor
public class SkierRestController {

    private final ISkierServices skierServices;

    @Operation(description = "Add Skier")
    @PostMapping("/add")
    public SkierDTO addSkier(@RequestBody SkierDTO skier){
        return  skierServices.addSkier(skier);
    }

    @Operation(description = "Add Skier And Assign To Course")
    @PostMapping("/addAndAssign/{numCourse}")
    public SkierDTO addSkierAndAssignToCourse(@RequestBody SkierDTO skier,
                                           @PathVariable("numCourse") Long numCourse){
        return  skierServices.addSkierAndAssignToCourse(skier,numCourse);
    }
    @Operation(description = "Assign Skier To Subscription")
    @PutMapping("/assignToSub/{numSkier}/{numSub}")
    public SkierDTO assignToSubscription(@PathVariable("numSkier")Long numSkier,
                               @PathVariable("numSub") Long numSub){
        return skierServices.assignSkierToSubscription(numSkier, numSub);
    }

    @Operation(description = "Assign Skier To Piste")
    @PutMapping("/assignToPiste/{numSkier}/{numPiste}")
    public SkierDTO assignToPiste(@PathVariable("numSkier")Long numSkier,
                               @PathVariable("numPiste") Long numPiste){
        return skierServices.assignSkierToPiste(numSkier,numPiste);
    }
    @Operation(description = "retrieve Skiers By Subscription Type")
    @GetMapping("/getSkiersBySubscription")
    public List<SkierDTO> retrieveSkiersBySubscriptionType(TypeSubscription typeSubscription) {
        return skierServices.retrieveSkiersBySubscriptionType(typeSubscription);
    }
    @Operation(description = "Retrieve Skier by Id")
    @GetMapping("/get/{id-skier}")
    public SkierDTO getById(@PathVariable("id-skier") Long numSkier){
        return skierServices.retrieveSkier(numSkier);
    }

    @Operation(description = "Delete Skier by Id")
    @DeleteMapping("/delete/{id-skier}")
    public void deleteById(@PathVariable("id-skier") Long numSkier){
        skierServices.removeSkier(numSkier);
    }

    @Operation(description = "Retrieve all Skiers")
    @GetMapping("/all")
    public List<SkierDTO> getAllSkiers(){
        return skierServices.retrieveAllSkiers();
    }

}
