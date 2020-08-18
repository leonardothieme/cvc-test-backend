package com.cvc.backend.service;

import com.cvc.backend.common.client.HotelsClient;
import com.cvc.backend.common.model.HotelsDetailsResponse;
import com.cvc.backend.model.response.HotelTotalAccommodationResponse;
import com.cvc.backend.model.response.RoomCategoryResponse;
import com.cvc.backend.model.response.RoomDetails;
import com.cvc.backend.model.response.RoomPriceDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Period;
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

    public HotelTotalAccommodationResponse totalAccommodation(final Long cityId,
                                                              final LocalDate checkInDate,
                                                              final LocalDate checkOutDate,
                                                              final Integer numberOfAdults,
                                                              final Integer numberOfChildren){


        final var hotelsDetails =   hotelsClient.findByHotelId(cityId).stream().findFirst().get();

        final var  days = ChronoUnit.DAYS.between(checkInDate,checkOutDate);

        final var hotels = hotelsDetails.getRooms().stream().findFirst().get();

        final var roomPrice = new RoomPriceDetails();

        final var category = new RoomCategoryResponse();
        final var roomDetails = new RoomDetails();
        roomDetails.setId(hotels.getRoomID());
        roomPrice.setPricePerDayChild(hotels.getPrice().getChild());



        final var totalPrice = hotels.getPrice().getAdult().multiply(new BigDecimal(days)).add(hotels.getPrice().getChild().multiply(new BigDecimal(days)));
        category.setName(hotels.getCategoryName());

        roomDetails.setTotalPrice(totalPrice);

        roomDetails.setCategory(category);
//        roomDetails.getPriceDetail().setPricePerDayAdult(hotels.getPrice().getAdult());
//        roomDetails.getPriceDetail().setPricePerDayChild(hotels.getPrice().getChild());



        final var  response = new HotelTotalAccommodationResponse();

        response.setCity(hotelsDetails.getCityName());
        response.setId(hotelsDetails.getId());
        response.setRooms(roomDetails);



        return response;
    }
}
