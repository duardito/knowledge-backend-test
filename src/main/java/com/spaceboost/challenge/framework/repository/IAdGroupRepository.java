package com.spaceboost.challenge.framework.repository;

import com.spaceboost.challenge.domain.adgroup.AdGroup;
import com.spaceboost.challenge.framework.api.request.RequestAdGroup;

public interface IAdGroupRepository {
    AdGroup find(Long id);

    AdGroup create(RequestAdGroup requestAdGroup);

    AdGroup addKeywords(Long id, Long keywordId);
}
