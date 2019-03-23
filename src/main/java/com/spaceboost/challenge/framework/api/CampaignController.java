package com.spaceboost.challenge.framework.api;

import com.spaceboost.challenge.domain.campain.CampainDto;
import com.spaceboost.challenge.domain.campain.ICampainService;
import com.spaceboost.challenge.framework.api.request.RequestCampaign;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class CampaignController {

    private final ICampainService iCampainService;

    public CampaignController(ICampainService iCampainService) {
        this.iCampainService = iCampainService;
    }

    @GetMapping(value = "/campaigns/{id}")
    public ResponseEntity<CampainDto> get(@PathVariable Long id) {
        CampainDto campainDto = iCampainService.getBy(id);
        return new ResponseEntity<CampainDto>(campainDto, campainDto == null ? HttpStatus.NOT_FOUND : HttpStatus.OK);
    }

    @PostMapping(value = "/campaigns/")
    public ResponseEntity<CampainDto> create(@RequestBody RequestCampaign requestCampaign) {
        return new ResponseEntity<CampainDto>(iCampainService.create(requestCampaign.getId()), HttpStatus.CREATED);
    }

    @GetMapping(value = "/campaigns/WithMostCostButLessConversions")
    public ResponseEntity<CampainDto> getWithMostCostButLessConversions() {
        CampainDto campainDto = iCampainService.getWithMostCostButLessConversions();
        return new ResponseEntity<CampainDto>(campainDto, campainDto.getId() == null ? HttpStatus.NOT_FOUND : HttpStatus.OK);
    }

}
