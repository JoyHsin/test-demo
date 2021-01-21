package com.test.ansible.pojo.launch;

import lombok.Data;

@Data
public class Project {

    private Integer id;

    private String name;

    private String description;

    private String status;

    private String scmType;
}
