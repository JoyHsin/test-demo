package com.test.ansible.pojo.events;

import lombok.Data;

@Data
public class SummaryFields {
    private Host host;
    private Job job;
    private Role role;
}
