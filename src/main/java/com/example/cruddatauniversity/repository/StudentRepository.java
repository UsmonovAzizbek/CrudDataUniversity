package com.example.cruddatauniversity.repository;

import com.example.cruddatauniversity.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {

    boolean existsByGroupId(Integer group_id);
    List<Student> findAllByUniversityId(Integer university_id);

}
