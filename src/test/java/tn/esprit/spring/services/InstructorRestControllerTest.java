package tn.esprit.spring.services;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import tn.esprit.spring.controllers.InstructorRestController;
import tn.esprit.spring.dto.InstructorDTO;
import tn.esprit.spring.entities.Instructor;
import tn.esprit.spring.entities.InstructorMapper;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class InstructorRestControllerTest {

    @Mock
    private IInstructorServices instructorServices;

    @Mock
    private InstructorMapper instructorMapper;

    @InjectMocks
    private InstructorRestController instructorRestController;

    private Instructor instructor;
    private InstructorDTO instructorDTO;

    @BeforeEach
    void setUp() {
        instructor = new Instructor();
        instructor.setNumInstructor(1L);
        instructor.setFirstName("John");
        instructor.setLastName("Doe");

        instructorDTO = new InstructorDTO();
        instructorDTO.setNumInstructor(1L);
        instructorDTO.setFirstName("John");
        instructorDTO.setLastName("Doe");
    }

    @Test
    void testAddInstructor() {
        when(instructorMapper.toEntity(any(InstructorDTO.class))).thenReturn(instructor);
        when(instructorServices.addInstructor(any(Instructor.class))).thenReturn(instructor);
        when(instructorMapper.toDTO(any(Instructor.class))).thenReturn(instructorDTO);

        ResponseEntity<InstructorDTO> response = instructorRestController.addInstructor(instructorDTO);

        assertNotNull(response, "La réponse ne doit pas être null");
        assertEquals(HttpStatus.CREATED, response.getStatusCode(), "Le statut HTTP doit être CREATED");
        assertEquals(instructorDTO, response.getBody(), "Le DTO retourné ne correspond pas au DTO attendu");

        verify(instructorServices, times(1)).addInstructor(any(Instructor.class));
    }

    @Test
    void testAddAndAssignToInstructor() {
        when(instructorMapper.toEntity(any(InstructorDTO.class))).thenReturn(instructor);
        when(instructorServices.addInstructorAndAssignToCourse(any(Instructor.class), anyLong())).thenReturn(instructor);
        when(instructorMapper.toDTO(any(Instructor.class))).thenReturn(instructorDTO);

        ResponseEntity<InstructorDTO> response = instructorRestController.addAndAssignToInstructor(instructorDTO, 1L);

        assertNotNull(response, "La réponse ne doit pas être null");
        assertEquals(HttpStatus.CREATED, response.getStatusCode(), "Le statut HTTP doit être CREATED");
        assertEquals(instructorDTO, response.getBody(), "Le DTO retourné ne correspond pas au DTO attendu");

        verify(instructorServices, times(1)).addInstructorAndAssignToCourse(any(Instructor.class), anyLong());
    }

    @Test
    void testGetAllInstructors() {
        List<Instructor> instructors = new ArrayList<>();
        instructors.add(instructor);

        when(instructorServices.retrieveAllInstructors()).thenReturn(instructors);

        List<Instructor> response = instructorRestController.getAllInstructors();

        assertNotNull(response, "La réponse ne doit pas être null");
        assertEquals(1, response.size(), "La liste des instructeurs doit contenir 1 élément");
        assertEquals(instructor, response.get(0), "L'instructeur retourné doit correspondre à celui attendu");

        verify(instructorServices, times(1)).retrieveAllInstructors();
    }

    @Test
    void testUpdateInstructor() {
        when(instructorMapper.toEntity(any(InstructorDTO.class))).thenReturn(instructor);
        when(instructorServices.updateInstructor(any(Instructor.class))).thenReturn(instructor);
        when(instructorMapper.toDTO(any(Instructor.class))).thenReturn(instructorDTO);

        ResponseEntity<InstructorDTO> response = instructorRestController.updateInstructor(instructorDTO);

        assertNotNull(response, "La réponse ne doit pas être null");
        assertEquals(HttpStatus.OK, response.getStatusCode(), "Le statut HTTP doit être OK");
        assertEquals(instructorDTO, response.getBody(), "Le DTO retourné ne correspond pas au DTO attendu");

        verify(instructorServices, times(1)).updateInstructor(any(Instructor.class));
    }

    @Test
    void testGetById() {
        when(instructorServices.retrieveInstructor(1L)).thenReturn(instructor);

        Instructor result = instructorRestController.getById(1L);

        assertNotNull(result, "L'instructeur retourné ne doit pas être null");
        assertEquals(instructor, result, "L'instructeur retourné doit correspondre à celui attendu");

        verify(instructorServices, times(1)).retrieveInstructor(1L);
    }
}
