package com.test.ansible.pojo.events;

import lombok.Data;

import java.util.List;

@Data
public class Events {

    private Integer count;

    private String next;

    private String previous;

    private List<EventLog> results;
}
