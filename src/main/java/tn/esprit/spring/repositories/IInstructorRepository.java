package tn.esprit.spring.repositories;
import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.spring.entities.InstructorDTO;


public interface IInstructorRepository extends JpaRepository<InstructorDTO, Long> {

}
