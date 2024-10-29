package tn.esprit.spring.entities;
import lombok.Data;

@Data
public class CourseDTO {
    private String title;

    private int level;
    private TypeCourse typeCourse;
    private String description;
    private Support support;
    private Float price;
    private int timeSlot;
}
