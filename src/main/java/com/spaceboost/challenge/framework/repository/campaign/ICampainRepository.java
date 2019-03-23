package com.spaceboost.challenge.framework.repository.campaign;

import com.spaceboost.challenge.domain.campain.Campaign;

public interface ICampainRepository {

    Campaign find(Long id);

    Campaign create(Long id);

    Campaign addAdGroups(Long id, Long groupId);
}
