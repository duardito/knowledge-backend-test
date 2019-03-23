package com.spaceboost.challenge.framework.repository.adgroup;

import com.spaceboost.challenge.domain.adgroup.AdGroup;
import com.spaceboost.challenge.framework.api.request.RequestAdGroup;

public interface IAdGroupRepository {
    AdGroup find(Long id);

    AdGroup create(RequestAdGroup requestAdGroup);

    AdGroup findMostCostLessConverted();
}
