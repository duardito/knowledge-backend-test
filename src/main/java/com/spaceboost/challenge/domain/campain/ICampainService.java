package com.spaceboost.challenge.domain.campain;

public interface ICampainService {
    CampaingDto getBy(Long id);

    CampaingDto create(Long id);

}
