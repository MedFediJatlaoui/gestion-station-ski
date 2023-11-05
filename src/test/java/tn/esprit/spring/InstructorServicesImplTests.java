package tn.esprit.spring;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import tn.esprit.spring.entities.Course;
import tn.esprit.spring.entities.Instructor;
import tn.esprit.spring.entities.Support;
import tn.esprit.spring.entities.TypeCourse;
import tn.esprit.spring.repositories.ICourseRepository;
import tn.esprit.spring.repositories.IInstructorRepository;
import tn.esprit.spring.services.InstructorServicesImpl;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
public class InstructorServicesImplTests {

    @InjectMocks
    private InstructorServicesImpl instructorServices;

    @Mock
    private IInstructorRepository instructorRepository;

    @Mock
    private ICourseRepository courseRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testAddInstructor() {
        Instructor instructor = new Instructor(1L, "John", "Doe", LocalDate.now(), Collections.emptySet());

        Mockito.when(instructorRepository.save(instructor)).thenReturn(instructor);

        Instructor savedInstructor = instructorServices.addInstructor(instructor);

        assertNotNull(savedInstructor);
        assertEquals(1L, savedInstructor.getNumInstructor());
        assertEquals("John", savedInstructor.getFirstName());
        assertEquals("Doe", savedInstructor.getLastName());
    }

    @Test
    public void testRetrieveAllInstructors() {
        Instructor instructor1 = new Instructor(1L, "John", "Doe", LocalDate.now(), Collections.emptySet());
        Instructor instructor2 = new Instructor(2L, "Alice", "Smith", LocalDate.now(), Collections.emptySet());

        Mockito.when(instructorRepository.findAll()).thenReturn(Arrays.asList(instructor1, instructor2));

        List<Instructor> instructors = instructorServices.retrieveAllInstructors();

        assertNotNull(instructors);
        assertEquals(2, instructors.size());
    }

    @Test
    public void testUpdateInstructor() {
        Instructor instructor = new Instructor(1L, "John", "Doe", LocalDate.now(), Collections.emptySet());

        Mockito.when(instructorRepository.save(instructor)).thenReturn(instructor);

        Instructor updatedInstructor = instructorServices.updateInstructor(instructor);

        assertNotNull(updatedInstructor);
        assertEquals(1L, updatedInstructor.getNumInstructor());
        assertEquals("John", updatedInstructor.getFirstName());
        assertEquals("Doe", updatedInstructor.getLastName());
    }

    @Test
    public void testRetrieveInstructor() {
        Instructor instructor = new Instructor(1L, "John", "Doe", LocalDate.now(), Collections.emptySet());

        Mockito.when(instructorRepository.findById(1L)).thenReturn(Optional.of(instructor));

        Instructor retrievedInstructor = instructorServices.retrieveInstructor(1L);

        assertNotNull(retrievedInstructor);
        assertEquals(1L, retrievedInstructor.getNumInstructor());
        assertEquals("John", retrievedInstructor.getFirstName());
        assertEquals("Doe", retrievedInstructor.getLastName());
    }

    @Test
    public void testAddInstructorAndAssignToCourse() {
        Instructor instructor = new Instructor(1L, "John", "Doe", LocalDate.now(), Collections.emptySet());
        Course course = new Course(1L, 0, TypeCourse.COLLECTIVE_CHILDREN, Support.SNOWBOARD, 0.0f, 0,
                Collections.emptySet());

        Mockito.when(courseRepository.findById(1L)).thenReturn(Optional.of(course));
        Mockito.when(instructorRepository.save(instructor)).thenReturn(instructor);

        Instructor assignedInstructor = instructorServices.addInstructorAndAssignToCourse(instructor, 1L);

        assertNotNull(assignedInstructor);
        assertEquals(1, assignedInstructor.getCourses().size());
    }

}
