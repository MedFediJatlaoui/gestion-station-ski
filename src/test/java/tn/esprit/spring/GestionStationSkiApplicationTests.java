package tn.esprit.spring;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import tn.esprit.spring.services.*;

//@SpringBootTest
@SpringBootTest(classes = {CourseServicesImpl.class, InstructorServicesImpl.class , PisteServicesImpl.class, RegistrationServicesImpl.class, SkierServicesImpl.class, SubscriptionServicesImpl.class }, properties = "spring.autoconfigure.exclude=org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration")
class GestionStationSkiApplicationTests {

	@Test
	void contextLoads() {
	}

}
