package com.assignment.rewardPoints.model;

import com.assignment.rewardPoints.dto.Transaction;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Table(name="customer")
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CustomerEntity {

    @Id
    private Long id;

    private String name;

    @OneToMany(targetEntity = TransactionEntity.class,cascade = CascadeType.ALL)
    @JoinColumn(name ="customer_id",referencedColumnName = "id")
    private List<TransactionEntity> transactions = new ArrayList<>();

}
