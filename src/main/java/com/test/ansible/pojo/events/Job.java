package com.test.ansible.pojo.events;

import lombok.Data;

@Data
public class Job {

    private Integer id;

    private String name;

    private String description;

    private String status;

    private boolean failed;

    private double elapsed;

    private String type;

    private Integer jobTemplateId;

    private String jobTemplateName;
}
