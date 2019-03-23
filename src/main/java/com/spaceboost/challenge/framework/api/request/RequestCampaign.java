package com.spaceboost.challenge.framework.api.request;

import javax.validation.constraints.NotNull;

public class RequestCampaign {

    @NotNull
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
