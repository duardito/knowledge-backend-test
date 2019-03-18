package com.spaceboost.challenge.domain.campain;

import java.util.Collections;
import java.util.List;

public class Campaign {

    private Long id;
    private List<Long> groupId;

    public Campaign(){

    }

    private Campaign(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public Campaign create(Long id) {
        return new Campaign(id);
    }

    public List<Long> getGroupsID() {
        return Collections.unmodifiableList(groupId);
    }

    public void add(Long groupId) {
        this.getGroupsID().add(groupId);
    }
}
