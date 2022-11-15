package dmacc.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import dmacc.beans.Customer;

public interface CustomerRepository extends
JpaRepository<Customer, Long> {

}
