package com.test.ansible.pojo.launch;

import com.test.ansible.pojo.launch.nopara.Artifacts;
import com.test.ansible.pojo.launch.nopara.IgnoredFields;
import com.test.ansible.pojo.launch.nopara.JobEnv;
import lombok.Data;

import java.util.List;

@Data
public class LaunchResult {

        private Integer job;

        private IgnoredFields ignored_fields;

        private Integer id;

        private String type;

        private String url;

        private Related related;

        private SummaryFields summaryFields;

        private String created; //创建时间

        private String modified;

        private String name;

        private String description;

        private String jobType;

        private Integer inventory;

        private Integer project;

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

        private Integer unifiedJobTemplate;

        private String launchType;

        private String status;

        private boolean failed;

        private String started;

        private String finished;

        private Integer elapsed;

        private String jobArgs;

        private String jobCwd;

        private JobEnv jobEnv;

        private String jobExplanation;

        private String executionNode;

        private String controllerNode;

        private String resultTraceback;

        private boolean eventProcessingFinished;

        private Integer jobTemplate;

        private List<String> passwordsNeededToStart;

        private boolean askDiffModeOnLaunch;

        private boolean askVariablesOnLaunch;

        private boolean askLimitOnLaunch;

        private boolean askTagsOnLaunch;

        private boolean askSkipTagsOnLaunch;

        private boolean askJobTypeOnLaunch;

        private boolean askVerbosityOnLaunch;

        private boolean askInventoryOnLaunch;

        private boolean askCredentialOnLaunch;

        private boolean allowSimultaneous;

        private Artifacts artifacts;

        private String scmRevision;

        private String instanceGroup;

        private boolean diffMode;

        private Integer jobSliceNumber;

        private Integer jobSliceCount;

        private Integer credential;

        private String vaultCredential;

}
