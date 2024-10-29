package tn.esprit.spring.entities;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
@Data
public class CourseDTO {
    @NotNull(message = "Title cannot be null")
    private String title;
    @NotNull(message = "Level cannot be null")
    @Positive(message = "Level must be a positive integer")
    private int level;
    @NotNull(message = "Type of course cannot be null")
    private TypeCourse typeCourse;
    @NotNull(message = "cannot be null")
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
