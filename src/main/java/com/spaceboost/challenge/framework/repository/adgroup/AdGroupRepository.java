package com.spaceboost.challenge.framework.repository.adgroup;

import com.spaceboost.challenge.domain.adgroup.AdGroup;
import com.spaceboost.challenge.domain.exception.DuplicatedKeyException;
import com.spaceboost.challenge.domain.exception.ObjectNotFoundException;
import com.spaceboost.challenge.framework.api.request.RequestAdGroup;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class AdGroupRepository implements IAdGroupRepository {

    private List<AdGroup> persistence = new ArrayList<>();

    private List<AdGroup> getCopy() {
        return Collections.unmodifiableList(persistence);
    }

    private Boolean getSequenceNextValue(Long id) {
        return getCopy().stream().filter(p -> p.getId().equals(id)).findFirst().isPresent();
    }

    @Override
    public AdGroup find(Long id) {
        return getCopy().
                stream().
                filter(campain -> campain.getId().equals(id)).
                findFirst().
                orElseThrow(() -> new ObjectNotFoundException("AdGroup doesn't exist"));
    }

    @Override
    public AdGroup create(RequestAdGroup request) {
        if (getSequenceNextValue(request.getId())) {
            throw new DuplicatedKeyException("Duplicated key");
        }

        AdGroup adGroup = new AdGroup().
                create(request.getId(), request.getCampaignId(), request.getClicks(), request.getConversions(), request.getCost());
        persistence.add(adGroup);
        return adGroup;
    }

    @Override
    public AdGroup addKeywords(Long id, Long keywordId) {
        AdGroup adGroup = find(id);
        adGroup.add(keywordId);
        return adGroup;
    }

    @Override
    public AdGroup findMostCostLessConverted() {

        Comparator<CostAndConversions> comparatorConversion = Comparator.comparing(CostAndConversions::getCost);

        comparatorConversion = comparatorConversion.thenComparing(Comparator.comparing(CostAndConversions::getConversions).reversed());

        final Collector<AdGroup, CostAndConversions, CostAndConversions> collector = new AdGroupCostAndConversionsCollector();

        final List<CostAndConversions> list = getCopy().stream().
                collect(
                        Collectors.groupingBy(
                                AdGroup::getCampaignId, collector))
                .values()
                .stream()
                .sorted(comparatorConversion)
                .collect(Collectors.toList());

        final CostAndConversions convert = list.get(list.size() - 1);

        return new AdGroup().create(convert.id, convert.campaignId, convert.clicks, convert.conversions, convert.cost);
    }
}
