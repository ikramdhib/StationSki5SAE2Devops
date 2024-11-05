package tn.esprit.spring.services;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import tn.esprit.spring.dto.InstructorDTO;
import tn.esprit.spring.entities.Course;
import tn.esprit.spring.entities.Instructor;
import tn.esprit.spring.entities.InstructorMapper;
import tn.esprit.spring.repositories.ICourseRepository;
import tn.esprit.spring.repositories.IInstructorRepository;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class InstructorServicesImplTest {
    @Mock
    private IInstructorRepository instructorRepository;

    @Mock
    private ICourseRepository courseRepository;

    @InjectMocks
    private InstructorServicesImpl instructorService;
    @InjectMocks
    private final InstructorMapper instructorMapper = new InstructorMapper();

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }
    @Test
    void testToDTO() {
        // Given
        Instructor instructor = new Instructor();
        instructor.setNumInstructor(1L);
        instructor.setFirstName("John");
        instructor.setLastName("Doe");
        instructor.setDateOfHire(LocalDate.of(2020, 1, 1));
        instructor.setCourses(new HashSet<>()); // Ajoutez des cours si nécessaire

        // When
        InstructorDTO instructorDTO = instructorMapper.toDTO(instructor);

        // Then
        assertEquals(instructor.getNumInstructor(), instructorDTO.getNumInstructor());
        assertEquals(instructor.getFirstName(), instructorDTO.getFirstName());
        assertEquals(instructor.getLastName(), instructorDTO.getLastName());
        assertEquals(instructor.getDateOfHire(), instructorDTO.getDateOfHire());
        assertEquals(instructor.getCourses(), instructorDTO.getCourses());
    }

    @Test
    void testToEntity() {
        // Given
        InstructorDTO instructorDTO = new InstructorDTO();
        instructorDTO.setNumInstructor(1L);
        instructorDTO.setFirstName("John");
        instructorDTO.setLastName("Doe");
        instructorDTO.setDateOfHire(LocalDate.of(2020, 1, 1));
        instructorDTO.setCourses(new HashSet<>()); // Ajoutez des cours si nécessaire

        // When
        Instructor instructor = instructorMapper.toEntity(instructorDTO);

        // Then
        assertEquals(instructorDTO.getNumInstructor(), instructor.getNumInstructor());
        assertEquals(instructorDTO.getFirstName(), instructor.getFirstName());
        assertEquals(instructorDTO.getLastName(), instructor.getLastName());
        assertEquals(instructorDTO.getDateOfHire(), instructor.getDateOfHire());
        assertEquals(instructorDTO.getCourses(), instructor.getCourses());
    }

    @Test
    void testAddInstructor() {
        Instructor instructorDTO = new Instructor();
        Instructor instructor = new Instructor();

        when(instructorRepository.save(any(Instructor.class))).thenReturn(instructor);

        Instructor result = instructorService.addInstructor(instructorDTO);

        assertNotNull(result);
        verify(instructorRepository, times(1)).save(any(Instructor.class));
    }

    @Test
    void testRetrieveAllInstructors() {
        Instructor instructor1 = new Instructor();
        Instructor instructor2 = new Instructor();
        List<Instructor> instructors = Arrays.asList(instructor1, instructor2);

        when(instructorRepository.findAll()).thenReturn(instructors);

        List<Instructor> result = instructorService.retrieveAllInstructors();

        assertEquals(2, result.size());
        verify(instructorRepository, times(1)).findAll();
    }

    @Test
    void testUpdateInstructor() {
        Instructor instructorDTO = new Instructor();
        Instructor instructor = new Instructor();

        when(instructorRepository.save(any(Instructor.class))).thenReturn(instructor);

        Instructor result = instructorService.updateInstructor(instructorDTO);

        assertNotNull(result);
        verify(instructorRepository, times(1)).save(any(Instructor.class));
    }

    @Test
    void testRetrieveInstructor() {
        Instructor instructor = new Instructor();
        when(instructorRepository.findById(1L)).thenReturn(Optional.of(instructor));

        Instructor result = instructorService.retrieveInstructor(1L);

        assertNotNull(result);
        assertEquals(instructor, result);
        verify(instructorRepository, times(1)).findById(1L);
    }

    @Test
    void testAddInstructorAndAssignToCourse() {
        Instructor instructorDTO = new Instructor();
        Instructor instructor = new Instructor();
        Course course = new Course();
        Set<Course> courseSet = new HashSet<>();
        courseSet.add(course);
        instructor.setCourses(courseSet);

        when(courseRepository.findById(1L)).thenReturn(Optional.of(course));
        when(instructorRepository.save(any(Instructor.class))).thenReturn(instructor);

        Instructor result = instructorService.addInstructorAndAssignToCourse(instructorDTO, 1L);

        assertNotNull(result);
        assertEquals(1, result.getCourses().size());
        verify(courseRepository, times(1)).findById(1L);
        verify(instructorRepository, times(1)).save(any(Instructor.class));
    }

}