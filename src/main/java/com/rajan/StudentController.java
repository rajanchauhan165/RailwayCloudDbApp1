package com.rajan;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StudentController {

	private StudentRepo studentRepo;
	
	@Autowired
	public StudentController(StudentRepo studentRepo) {
		this.studentRepo = studentRepo;
	}
	
	@PostMapping("/test1")
	public String postRequest() {
		return "Returning from Post Request";
	}
	
	@PutMapping("/test2")
	public String putRequest() {
		return "Returning from Put Request";
	}
	
	@PostMapping("/addstudent")
	public ResponseEntity<Student> saveStudentHandler(@RequestBody Student student){
		Student savedStudent= studentRepo.save(student);
		return new ResponseEntity<Student>(savedStudent,HttpStatus.OK);
	}
	
	@GetMapping("/findstudent/{roll}")
	public ResponseEntity<Student> getStudentByRoll(@PathVariable Integer roll){
		Optional<Student> studentOptional = studentRepo.findById(roll);
		Student student = studentOptional.get();
		return new ResponseEntity<Student>(student,HttpStatus.OK);
	}
	
	@GetMapping("/allstudents")
	public ResponseEntity<List<Student>> getAllStudent(){
		List<Student> list= studentRepo.findAll();
		return new ResponseEntity<List<Student>>(list,HttpStatus.OK);
		
		
	}
}
