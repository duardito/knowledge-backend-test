package com.spaceboost.challenge.framework.api;

import com.spaceboost.challenge.domain.keyword.IKeywordService;
import com.spaceboost.challenge.domain.keyword.KeywordDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class KeywordController {

    private final IKeywordService iKeywordService;

    public KeywordController(IKeywordService iKeywordService) {
        this.iKeywordService = iKeywordService;
    }

    @PostMapping("/keywords")
    public ResponseEntity<KeywordDto> create(@RequestBody RequestKeyword requestKeyword) {
        KeywordDto keyword = iKeywordService.create(requestKeyword);
        return new ResponseEntity<KeywordDto>(keyword, HttpStatus.CREATED);
    }

    @GetMapping("/campaigns/{campaignId}/adGroups/{adGroupId}/keywords/{keywordId}")
    public ResponseEntity<KeywordDto> get(@PathVariable Long campaignId, @PathVariable Long adGroupId, @PathVariable Long keywordId) {
        KeywordDto keyword = iKeywordService.getByIdByCampaignAndByGroup(campaignId, adGroupId, keywordId);
        return new ResponseEntity<KeywordDto>(keyword, keyword == null ? HttpStatus.NOT_FOUND: HttpStatus.OK);
    }
}
