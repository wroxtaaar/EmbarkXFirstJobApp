package com.embarkx.embarkxfirstJobApp.job;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/jobs")
public class JobController {

    private final JobService jobService;

    public JobController(JobService jobService) {
        this.jobService = jobService;
    }

    @GetMapping
    public ResponseEntity<List<Job>> findAll() {
        List<Job> jobs= jobService.findAll();
        return new ResponseEntity<>(jobs, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Job> getJobById(@PathVariable Long id) {
         Job job = jobService.getJobById(id);
         if(job == null) {
             return new ResponseEntity<>(HttpStatus.NOT_FOUND);
         }
         return new ResponseEntity<>(job, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<String> createJob(@RequestBody Job job) {
        jobService.createJob(job);
        return new ResponseEntity<>("Job created successfully!", HttpStatus.CREATED);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteJobById(@PathVariable Long id) {
        boolean deleted = jobService.deleteJobById(id);
        if(!deleted) {
            return new ResponseEntity<>("Job not found!", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>("Job deleted successfully!", HttpStatus.OK);

    }


    @PutMapping("/{id}")
    public ResponseEntity<String> updateJobById(@PathVariable Long id, @RequestBody Job job) {

        boolean updated = jobService.updateJobById(id, job);

        if(!updated) {
            return new ResponseEntity<>("Job not found!", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>("Job updated successfully!", HttpStatus.OK);    }



}
