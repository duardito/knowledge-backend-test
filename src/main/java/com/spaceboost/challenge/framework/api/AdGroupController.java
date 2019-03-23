package com.spaceboost.challenge.framework.api;

import com.spaceboost.challenge.domain.adgroup.AdGroupDto;
import com.spaceboost.challenge.domain.adgroup.IAdGroupService;
import com.spaceboost.challenge.framework.api.request.RequestAdGroup;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class AdGroupController {

    private final IAdGroupService iAdGroupService;

    public AdGroupController(IAdGroupService iAdGroupService) {
        this.iAdGroupService = iAdGroupService;
    }

    @GetMapping("/adGroup/{id}")
    public ResponseEntity<AdGroupDto> get(@PathVariable Long id) {
        final AdGroupDto adGroup = iAdGroupService.getBy(id);
        return new ResponseEntity<AdGroupDto>(adGroup, adGroup == null ? HttpStatus.NOT_FOUND : HttpStatus.OK);
    }

    @PostMapping("/adGroup/")
    public ResponseEntity<AdGroupDto> create(@Valid @RequestBody RequestAdGroup requestAdGroup) {
        final AdGroupDto adGroup = iAdGroupService.create(requestAdGroup);
        return new ResponseEntity<AdGroupDto>(adGroup, HttpStatus.CREATED);
    }

}
