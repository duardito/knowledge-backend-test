package com.spaceboost.challenge.domain.compound;

import com.spaceboost.challenge.domain.adgroup.AdGroupDto;
import com.spaceboost.challenge.domain.adgroup.IAdGroupService;
import com.spaceboost.challenge.domain.keyword.IKeywordService;
import com.spaceboost.challenge.domain.keyword.KeywordDto;

public class KeywordAdGroupService implements IKeywordAdGroupService{

    private final IKeywordService iKeywordService;
    private final IAdGroupService iAdGroupService;

    public KeywordAdGroupService(IKeywordService iKeywordService, IAdGroupService iAdGroupService) {
        this.iKeywordService = iKeywordService;
        this.iAdGroupService = iAdGroupService;
    }

    @Override
    public KeywordAdgroupDto getMostCostLessConverted(){
        KeywordDto keyword = iKeywordService.getMostCostLessConverted();
        AdGroupDto adGroup = iAdGroupService.getMostCostLessConverted();
        return new KeywordAdgroupDto(keyword,adGroup);
    }


}
