package tn.esprit.spring.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import tn.esprit.spring.entities.SkierDTO;
import tn.esprit.spring.entities.SubscriptionDTO;
import tn.esprit.spring.entities.TypeSubscription;

import java.util.List;

public interface ISkierRepository extends JpaRepository<SkierDTO, Long> {
   List<SkierDTO> findBySubscription_TypeSub(TypeSubscription typeSubscription);
   SkierDTO findBySubscription(SubscriptionDTO subscription);


}
