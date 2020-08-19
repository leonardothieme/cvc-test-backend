package com.cvc.backend.model.payload;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(description = "Hotel Total Accomodation Response")
@Builder
public class HotelTotalAccommodationParamsPayload {

    @ApiModelProperty(value = "Id to city", example = "1", required = true)
    @NotNull(message = "{HotelTotalAccomodationParamsPayload.cityId.NotNull}")
    private Long cityId;

    @ApiModelProperty(value = "Date checkin to accomodations", example = "04-01-2020", required = true)
    @NotNull(message = "{HotelTotalAccomodationParamsPayload.checkInDate.NotNull}")
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private LocalDate checkInDate;

    @ApiModelProperty(value = "Date checkout to accomodations", example = "09-01-2020", required = true)
    @NotNull(message = "{HotelTotalAccomodationParamsPayload.checkOutDate.NotNull}")
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private LocalDate checkOutDate;

    @ApiModelProperty(value = "Number of adults to accomodations", example = "2", required = true)
    @NotNull(message = "{HotelTotalAccomodationParamsPayload.numberOfAdults.NotNull}")
    private Integer numberOfAdults;

    @ApiModelProperty(value = "Number of children to accomodations", example = "1", required = true)
    @NotNull(message = "{HotelTotalAccomodationParamsPayload.numberOfChildren.NotNull}")
    private Integer numberOfChildren;
}
