package tn.esprit.spring.tdo;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import tn.esprit.spring.entities.Piste;

@Getter
@Setter
@FieldDefaults(level=AccessLevel.PRIVATE)
public class PisteDTO extends Piste {


}