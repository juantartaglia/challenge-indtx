package com.jmt.challengeindtx.domain.service;

import com.jmt.challengeindtx.domain.Price;

import java.time.LocalDateTime;

public interface PriceService {
    Price getAppliedPriceByBrandAndDate(Long brandId, Long productId, LocalDateTime date);
}
