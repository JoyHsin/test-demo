package com.test.ansible.pojo.status;

import lombok.Data;

@Data
public class Status {

        private Integer id;
        private String type;
        private String url;
        private Related related;
        private SummaryFields summaryfields;
        private String timestamp;
        private String operation;
        private Changes changes;
        private String object1;
        private String object2;
        private String objectAssociation;
}
