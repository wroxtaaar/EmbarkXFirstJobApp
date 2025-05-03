package com.embarkx.embarkxfirstJobApp.job.impl;

import com.embarkx.embarkxfirstJobApp.job.Job;
import com.embarkx.embarkxfirstJobApp.job.JobService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class JobServiceImpl implements JobService {

    private List<Job> jobs = new ArrayList<>();


    @Override
    public List<Job> findAll() {
        return jobs;
    }

    @Override
    public void createJob(Job job) {
        jobs.add(job);
    }
}
