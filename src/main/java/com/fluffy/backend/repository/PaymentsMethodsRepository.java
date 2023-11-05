package com.fluffy.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fluffy.backend.entity.PaymentsMethods;

@Repository
public interface PaymentsMethodsRepository extends JpaRepository<PaymentsMethods, Long> {

}
