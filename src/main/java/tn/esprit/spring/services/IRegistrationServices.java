package tn.esprit.spring.services;

import tn.esprit.spring.entities.*;

import java.util.List;

public interface IRegistrationServices {

	Registration assignRegistrationToCourse(Long numRegistration, Long numCourse);
	List<Integer> numWeeksCourseOfInstructorBySupport(Long numInstructor, Support support);
}

