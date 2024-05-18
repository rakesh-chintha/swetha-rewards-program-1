package com.assignment.rewardPoints.service;

import com.assignment.rewardPoints.Repo.CustomerRepository;
import com.assignment.rewardPoints.Repo.TransactionRepository;
import com.assignment.rewardPoints.dto.Customer;
import com.assignment.rewardPoints.model.CustomerEntity;
import com.assignment.rewardPoints.model.TransactionEntity;
import com.assignment.rewardPoints.web.rest.errors.CustomerNotFoundException;
import com.assignment.rewardPoints.web.rest.errors.DBSaveException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class RewardPointsService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    private static final Logger LOGGER = LoggerFactory.getLogger(RewardPointsService.class);

    @Transactional
    public void createTransaction(Customer customer) throws DBSaveException {
        LOGGER.debug("Customer transaction creation started");
        try {
            // Check if the customer exists in the database
            Optional<CustomerEntity> existingCustomerOptional = customerRepository.findById(Long.valueOf(customer.getId()));
            CustomerEntity customerEntity;
            if (existingCustomerOptional.isPresent()) {
                // Customer exists, fetch the existing customer entity
                customerEntity = existingCustomerOptional.get();
            } else {
                // Customer does not exist, create a new customer entity
                customerEntity = new CustomerEntity();
                customerEntity.setId(Long.valueOf(customer.getId()));
                customerEntity.setName(customer.getName());
            }

            // Create a new transaction entity
            TransactionEntity transactionEntity = new TransactionEntity();
            transactionEntity.setDate(customer.getTransactions().getDate());
            transactionEntity.setAmount(customer.getTransactions().getAmount());

            // Add the transaction to the customer's list of transactions
            customerEntity.getTransactions().add(transactionEntity);

            // Save the customer entity (this will either create a new customer or update an existing one)
            customerRepository.save(customerEntity);

            LOGGER.debug("Customer transaction creation completed successfully");
        } catch (Exception e) {
            e.printStackTrace();
            throw new DBSaveException("Unable to create transaction");
        }
    }

    public void deleteCustomer(Long customerId) throws CustomerNotFoundException {
        LOGGER.debug("Customer deletion started");
        Optional<CustomerEntity> existingCustomerOptional = customerRepository.findById(Long.valueOf(customerId));
        if (existingCustomerOptional.isPresent()) {
            customerRepository.deleteById(customerId);
        }else{
            throw new CustomerNotFoundException("Customer not found");
        }
        LOGGER.debug("Customer deletion completed successfully");
    }

    public int findTransactionsByCustomerIdAndDateBetween(Long customerId, LocalDateTime startDate) throws CustomerNotFoundException {
        int totalPoints = 0;
        Optional<CustomerEntity> existingCustomerOptional = customerRepository.findById(Long.valueOf(customerId));
        if (existingCustomerOptional.isPresent()) {
            LocalDateTime endDate = LocalDateTime.now();
            //List<TransactionEntity> transactions = transactionRepository.findByCustomerIdAndDateBetween(customerId, startDate, endDate);
/*            for (TransactionEntity transaction : transactions) {
                totalPoints += calculateRewardPoints(transaction);
            }*/
        }else{
            throw new CustomerNotFoundException("Customer not found");
        }
        return totalPoints;
    }

    public int calculateRewardPoints(TransactionEntity transaction) {
        int points = 0;
        double amount = transaction.getAmount();

        if (amount > 100) {
            points += (int) ((amount - 100) * 2);
        }
        if (amount > 50) {
            points += (int) ((Math.min(amount, 100) - 50));
        }

        return points;
    }
}
