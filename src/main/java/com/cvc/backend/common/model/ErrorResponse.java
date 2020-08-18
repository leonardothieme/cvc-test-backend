package com.cvc.backend.common.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@ApiModel(description = "Represents all error responses in service.")
public class ErrorResponse implements Serializable {

    private static final long serialVersionUID = -6431868610599519417L;

    @ApiModelProperty("An important information about this error (Ex. field name).")
    private String error;
    @ApiModelProperty("Business Error code, that vary due to the context.")
    private String errorCode;
    @ApiModelProperty("A basic description about what happened.")
    private String errorDescription;

    @ApiModelProperty("An inner body, if exists.")

    public ErrorResponse code(final String code) {
        this.errorCode = code;
        return this;
    }

    public ErrorResponse tag(final String tag) {
        this.error = tag;
        return this;
    }

    public ErrorResponse description(final String description) {
        this.errorDescription = description;
        return this;
    }

    public static ErrorResponse as(final String description) {
        return new ErrorResponse().description(description);
    }
}

