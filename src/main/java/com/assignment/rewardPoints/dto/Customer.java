package com.assignment.rewardPoints.dto;

import com.assignment.rewardPoints.constants.AppConstants;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Customer {

    @ApiModelProperty(notes = "id", example = "123456")
    @JsonProperty(value = "id", required = true)
    @NotNull(message = AppConstants.CUSTOMER_ID_NULL)
    @Pattern(regexp = "^\\d{6}$",message = AppConstants.INVALID_CUSTOMER_ID)
    private String id;

    @ApiModelProperty(notes = "id", example = "John")
    @JsonProperty(value = "name")
    private String name;

    @NotNull(message  = AppConstants.INVALID_TRANSACTION_DETAILS)
    private Transaction transactions;

}
