package sophosCollege.controller;

import java.util.ArrayList;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import sophosCollege.classes.Teacher;
import sophosCollege.services.teacherService;

@RestController
public class teacherController {

	@RequestMapping(value="/{nit}/teacher/", method = RequestMethod.POST)
	public ResponseEntity<String> createTeacher(@RequestBody Teacher teacher, @PathVariable int nit) {
			return ResponseEntity.ok(teacherService.handleTeacher(teacher, nit));	
	}
	
	@RequestMapping(value="/{nit}/teacher/", method = RequestMethod.PATCH)
	public ResponseEntity<String> updateTeacher(@RequestBody Teacher teacher, @PathVariable int nit) {
			return ResponseEntity.ok(teacherService.handleTeacher(teacher, nit));	
	}
	
	@RequestMapping(value="/{nit}/teacher/all", method = RequestMethod.GET)
	public ResponseEntity<ArrayList<Teacher>> getTeachers(@PathVariable int nit){
		return ResponseEntity.ok(teacherService.getAllTeachers(nit));
	}
	
	@RequestMapping(value="/{nit}/teacher/cc", method = RequestMethod.GET)
	public ResponseEntity<Teacher> getTeacher(@PathVariable int nit, @RequestParam int cc){
		return ResponseEntity.ok(teacherService.findTeacher(nit,cc));
	}
	
	@RequestMapping(value="/{nit}/teacher/name", method = RequestMethod.GET)
	public ResponseEntity<Teacher> getTeacherByName(@PathVariable int nit, @RequestParam String name){
		return ResponseEntity.ok(teacherService.findTeacherByName(nit,name));
	}
	
	@RequestMapping(value="/{nit}/teacher/", method = RequestMethod.DELETE)
	public ResponseEntity<String> deleteTeacher(@PathVariable int nit, @RequestParam int cc){
		return ResponseEntity.ok(teacherService.deleteTeacher(nit,cc));
	}
}
