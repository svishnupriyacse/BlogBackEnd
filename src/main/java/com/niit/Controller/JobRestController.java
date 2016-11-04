package com.niit.Controller;

import java.util.List;

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

import com.niit.DAO.JobsDAO;
import com.niit.Model.Jobs;
import com.niit.Model.User;

@RestController
public class JobRestController {
	@Autowired
	JobsDAO jobs;

	public JobsDAO getJobs() {
		return jobs;
	}

	public void setJobs(JobsDAO jobs) {
		this.jobs = jobs;
	}
	
	@GetMapping("/joblist")
	public List getjobs() {
		return jobs.list();
	}
	@PutMapping("/joblist/{id}")
	public ResponseEntity updatejob(@PathVariable Long id, @RequestBody Jobs job) {

	job = jobs.saveOrUpdate(job);;

		if (null == job) {
			return new ResponseEntity("No Jobs found for ID " + id, HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity(job, HttpStatus.OK);
	}
	@DeleteMapping("/joblist/{id}")
	public ResponseEntity deletejob(@PathVariable Long id) {
		Jobs job=jobs.get(id);
 		if (job==null) {
			return new ResponseEntity("No Customer found for ID " + id, HttpStatus.NOT_FOUND);
		}
 		jobs.delete(id);
		return new ResponseEntity(id, HttpStatus.OK);

	}
	@PostMapping(value = "/joblist")
	public ResponseEntity createjob(@RequestBody Jobs job) {

		jobs.saveOrUpdate(job);

		return new ResponseEntity(job, HttpStatus.OK);
	}

}
