package tn.esprit.spring.entities;

import org.springframework.stereotype.Component;
import tn.esprit.spring.dto.SubscriptionDTO;
@Component
public class SubscriptionMapper {
    public SubscriptionDTO toDTO(Subscription subscription) {
        return new SubscriptionDTO(
                subscription.getNumSub(),
                subscription.getStartDate(),
                subscription.getEndDate(),
                subscription.getPrice(),
                subscription.getTypeSub()
        );
    }

    public Subscription toEntity(SubscriptionDTO subscriptionDTO) {
        return Subscription.builder()
                .numSub(subscriptionDTO.getNumSub())
                .startDate(subscriptionDTO.getStartDate())
                .endDate(subscriptionDTO.getEndDate())
                .price(subscriptionDTO.getPrice())
                .typeSub(subscriptionDTO.getTypeSub())
                .build();
    }
}
