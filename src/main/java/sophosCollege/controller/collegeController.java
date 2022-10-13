package sophosCollege.controller;

import java.util.ArrayList;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import sophosCollege.College;
import sophosCollege.Course;
import sophosCollege.Student;
import sophosCollege.Teacher;
import sophosCollege.services.CollegeService;

@RestController
public class collegeController {

	@RequestMapping(value="/", method = RequestMethod.POST)
	public ResponseEntity<String> createCollege(@RequestBody College college) {
			return ResponseEntity.ok(CollegeService.addNewCollege(college));	
	}
	
	@RequestMapping(value="/{nit}/course/", method = RequestMethod.POST)	
	public ResponseEntity<String> createCourse(@RequestBody Course course, @PathVariable int nit) {
			return ResponseEntity.ok(CollegeService.addNewCourse(course, nit));
	}

	@RequestMapping(value="/{nit}/teacher/", method = RequestMethod.POST)
	public ResponseEntity<String> createTeacher(@RequestBody Teacher teacher, @PathVariable int nit) {
			return ResponseEntity.ok(CollegeService.addNewTeacher(teacher, nit));	
	}

	@RequestMapping(value="/{nit}/student/", method = RequestMethod.POST)
	public ResponseEntity<String> createStudent(@RequestBody Student student, @PathVariable int nit) {
			return ResponseEntity.ok(CollegeService.addNewStudent(student, nit));
	}
	

	@RequestMapping(value="/{nit}/course/getAll", method = RequestMethod.GET)
	public ResponseEntity<ArrayList<Course>> getCourses(@PathVariable int nit){
		return ResponseEntity.ok(CollegeService.getAllCourses(nit));
	}

	@RequestMapping(value="/{nit}/teacher/getAll", method = RequestMethod.GET)
	public ResponseEntity<ArrayList<Teacher>> getTeachers(@PathVariable int nit){
		return ResponseEntity.ok(CollegeService.getAllTeachers(nit));
	}

	@RequestMapping(value="/{nit}/student/getAll", method = RequestMethod.GET)
	public ResponseEntity<ArrayList<Student>> getStudents(@PathVariable int nit){
		return ResponseEntity.ok(CollegeService.getAllStudents(nit));
	}

	@RequestMapping(value="/{nit}/course/{id}", method = RequestMethod.GET)
	public ResponseEntity<Course> getCourse(@PathVariable int nit, @RequestParam int id){
		return ResponseEntity.ok(CollegeService.findCourse(nit, id));
	}

	@RequestMapping(value="/{nit}/teacher/{cc}", method = RequestMethod.GET)
	public ResponseEntity<Teacher> getTeacher(@PathVariable int nit, @RequestParam int cc){
		return ResponseEntity.ok(CollegeService.findTeacher(nit,cc));
	}

	@RequestMapping(value="/{nit}/student/{cc}", method = RequestMethod.GET)
	public ResponseEntity<Student> getStudent(@PathVariable int nit, @RequestParam int cc){
		return ResponseEntity.ok(CollegeService.findStudent(nit,cc));
	}

	@RequestMapping(value="/{nit}/course/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<String> deleteCourse(@PathVariable int nit, @RequestParam int id){
		return ResponseEntity.ok(CollegeService.deleteCourse(nit,id));
	}

	@RequestMapping(value="/{nit}/teacher/{cc}", method = RequestMethod.DELETE)
	public ResponseEntity<String> deleteTeacher(@PathVariable int nit, @RequestParam int cc){
		return ResponseEntity.ok(CollegeService.deleteTeacher(nit,cc));
	}

	@RequestMapping(value="/{nit}/student/", method = RequestMethod.DELETE)
	public ResponseEntity<String> deleteStudent(@PathVariable int nit, @RequestParam int cc){
		return ResponseEntity.ok(CollegeService.deleteStudent(nit,cc));
	}
}
