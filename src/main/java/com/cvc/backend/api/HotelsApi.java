package com.cvc.backend.api;

import com.cvc.backend.common.model.ErrorResponse;
import com.cvc.backend.common.model.HotelsDetailsResponse;
import com.cvc.backend.config.pageable.ApiPageable;
import com.cvc.backend.model.response.HotelTotalAccommodationResponse;
import io.swagger.annotations.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;
import springfox.documentation.annotations.ApiIgnore;


import java.time.LocalDate;
import java.util.Collection;

@Api(tags = "Hotels")
public interface HotelsApi {

    @ApiOperation(value = "Find Hotel by Id")
    @ApiResponses({@ApiResponse(code = 200, message = "Find hotel with success"),
            @ApiResponse(code = 204, message = "No hotel was found", response = Void.class),
            @ApiResponse(code = 400, message = "Wrong payload parameters", response = ErrorResponse.class),
            @ApiResponse(code = 500, message = "Unexpected error", response = ErrorResponse.class)})
    ResponseEntity<Collection<HotelsDetailsResponse>> findByHotelId(@ApiParam(value = "Hotel id to search", example = "1", required = true) final Long cityId);

    @ApiOperation(value = "Find all existent tokens")
    @ApiResponses({@ApiResponse(code = 200, message = "Tokens found with success"),
            @ApiResponse(code = 204, message = "Can't find any token"),
            @ApiResponse(code = 400, message = "Invalid information was sent", response = ErrorResponse.class),
            @ApiResponse(code = 500, message = "Unexpected error ocurred", response = ErrorResponse.class)})
    ResponseEntity<Collection<HotelsDetailsResponse>>findByCityId(@ApiParam(value = "cityId") final Long cityId);

    @ApiOperation(value = "Find all existent tokens")
    @ApiResponses({@ApiResponse(code = 200, message = "Tokens found with success"),
            @ApiResponse(code = 204, message = "Can't find any token"),
            @ApiResponse(code = 400, message = "Invalid information was sent", response = ErrorResponse.class),
            @ApiResponse(code = 500, message = "Unexpected error ocurred", response = ErrorResponse.class)})
    HotelTotalAccommodationResponse totalAccommodation(@ApiParam(value = "cityId") final Long cityId,
                                                       @ApiParam(value = "checkInDate") @DateTimeFormat(pattern = "dd-MM-yyyy") final LocalDate checkInDate,
                                                       @ApiParam(value = "checkOutDate") @DateTimeFormat(pattern = "dd-MM-yyyy") final LocalDate checkOutDate,
                                                       @ApiParam(value = "numberOfAdults") final Integer numberOfAdults,
                                                       @ApiParam(value = "numberOfChildren") final Integer numberOfChildren );
}
