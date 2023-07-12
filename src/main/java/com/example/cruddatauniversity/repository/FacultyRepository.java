package com.example.cruddatauniversity.repository;

import com.example.cruddatauniversity.entity.Faculty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FacultyRepository extends JpaRepository<Faculty, Integer> {

    List<Faculty> findAllByUniversityId(Integer id);
}
