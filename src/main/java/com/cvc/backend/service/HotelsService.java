package com.cvc.backend.service;

import com.cvc.backend.common.client.HotelsClient;
import com.cvc.backend.common.model.HotelsDetailsResponse;
import com.cvc.backend.common.model.RoomsHotels;
import com.cvc.backend.exception.handler.DateAfterCheckoutException;
import com.cvc.backend.exception.handler.HotelNoContentException;
import com.cvc.backend.model.payload.HotelTotalAccommodationParamsPayload;
import com.cvc.backend.model.response.HotelTotalAccommodationResponse;
import com.cvc.backend.model.response.RoomCategoryResponse;
import com.cvc.backend.model.response.RoomDetails;
import com.cvc.backend.model.response.RoomPriceDetails;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.temporal.ChronoUnit;
import java.util.Collection;

@Service
@RequiredArgsConstructor
public class HotelsService {

    private final HotelsClient hotelsClient;

    public Collection<HotelsDetailsResponse> findByHotelId(final Long hotelId) {
       return hotelsClient.findByHotelId(hotelId);
    }

    public Collection<HotelsDetailsResponse> findByCityId(final Long cityId) {
        return hotelsClient.findByCityId(cityId);
    }

    public HotelTotalAccommodationResponse totalAccommodation(final HotelTotalAccommodationParamsPayload params){

        final var hotelsDetails = hotelsClient.findByHotelId(params.getCityId()).stream().findFirst().orElseThrow(HotelNoContentException::new);

        if (params.getCheckInDate().isAfter(params.getCheckOutDate())) {
            throw new DateAfterCheckoutException();
        }

        final var  days = ChronoUnit.DAYS.between(params.getCheckInDate(),params.getCheckOutDate());

        final var hotels = hotelsDetails.getRooms().stream().findFirst().get();

        final var category = mapperToRoomCategory(hotels);

        final var roomPrice = mapperToPriceHotels(hotels);

        final var totalPrice = hotels.getPrice().getAdult().multiply(new BigDecimal(days)).add(hotels.getPrice().getChild().multiply(new BigDecimal(days)));

        final var totalPriceComission = totalPrice.divide(new BigDecimal(0.70), RoundingMode.HALF_UP);

        final var roomDetails = mapperToRoomDetails(hotels, category, roomPrice, totalPrice.add(totalPriceComission));

        return new HotelTotalAccommodationResponse(hotelsDetails, roomDetails);
    }

    private RoomCategoryResponse mapperToRoomCategory(final RoomsHotels hotels) {
        return RoomCategoryResponse.builder().name(hotels.getCategoryName()).build();
    }

    private RoomPriceDetails mapperToPriceHotels(final RoomsHotels hotels) {
        return RoomPriceDetails.builder().pricePerDayAdult(hotels.getPrice().getAdult()).pricePerDayChild(hotels.getPrice().getChild()).build();

    }

    private RoomDetails mapperToRoomDetails(final RoomsHotels hotels, final RoomCategoryResponse category, final RoomPriceDetails roomPrice, final BigDecimal totalPrice) {
        return RoomDetails.builder().totalPrice(totalPrice).category(category).priceDetail(roomPrice).id(hotels.getRoomID()).build();
    }

}
