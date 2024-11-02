package tn.esprit.spring.tdo;


import lombok.*;
import lombok.experimental.FieldDefaults;
import tn.esprit.spring.entities.Registration;

@Getter
@Setter
@ToString
@Builder
@FieldDefaults(level=AccessLevel.PRIVATE)
public class RegistrationDTO extends Registration {

    int numWeek;

}