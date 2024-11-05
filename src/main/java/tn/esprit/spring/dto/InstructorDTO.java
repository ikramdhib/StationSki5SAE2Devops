package tn.esprit.spring.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;
import tn.esprit.spring.entities.Course;
import tn.esprit.spring.entities.Instructor;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.time.LocalDate;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class InstructorDTO  {
    private Long numInstructor;
    private String firstName;
    private String lastName;
    private LocalDate dateOfHire;
    private Set<Course> courses;
}