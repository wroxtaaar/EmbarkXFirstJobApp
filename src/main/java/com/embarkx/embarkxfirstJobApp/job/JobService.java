package com.embarkx.embarkxfirstJobApp.job;

import java.util.List;

public interface JobService {

    List<Job> findAll();
    Job getJobById(Long id);
    void createJob(Job job);

}
