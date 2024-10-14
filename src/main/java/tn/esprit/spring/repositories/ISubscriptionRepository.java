package tn.esprit.spring.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import org.springframework.data.repository.query.Param;
import tn.esprit.spring.entities.SubscriptionDTO;
import tn.esprit.spring.entities.TypeSubscription;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

public interface ISubscriptionRepository extends JpaRepository<SubscriptionDTO, Long> {

    @Query("select s from SubscriptionDTO s where s.typeSub = :typeS order by s.startDate")
    Set<SubscriptionDTO> findByTypeSubOrderByStartDateAsc(@Param("typeS") TypeSubscription typeSub);

    List<SubscriptionDTO> getSubscriptionsByStartDateBetween(LocalDate date1, LocalDate date2);

    @Query("select distinct s from SubscriptionDTO s where s.endDate <= CURRENT_TIME order by s.endDate")
    List<SubscriptionDTO> findDistinctOrderByEndDateAsc();


    @Query("select (sum(s.price))/(count(s)) from SubscriptionTDO s where s.typeSub = ?1")
    Float recurringRevenueByTypeSubEquals(TypeSubscription typeSub);


}
