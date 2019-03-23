package com.spaceboost.challenge.framework.api;

import com.spaceboost.challenge.domain.keyword.IKeywordService;
import com.spaceboost.challenge.domain.keyword.KeywordDto;
import com.spaceboost.challenge.framework.api.request.RequestKeyword;
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
        final KeywordDto keyword = iKeywordService.create(requestKeyword);
        return new ResponseEntity<KeywordDto>(keyword, HttpStatus.CREATED);
    }

    /*
    FIXME: we need to check this endpoint , specification is not clear with current path
     */
    @GetMapping("/campaigns/{campaignId}/adGroups/{adGroupId}/keywords/{keywordId}")
    public ResponseEntity<KeywordDto> get(@PathVariable Long campaignId, @PathVariable Long adGroupId, @PathVariable Long keywordId) {
        final KeywordDto keyword = iKeywordService.getByIdByCampaignAndByGroup(campaignId, adGroupId, keywordId);
        return new ResponseEntity<KeywordDto>(keyword, keyword == null ? HttpStatus.NOT_FOUND : HttpStatus.OK);
    }

    @GetMapping("/keywords/getMostConverted")
    public ResponseEntity<KeywordDto> getMostConverted() {
        final KeywordDto keyword = iKeywordService.getMostConverted();
        return new ResponseEntity<KeywordDto>(keyword, keyword == null ? HttpStatus.NOT_FOUND : HttpStatus.OK);
    }

    @GetMapping("/keywords/getMostClicked")
    public ResponseEntity<KeywordDto> getMostClicked() {
        final KeywordDto keyword = iKeywordService.getMostClicked();
        return new ResponseEntity<KeywordDto>(keyword, HttpStatus.OK);
    }
}
