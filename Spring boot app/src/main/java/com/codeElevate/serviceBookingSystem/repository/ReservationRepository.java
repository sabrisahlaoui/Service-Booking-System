package com.codeElevate.serviceBookingSystem.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.codeElevate.serviceBookingSystem.entity.Reservation;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
	
	List<Reservation> findAllByCompanyId(Long companyId);
	
	List<Reservation> findAllByUserId(Long userId);

}
