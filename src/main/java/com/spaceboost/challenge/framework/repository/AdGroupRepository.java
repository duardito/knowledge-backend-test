package com.spaceboost.challenge.framework.repository;

import com.spaceboost.challenge.domain.adgroup.AdGroup;
import com.spaceboost.challenge.domain.exception.DuplicatedKeyException;
import com.spaceboost.challenge.domain.exception.ObjectNotFoundException;
import com.spaceboost.challenge.framework.api.request.RequestAdGroup;

import java.util.ArrayList;
import java.util.List;

public class AdGroupRepository implements IAdGroupRepository{

    private static List<AdGroup>persistence = new ArrayList<>();

    private Boolean getSequenceNextValue(Long id) {
        return persistence.stream().filter(p-> p.getId().equals(id)).findFirst().isPresent();
    }

    @Override
    public AdGroup find(Long id) {
        return persistence.
                stream().
                filter(campain -> campain.getId().equals(id)).
                findFirst().
                orElseThrow(() -> new ObjectNotFoundException("AdGroup doesn't exist"));
    }

    @Override
    public AdGroup create(RequestAdGroup request) {
        if(getSequenceNextValue(request.getId())){
            throw new DuplicatedKeyException("Duplicated key");
        }

        AdGroup adGroup = new AdGroup().
                create(request.getId(),request.getCampaignId(),request.getClicks(),request.getConversions(),request.getCost());
        persistence.add(adGroup);
        return adGroup;
    }

    @Override
    public AdGroup addKeywords(Long id, Long keywordId) {
        AdGroup adGroup = find(id);
        adGroup.add(keywordId);
        return adGroup;
    }

}
