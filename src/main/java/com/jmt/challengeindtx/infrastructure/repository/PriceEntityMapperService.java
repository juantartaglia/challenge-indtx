package com.jmt.challengeindtx.infrastructure.repository;

import com.jmt.challengeindtx.domain.Price;

public abstract class PriceEntityMapperService {
    public static Price mapPriceEntityToPrice(PriceEntity priceEntity){
        Price price = new Price();
        price.setPrice(priceEntity.getPrice());
        price.setCurr(priceEntity.getCurr().getValue());
        price.setId(priceEntity.getId());
        price.setPriority(priceEntity.getPriority());
        price.setProductId(priceEntity.getProductId());
        price.setEndDate(priceEntity.getEndDate());
        price.setStartDate(priceEntity.getStartDate());
        price.setPriceListId(priceEntity.getPriceListId());
        price.setBrandId(priceEntity.getBrandId());

        return price;
    }
}
