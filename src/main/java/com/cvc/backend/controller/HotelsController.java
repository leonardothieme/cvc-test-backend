package com.cvc.backend.controller;

import com.cvc.backend.api.HotelsApi;
import com.cvc.backend.common.model.HotelsDetailsResponse;
import com.cvc.backend.model.HotelTotalAccommodationParamsPayload;
import com.cvc.backend.model.response.HotelTotalAccommodationResponse;
import com.cvc.backend.service.HotelsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collection;

import static org.springframework.http.HttpStatus.OK;

@CrossOrigin
@RestController
@RequestMapping("/hotels")
@RequiredArgsConstructor
public class HotelsController implements HotelsApi {

    private final HotelsService service;

    @GetMapping("/hotel/{hotelId}")
    @Override
    @ResponseStatus(code = OK)
    public ResponseEntity<Collection<HotelsDetailsResponse>> findByHotelId(@PathVariable("hotelId") final Long hotelId) {
        final var response = service.findByHotelId(hotelId);

        return response.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(response);
    }

    @GetMapping("/city/{cityId}")
    @ResponseStatus(code = OK)
    public ResponseEntity<Collection<HotelsDetailsResponse>> findByCityId(@PathVariable("cityId") final Long cityId) {
        final var response = service.findByCityId(cityId);

        return response.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(response);
    }



    @GetMapping("/city/total")
    public HotelTotalAccommodationResponse totalAccommodation(@Valid HotelTotalAccommodationParamsPayload params) {

       return service.totalAccommodation(params);

    }
}
