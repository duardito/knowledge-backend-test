package com.spaceboost.challenge.framework.api;

import com.spaceboost.challenge.domain.adgroup.AdGroupDto;
import com.spaceboost.challenge.domain.adgroup.IAdGroupService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class AdGroupController {

    private final IAdGroupService iAdGroupService;

    public AdGroupController(IAdGroupService iAdGroupService) {
        this.iAdGroupService = iAdGroupService;
    }

    @GetMapping("/adGroup/{id}")
    public ResponseEntity<AdGroupDto> get(@PathVariable Long id) {
        AdGroupDto adGroup = iAdGroupService.getBy(id);
        return new ResponseEntity<AdGroupDto>(adGroup, adGroup.getId() == null ? HttpStatus.NOT_FOUND : HttpStatus.OK);
    }

    @PostMapping("/adGroup/")
    public ResponseEntity<AdGroupDto> create(@RequestBody RequestAdGroup requestAdGroup) {
        AdGroupDto adGroup = iAdGroupService.create(requestAdGroup);
        return new ResponseEntity<AdGroupDto>(adGroup, HttpStatus.CREATED);
    }

}