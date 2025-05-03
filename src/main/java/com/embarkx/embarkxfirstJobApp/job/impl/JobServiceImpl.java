package com.embarkx.embarkxfirstJobApp.job.impl;

import com.embarkx.embarkxfirstJobApp.job.Job;
import com.embarkx.embarkxfirstJobApp.job.JobService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class JobServiceImpl implements JobService {

    private List<Job> jobs = new ArrayList<>();
    private Long nextId = 1L;


    @Override
    public List<Job> findAll() {
        return jobs;
    }

    @Override
    public Job getJobById(Long id) {
        return jobs.stream()
                   .filter(job -> job.getId().equals(id))
                   .findFirst()
                   .orElse(null);
    }


    @Override
    public void createJob(Job job) {
        job.setId(nextId++);
        jobs.add(job);
    }

    @Override
    public boolean deleteJobById(Long id) {
        return jobs.removeIf(job -> job.getId().equals(id));
    }

    @Override
        public boolean updateJobById(Long id, Job job) {
        Job jobToUpdate = jobs.stream()
                .filter(existingJob -> existingJob.getId().equals(id))
                .findFirst()
                .orElse(null);

        if (jobToUpdate != null) {
            jobToUpdate.setTitle(job.getTitle());
            jobToUpdate.setDescription(job.getDescription());
            jobToUpdate.setMinSalary(job.getMinSalary());
            jobToUpdate.setMaxSalary(job.getMaxSalary());
            jobToUpdate.setLocation(job.getLocation());
            return true;
        }

        return false;
    }
}
