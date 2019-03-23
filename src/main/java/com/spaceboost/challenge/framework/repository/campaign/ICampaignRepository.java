package com.spaceboost.challenge.framework.repository.campaign;

import com.spaceboost.challenge.domain.campain.Campaign;

public interface ICampaignRepository {

    Campaign find(Long id);

    Campaign create(Long id);

}
