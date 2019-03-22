package com.spaceboost.challenge.framework.repository;

import com.spaceboost.challenge.domain.exception.DuplicatedKeyException;
import com.spaceboost.challenge.domain.exception.ObjectNotFoundException;
import com.spaceboost.challenge.domain.keyword.Keyword;
import com.spaceboost.challenge.framework.api.request.RequestKeyword;

import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class KeywordRepository implements IKeywordRepository {

    private List<Keyword> persistence = new ArrayList<>();

    private List<Keyword> getCopy() {
        return Collections.unmodifiableList(persistence);
    }

    private Boolean getSequenceNextValue(Long id) {
        return getCopy().stream().anyMatch(p -> id.equals(p.getId()));
    }

    @Override
    public Keyword find(Long id) {
        return getCopy().
                stream().
                filter(campain -> id.equals(campain.getId())).
                findFirst().
                get();
    }

    @Override
    public Keyword findByIdByCampaignAndByGroup(Long campaignId, Long adGroupId, Long keywordId) {
        return getCopy().stream().
                filter(p -> keywordId.equals(p.getId()) && adGroupId.equals(p.getAdGroupId()) && campaignId.equals(p.getCampaignId())).
                findFirst().
                orElseThrow(() -> new ObjectNotFoundException("keyword"));
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

        Comparator<CostAndConverssions> comparatorConversion = Comparator.comparing(CostAndConverssions::getCost);

        comparatorConversion = comparatorConversion.thenComparing(Comparator.comparing(CostAndConverssions::getConversions).reversed());

        final Collector<Keyword, CostAndConverssions, CostAndConverssions> collector = new KeywordCostAndConverssionsCollector();

        final List<CostAndConverssions> list = getCopy().stream().
                collect(
                        Collectors.groupingBy(
                                Keyword::getCampaignId, collector))
                .values()
                .stream()
                .sorted(comparatorConversion)
                .collect(Collectors.toList());

        final CostAndConverssions convert = list.get(list.size() - 1);

        return new Keyword().create(convert.id, convert.campaignId, convert.adGroupId, convert.clicks, convert.conversions, convert.cost);
    }
}
