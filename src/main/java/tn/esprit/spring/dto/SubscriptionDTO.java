package tn.esprit.spring.dto;

import lombok.*;
import tn.esprit.spring.entities.TypeSubscription;

import java.time.LocalDate;
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class SubscriptionDTO {
    private Long numSub;
    private LocalDate startDate;
    private LocalDate endDate;
    private Float price;
    private TypeSubscription typeSub;
}
