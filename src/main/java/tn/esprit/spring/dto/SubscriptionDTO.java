package tn.esprit.spring.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import tn.esprit.spring.entities.TypeSubscription;

import java.time.LocalDate;

@Getter
@Setter
@ToString
public class SubscriptionDTO {
    private Long numSub;
    private LocalDate startDate;
    private LocalDate endDate;
    private Float price;
    private TypeSubscription typeSub;
}
