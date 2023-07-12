package com.example.cruddatauniversity.controller;

import com.example.cruddatauniversity.dto.UniversityAddressDTO;
import com.example.cruddatauniversity.entity.Address;
import com.example.cruddatauniversity.entity.University;
import com.example.cruddatauniversity.repository.AddressRepository;
import com.example.cruddatauniversity.repository.UniversityRepository;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.amqp.RabbitConnectionDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class UniversityController {
    @Autowired
    UniversityRepository universityRepository;

    @Autowired
    AddressRepository addressRepository;


    @RequestMapping(value = "/university", method = RequestMethod.GET)
    public List<University> getUniversity(){
        return  universityRepository.findAll();
    }

    @RequestMapping(value = "/university", method = RequestMethod.POST)
    public String addUniversity(@RequestBody UniversityAddressDTO universityAddressDTO) {
        boolean byName = universityRepository.existsByName(universityAddressDTO.getName());
        if (!byName) {
            University university = new University();
            university.setName(universityAddressDTO.getName());
            Address address = new Address();
            address.setCity(universityAddressDTO.getCity());
            address.setDistrict(universityAddressDTO.getDistrict());
            address.setStreet(universityAddressDTO.getStreet());
            addressRepository.save(address);
            university.setAddress(address);
            universityRepository.save(university);
            return "Add University";
        } else {
            return "Bu nomdagi university kiritilgan!";
        }
    }

    @RequestMapping(value = "/university/{id}", method = RequestMethod.DELETE)
    public String deleteUniversity(@RequestBody University university, @PathVariable Integer id){
        universityRepository.deleteById(id);
        return "delete University";
    }

    @RequestMapping(value = "/university/{id}", method = RequestMethod.GET)
    public Optional<University> getIdUniversity(@PathVariable Integer id){
        Optional<University> universityOptional = universityRepository.findById(id);
        return universityOptional;
    }


    @RequestMapping(value = "/university/{id}", method = RequestMethod.PUT)
    public String updateUniversity(@RequestBody UniversityAddressDTO universityAddressDTO, @PathVariable Integer id){

        Optional<University> university1 = universityRepository.findById(id);

        if (university1.isPresent()){
            University university2 = university1.get();
            university2.setName(universityAddressDTO.getName());
            Address address = new Address();
            address.setCity(universityAddressDTO.getCity());
            address.setDistrict(universityAddressDTO.getDistrict());
            address.setStreet(universityAddressDTO.getStreet());
            addressRepository.save(address);
            university2.setAddress(address);
            universityRepository.save(university2);
        }
        return "update University";
    }

}
