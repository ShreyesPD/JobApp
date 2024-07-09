package com.example.JobApp.repo;

import com.example.JobApp.model.JobPost;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.*;

import static org.assertj.core.api.Assertions.*;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
//@DataJpaTest
class JobRepoTest {

    @Autowired
    private JobRepo jobRepo;

    @Test
    void findByPostProfileContainingOrPostDescContaining() {
        System.out.println("hello world test");

        List<String> techStackList = new ArrayList<String>();
        techStackList.add("java");
        techStackList.add("spring");
        techStackList.add("springboot");

        JobPost jobPost = new JobPost(27, "java dev", "java developer post", 15, techStackList);
        jobRepo.save(jobPost);

        List<JobPost> actualResult = jobRepo.findByPostProfileContainingOrPostDescContaining("java", "java");
        System.out.println("$$$$$" + actualResult);

//        List<JobPost> expectedResult = new ArrayList<JobPost>();
//        expectedResult.add(jobPost);
        System.out.println(actualResult.getLast().getPostId());
        assertThat(actualResult.getLast().getPostId()).isEqualTo(jobPost.getPostId());
    }

}