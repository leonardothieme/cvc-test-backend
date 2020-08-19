package com.cvc.backend.common.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModel;
import lombok.Builder;
import lombok.Data;

@ApiModel("Room detail info")
@Data
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class RoomsHotels {

    private Long roomID;
    private String categoryName;
    private PriceRoom price;
}
