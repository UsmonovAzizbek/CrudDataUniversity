package com.example.cruddatauniversity.repository;

import com.example.cruddatauniversity.entity.Group;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GroupRepository extends JpaRepository<Group, Integer> {

    boolean existsByFaculty_Id(Integer faculty_id);

    List<Group> findAllByFaculty_Id(Integer faculty_id);
}
