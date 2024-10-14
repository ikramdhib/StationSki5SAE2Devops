package tn.esprit.spring.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import tn.esprit.spring.entities.PisteTDO;

public interface IPisteRepository extends JpaRepository<PisteTDO, Long> {

}
