package com.spaceboost.challenge.framework.repository.campaign;

import com.spaceboost.challenge.domain.campain.Campaign;
import com.spaceboost.challenge.domain.exception.DuplicatedKeyException;
import com.spaceboost.challenge.domain.exception.ObjectNotFoundException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CampainRepository implements ICampainRepository {

    private List<Campaign> persistence = new ArrayList<Campaign>();

    private List<Campaign> getCopy() {
        return Collections.unmodifiableList(persistence);
    }

    private Boolean getSequenceNextValue(Long id) {
        return getCopy().stream().filter(p -> p.getId().equals(id)).findFirst().isPresent();
    }

    @Override
    public Campaign find(Long id) {
        return getCopy().
                stream().
                filter(campain -> campain.getId().equals(id)).
                findFirst().
                orElseThrow(() -> new ObjectNotFoundException("Campaign doesn't exist"));
    }

    @Override
    public Campaign create(Long id) {
        if (getSequenceNextValue(id)) {
            throw new DuplicatedKeyException("Invalid key");
        }
        Campaign campaign = new Campaign().create(id);
        persistence.add(campaign);
        return campaign;
    }

    @Override
    public Campaign addAdGroups(Long id, Long groupId) {
        Campaign campaign = find(id);
        campaign.add(groupId);
        return campaign;
    }
}
