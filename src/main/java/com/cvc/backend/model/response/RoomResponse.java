package com.cvc.backend.model.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.util.Collection;

@ApiModel("Room price info")
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class RoomResponse {

    private Collection<RoomDetails> rooms;
}
