package com.cvc.backend.model.response;

import io.swagger.annotations.ApiModelProperty;
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

    @ApiModelProperty(value = "Room to hotel", example = "1")
    private Long id;

    @ApiModelProperty(value = "Category to room", example = "ELITE")
    private RoomCategoryResponse category;

    @ApiModelProperty(value = "Total price payment", example = "1209.10")
    private BigDecimal totalPrice;

    @ApiModelProperty(value = "Price Detail")
    private RoomPriceDetails priceDetail;


}
