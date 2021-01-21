package com.test.ansible.pojo.events;

import lombok.Data;

@Data
public class EventData {

        private String playPattern;

        private String play;

        private String task;

        private String taskArgs;

        private String remoteAddr;

        private Res res;

        private Integer pid;

        private String playUuid;

        private String taskUuid;

        private String playbookUuid;

        private String playbook;

        private String taskAction;

        private String host;

        private String taskPath;

}
