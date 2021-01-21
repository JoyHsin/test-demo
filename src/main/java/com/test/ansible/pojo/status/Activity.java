package com.test.ansible.pojo.status;

import lombok.Data;

import java.util.List;
@Data
public class Activity {
    private Integer count;
    private String next;
    private String previous;
    private List<Status> results;
}
