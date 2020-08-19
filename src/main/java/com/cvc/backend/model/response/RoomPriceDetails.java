package com.cvc.backend.model.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@ApiModel("Room price info")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class RoomPriceDetails {

    @ApiModelProperty(value = "Price day adult", example = "123.10")
    private BigDecimal pricePerDayAdult;

    @ApiModelProperty(value = "Price day children", example = "110.10")
    private BigDecimal pricePerDayChild;

}
