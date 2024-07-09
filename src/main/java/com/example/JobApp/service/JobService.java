package com.example.JobApp.service;

import com.example.JobApp.dto.JobPostDTO;
import com.example.JobApp.exception.ResourceNotFoundException;
import com.example.JobApp.model.CategoryJob;
import com.example.JobApp.model.JobPost;
import com.example.JobApp.repo.CategoryJobRepo;
import com.example.JobApp.repo.JobRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class JobService {

//    List<JobPost> jobs = new ArrayList<>(Arrays.asList(new JobPost(1, "Java Developer", "Must have good experience in core Java and advanced Java", 2, List.of("Core Java", "J2EE", "Spring Boot", "Hibernate")), new JobPost(2, "Frontend Developer", "Experience in building responsive web applications using React", 3, List.of("HTML", "CSS", "JavaScript", "React")), new JobPost(3, "Data Scientist", "Strong background in machine learning and data analysis", 4, List.of("Python", "Machine Learning", "Data Analysis"))));

    @Autowired
    private JobRepo repo;

    @Autowired
    private CategoryJobRepo categoryJobRepo;

    public JobService(JobRepo jobRepo) {
        this.repo = jobRepo;
    }

    @Transactional
    public CategoryJob createCategoryAndJob(CategoryJob categoryJob) {
        List<JobPost> listJobPost = new ArrayList<>();
        for (JobPost jobPost : categoryJob.getJobPosts()) {
            JobPost jobPost1 = new JobPost();
            jobPost1.setPostId(jobPost.getPostId());
            jobPost1.setPostProfile(jobPost.getPostProfile());
            jobPost1.setPostDesc(jobPost.getPostDesc());
            jobPost1.setReqExperience(jobPost.getReqExperience());
            jobPost1.setPostTechStack(jobPost.getPostTechStack());
            listJobPost.add(repo.save(jobPost));
        }
//        System.out.println(1 / 0);
        categoryJob.setJobPosts(listJobPost);
        CategoryJob categoryJob1 = categoryJobRepo.save(categoryJob);
        return categoryJob1;
    }

    @Transactional
    public JobPostDTO addJob(JobPostDTO jobPostDTO) {
//        repo.addJobs(jobPost);
        JobPost jobPost = new JobPost();
        jobPost.setPostId(jobPostDTO.getPostId());
        jobPost.setPostProfile(jobPostDTO.getPostProfile());
        jobPost.setPostDesc(jobPostDTO.getPostDesc());
        jobPost.setReqExperience(jobPostDTO.getReqExperience());
        jobPost.setPostTechStack(jobPostDTO.getPostTechStack());
        jobPost = repo.save(jobPost);
        jobPostDTO.setPostId(jobPost.getPostId());
        return jobPostDTO;
    }

    public List<JobPostDTO> getAllJobs(Integer pageNumber, Integer pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by("postId"));
        Page<JobPost> allPageJobs = repo.findAll(pageable);
        List<JobPost> jobPostList = allPageJobs.getContent();
        List<JobPostDTO> jobPostDTOList = new ArrayList<>();
        if (!jobPostList.isEmpty()) {
            jobPostList.forEach(e -> jobPostDTOList.add(new JobPostDTO(e.getPostId(), e.getPostProfile(), e.getPostDesc(), e.getReqExperience(), e.getPostTechStack())));
            return jobPostDTOList;
        } else {
            throw new ResourceNotFoundException("there are no jobs posted currently!");
        }
    }

    @Transactional
    public void deleteJobById(Integer postId) {
//        System.out.println(" service " + postId);
        if (repo.existsById(postId)) repo.deleteById(postId);
        else throw new ResourceNotFoundException("task with this post id does not exists. PostID: " + postId);
    }

    public List<JobPostDTO> search(String keyword) {
        List<JobPost> jobPostList = repo.findByPostProfileContainingOrPostDescContaining(keyword, keyword);
        List<JobPostDTO> jobPostDTOList = new ArrayList<>();
        if (!jobPostList.isEmpty()) {
            jobPostList.forEach(e -> jobPostDTOList.add(new JobPostDTO(e.getPostId(), e.getPostProfile(), e.getPostDesc(), e.getReqExperience(), e.getPostTechStack())));
            return jobPostDTOList;
        } else {
            throw new ResourceNotFoundException("there are no jobs posted with this keyword currently!");
        }
    }

    public JobPostDTO getJobById(Integer postId) {
        if (repo.existsById(postId)) {
            JobPost jobPost = repo.findById(postId).get();
            JobPostDTO jobPostDTO = new JobPostDTO();
            jobPostDTO.setPostId(jobPost.getPostId());
            jobPostDTO.setPostProfile(jobPost.getPostProfile());
            jobPostDTO.setPostDesc(jobPost.getPostDesc());
            jobPostDTO.setReqExperience(jobPost.getReqExperience());
            jobPostDTO.setPostTechStack(jobPost.getPostTechStack());
            return jobPostDTO;
        } else {
            throw new ResourceNotFoundException("there are no jobs with this job ID posted currently!");
        }
    }

    @Transactional
    public JobPostDTO updateJob(JobPostDTO jobPostDTO, Integer postId) {
        if (repo.existsById(postId)) {
            JobPost jobPost = new JobPost();
            jobPost.setPostId(jobPostDTO.getPostId());
            jobPost.setPostProfile(jobPostDTO.getPostProfile());
            jobPost.setPostDesc(jobPostDTO.getPostDesc());
            jobPost.setReqExperience(jobPostDTO.getReqExperience());
            jobPost.setPostTechStack(jobPostDTO.getPostTechStack());
            repo.save(jobPost);
            jobPostDTO.setPostId(postId);
            return jobPostDTO;
        } else {
            throw new ResourceNotFoundException("this job doesn't exists");
        }
    }
}

