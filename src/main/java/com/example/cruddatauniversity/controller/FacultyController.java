package com.example.cruddatauniversity.controller;

import com.example.cruddatauniversity.dto.FacultyDTO;
import com.example.cruddatauniversity.entity.Faculty;
import com.example.cruddatauniversity.entity.University;
import com.example.cruddatauniversity.repository.FacultyRepository;
import com.example.cruddatauniversity.repository.UniversityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

@RestController
public class FacultyController {

    @Autowired
    FacultyRepository facultyRepository;

    @Autowired
    UniversityRepository universityRepository;

    @RequestMapping(value = "/faculty", method = RequestMethod.POST)
    public String addFaculty(@RequestBody FacultyDTO facultyDTO){
        Faculty faculty1 = new Faculty();
        faculty1.setName(facultyDTO.getName());
        Optional<University> optionalUniversity = universityRepository.findById(facultyDTO.getUniversity_id());
        if (optionalUniversity.isPresent()){
            faculty1.setUniversity(optionalUniversity.get());
            facultyRepository.save(faculty1);
            return "save faculty";
        }
        return "University not found";

    }

    @RequestMapping(value = "/faculty", method = RequestMethod.GET)
    public List<Faculty> getAllFaculty(){
        return facultyRepository.findAll();
    }

    @RequestMapping(value = "/faculty/{university_id}", method = RequestMethod.GET)
    public List<Faculty> getFaculty(@PathVariable Integer university_id){
        List<Faculty> byUniversityId = facultyRepository.findAllByUniversityId(university_id);
        return byUniversityId;
    }

    @RequestMapping(value = "/faculty/{id}", method = RequestMethod.DELETE)
    public String deleteFaculty(@RequestBody Faculty faculty, @PathVariable Integer id){
        facultyRepository.deleteById(id);
        return "Delete Faculty";
    }

    @RequestMapping(value = "/faculty/{faculty_id}", method = RequestMethod.PUT)
    public void updateFaculty(@RequestBody FacultyDTO facultyDTO, @PathVariable Integer faculty_id){
        Optional<Faculty> optionalFaculty = facultyRepository.findById(faculty_id);
        if (optionalFaculty.isPresent()){
            Faculty faculty = optionalFaculty.get();
            faculty.setName(facultyDTO.getName());
            facultyRepository.save(faculty);
        }
    }
}
