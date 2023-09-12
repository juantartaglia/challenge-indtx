package com.jmt.challengeindtx.domain.repository;

import com.jmt.challengeindtx.domain.Price;

import java.time.LocalDateTime;
import java.util.List;

public interface PriceRepository {
    List<Price> getPriceByProductIdAndDateAndBrand(Long productId, LocalDateTime applicationDate, long brandId);
}
