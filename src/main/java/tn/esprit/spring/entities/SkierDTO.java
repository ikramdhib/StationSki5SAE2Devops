package tn.esprit.spring.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SkierDTO {
    String firstName;
    String lastName;
    LocalDate dateOfBirth;
    String city;
}
