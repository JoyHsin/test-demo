package com.test.ansible.pojo.status;

import lombok.Data;

import java.util.List;
@Data
public class Changes {

        private String name;
        private String description;
        private String jobType;
        private String inventory;
        private String project;
        private String playbook;
        private Integer forks;
        private String limit;
        private Integer verbosity;
        private String extraVars;
        private String jobTags;
        private boolean forceHandlers;
        private String skipTags;
        private String startAtTask;
        private Integer timeout;
        private boolean useFactCache;
        private String jobTemplate;
        private boolean allowSimultaneous;
        private String instanceGroup;
        private boolean diffMode;
        private Integer jobSliceNumber;
        private Integer jobSliceCount;
        private Integer credential;
        private String vaultCredential;
        private Integer id;
        private List<String> credentials;
        private List<String> labels;
}
