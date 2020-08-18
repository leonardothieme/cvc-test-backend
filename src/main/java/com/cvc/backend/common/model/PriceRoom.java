package com.cvc.backend.common.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.math.BigDecimal;

@ApiModel("Room price info")
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class PriceRoom {

    private BigDecimal adult;
    private BigDecimal child;
}
