package com.cvc.backend.common.client;

import com.cvc.backend.common.model.HotelsDetailsResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Collection;

@FeignClient(name = "hotel-client", url = "${hotel.url}")
public interface HotelsClient {

    @GetMapping("${hotel.path.find-by-hotelId}")
    Collection<HotelsDetailsResponse> findByHotelId(@PathVariable("hotelId") Long hotelId);

    @GetMapping("${hotel.path.find-by-cityId}")
    Collection<HotelsDetailsResponse> findByCityId(@PathVariable("cityId") Long hotelId);
}