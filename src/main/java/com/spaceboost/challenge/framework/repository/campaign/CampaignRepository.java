package com.spaceboost.challenge.framework.repository.campaign;

import com.spaceboost.challenge.domain.campain.Campaign;
import com.spaceboost.challenge.infraestructure.exception.DuplicatedKeyException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CampaignRepository implements ICampaignRepository {

    private volatile List<Campaign> persistence = new ArrayList<Campaign>();

    private List<Campaign> getCopy() {
        return Collections.unmodifiableList(persistence);
    }

    private Boolean getSequenceNextValue(Long id) {
        return getCopy().stream().filter(p -> id.equals(p.getId())).findFirst().isPresent();
    }

    @Override
    public Campaign find(Long id) {
        return getCopy().
                stream().
                filter(campain -> id.equals(campain.getId())).
                findFirst().
                orElse(null);
    }

    @Override
    public Campaign create(Long id) {
        if (getSequenceNextValue(id)) {
            throw new DuplicatedKeyException("Invalid key");
        }
        Campaign campaign = Campaign.create(id);
        persistence.add(campaign);
        return campaign;
    }

}
