package com.jmt.challengeindtx.application.rest;

import com.jmt.challengeindtx.application.PriceMapperService;
import com.jmt.challengeindtx.application.response.GetAppliedPriceResponse;
import com.jmt.challengeindtx.domain.service.PriceService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/product")
public class PriceController {

    private final PriceService priceService;

    public PriceController(PriceService priceService) {
        this.priceService = priceService;
    }

    @GetMapping("/{product_id}/price")
    public GetAppliedPriceResponse getAppliedPriceResponse(@PathVariable("product_id") Long productId,
                                                           @RequestParam(name = "date_time") @Validated @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
                                                           LocalDateTime dateTime,
                                                           @RequestParam(name = "brand_id") @Validated Long brandId){
        return PriceMapperService.mapPriceToAppliedPriceResponse(priceService.getAppliedPriceByBrandAndDate(brandId, productId, dateTime));
    }
}
