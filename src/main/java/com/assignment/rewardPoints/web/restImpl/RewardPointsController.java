package com.assignment.rewardPoints.web.restImpl;

import com.assignment.rewardPoints.constants.AppConstants;
import com.assignment.rewardPoints.dto.Customer;
import com.assignment.rewardPoints.dto.ResponseModel;
import com.assignment.rewardPoints.service.RewardPointsService;
import com.assignment.rewardPoints.web.rest.errors.CustomerNotFoundException;
import com.assignment.rewardPoints.web.rest.errors.DBSaveException;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;
import java.time.LocalDateTime;

@RestController
@Slf4j
@Api(value = "Rewards Program", tags = "Rewards")
@RequestMapping("api/v1/customer")
public class RewardPointsController {

    @Autowired
    private RewardPointsService rewardsService;

    private static final Logger LOGGER = LoggerFactory.getLogger(RewardPointsController.class);

    @ApiOperation(value = AppConstants.REWARDS_POST_OP_VALUE, notes = AppConstants.REWARDS_POST_OP_DETAILS)
    @ApiResponses(value = { @ApiResponse(code = 500, message = "Internal server error"),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 422, message = "Unprocessable Entity"),
            @ApiResponse(code = 201, message = "Success. Transaction created successfully")})
    @PostMapping(value = "/createTransaction", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createCustomerTransaction(@Valid @RequestBody Customer customer) {
        LOGGER.debug(String.format(AppConstants.TRANSACTION_LOG_MEG, customer.getId(), customer.getName(), customer.getTransactions().toString()));
        ResponseModel responseModel;
        try{
            // save the transaction and return the response
            rewardsService.createTransaction(customer);
            responseModel = new ResponseModel(
                    String.format(AppConstants.TRANSACTION_CREATED, customer.getId()),
                    HttpStatus.CREATED.value());
            return new ResponseEntity<>(responseModel, HttpStatus.CREATED);
        }
        catch(DBSaveException e){
            LOGGER.error("Error occurred while creating transaction for a customer {}",
                    e.getMessage());
            responseModel = new ResponseModel("Internal server error", HttpStatus.INTERNAL_SERVER_ERROR.value());
            return new ResponseEntity<>(responseModel.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        catch(Exception e){
            LOGGER.error("Runtime Error occurred while creating transaction for a customer {}",
                    e.getMessage());
            responseModel = new ResponseModel("Internal server error", HttpStatus.INTERNAL_SERVER_ERROR.value());
            return new ResponseEntity<>(responseModel.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @ApiOperation(value = "Delete customer")
    @ApiResponses(value = { @ApiResponse(code = 500, message = "Internal Server error"),
            @ApiResponse(code = 400, message = "Bad Request"), @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 404, message = "Customer not found"),
            @ApiResponse(code = 200, message = "Customer deleted successfully")})
    @DeleteMapping(value = "/customer/{customerId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> deleteCustomer(
            @ApiParam(defaultValue = "123456", value = "Customer Id", required = true) @PathVariable("customerId") @NotEmpty String customerId) {

        ResponseModel responseModel;
        LOGGER.debug(String.format(AppConstants.CUSTOMER_DELETION_REQUEST, customerId));
        try {
            rewardsService.deleteCustomer(Long.valueOf(customerId));

            responseModel = new ResponseModel(
                    String.format(AppConstants.CUSTOMER_DELETED, customerId),
                    HttpStatus.OK.value());
            return new ResponseEntity<>(responseModel, HttpStatus.OK);
        } catch (CustomerNotFoundException e) {
            responseModel = new ResponseModel("Customer not found", HttpStatus.NOT_FOUND.value());
            return new ResponseEntity<>(responseModel.getMessage(), HttpStatus.NOT_FOUND);
        }
        catch (Exception e) {
            responseModel = new ResponseModel("Failed to delete customer", HttpStatus.INTERNAL_SERVER_ERROR.value());
            return new ResponseEntity<>(responseModel.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @ApiOperation(value = "Get Mapping Details for the Given Route Number")
    @ApiResponses(value = { @ApiResponse(code = 500, message = "Server error"),
            @ApiResponse(code = 400, message = "Bad Request"), @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 404, message = "Customer is not found") })
    @GetMapping
    public ResponseEntity<?> getRewardPointsByCustomerId(
            @ApiParam(defaultValue = "123456", value = "customer Id", required = true) @RequestParam("customerId") @NotEmpty String customerId,
            @ApiParam(defaultValue = "2023-12-22", value = "start date", required = true) @RequestParam("startDate") @NotEmpty String startDate) {

        ResponseModel responseModel;
        LOGGER.debug(String.format(AppConstants.CUSTOMER_DELETION_REQUEST, customerId));

        LocalDate currentDate = LocalDate.now();
        LocalDate currentDateMinus3Months = currentDate.minusMonths(3);
        if (LocalDateTime.parse(startDate).isBefore(currentDateMinus3Months.atStartOfDay())) {
            responseModel = new ResponseModel("Invalid start date", HttpStatus.BAD_REQUEST.value());
            return new ResponseEntity<>(responseModel, HttpStatus.BAD_REQUEST);
        }
        try {
            //int rewardPoints = rewardsService.findTransactionsByCustomerIdAndDateBetween(Long.valueOf(customerId), LocalDateTime.parse(startDate));
            return ResponseEntity.ok(20);
        }/*catch (CustomerNotFoundException e) {
            responseModel = new ResponseModel("Customer not found", HttpStatus.NOT_FOUND.value());
            return new ResponseEntity<>(responseModel.getMessage(), HttpStatus.NOT_FOUND);
        }*/
        catch (Exception e) {
            responseModel = new ResponseModel("Issue with the server, please try gain after sometime", HttpStatus.INTERNAL_SERVER_ERROR.value());
            return new ResponseEntity<>(responseModel.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}