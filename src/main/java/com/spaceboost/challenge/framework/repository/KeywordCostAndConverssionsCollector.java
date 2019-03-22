package com.spaceboost.challenge.framework.repository;

import com.spaceboost.challenge.domain.keyword.Keyword;

import java.util.EnumSet;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;

public class KeywordCostAndConverssionsCollector implements Collector<Keyword,CostAndConverssions,CostAndConverssions>{
    @Override
    public Supplier<CostAndConverssions> supplier() {
        return CostAndConverssions::new;
    }

    @Override
    public BiConsumer<CostAndConverssions, Keyword> accumulator() {
        return (costAndConverssions, keyword) -> {
            costAndConverssions.conversions += keyword.getConversions();
            costAndConverssions.cost += keyword.getCost();
            costAndConverssions.id = keyword.getId();
            costAndConverssions.adGroupId = keyword.getAdGroupId();
            costAndConverssions.clicks = keyword.getClicks();
            costAndConverssions.campaignId = keyword.getCampaignId();
        };
    }

    @Override
    public BinaryOperator<CostAndConverssions> combiner() {
        return (costAndConverssions, costAndConverssions1) -> {
            costAndConverssions.conversions += costAndConverssions1.conversions;
            costAndConverssions.cost += costAndConverssions1.cost;
            return costAndConverssions;
        };
    }

    @Override
    public Function<CostAndConverssions, CostAndConverssions> finisher() {
        return Function.identity();
    }

    @Override
    public Set<Characteristics> characteristics() {
        return EnumSet.of(Collector.Characteristics.IDENTITY_FINISH);
    }
}
