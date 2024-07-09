package com.example.JobApp.repo;

import com.example.JobApp.model.CategoryJob;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryJobRepo extends JpaRepository<CategoryJob, Integer> {

}
