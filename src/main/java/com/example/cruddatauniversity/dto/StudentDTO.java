package com.example.cruddatauniversity.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class StudentDTO {

    private String name;
    private Integer group_id;
    private Integer university_id;
}
