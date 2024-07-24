package com.example.JobApp.model;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.context.annotation.Primary;

import java.time.LocalDateTime;

@Entity
@Table(name = "application")
public class Application {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer app_id;

    @OneToOne
    private JobPost jobId;

    @OneToOne
    private User applicantId;

    private Status status;

    @Column(nullable = false, updatable = false)
    @CreationTimestamp
    private LocalDateTime createdAt;
    @Column(nullable = false)
    @UpdateTimestamp
    private LocalDateTime updatedAt;


    public Application(Integer app_id, JobPost jobId, User applicantId, Status status) {
        this.app_id = app_id;
        this.jobId = jobId;
        this.applicantId = applicantId;
        this.status = status;
    }

    public Application(){

    }

    public Integer getApp_id() {
        return app_id;
    }

    public void setApp_id(Integer app_id) {
        this.app_id = app_id;
    }

    public JobPost getJobId() {
        return jobId;
    }

    public void setJobId(JobPost jobId) {
        this.jobId = jobId;
    }

    public User getApplicantId() {
        return applicantId;
    }

    public void setApplicantId(User applicantId) {
        this.applicantId = applicantId;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
