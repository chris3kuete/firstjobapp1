package com.embarkx.firstjobapp1.job;


import java.util.List;

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
public class JobController {
	
	
	private JobService jobService;
	
	//this initializes jobService
	public JobController(JobService jobService) {
		super();
		this.jobService = jobService;
	}

	//Create endpoint to return all the jobs
	@GetMapping("/jobs")
	//public List<Job> findAll(){
	public ResponseEntity<List<Job>> findAll(){
		//return jobService.findAll();
		return ResponseEntity.ok(jobService.findAll());
	}
	
	@PostMapping("/jobs")
	//public String createJob(@RequestBody Job job) {
	public ResponseEntity<String> createJob(@RequestBody Job job){
		jobService.createJob(job);
		//return "job added successfully";
		return new ResponseEntity<>("job added successfully", HttpStatus.OK);
	}
	
	@GetMapping("/jobs/{id}")
	//public Job getJobById(@PathVariable Long id) {
	public ResponseEntity<Job> getJobById(@PathVariable Long id){
		Job job = jobService.getJobById(id);
		if(job != null) {
			//return job;
			return new ResponseEntity<>(job, HttpStatus.OK);
		}
		//return new Job(1L, "TestJob", "TestJob", "2000", "5000", "loc");
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);		
		
	}
	
	@DeleteMapping("/jobs/{id}")
	public ResponseEntity<String> deleteJob(@PathVariable Long id){
		boolean deleted = jobService.deleteJob(id);
		if (deleted) {
			return new ResponseEntity<>("Job deleted successfully",HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	@PutMapping("/jobs/{id}")
	public ResponseEntity<String> updateJob(@PathVariable Long id, @RequestBody Job updatedJob){
		boolean updated = jobService.updateJob(id, updatedJob);
		if(updated) {
			return new ResponseEntity<>("Job updated well", HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		
	}
	
	
}
