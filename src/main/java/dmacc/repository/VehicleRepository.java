package dmacc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import dmacc.beans.Vehicle;

/**
 * @author Joe Shilkaitis - jshilkaitis
 * CIS175 - Fall 2022
 * Nov 12, 2022
 */
@Repository
public interface VehicleRepository extends
JpaRepository<Vehicle, Long> { }
