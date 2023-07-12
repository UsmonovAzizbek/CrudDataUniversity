package com.example.cruddatauniversity.controller;
//azizbek
import com.example.cruddatauniversity.dto.StudentDTO;
import com.example.cruddatauniversity.entity.Group;
import com.example.cruddatauniversity.entity.Student;
import com.example.cruddatauniversity.repository.GroupRepository;
import com.example.cruddatauniversity.repository.StudentRepository;
import jakarta.persistence.GeneratedValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class StudentController {

    @Autowired
    StudentRepository studentRepository;

    @Autowired
    GroupRepository groupRepository;

    @RequestMapping(value = "/student", method = RequestMethod.POST)
    public String addStudent(@RequestBody StudentDTO studentDTO){
        Student student = new Student();
        if (!studentRepository.existsByGroupId(studentDTO.getGroup_id())){
            student.setName(studentDTO.getName());
            Optional<Group> optionalGroup = groupRepository.findById(studentDTO.getGroup_id());
            student.setGroup(optionalGroup.get());
            studentRepository.save(student);
            return "Add Student";
        }else{
            return "Not Found Group";
        }
    }

    @RequestMapping(value = "/student", method = RequestMethod.GET)
    public List<Student> getStudent(){
        return studentRepository.findAll();
    }


    @RequestMapping(value = "/student/{university_id}", method = RequestMethod.GET)
    public List<Student> getIdStudent(@PathVariable Integer university_id){
        List<Student> allByUniversityId = studentRepository.findAllByUniversityId(university_id);
        return allByUniversityId;
    }

    @RequestMapping(value = "/student{student_id}", method = RequestMethod.DELETE)
    public String deleteStudent(@RequestBody Student student, @PathVariable Integer student_id){
        studentRepository.deleteById(student_id);
        return "Delete Student";
    }

    @RequestMapping(value = "/student{id}", method = RequestMethod.PUT)
    public String updateStudent(@RequestBody StudentDTO studentDTO, @PathVariable Integer id){
        Optional<Student> optionalStudent = studentRepository.findById(id);
        if (optionalStudent.isPresent()){
            Student student = optionalStudent.get();
            student.setName(studentDTO.getName());
            studentRepository.save(student);
        }
        return "Update Student";
    }
}
