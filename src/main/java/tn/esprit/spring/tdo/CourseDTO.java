package tn.esprit.spring.tdo;


import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import tn.esprit.spring.entities.Course;

@Getter
@Setter
@FieldDefaults(level=AccessLevel.PRIVATE)

public class CourseDTO extends Course {



}
