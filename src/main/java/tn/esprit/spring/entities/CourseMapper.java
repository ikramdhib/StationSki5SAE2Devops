package tn.esprit.spring.entities;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import tn.esprit.spring.tdo.CourseDTO;

public class CourseMapper {
    public CourseDTO toDTO(Course course) {
        return new CourseDTO(
                course.getNumCourse(),
                course.getLevel(),
                course.getTypeCourse(),
                course.getSupport(),
                course.getPrice(),
                course.getTimeSlot(),
                course.getDescription(),
                course.getLocation()
        );
    }

    public Course toEntity(CourseDTO courseDTO) {
        return Course.builder()
                .numCourse(courseDTO.getNumCourse())
                .level(courseDTO.getLevel())
                .typeCourse(courseDTO.getTypeCourse())
                .support(courseDTO.getSupport())
                .price(courseDTO.getPrice())
                .timeSlot(courseDTO.getTimeSlot())
                .description(courseDTO.getDescription())
                .location(courseDTO.getLocation())
                .build();
    }

}
