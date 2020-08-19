package com.cvc.backend.common.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

import java.util.Collection;

@ApiModel("Hotels detail info")
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@Builder
public class HotelsDetailsResponse {

    private Long id;
    private String name;
    private Integer cityCode;
    private String cityName;
    private Collection <RoomsHotels> rooms;
}
