package com.dvlcube.app.rest;

import static com.dvlcube.app.manager.data.e.Menu.CONFIGURATION;

import java.util.List;

//import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.dvlcube.app.interfaces.MenuItem;
import com.dvlcube.app.jpa.repo.JobRepository;
import com.dvlcube.app.manager.data.JobBean;

@RestController
@MenuItem(value = CONFIGURATION)
@RequestMapping("${dvl.rest.prefix}/jobs")
public class JobService {
	
	@Autowired
	private JobRepository jobRepository;
	
	@GetMapping
	public ResponseEntity<Iterable<JobBean>> get(){
		Iterable<JobBean> jobs = jobRepository.findAll();
		return ResponseEntity.ok(jobs);
	}
	
	@PostMapping
	public ResponseEntity<JobBean> post(@RequestBody JobBean jobBean) {
		return ResponseEntity.ok().body(jobRepository.save(jobBean));
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity delete(@PathVariable Long id) {
		//Optional<JobBean> bean = Optional.ofNullable(jobRepository.findById(id).orElseThrow(()-> new RuntimeException("Job not Found")));
		try {
			jobRepository.deleteById(id);
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}catch (RuntimeException e) {
			return new ResponseEntity(HttpStatus.BAD_REQUEST);
		}
		
	}
	
}
