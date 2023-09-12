package com.jmt.challengeindtx.rest;

import com.jmt.challengeindtx.domain.Price;
import com.jmt.challengeindtx.domain.repository.PriceRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
class PriceControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PriceRepository priceRepository;


    @Test
    @DisplayName("whenPriceNotFoundForDateIn10JanuaryAt10AM_controllerReturns404")
    void whenPriceNotFoundForDateIn10JanuaryAt10AM_controllerReturns404() throws Exception {

        when(priceRepository.getPriceByProductIdAndDateAndBrand(35455L,LocalDateTime.parse("2021-01-10T10:00:00"),1))
                .thenReturn(List.of());

        String url = String.format("/product/%s/price",35455L);
        mockMvc.perform(MockMvcRequestBuilders.get(url)
                        .param("date_time", "2021-01-10T10:00:00")
                        .param("brand_id", "1"))
                .andExpect(status().isNotFound());
    }

    @Test
    @DisplayName("whenPriceNotFoundForProductId35456_controllerReturns404")
    void whenPriceNotFoundForProductId35456_controllerReturns404() throws Exception {

        when(priceRepository.getPriceByProductIdAndDateAndBrand(35456L,LocalDateTime.parse("2020-06-14T21:00:00"),1))
                .thenReturn(List.of());

        String url = String.format("/product/%s/price",35456L);
        mockMvc.perform(MockMvcRequestBuilders.get(url)
                        .param("date_time", "2020-06-14T21:00:00")
                        .param("brand_id", "1"))
                .andExpect(status().isNotFound());
    }



    @Test
    @DisplayName("whenRequestWithoutDateTimeValue_controllerReturns400")
    void whenRequestWithoutDateTimeValue_controllerReturns404() throws Exception {

        when(priceRepository.getPriceByProductIdAndDateAndBrand(35456L,LocalDateTime.parse("2020-06-14T21:00:00"),1))
                .thenReturn(List.of());

        String url = String.format("/product/%s/price",35456L);
        mockMvc.perform(MockMvcRequestBuilders.get(url)
                .param("brand_id", "1"))
                .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("whenRequestWithoutBrandIdValue_controllerReturns400")
    void whenRequestWithoutBrandIdValue_controllerReturns400() throws Exception {

        when(priceRepository.getPriceByProductIdAndDateAndBrand(35456L,LocalDateTime.parse("2020-06-14T21:00:00"),1))
                .thenReturn(List.of());

        String url = String.format("/product/%s/price",35456L);
        mockMvc.perform(MockMvcRequestBuilders.get(url)
                .param("date_time", "2020-06-14T21:00:00"))
                .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("whenRequestWithoutDateTimeAndBrandIdValue_controllerReturns400")
    void whenRequestWithoutDateTimeAndBrandIdValue_controllerReturns400() throws Exception {

        when(priceRepository.getPriceByProductIdAndDateAndBrand(35456L,LocalDateTime.parse("2020-06-14T21:00:00"),1))
                .thenReturn(List.of());

        mockMvc.perform(MockMvcRequestBuilders.get("/product/35456/price"))
                .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("whenRequestDateIsJune14At10AM_thenControllerReturnPriceList1_TestBusinessCase_1")
    void whenRequestDateIsJune14At10AM_thenControllerReturnPriceList1() throws Exception {

        Price price = new Price(0L,1,LocalDateTime.parse("2020-06-14T00:00:00"),LocalDateTime.parse("2020-12-31T23:59:59"),1L,35455L,1, BigDecimal.valueOf(35.50), "EUR");

        when(priceRepository.getPriceByProductIdAndDateAndBrand(35455L,LocalDateTime.parse("2020-06-14T10:00:00"),1))
        .thenReturn(List.of(price));

        String url = String.format("/product/%s/price",price.getProductId());
        mockMvc.perform(MockMvcRequestBuilders.get(url)
                        .param("date_time", "2020-06-14T10:00:00")
                        .param("brand_id", "1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.productId").value(35455))
                .andExpect(jsonPath("$.brandId").value(1))
                .andExpect(jsonPath("$.priceListId").value(1))
                .andExpect(jsonPath("$.dateRange.startDate").value("2020-06-14T00:00:00"))
                .andExpect(jsonPath("$.dateRange.endDate").value("2020-12-31T23:59:59"))
                .andExpect(jsonPath("$.price").value(35.50));
    }

    @Test
    @DisplayName("whenRequestDateIsJune14At4PM_thenControllerReturnPriceList2_TestBusinessCase_2")
    void whenRequestDateIsJune14At4PM_thenControllerReturnPriceList2() throws Exception {

        Price price0 = new Price(0L,1,LocalDateTime.parse("2020-06-14T00:00:00"),LocalDateTime.parse("2020-12-31T23:59:59"),1L,35455L,0,BigDecimal.valueOf(35.50), "EUR");
        Price price1 = new Price(1L,1,LocalDateTime.parse("2020-06-14T15:00:00"),LocalDateTime.parse("2020-06-14T18:30:00"),2L,35455L,1,BigDecimal.valueOf(25.45), "EUR");
        List<Price> priceList = List.of(price0, price1);

        when(priceRepository.getPriceByProductIdAndDateAndBrand(35455L,LocalDateTime.parse("2020-06-14T10:00:00"),1L))
                .thenReturn((List<Price>) priceList);

        String url = String.format("/product/%s/price",price0.getProductId());
        mockMvc.perform(MockMvcRequestBuilders.get(url)
                        .param("date_time", "2020-06-14T10:00:00")
                        .param("brand_id", "1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.productId").value(35455))
                .andExpect(jsonPath("$.brandId").value(1))
                .andExpect(jsonPath("$.priceListId").value(2))
                .andExpect(jsonPath("$.dateRange.startDate").value("2020-06-14T15:00:00"))
                .andExpect(jsonPath("$.dateRange.endDate").value("2020-06-14T18:30:00"))
                .andExpect(jsonPath("$.price").value(25.45));
    }

    @Test
    @DisplayName("whenRequestDateIsJune14At9PM_thenControllerReturnPriceList1_TestBusinessCase_3")
    void whenRequestDateIsJune14At9PM_thenControllerReturnPriceList1() throws Exception {

        Price price = new Price(0L,1,LocalDateTime.parse("2020-06-14T00:00:00"),LocalDateTime.parse("2020-12-31T23:59:59"),1L,35455L,1, BigDecimal.valueOf(35.50), "EUR");

        when(priceRepository.getPriceByProductIdAndDateAndBrand(35455L,LocalDateTime.parse("2020-06-14T21:00:00"),1L))
                .thenReturn(List.of(price));

        String url = String.format("/product/%s/price",price.getProductId());
        mockMvc.perform(MockMvcRequestBuilders.get(url)
                        .param("date_time", "2020-06-14T21:00:00")
                        .param("brand_id", "1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.productId").value(35455))
                .andExpect(jsonPath("$.brandId").value(1))
                .andExpect(jsonPath("$.priceListId").value(1))
                .andExpect(jsonPath("$.dateRange.startDate").value("2020-06-14T00:00:00"))
                .andExpect(jsonPath("$.dateRange.endDate").value("2020-12-31T23:59:59"))
                .andExpect(jsonPath("$.price").value(35.50));
    }

    @Test
    @DisplayName("whenRequestDateIsJune15At10AM_thenControllerReturnPriceList3_TestBusinessCase_4")
    void whenRequestDateIsJune15At10AM_thenControllerReturnPriceList3() throws Exception {

        Price price = new Price(2L,1,LocalDateTime.parse("2020-06-15T00:00:00"),LocalDateTime.parse("2020-06-15T11:00:00"),3L,35455L,1,BigDecimal.valueOf(30.50), "EUR");

        when(priceRepository.getPriceByProductIdAndDateAndBrand(35455L,LocalDateTime.parse("2020-06-15T10:00:00"),1L))
                .thenReturn(List.of(price));

        String url = String.format("/product/%s/price",price.getProductId());
        mockMvc.perform(MockMvcRequestBuilders.get(url)
                        .param("date_time", "2020-06-15T10:00:00")
                        .param("brand_id", "1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.productId").value(35455))
                .andExpect(jsonPath("$.brandId").value(1))
                .andExpect(jsonPath("$.priceListId").value(3))
                .andExpect(jsonPath("$.dateRange.startDate").value("2020-06-15T00:00:00"))
                .andExpect(jsonPath("$.dateRange.endDate").value("2020-06-15T11:00:00"))
                .andExpect(jsonPath("$.price").value(30.50));
    }

    @Test
    @DisplayName("whenRequestDateIsJune16At9PM_thenControllerReturnPriceList4_TestBusinessCase_5")
    void whenRequestDateIsJune16At9PM_thenControllerReturnPriceList4() throws Exception {
        Integer productId = 35455;
        Price price0 = new Price(0L,1,LocalDateTime.parse("2020-06-14T00:00:00"),LocalDateTime.parse("2020-12-31T23:59:59"),1L,productId.longValue(),0,BigDecimal.valueOf(35.50), "EUR");
        Price price1 = new Price(3L,1,LocalDateTime.parse("2020-06-15T16:00:00"),LocalDateTime.parse("2020-12-31T23:59:59"),4L,productId.longValue(),1,BigDecimal.valueOf(38.95), "EUR");

        when(priceRepository.getPriceByProductIdAndDateAndBrand(35455L,LocalDateTime.parse("2020-06-16T21:00:00"),1L))
                .thenReturn(List.of(price0,price1));

        String url = String.format("/product/%s/price",productId);
        mockMvc.perform(MockMvcRequestBuilders.get(url)
                        .param("date_time", "2020-06-16T21:00:00")
                        .param("brand_id", "1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.productId").value(35455))
                .andExpect(jsonPath("$.brandId").value(1))
                .andExpect(jsonPath("$.priceListId").value(4))
                .andExpect(jsonPath("$.dateRange.startDate").value("2020-06-15T16:00:00"))
                .andExpect(jsonPath("$.dateRange.endDate").value("2020-12-31T23:59:59"))
                .andExpect(jsonPath("$.price").value(38.95));
    }

}
