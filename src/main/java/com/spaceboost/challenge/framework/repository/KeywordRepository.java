package com.spaceboost.challenge.framework.repository;

import com.spaceboost.challenge.domain.exception.DuplicatedKeyException;
import com.spaceboost.challenge.domain.exception.ObjectNotFoundException;
import com.spaceboost.challenge.domain.keyword.Keyword;
import com.spaceboost.challenge.framework.api.RequestKeyword;
import org.springframework.web.bind.annotation.PathVariable;

import java.security.Key;
import java.util.ArrayList;
import java.util.List;

public class KeywordRepository implements IKeywordRepository {

    private static List<Keyword> persistence = new ArrayList<>();

    private Boolean getSequenceNextValue(Long id) {
        return persistence.stream().filter(p -> p.getId().equals(id)).findFirst().isPresent();
    }

    @Override
    public Keyword find(Long id) {
        return persistence.
                stream().
                filter(campain -> campain.getId().equals(id)).
                findFirst().
                get();
    }

    @Override
    public Keyword findByIdByCampaignAndByGroup(Long campaignId, Long adGroupId, Long keywordId) {
        return persistence.stream().
                filter(p -> p.getId().equals(keywordId) && p.getAdGroupId().equals(adGroupId) && p.getCampaignId().equals(campaignId)).
                findFirst().
                orElseThrow(() -> new ObjectNotFoundException("keyword"));
    }

    @Override
    public Keyword create(RequestKeyword request) {
        if (getSequenceNextValue(request.getId())) {
            throw new DuplicatedKeyException(request.getId().toString());
        }

        Keyword keyword = new Keyword().
                create(request.getId(), request.getCampaignId(), request.getAdGroupId(), request.getClicks(), request.getConversions(), request.getCost());
        persistence.add(keyword);
        return keyword;
    }
}
