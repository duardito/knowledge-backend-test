package com.spaceboost.challenge.framework.api;

import com.spaceboost.challenge.domain.campain.CampaingDto;
import com.spaceboost.challenge.domain.campain.ICampainService;
import com.spaceboost.challenge.framework.api.request.RequestCampaign;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class CampaignController {

    private final ICampainService iCampainService;

    public CampaignController(ICampainService iCampainService) {
        this.iCampainService = iCampainService;
    }

    @GetMapping(value = "/campaigns/{id}")
    public ResponseEntity<CampaingDto> get(@PathVariable Long id) {
        final CampaingDto campaingDto = iCampainService.getBy(id);
        return new ResponseEntity<CampaingDto>(campaingDto, campaingDto == null ? HttpStatus.NOT_FOUND : HttpStatus.OK);
    }

    @PostMapping(value = "/campaigns/")
    public ResponseEntity<CampaingDto> create(@Valid @RequestBody RequestCampaign requestCampaign) {
        final CampaingDto campaingDto = iCampainService.create(requestCampaign.getId());
        return new ResponseEntity<CampaingDto>(campaingDto, HttpStatus.CREATED);
    }

}
