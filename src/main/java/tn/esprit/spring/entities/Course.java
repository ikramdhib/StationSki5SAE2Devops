package tn.esprit.spring.entities;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import lombok.experimental.FieldDefaults;
@Getter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level=AccessLevel.PRIVATE)
@Entity
@Builder
public class Course implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long numCourse;

	@NotNull(message = "Title cannot be null")
	@Size(min = 1, max = 100, message = "Title must be between 1 and 100 characters")
	private String title;

	@Positive(message = "Level must be a positive integer")
	private int level;

	@NotNull(message = "Type of course cannot be null")
	@Enumerated(EnumType.STRING)
	TypeCourse typeCourse;

	@Size(max = 255, message = "Description cannot be longer than 255 characters")
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
