package com.assignment.rewardPoints.dto;

import com.assignment.rewardPoints.constants.AppConstants;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Transaction {

    @ApiModelProperty(notes = "amount in dollars", example = "300")
    @JsonProperty(value = "amount", required = true)
    @NotNull(message = AppConstants.INVALID_TRANSACTION_AMOUNT)
    private double amount;

    @ApiModelProperty(notes = "date", example = "9999-12-31")
    @JsonProperty(value = "date", required = true)
    @NotNull(message = AppConstants.INVALID_TRANSACTION_DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date date;
}
