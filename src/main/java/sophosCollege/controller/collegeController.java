package sophosCollege.controller;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import sophosCollege.classes.College;
import sophosCollege.services.collegeService;

@RestController
public class collegeController {

	@RequestMapping(value="/", method = RequestMethod.POST)
	public ResponseEntity<String> createCollege(@RequestBody College college) {
			return ResponseEntity.ok(collegeService.addNewCollege(college));	
	}
	
	@RequestMapping(value="/{nit}/enrollCourse", method = RequestMethod.POST)
	public ResponseEntity<String> enrollStudentInACourse(@PathVariable int nit, @RequestParam int ccStudent, @RequestParam int idCourse) {
			return ResponseEntity.ok(collegeService.enrollStudent(nit,ccStudent,idCourse));	
	}


}
