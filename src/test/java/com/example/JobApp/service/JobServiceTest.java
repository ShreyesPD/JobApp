//package com.example.JobApp.service;
//
//import com.example.JobApp.dto.JobPostDTO;
//import com.example.JobApp.model.JobPost;
//import com.example.JobApp.repo.JobRepo;
//import org.junit.jupiter.api.AfterEach;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.data.domain.*;
//
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.List;
//import java.util.Optional;
//
//import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.ArgumentMatchers.anyInt;
//import static org.mockito.Mockito.*;
//
////@SpringBootTest
////@ExtendWith(MockitoExtension.class)
//@SpringBootTest
//class JobServiceTest {
//
//    @Mock
//    private JobRepo jobRepo;
//
//    @Autowired
//    private JobService jobService;
//
//    AutoCloseable autoCloseable;
//
//    JobPost jobPost;
//    JobPost jobPost2;
//
//    JobPostDTO jobPostDTO;
//    JobPostDTO jobPostDTO2;
//
//    @BeforeEach
//    void setUp() {
//        autoCloseable = MockitoAnnotations.openMocks(this);
//
//        jobService = new JobService(jobRepo);
////        System.out.println("method called!1");
//        List<String> techStackList = new ArrayList<String>();
//        techStackList.add("java");
//        techStackList.add("spring");
//        techStackList.add("springboot");
//        jobPost = new JobPost(10, "java dev", "java developer role", 3, techStackList);
//        jobPostDTO = new JobPostDTO(10, "java dev", "java developer role", 3, techStackList);
//        jobPost2 = new JobPost(11, "Devops", "Devops role", 5, techStackList);
//        jobPostDTO2 = new JobPostDTO(11, "Devops", "Devops role", 5, techStackList);
////        System.out.println("sdsd" + jobPost.getPostProfile());
//    }
//
//    @AfterEach
//    void tearDown() throws Exception {
//        autoCloseable.close();
//    }
//
//    @Test
//    void addJob() {
//        when(jobRepo.save(any(JobPost.class))).thenReturn(jobPost);
//        JobPostDTO addJob = jobService.addJob(jobPostDTO);
//        assertEquals(jobPost.getPostProfile(), addJob.getPostProfile());
//    }
//
//    @Test
//    void getAllJobs() {
//
//        List<JobPost> jobPosts = Arrays.asList(jobPost, jobPost2);
//        Page<JobPost> pageJobPosts = new PageImpl<>(jobPosts);
//
//        Pageable pageable = PageRequest.of(0, 2, Sort.by("postId"));
//        when(jobRepo.findAll(pageable)).thenReturn(pageJobPosts);
//
//        List<JobPostDTO> jobPostDTOList = jobService.getAllJobs(0, 2);
//
//        assertThat(jobPostDTOList.get(0).getPostId()).isEqualTo(jobPost.getPostId());
////        assertThat(jobPostList.get(0).getPostId()).isEqualTo(122);
//        assertThat(jobPostDTOList.get(1).getPostId()).isEqualTo(jobPost2.getPostId());
//    }
//
//
//    @Test
//    void getJobById() {
////        System.out.println(jobPost.getPostProfile());
//        when(jobRepo.existsById(10)).thenReturn(true);
//        when(jobRepo.findById(10)).thenReturn(Optional.ofNullable(jobPost));
//        String empName = "java dev";
//        JobPostDTO jobById = jobService.getJobById(10);
//        assertEquals(empName, jobById.getPostProfile());
////        System.out.println("test$$$$$$$ : " + jobById.getPostProfile());
////        assertThat("Java dev").isEqualTo(jobById.getPostProfile());
//    }
//
//    @Test
//    void deleteById() {
//        when(jobRepo.existsById(jobPost.getPostId())).thenReturn(true);
//        jobService.deleteJobById(jobPost.getPostId());
//        verify(jobRepo, times(1)).deleteById(jobPost.getPostId());
//    }
//
//}