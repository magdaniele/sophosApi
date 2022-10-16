package sophosCollege.controller;

import java.util.ArrayList;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import sophosCollege.classes.Student;
import sophosCollege.services.courseService;
import sophosCollege.services.studentService;

@RestController
public class studentController {

	@RequestMapping(value="/{nit}/student/", method = RequestMethod.POST)
	public ResponseEntity<String> createStudent(@RequestBody Student student, @PathVariable int nit) {
			return ResponseEntity.ok(studentService.handleStudent(student, nit));
	}
	
	@RequestMapping(value="/{nit}/student/", method = RequestMethod.PATCH)
	public ResponseEntity<String> updateStudent(@RequestBody Student student, @PathVariable int nit) {
			return ResponseEntity.ok(studentService.handleStudent(student, nit));
	}

	@RequestMapping(value="/{nit}/student/all", method = RequestMethod.GET)
	public ResponseEntity<ArrayList<Student>> getStudents(@PathVariable int nit){
		return ResponseEntity.ok(studentService.getAllStudents(nit));
	}
	
	@RequestMapping(value="/{nit}/student/cc", method = RequestMethod.GET)
	public ResponseEntity<Student> getStudent(@PathVariable int nit, @RequestParam int cc){
		Student student = studentService.findStudent(nit,cc);
		student.setCoursesGiven(courseService.findCourseByStatus(nit, cc, "ENRROLLED"));
		student.setCurrentCourses(courseService.findCourseByStatus(nit, cc, "APPROVED"));
		return ResponseEntity.ok(student);
	}

	@RequestMapping(value="/{nit}/student/name", method = RequestMethod.GET)
	public ResponseEntity<Student> getStudentByName(@PathVariable int nit, @RequestParam String name){
		Student student = studentService.findStudentByName(nit,name);
		student.setCoursesGiven(courseService.findCourseByStatus(nit, student.getCc(), "ENRROLLED"));
		student.setCurrentCourses(courseService.findCourseByStatus(nit, student.getCc(), "APPROVED"));
		return ResponseEntity.ok(student);
	}
	
	@RequestMapping(value="/{nit}/student/faculty", method = RequestMethod.GET)
	public ResponseEntity<ArrayList<Student>> getStudentByFaculty(@PathVariable int nit, @RequestParam String faculty){
		return ResponseEntity.ok(studentService.findStudentsByFaculty(nit,faculty));
	}
	
	@RequestMapping(value="/{nit}/student/", method = RequestMethod.DELETE)
	public ResponseEntity<String> deleteStudent(@PathVariable int nit, @RequestParam int cc){
		return ResponseEntity.ok(studentService.deleteStudent(nit,cc));
	}
}
