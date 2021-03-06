package com.test.ansible.pojo.launch;

import lombok.Data;

@Data
public class Credentials {

    private Integer id;
    private String name;
    private String description;
    private String kind;
    private boolean cloud;
    private Integer credentialTypeId;
}
