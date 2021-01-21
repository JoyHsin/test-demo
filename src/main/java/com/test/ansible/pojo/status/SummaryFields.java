package com.test.ansible.pojo.status;

import com.test.ansible.pojo.events.Job;
import com.test.ansible.pojo.launch.JobTemplate;
import lombok.Data;

import java.util.List;
@Data
public class SummaryFields {

    private List<Job> job;
    private List<JobTemplate> job_template;
    private Actor actor;

}
