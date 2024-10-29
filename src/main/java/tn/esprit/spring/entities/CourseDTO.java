package tn.esprit.spring.entities;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

@Data
@Builder
public class CourseDTO {

    @NotNull(message = "Title cannot be null")
    @Size(min = 1, max = 100, message = "Title must be between 1 and 100 characters")
    private String title;

    @NotNull(message = "Level cannot be null")
    @Positive(message = "Level must be a positive integer")
    private int level;

    @NotNull(message = "Type of course cannot be null")
    private TypeCourse typeCourse;

    @Size(max = 255, message = "Description cannot be longer than 255 characters")
    private String description;

    @NotNull(message = "Support cannot be null")
    private Support support;

    @NotNull(message = "Price cannot be null")
    @Positive(message = "Price must be a positive number")
    private Float price;

    @NotNull(message = "Time slot cannot be null")
    @Positive(message = "Time slot must be a positive integer")
    private int timeSlot;
}
