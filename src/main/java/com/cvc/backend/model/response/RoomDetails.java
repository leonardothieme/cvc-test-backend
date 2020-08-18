package com.cvc.backend.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RoomDetails {

    private Long id;
    private RoomCategoryResponse category;
    private BigDecimal totalPrice;
    private RoomPriceDetails priceDetail;


}
