package com.example.JobApp.controller;

import com.example.JobApp.dto.JobPostDTO;
import com.example.JobApp.model.JobPost;
import com.example.JobApp.service.JobService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(JobAppController.class)
class JobAppControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private JobService jobService;

    JobPostDTO jobPostDTO;
    JobPostDTO jobPostDTO2;
    //    List<JobPost> jobPostsList = new ArrayList<>();
    List<JobPostDTO> jobPostsDTOList = new ArrayList<>();


    @BeforeEach
    void setUp() {
        List<String> techStackList = new ArrayList<String>();
        techStackList.add("java");
        techStackList.add("spring");
        techStackList.add("springboot");
        jobPostDTO = new JobPostDTO(10, "Java dev", "java developer role", 3, techStackList);
        jobPostDTO2 = new JobPostDTO(11, "Devops", "Devops role", 5, techStackList);
        jobPostsDTOList.add(jobPostDTO);
        jobPostsDTOList.add(jobPostDTO2);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void viewAllJobs() throws Exception {
        when(jobService.getAllJobs(anyInt(), anyInt())).thenReturn(jobPostsDTOList);
        mockMvc.perform(get("/viewalljobs")).andDo(print()).andExpect(status().isOk());
    }

    @Test
    void getJobById() throws Exception {
        when(jobService.getJobById(5)).thenReturn(jobPostDTO);
        mockMvc.perform(get("/getjob/5")).andDo(print()).andExpect(status().isOk());
    }

}