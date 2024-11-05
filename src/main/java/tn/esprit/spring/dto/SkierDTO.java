package tn.esprit.spring.dto;


import lombok.Getter;
import lombok.Setter;
import tn.esprit.spring.entities.Piste;
import tn.esprit.spring.entities.Registration;
import tn.esprit.spring.entities.Subscription;

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
