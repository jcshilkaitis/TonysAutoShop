package dmacc.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import dmacc.beans.Appointments;

public interface AppointmentRepository extends
JpaRepository<Appointments, Long> {

}
