package com.spaceboost.challenge.framework.repository.keyword;

import com.spaceboost.challenge.domain.keyword.Keyword;

import java.util.EnumSet;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;

public class KeywordCostAndConverssionsCollector implements Collector<Keyword, CostAndConversions, CostAndConversions>{
    @Override
    public Supplier<CostAndConversions> supplier() {
        return CostAndConversions::new;
    }

    @Override
    public BiConsumer<CostAndConversions, Keyword> accumulator() {
        return (costAndConversions, keyword) -> {
            costAndConversions.conversions += keyword.getConversions();
            costAndConversions.cost += keyword.getCost();
            costAndConversions.id = keyword.getId();
            costAndConversions.adGroupId = keyword.getAdGroupId();
            costAndConversions.clicks = keyword.getClicks();
            costAndConversions.campaignId = keyword.getCampaignId();
        };
    }

    @Override
    public BinaryOperator<CostAndConversions> combiner() {
        return (costAndConversions, costAndConversions1) -> {
            costAndConversions.conversions += costAndConversions1.conversions;
            costAndConversions.cost += costAndConversions1.cost;
            return costAndConversions;
        };
    }

    @Override
    public Function<CostAndConversions, CostAndConversions> finisher() {
        return Function.identity();
    }

    @Override
    public Set<Characteristics> characteristics() {
        return EnumSet.of(Collector.Characteristics.IDENTITY_FINISH);
    }
}
