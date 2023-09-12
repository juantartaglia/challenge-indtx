package com.jmt.challengeindtx.application.response;

import java.math.BigDecimal;

public class GetAppliedPriceResponse {


    private Long productId;
    private long brandId;
    private long priceListId;

    private DateRange dateRange;

    private BigDecimal price;

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public long getBrandId() {
        return brandId;
    }

    public void setBrandId(long brandId) {
        this.brandId = brandId;
    }

    public long getPriceListId() {
        return priceListId;
    }

    public void setPriceListId(long priceListId) {
        this.priceListId = priceListId;
    }

    public DateRange getDateRange() {
        return dateRange;
    }

    public void setDateRange(DateRange dateRange) {
        this.dateRange = dateRange;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
