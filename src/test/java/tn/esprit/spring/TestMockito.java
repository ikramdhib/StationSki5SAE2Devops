package tn.esprit.spring;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import tn.esprit.spring.entities.Course;
import tn.esprit.spring.entities.Instructor;
import tn.esprit.spring.repositories.ICourseRepository;
import tn.esprit.spring.repositories.IInstructorRepository;
import tn.esprit.spring.services.InstructorServicesImpl;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

class InstructorServicesImplTest {

    @Mock
    private IInstructorRepository instructorRepository;

    @Mock
    private ICourseRepository courseRepository;

    @InjectMocks
    private InstructorServicesImpl instructorService;

    private Instructor instructor;
    private Course course;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        instructor = new Instructor(1L, "John", "Doe", LocalDate.of(2020, 1, 1), new HashSet<>());
        course = new Course();  // Assuming the Course class exists and is imported
        course.setNumCourse(1L); // Set course ID or properties as needed
    }

    @Test
    void testAddInstructor() {
        when(instructorRepository.save(instructor)).thenReturn(instructor);
        Instructor savedInstructor = instructorService.addInstructor(instructor);
        assertNotNull(savedInstructor);
        assertEquals("John", savedInstructor.getFirstName());
        verify(instructorRepository, times(1)).save(instructor);
    }

    @Test
    void testRetrieveAllInstructors() {
        when(instructorRepository.findAll()).thenReturn(Arrays.asList(instructor));
        List<Instructor> instructors = instructorService.retrieveAllInstructors();
        assertEquals(1, instructors.size());
        assertEquals("John", instructors.get(0).getFirstName());
        verify(instructorRepository, times(1)).findAll();
    }

    @Test
    void testUpdateInstructor() {
        when(instructorRepository.save(instructor)).thenReturn(instructor);
        Instructor updatedInstructor = instructorService.updateInstructor(instructor);
        assertEquals("John", updatedInstructor.getFirstName());
        verify(instructorRepository, times(1)).save(instructor);
    }

    @Test
    void testRetrieveInstructor() {
        when(instructorRepository.findById(1L)).thenReturn(Optional.of(instructor));
        Instructor foundInstructor = instructorService.retrieveInstructor(1L);
        assertNotNull(foundInstructor);
        assertEquals(1L, foundInstructor.getNumInstructor());
        verify(instructorRepository, times(1)).findById(1L);
    }

    @Test
    void testAddInstructorAndAssignToCourse() {
        when(courseRepository.findById(1L)).thenReturn(Optional.of(course));
        when(instructorRepository.save(instructor)).thenReturn(instructor);

        Instructor savedInstructor = instructorService.addInstructorAndAssignToCourse(instructor, 1L);
        assertNotNull(savedInstructor);
        Set<Course> courses = savedInstructor.getCourses();
        assertEquals(1, courses.size());
        assertTrue(courses.contains(course));

        verify(courseRepository, times(1)).findById(1L);
        verify(instructorRepository, times(1)).save(instructor);
    }
}