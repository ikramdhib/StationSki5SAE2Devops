package tn.esprit.spring.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import tn.esprit.spring.entities.Course;
import tn.esprit.spring.entities.TypeCourse;
import tn.esprit.spring.repositories.ICourseRepository;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
class CourseServicesImplMockTest {
    @InjectMocks
    private CourseServicesImpl courseServices;
    @Mock
    private ICourseRepository courseRepository;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void retrieveAllCourses() {
        Course course1 = new Course();
        Course course2 = new Course();
        when(courseRepository.findAll()).thenReturn(Arrays.asList(course1,course2));
        // Act
        List<Course> courses = courseServices.retrieveAllCourses();

        // Assert
        assertEquals(2, courses.size());
        verify(courseRepository, times(1)).findAll();
    }

    @Test
    void addCourse() {
        Course course = new Course();
        when(courseRepository.save(course)).thenReturn(course);
        Course savedCourse = courseServices.addCourse(course);
        assertNotNull(savedCourse);
        verify(courseRepository, times(1)).save(course);
    }

    @Test
    void updateCourse() {
        Course course = new Course();
        when(courseRepository.save(course)).thenReturn(course);
        Course updatedCourse = courseServices.updateCourse(course);
        assertNotNull(updatedCourse);
        verify(courseRepository, times(1)).save(course);
    }

    @Test
    void retrieveCourse() {
        Long courseId = 1L;
        Course course = new Course();
        when(courseRepository.findById(courseId)).thenReturn(Optional.of(course));
        Course retrievedCourse = courseServices.retrieveCourse(courseId);
        assertNotNull(retrievedCourse);
        verify(courseRepository, times(1)).findById(courseId);
    }


    @Test
    void searchCourses() {
        // Arrange
        Integer level = 2;
        TypeCourse typeCourse = TypeCourse.COLLECTIVE_CHILDREN;
        Float minPrice = 100.0f;
        Float maxPrice = 500.0f;

        // Utilisation du Builder pour créer des cours
        List<Course> mockCourses = Arrays.asList(
                Course.builder()
                        .level(level)
                        .typeCourse(typeCourse)
                        .price(200.0f)
                        .build(),
                Course.builder()
                        .level(level)
                        .typeCourse(typeCourse)
                        .price(300.0f)
                        .build()
        );

        when(courseRepository.findAllByCriteria(level, typeCourse, minPrice, maxPrice))
                .thenReturn(mockCourses);

        // Act
        List<Course> courses = courseServices.searchCourses(level, typeCourse, minPrice, maxPrice);

        // Assert
        assertEquals(2, courses.size());
        assertTrue(courses.stream().allMatch(course -> course.getPrice() >= minPrice && course.getPrice() <= maxPrice));
        verify(courseRepository, times(1)).findAllByCriteria(level, typeCourse, minPrice, maxPrice);

    }

    @Test
    void recommendCourses() {
        // Arrange
        TypeCourse typeCourse = TypeCourse.COLLECTIVE_CHILDREN;

        Course course1 = Course.builder()
                .title("Cours A")
                .typeCourse(typeCourse)
                .build();

        Course course2 = Course.builder()
                .title("Cours B")
                .typeCourse(typeCourse)
                .build();
        when(courseRepository.findByTypeCourse(typeCourse)).thenReturn(Arrays.asList(course1, course2));

        // Act
        List<Course> recommendedCourses = courseServices.recommendCourses(typeCourse);

        // Assert
        assertEquals(2, recommendedCourses.size(), "Il devrait y avoir 2 cours recommandés");
        assertEquals("Cours A", recommendedCourses.get(0).getTitle());
        assertEquals("Cours B", recommendedCourses.get(1).getTitle());
        verify(courseRepository, times(1)).findByTypeCourse(typeCourse);
    }
}