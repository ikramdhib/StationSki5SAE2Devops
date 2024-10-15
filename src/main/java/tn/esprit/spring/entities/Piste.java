package tn.esprit.spring.entities;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.*;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level=AccessLevel.PRIVATE)
@Entity
@Builder
public class Piste implements Serializable {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long numPiste;
	private String namePiste;
	@Enumerated(EnumType.STRING)
	private Color color;
	private int length;
	private int slope;

	@ManyToMany(mappedBy= "pistes")
	private Set<Skier> skiers;
	
}
