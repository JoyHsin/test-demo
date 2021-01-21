package com.test.ansible.pojo.status;

import lombok.Data;

import java.util.List;
@Data
public class Related {

    private String actor;

    private List<String> job;
}
