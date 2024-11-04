package tn.esprit.spring.tdo;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import tn.esprit.spring.entities.Piste;
import tn.esprit.spring.entities.Registration;
import tn.esprit.spring.entities.Skier;
import tn.esprit.spring.entities.Subscription;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;

@Getter
@Setter
public class SkierDTO  {

    private Long numSkier;
    private String firstName;
    private String lastName;
    private LocalDate dateOfBirth;
    private String city;

    private Subscription subscription;

    private Set<Piste> pistes;


    private Set<Registration> registrations;




}
