package com.spaceboost.challenge.domain.campain;

public interface ICampainService {
    CampainDto getBy(Long id);

    CampainDto create(Long id);

}
