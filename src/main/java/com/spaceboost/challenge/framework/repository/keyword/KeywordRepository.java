package com.spaceboost.challenge.framework.repository.keyword;

import com.spaceboost.challenge.domain.exception.DuplicatedKeyException;
import com.spaceboost.challenge.domain.keyword.Keyword;
import com.spaceboost.challenge.framework.api.request.RequestKeyword;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class KeywordRepository implements IKeywordRepository {

    private List<Keyword> persistence = new ArrayList<>();

    private List<Keyword> getCopy() {
        return Collections.unmodifiableList(persistence);
    }

    private Boolean getSequenceNextValue(Long id) {
        return getCopy().stream().filter(p -> id.equals(p.getId())).findFirst().isPresent();
    }


    @Override
    public Keyword findByIdByCampaignAndByGroup(Long campaignId, Long adGroupId, Long keywordId) {
        return getCopy().stream().
                filter(p -> keywordId.equals(p.getId()) && adGroupId.equals(p.getAdGroupId()) && campaignId.equals(p.getCampaignId())).
                findFirst().
                orElse(null);
    }

    @Override
    public Keyword create(RequestKeyword request) {
        if (getSequenceNextValue(request.getId())) {
            throw new DuplicatedKeyException(request.getId().toString());
        }

        Keyword keyword = new Keyword().
                create(request.getId(), request.getCampaignId(), request.getAdGroupId(), request.getClicks(), request.getConversions(), request.getCost());
        persistence.add(keyword);
        return keyword;
    }

    @Override
    public Keyword findMostConverted() {
        Comparator<Keyword> comparator = Comparator.comparing(Keyword::getConversions);
        return getCopy().stream().max(comparator).get();
    }

    @Override
    public Keyword findMostClicked() {
        Comparator<Keyword> comparator = Comparator.comparing(Keyword::getClicks);
        return getCopy().stream().max(comparator).get();
    }


    @Override
    public Keyword findMostCostLessConverted() {

        Comparator<CostAndConversions> comparatorConversion = Comparator.comparing(CostAndConversions::getCost);

        comparatorConversion = comparatorConversion.
                thenComparing(Comparator.comparing(CostAndConversions::getConversions).
                        reversed()).reversed();

        final Collector<Keyword, CostAndConversions, CostAndConversions> collector = new KeywordCostAndConversionsCollector();

        final CostAndConversions convert = getCopy().stream().
                collect(
                        Collectors.groupingBy(
                                Keyword::getCampaignId, collector))
                .values()
                .stream()
                .sorted(comparatorConversion)
                .findFirst()
                .get();

        return new Keyword().create(convert.id, convert.campaignId, convert.adGroupId, convert.clicks, convert.conversions, convert.cost);
    }
}
