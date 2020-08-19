package com.cvc.backend.model.response;

import com.cvc.backend.common.model.HotelsDetailsResponse;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
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

    @ApiModelProperty(value = "Id to hotel", example = "1")
    private Long id;

    @ApiModelProperty(value = "City To Hotel", example = "Sao Paulo")
    private String city;

    @ApiModelProperty(value = "Rooms to hotel")
    private RoomDetails rooms;

    public HotelTotalAccommodationResponse (final HotelsDetailsResponse hotelsDetails, final RoomDetails roomDetails ) {
        id = hotelsDetails.getId();
        city = hotelsDetails.getCityName();
        rooms = roomDetails;
    }


}
