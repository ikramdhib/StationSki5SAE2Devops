package tn.esprit.spring.tdo;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import tn.esprit.spring.entities.Instructor;

@Getter
@Setter
@FieldDefaults(level=AccessLevel.PRIVATE)
public class InstructorDTO extends Instructor {

}