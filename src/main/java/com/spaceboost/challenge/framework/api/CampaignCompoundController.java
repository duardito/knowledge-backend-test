package com.spaceboost.challenge.framework.api;

import com.spaceboost.challenge.domain.compound.IKeywordAdGroupService;
import com.spaceboost.challenge.domain.compound.KeywordAdGroupDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CampaignCompoundController {

    private final IKeywordAdGroupService iKeywordAdGroupService;

    public CampaignCompoundController(IKeywordAdGroupService iKeywordAdGroupService) {
        this.iKeywordAdGroupService = iKeywordAdGroupService;
    }

    @GetMapping(value = "/campaigns/mostCostLessConversions")
    public ResponseEntity<KeywordAdGroupDto> getCampaignWithMostCostAndLessConversions() {
        KeywordAdGroupDto mostAndLess = iKeywordAdGroupService.getMostCostLessConverted();
        return new ResponseEntity<KeywordAdGroupDto>(mostAndLess, HttpStatus.OK);
    }
}
