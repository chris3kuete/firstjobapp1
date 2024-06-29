package com.embarkx.firstjobapp1.job.impl;

import java.util.ArrayList;
import java.util.Optional;
import java.util.Iterator;
import java.util.List;

//import org.apache.el.stream.Optional;
import org.springframework.stereotype.Service;

import com.embarkx.firstjobapp1.job.Job;
import com.embarkx.firstjobapp1.job.JobRepository;
import com.embarkx.firstjobapp1.job.JobService;

@Service
public class JobServiceImpl implements JobService {
	
	//private List<Job> jobs = new ArrayList<>();
	JobRepository jobRepository;
	
	
	public JobServiceImpl(JobRepository jobRepository) {
		super();
		this.jobRepository = jobRepository;
	}

	//private Long nextId = 1L;

	@Override
	public List<Job> findAll() {
		
		return jobRepository.findAll();
	}

	@Override
	public void createJob(Job job) {
		//job.setId(nextId++);
		jobRepository.save(job);
		
	}

	@Override
	public Job getJobById(Long id) {
		return jobRepository.findById(id).orElse(null);
	}

	@Override
	public boolean deleteJob(Long id) {
		try{
			jobRepository.deleteById(id);
			return true;	
		}catch(Exception e) {
			return false;
		}
		
		
	}

	@Override
	public boolean updateJob(Long id, Job updatedJob) {
		
		Optional<Job> jobOptional = jobRepository.findById(id);	
		if(jobOptional.isPresent()) {
			Job job = jobOptional.get();
			job.setTitle(updatedJob.getTitle());
			job.setDescription(updatedJob.getDescription());
			job.setMinSalary(updatedJob.getMinSalary());
			job.setMaxSalary(updatedJob.getMaxSalary());
			job.setLocation(updatedJob.getLocation());
			jobRepository.save(job);
				
			return true;
			}

		return false;
	}

}
