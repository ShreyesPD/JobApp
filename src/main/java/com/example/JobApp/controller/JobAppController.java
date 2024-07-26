package com.example.JobApp.controller;

import com.example.JobApp.dto.JobPostDTO;
import com.example.JobApp.service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("job")
public class JobAppController {

    @Autowired
    private JobService service;

    @PostMapping("createJob")
    public ResponseEntity<JobPostDTO> success(@RequestBody JobPostDTO jobPostDTO) {
//        System.out.println("JobPost %%% " + jobPost);
//        service.addJob(jobPost);
        return new ResponseEntity<>(service.addJob(jobPostDTO), HttpStatus.CREATED);
    }

    @GetMapping("viewalljobs")
    public ResponseEntity<List<JobPostDTO>> viewAllJobs(
            @RequestParam(value = "pageNumber", defaultValue = "0", required = false) Integer pageNumber,
            @RequestParam(value = "pageSize", defaultValue = "1", required = false) Integer pageSize) {
        List<JobPostDTO> jobs = service.getAllJobs(pageNumber, pageSize);
//        m.addAttribute("jobPosts", jobs);
        return new ResponseEntity<>(jobs, HttpStatus.OK);
    }

    @GetMapping("getjob/{postId}")
    public ResponseEntity<JobPostDTO> getJobById(@PathVariable("postId") Integer postId) {
//        System.out.println(service.getJobById(postId));
        return new ResponseEntity<>(service.getJobById(postId), HttpStatus.OK);
    }

    @GetMapping("findjob/{keyword}")
    public ResponseEntity<List<JobPostDTO>> findJob(@PathVariable("keyword") String keyword) {
        System.out.println(service.search(keyword));
        return new ResponseEntity<>(service.search(keyword), HttpStatus.OK);
    }

    @PutMapping("updatejob/{postId}")
    public ResponseEntity<JobPostDTO> updateJob(@RequestBody JobPostDTO jobDTO, @PathVariable("postId") Integer postId) {
//        System.out.println(job);
//        service.updateJob(job, postId);
        return new ResponseEntity<>(service.updateJob(jobDTO, postId), HttpStatus.OK);
    }

    @DeleteMapping("removejob/{postId}")
    public ResponseEntity<Void> deleteJobById(@PathVariable("postId") Integer postId) {
        service.deleteJobById(postId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
