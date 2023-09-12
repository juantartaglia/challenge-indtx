package com.jmt.challengeindtx.domain.service;

import com.jmt.challengeindtx.domain.Price;
import com.jmt.challengeindtx.domain.exception.ResourceNotFoundException;
import com.jmt.challengeindtx.domain.repository.PriceRepository;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Comparator;

@Component
public class DomainPriceService implements PriceService{

    private final PriceRepository priceRepository;

    public DomainPriceService(PriceRepository priceRepository) {
        this.priceRepository = priceRepository;
    }

    @Override
    public Price getAppliedPriceByBrandAndDate(Long brandId, Long productId, LocalDateTime date) {
        return priceRepository.getPriceByProductIdAndDateAndBrand(productId, date, brandId)
                .stream()
                .max(Comparator.comparing(Price::getPriority))
                .orElseThrow(() -> new ResourceNotFoundException("Record not found"));

    }
}
