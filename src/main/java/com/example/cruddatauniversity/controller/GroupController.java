package com.example.cruddatauniversity.controller;

import com.example.cruddatauniversity.dto.GroupDTO;
import com.example.cruddatauniversity.entity.Faculty;
import com.example.cruddatauniversity.entity.Group;
import com.example.cruddatauniversity.repository.FacultyRepository;
import com.example.cruddatauniversity.repository.GroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class GroupController {

    @Autowired
    GroupRepository groupRepository;

    @Autowired
    FacultyRepository facultyRepository;

    @RequestMapping(value = "/group", method = RequestMethod.POST)
    public String addGroup(@RequestBody GroupDTO groupDTO){
        Group group = new Group();
        if (!groupRepository.existsByFaculty_Id(groupDTO.getFaculty_id())){
            group.setName(groupDTO.getName());
            Optional<Faculty> optionalFaculty = facultyRepository.findById(groupDTO.getFaculty_id());
            group.setFaculty(optionalFaculty.get());
            groupRepository.save(group);
            return "Add Group";
        }else{
            return "Not Found Faculty";
        }
    }

    @RequestMapping(value = "/group", method = RequestMethod.GET)
    public List<Group> getAllGroup(){
        return groupRepository.findAll();
    }

    @RequestMapping(value = "/group{faculty_id}", method = RequestMethod.GET)
    public List<Group> getGroup(@PathVariable Integer faculty_id){
        List<Group> facultyId = groupRepository.findAllByFaculty_Id(faculty_id);
        return facultyId;
    }

    @RequestMapping(value = "/group{group_id}", method = RequestMethod.DELETE)
    public String deleteGroup(@RequestBody Group group , @PathVariable Integer group_id){
        groupRepository.deleteById(group_id);
        return "Delete Group";
    }

    @RequestMapping(value = "/group{id}", method = RequestMethod.PUT)
    public String updateGroup(@RequestBody GroupDTO groupDTO, @PathVariable Integer id){
        Optional<Group> optionalGroup = groupRepository.findById(id);
        if (optionalGroup.isPresent()){
            Group group = optionalGroup.get();
            group.setName(groupDTO.getName());
            groupRepository.save(group);
        }
        return "Update Group";
    }
}
