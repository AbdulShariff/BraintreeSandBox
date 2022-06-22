package com.sandbox.braintree.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.sandbox.braintree.model.AddInstrumentEntity;

@Repository
public interface PaymentMethodRepository extends JpaRepository<AddInstrumentEntity, Integer> {

    AddInstrumentEntity findByCustomersId(Integer id);
}
