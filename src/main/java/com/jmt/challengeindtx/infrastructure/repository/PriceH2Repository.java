package com.jmt.challengeindtx.infrastructure.repository;

import com.jmt.challengeindtx.domain.Price;
import com.jmt.challengeindtx.domain.repository.PriceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class PriceH2Repository implements PriceRepository {

    private final SpringDataH2PriceRepository repository;

    @Autowired
    public PriceH2Repository(SpringDataH2PriceRepository repository) {
        this.repository = repository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Price> getPriceByProductIdAndDateAndBrand(Long productId, LocalDateTime applicationDate, long brandId) {

        return repository.findByProductIdAndBrandIdAndDate(productId, brandId, applicationDate).stream().map(PriceEntityMapperService::mapPriceEntityToPrice).collect(Collectors.toList());
    }
}
