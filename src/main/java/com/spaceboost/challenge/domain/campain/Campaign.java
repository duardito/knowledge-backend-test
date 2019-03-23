package com.spaceboost.challenge.domain.campain;

public class Campaign {

    private Long id;

    public Campaign(){}

    private Campaign(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public Campaign create(Long id) {
        return new Campaign(id);
    }

}
