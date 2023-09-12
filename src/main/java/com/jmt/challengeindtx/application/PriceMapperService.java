package com.jmt.challengeindtx.application;

import com.jmt.challengeindtx.application.response.DateRange;
import com.jmt.challengeindtx.application.response.GetAppliedPriceResponse;
import com.jmt.challengeindtx.domain.Price;


public abstract class PriceMapperService {

    public static GetAppliedPriceResponse mapPriceToAppliedPriceResponse(Price price){
        GetAppliedPriceResponse getAppliedPriceResponse = new GetAppliedPriceResponse();
        getAppliedPriceResponse.setProductId(price.getProductId());
        getAppliedPriceResponse.setBrandId(price.getBrandId());
        getAppliedPriceResponse.setPrice(price.getPrice());
        getAppliedPriceResponse.setPriceListId(price.getPriceListId());
        getAppliedPriceResponse.setDateRange(new DateRange(price.getStartDate(), price.getEndDate()));
        return getAppliedPriceResponse;
    }
}
