package tn.esprit.spring.entities;

import org.springframework.stereotype.Component;
import tn.esprit.spring.dto.InstructorDTO;

@Component
public class InstructorMapper {

    public InstructorDTO toDTO(Instructor instructor) {
        return new InstructorDTO(
                instructor.getNumInstructor(),
                instructor.getFirstName(),
                instructor.getLastName(),
                instructor.getDateOfHire(),
                instructor.getCourses()
        );
    }

    public Instructor toEntity(InstructorDTO instructorDTO) {
        return Instructor.builder()
                .numInstructor(instructorDTO.getNumInstructor())
                .firstName(instructorDTO.getFirstName())
                .lastName(instructorDTO.getLastName())
                .dateOfHire(instructorDTO.getDateOfHire())
                .courses(instructorDTO.getCourses())
                .build();
    }
}
