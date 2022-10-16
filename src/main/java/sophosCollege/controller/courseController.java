package sophosCollege.controller;

import java.util.ArrayList;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import sophosCollege.classes.Course;
import sophosCollege.services.collegeService;
import sophosCollege.services.courseService;

@RestController
public class courseController {

	@RequestMapping(value="/{nit}/course/", method = RequestMethod.POST)	
	public ResponseEntity<String> createCourse(@RequestBody Course course, @PathVariable long nit) {
			return ResponseEntity.ok(courseService.handleCourse(course, nit));
	}
	
	@RequestMapping(value="/{nit}/course/", method = RequestMethod.PATCH)	
	public ResponseEntity<String> updateCourse(@RequestBody Course course, @PathVariable long nit) {
			return ResponseEntity.ok(courseService.handleCourse(course, nit));
	}
	
	@RequestMapping(value="/{nit}/course/all", method = RequestMethod.GET)
	public ResponseEntity<ArrayList<Course>> getCourses(@PathVariable long nit){
		return ResponseEntity.ok(courseService.getAllCourses(nit));
	}
	
	@RequestMapping(value="/{nit}/course/availablecourses", method = RequestMethod.GET)
	public ResponseEntity<ArrayList<Course>> getCoursesWithSpace(@PathVariable long nit){
		return ResponseEntity.ok(courseService.findCourseBySpace(nit));
	}
	
	@RequestMapping(value="/{nit}/course/id", method = RequestMethod.GET)
	public ResponseEntity<Course> getCourse(@PathVariable long nit, @RequestParam int id){
	Course course = courseService.findCourse(nit, id);
	course.setStudents(collegeService.findStudentsByCourse(nit, course.getId()));
		return ResponseEntity.ok(course);
	}
	
	@RequestMapping(value="/{nit}/course/name", method = RequestMethod.GET)
	public ResponseEntity<Course> getCourseByName(@PathVariable long nit, @RequestParam String name){
	Course course = courseService.findCourseByName(nit, name);
	course.setStudents(collegeService.findStudentsByCourse(nit, course.getId()));
		return ResponseEntity.ok(course);
	}
	@RequestMapping(value="/{nit}/course/", method = RequestMethod.DELETE)
	public ResponseEntity<String> deleteCourse(@PathVariable long nit, @RequestParam int id){
		return ResponseEntity.ok(courseService.deleteCourse(nit,id));
	}

}
