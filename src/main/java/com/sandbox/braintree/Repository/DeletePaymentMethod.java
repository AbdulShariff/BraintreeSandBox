package com.sandbox.braintree.Repository;

import com.sandbox.braintree.model.TransactionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeletePaymentMethod extends JpaRepository<TransactionEntity, Integer> {

    TransactionEntity findByCustomerId(Integer id);
}
