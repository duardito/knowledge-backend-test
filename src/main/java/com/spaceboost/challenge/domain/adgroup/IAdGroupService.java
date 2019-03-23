package com.spaceboost.challenge.domain.adgroup;

import com.spaceboost.challenge.framework.api.request.RequestAdGroup;

public interface IAdGroupService {
    AdGroupDto getMostCostLessConverted();

    AdGroupDto getBy(Long id);

    AdGroupDto create(RequestAdGroup requestAdGroup);
}
