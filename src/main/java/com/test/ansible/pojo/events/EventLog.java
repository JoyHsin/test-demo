package com.test.ansible.pojo.events;

import com.test.ansible.pojo.launch.Related;
import lombok.Data;

@Data
public class EventLog {

        private Integer id;
        private String type;
        private String url;
        private Related related;
        private SummaryFields summaryFields;
        private String created;
        private String modified;
        private Integer job;
        private String event;
        private Integer counter;
        private String eventDisplay;
        private EventData eventData;
        private Integer eventLevel;
        private boolean failed;
        private boolean changed;
        private String uuid;
        private String parentUuid;
        private String host;
        private String hostName;
        private String parent;
        private String playbook;
        private String play;
        private String task;
        private String role;
        private String stdout;
        private Integer startLine;
        private Integer endLine;
        private Integer verbosity;
}
