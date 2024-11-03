package tn.esprit.spring.entities;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.*;

import lombok.*;
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class Subscription implements Serializable {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long numSub;
	private LocalDate startDate;
	private LocalDate endDate;
	private Float price;
	//@Enumerated(EnumType.STRING)
	private TypeSubscription typeSub;

}
