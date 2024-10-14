package tn.esprit.spring.tdo;


import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.FieldDefaults;
import tn.esprit.spring.entities.Registration;

@Getter
@Setter
@ToString
@FieldDefaults(level=AccessLevel.PRIVATE)
public class RegistrationDTO extends Registration {


}
