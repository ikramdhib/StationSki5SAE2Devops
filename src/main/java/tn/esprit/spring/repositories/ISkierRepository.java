package tn.esprit.spring.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import tn.esprit.spring.entities.SkierTDO;
import tn.esprit.spring.entities.SubscriptionTDO;
import tn.esprit.spring.entities.TypeSubscription;

import java.util.List;

public interface ISkierRepository extends JpaRepository<SkierTDO, Long> {
   List<SkierTDO> findBySubscription_TypeSub(TypeSubscription typeSubscription);
   SkierTDO findBySubscription(SubscriptionTDO subscription);


}
