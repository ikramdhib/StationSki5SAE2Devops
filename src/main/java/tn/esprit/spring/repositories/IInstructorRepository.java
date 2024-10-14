package tn.esprit.spring.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.spring.entities.InstructorTDO;


public interface IInstructorRepository extends JpaRepository<InstructorTDO, Long> {

}
