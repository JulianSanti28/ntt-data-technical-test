package com.ntt.data.app.repository;

import com.ntt.data.app.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ICustomerRepository extends JpaRepository<Customer, Long> {
    Optional<Customer> findByDocumentNumber(final String documentNumber);
}
