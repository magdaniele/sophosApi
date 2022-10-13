package sophosCollege.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import sophosCollege.College;
import sophosCollege.services.CollegeService;

@RestController
@RequestMapping("/")
public class collegeController {

	@PostMapping
	public ResponseEntity<String> createCollege(@RequestBody College college) {
			return ResponseEntity.ok(CollegeService.addNewCollege(college));
		
	}
}
