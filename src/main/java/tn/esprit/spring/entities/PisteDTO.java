package tn.esprit.spring.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PisteDTO {
    Long numPiste;
    String namePiste;
    Color color;
    int length;
    int slope;
}
