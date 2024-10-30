package tn.esprit.spring.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.spring.entities.Subscription;
import tn.esprit.spring.entities.TypeSubscription;
import tn.esprit.spring.services.ISubscriptionServices;
import tn.esprit.spring.tdo.SubscriptionDTO;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Tag(name = "\uD83D\uDC65 Subscription Management")
@RestController
@RequestMapping("/subscription")
@RequiredArgsConstructor
public class SubscriptionRestController {

    private final ISubscriptionServices subscriptionServices;

    @Operation(description = "Add Subscription ")
    @PostMapping(value = "/add",produces = "text/plain")
    public Subscription addSubscription(@RequestBody SubscriptionDTO subscriptionTDO){
        return  subscriptionServices.addSubscription(subscriptionTDO);
    }
    @Operation(description = "Retrieve Subscription by Id")
    @GetMapping(value = "/get/{id-subscription}",produces = "text/plain")
    public Subscription getById(@PathVariable("id-subscription") Long numSubscription){
        return subscriptionServices.retrieveSubscriptionById(numSubscription);
    }
    
    @Operation(description = "Retrieve Subscriptions by Type")
    @GetMapping(value = "/all/{typeSub}",produces = "text/plain")
    public Set<Subscription> getSubscriptionsByType(@PathVariable("typeSub") TypeSubscription typeSubscription){
        return subscriptionServices.getSubscriptionByType(typeSubscription);
    }
    @Operation(description = "Update Subscription ")
    @PutMapping(value = "/update",produces = "text/plain")
    public Subscription updateSubscription(@RequestBody SubscriptionDTO subscriptionTDO){
        return  subscriptionServices.updateSubscription(subscriptionTDO);
    }
    @Operation(description = "Retrieve Subscriptions created between two dates")
    @GetMapping(value = "/all/{date1}/{date2}",produces = "text/plain")
    public List<Subscription> getSubscriptionsByDates(@PathVariable("date1") LocalDate startDate,
                                                      @PathVariable("date2") LocalDate endDate){
        return subscriptionServices.retrieveSubscriptionsByDates(startDate, endDate);
    }

}
