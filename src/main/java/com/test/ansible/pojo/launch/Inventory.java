package com.test.ansible.pojo.launch;

import lombok.Data;

@Data
public class Inventory {

        private Integer id;

        private String name;

        private String description;

        private boolean hasActiveFailures;

        private Integer totalHosts;

        private Integer hostsWithActiveFailures;

        private Integer totalGroups;

        private Integer groupsWithActiveFailures;

        private boolean hasInventorySources;

        private Integer totalInventorySources;

        private Integer inventorySourcesWithFailures;

        private Integer organizationId;

        private String kind;
}
