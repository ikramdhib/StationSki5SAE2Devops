package tn.esprit.spring.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CourseDTO {


    private String title;


    private int level;


    private TypeCourse typeCourse;

    private String description;


    private Support support;

    private Float price;

    private int timeSlot;
}
