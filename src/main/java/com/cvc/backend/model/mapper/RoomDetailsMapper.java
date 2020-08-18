package com.cvc.backend.model.mapper;

import com.cvc.backend.common.model.PriceRoom;
import com.cvc.backend.common.model.RoomsHotels;
import com.cvc.backend.model.response.RoomCategoryResponse;
import com.cvc.backend.model.response.RoomDetails;
import com.cvc.backend.model.response.RoomPriceDetails;

import java.math.BigDecimal;

public final class RoomDetailsMapper {

    public static RoomDetails toResponse(final RoomsHotels response, final BigDecimal totalPrice){
        return RoomDetails.builder().id(response.getRoomID()).totalPrice(totalPrice).category(toRoomCategory(response)).priceDetail(toRoomPriceDetails(response)).build();
    }

    public static RoomCategoryResponse toRoomCategory(final RoomsHotels response){
        return RoomCategoryResponse.builder().name(response.getCategoryName()).build();
    }

    public static RoomPriceDetails toRoomPriceDetails(final RoomsHotels response){
        return RoomPriceDetails.builder().pricePerDayAdult(response.getPrice().getAdult()).
                pricePerDayChild(response.getPrice().getChild()).build();
    }
}
