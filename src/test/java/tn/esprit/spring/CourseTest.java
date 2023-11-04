package tn.esprit.spring;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import tn.esprit.spring.entities.Course;
import tn.esprit.spring.repositories.ICourseRepository;
import tn.esprit.spring.services.CourseServicesImpl;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CourseTest {

    @InjectMocks
    private CourseServicesImpl courseServices;

    @Mock
    private ICourseRepository courseRepository;

    @BeforeEach
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testRetrieveAllCourses() {
        // Arrange
        List<Course> courses = Arrays.asList(new Course(), new Course());
        when(courseRepository.findAll()).thenReturn(courses);

        // Act
        List<Course> result = courseServices.retrieveAllCourses();

        // Assert
        assertEquals(2, result.size());
    }

    @Test
    public void testAddCourse() {
        // Arrange
        Course course = new Course();
        when(courseRepository.save(course)).thenReturn(course);

        // Act
        Course result = courseServices.addCourse(course);

        // Assert
        assertEquals(course, result);
    }

    @Test
    public void testUpdateCourse() {
        // Arrange
        Course course = new Course();
        when(courseRepository.save(course)).thenReturn(course);

        // Act
        Course result = courseServices.updateCourse(course);

        // Assert
        assertEquals(course, result);
    }

    @Test
    public void testRetrieveCourse() {
        // Arrange
        Long numCourse = 1L;
        Course course = new Course();
        when(courseRepository.findById(numCourse)).thenReturn(Optional.of(course));

        // Act
        Course result = courseServices.retrieveCourse(numCourse);

        // Assert
        assertEquals(course, result);
    }
}
