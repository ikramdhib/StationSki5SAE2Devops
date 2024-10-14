package tn.esprit.spring.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.spring.entities.SubscriptionDTO;
import tn.esprit.spring.entities.TypeSubscription;
import tn.esprit.spring.services.ISubscriptionServices;

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
    @PostMapping("/add")
    public SubscriptionDTO addSubscription(@RequestBody SubscriptionDTO subscriptionTDO){
        return  subscriptionServices.addSubscription(subscriptionTDO);
    }
    @Operation(description = "Retrieve Subscription by Id")
    @GetMapping("/get/{id-subscription}")
    public SubscriptionDTO getById(@PathVariable("id-subscription") Long numSubscription){
        return subscriptionServices.retrieveSubscriptionById(numSubscription);
    }
    
    @Operation(description = "Retrieve Subscriptions by Type")
    @GetMapping("/all/{typeSub}")
    public Set<SubscriptionDTO> getSubscriptionsByType(@PathVariable("typeSub")TypeSubscription typeSubscription){
        return subscriptionServices.getSubscriptionByType(typeSubscription);
    }
    @Operation(description = "Update Subscription ")
    @PutMapping("/update")
    public SubscriptionDTO updateSubscription(@RequestBody SubscriptionDTO subscriptionTDO){
        return  subscriptionServices.updateSubscription(subscriptionTDO);
    }
    @Operation(description = "Retrieve Subscriptions created between two dates")
    @GetMapping("/all/{date1}/{date2}")
    public List<SubscriptionDTO> getSubscriptionsByDates(@PathVariable("date1") LocalDate startDate,
                                                      @PathVariable("date2") LocalDate endDate){
        return subscriptionServices.retrieveSubscriptionsByDates(startDate, endDate);
    }

}
