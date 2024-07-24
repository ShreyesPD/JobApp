package com.example.JobApp.dto;

import com.example.JobApp.model.User;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;

import java.util.List;

public class JobPostDTO {

    private Integer postId;
    private String postProfile;
    private String postDesc;
    private Integer reqExperience;
    private List<String> postTechStack;
    private String location;
    private Integer salaryCTC;
    private User employer_id;


    public JobPostDTO(Integer postId, String postProfile, String postDesc, Integer reqExperience, List<String> postTechStack, String location, Integer salaryCTC, User employer_id) {
        this.postId = postId;
        this.postProfile = postProfile;
        this.postDesc = postDesc;
        this.reqExperience = reqExperience;
        this.postTechStack = postTechStack;
        this.location = location;
        this.salaryCTC = salaryCTC;
        this.employer_id = employer_id;
    }

    public JobPostDTO() {

    }

    public Integer getPostId() {
        return postId;
    }

    public void setPostId(Integer postId) {
        this.postId = postId;
    }

    public String getPostProfile() {
        return postProfile;
    }

    public void setPostProfile(String postProfile) {
        this.postProfile = postProfile;
    }

    public String getPostDesc() {
        return postDesc;
    }

    public void setPostDesc(String postDesc) {
        this.postDesc = postDesc;
    }

    public Integer getReqExperience() {
        return reqExperience;
    }

    public void setReqExperience(Integer reqExperience) {
        this.reqExperience = reqExperience;
    }

    public List<String> getPostTechStack() {
        return postTechStack;
    }

    public void setPostTechStack(List<String> postTechStack) {
        this.postTechStack = postTechStack;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Integer getSalaryCTC() {
        return salaryCTC;
    }

    public void setSalaryCTC(Integer salaryCTC) {
        this.salaryCTC = salaryCTC;
    }

    public User getEmployer_id() {
        return employer_id;
    }

    public void setEmployer_id(User employer_id) {
        this.employer_id = employer_id;
    }

}
