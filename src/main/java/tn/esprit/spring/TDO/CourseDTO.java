package tn.esprit.spring.TDO;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import tn.esprit.spring.entities.Course;
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CourseDTO extends Course {
}
