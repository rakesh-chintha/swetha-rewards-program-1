package com.assignment.rewardPoints.constants;

public class AppConstants {

    private AppConstants() {
    }

    //Customer ID
    public static final String INVALID_CUSTOMER_ID = "Invalid customer Id";

    public static final String CUSTOMER_ID_NULL = "Customer Id, cannot be null";

    //Transaction Amount
    public static final String INVALID_TRANSACTION_AMOUNT = "Invalid transaction amount";

    public static final String INVALID_TRANSACTION_DATE = "Invalid transaction date";

    public static final String INVALID_TRANSACTION_DETAILS = "Transaction details cannot be null";

    public static final String REWARDS_POST_OP_VALUE = "Create transaction of a customer";

    public static final String REWARDS_POST_OP_DETAILS = "User initiates customer transaction #. Rewards points service validates customer data and persists the required data in DB.\n"
            + "Acknowledgement - if transaction load is successful (or) error - Error code and corresponding message based on the error.";

    public static final String TRANSACTION_LOG_MEG = "Request for transaction creation of Customer, id - %1$s , Name - %2$s , TransactionDetails - %3$s";

    public static final String TRANSACTION_CREATED = "Transaction created successfully for the Customer, id - %1$s";

    public static final String CUSTOMER_DELETED = "Customer deleted successfully, id - %1$s";

    public static final String CUSTOMER_DELETION_REQUEST = "Request for customer deletion, id - %1$s";
}
