package com.cvc.backend.model.response;

import com.cvc.backend.common.model.HotelsDetailsResponse;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Collection;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(description = "Company associated to a Token")
@Builder
public class HotelTotalAccommodationResponse {

    private Long id;
    private String city;
    private RoomDetails rooms;


}
