package tn.esprit.spring.entities;

import java.io.Serializable;
import java.util.Set;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import lombok.experimental.FieldDefaults;
@Getter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level= AccessLevel.PRIVATE)
@Entity
@Builder
public class Course implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long numCourse;
	@NotNull(message = "Title cannot be null")
	private String title;
	@NotNull(message = "Level cannot be null")
	@Positive(message = "Level must be a positive integer")
	private int level;
	@NotNull(message = "Type of course cannot be null")
	@Enumerated(EnumType.STRING)
	TypeCourse typeCourse;
	@NotNull(message = "cannot be null")
	private String description;
	@NotNull(message = "Support cannot be null")
	@Enumerated(EnumType.STRING)
	Support support;
	@NotNull(message = "Price cannot be null")
	@Positive(message = "Price must be a positive number")
	private Float price;
	@Positive(message = "Time slot must be a positive integer")
	private int timeSlot;
	@JsonIgnore
	@OneToMany(mappedBy = "course")
	private Set<Registration> registrations;
}
