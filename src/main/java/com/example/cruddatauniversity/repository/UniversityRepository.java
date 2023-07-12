package com.example.cruddatauniversity.repository;

import com.example.cruddatauniversity.entity.University;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UniversityRepository  extends JpaRepository<University, Integer> {

    boolean existsByName(String name);

}
