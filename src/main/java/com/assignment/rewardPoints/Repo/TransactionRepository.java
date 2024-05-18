package com.assignment.rewardPoints.Repo;

import com.assignment.rewardPoints.model.TransactionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<TransactionEntity, Long> {

    //List<TransactionEntity> findByCustomerIdAndDateBetween(Long customer_id, LocalDateTime startDate, LocalDateTime endDate);

}