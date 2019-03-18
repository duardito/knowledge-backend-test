package com.spaceboost.challenge.domain.adgroup;

import com.spaceboost.challenge.framework.api.RequestAdGroup;

public interface IAdGroupService {
    AdGroupDto getBy(Long id);

    AdGroupDto create(RequestAdGroup requestAdGroup);
}
