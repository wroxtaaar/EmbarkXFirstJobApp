package com.embarkx.embarkxfirstJobApp.job.impl;

import com.embarkx.embarkxfirstJobApp.job.Job;
import com.embarkx.embarkxfirstJobApp.job.JobRepository;
import com.embarkx.embarkxfirstJobApp.job.JobService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class JobServiceImpl implements JobService {

    JobRepository jobRepository;

    @Override
    public List<Job> findAll() {
        return jobRepository.findAll();
    }

    @Override
    public Job getJobById(Long id) {
        return jobRepository.findById(id).orElse(null);
    }

    @Override
    public void createJob(Job job) {
        jobRepository.save(job);
    }

    @Override
    public boolean deleteJobById(Long id) {
        if(jobRepository.existsById(id)) {
            jobRepository.deleteById(id);
            return true;
        }        // If the company with the given ID does not exist, return false
        return false;
    }

    @Override
        public boolean updateJobById(Long id, Job updatedJob) {

        Optional<Job> jobOptional = jobRepository.findById(id);

        if (jobOptional.isPresent()) {
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
