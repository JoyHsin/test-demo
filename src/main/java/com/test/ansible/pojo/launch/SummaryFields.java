package com.test.ansible.pojo.launch;

import com.test.ansible.pojo.events.Job;
import com.test.ansible.pojo.status.Actor;
import lombok.Data;

import java.util.List;

@Data
public class SummaryFields {

    private  Inventory inventory;

    private Project project;

    private Credential credential;

    private JobTemplate jobTemplate;

    private UnifiedJobTemplate unifiedJobTemplate;

    private CreatedBy createdBy;

    private ModifiedBy modifiedBy;

    private UserCapabilities userCapabilities;

    private Labels labels;

    private List<String> extraCredentials;

    private List<Credentials> credentials;

}
