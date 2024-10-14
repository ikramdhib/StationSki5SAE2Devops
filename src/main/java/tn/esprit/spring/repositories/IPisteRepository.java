package tn.esprit.spring.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import tn.esprit.spring.entities.PisteDTO;

public interface IPisteRepository extends JpaRepository<PisteDTO, Long> {

}
