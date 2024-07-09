package com.example.JobApp.repo;

import com.example.JobApp.model.JobPost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Repository
public interface JobRepo extends JpaRepository<JobPost, Integer> {

//    public List<JobPost> getAllJobs() {
//        return jobs;
//    }
//
//    public void addJobs(JobPost job) {
//        jobs.add(job);
//        System.out.println(jobs);
//    }

//    public void deleteById(Integer id);

    List<JobPost> findByPostProfileContainingOrPostDescContaining(String postProfile, String postDesc);

//    JobPost getJobById(Integer postId);
}
