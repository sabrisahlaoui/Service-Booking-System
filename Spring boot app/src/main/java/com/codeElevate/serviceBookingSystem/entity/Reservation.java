package com.codeElevate.serviceBookingSystem.entity;

import java.sql.Date;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.codeElevate.serviceBookingSystem.dto.ReservationDTO;
import com.codeElevate.serviceBookingSystem.enums.ReservationStatus;
import com.codeElevate.serviceBookingSystem.enums.ReviewStatus;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Reservation {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private ReservationStatus reservationStatus;
	
	private ReviewStatus reviewStatus;
	
	private Date bookDate;
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "user_id", nullable = false)
	@OnDelete(action = OnDeleteAction.CASCADE)
	private User user;
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "company_id", nullable = false)
	@OnDelete(action = OnDeleteAction.CASCADE)
	private User company;
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "ad_id", nullable = false)
	@OnDelete(action = OnDeleteAction.CASCADE)
	private Ad ad;
	
	public ReservationDTO getReservationDto() {
		ReservationDTO dto = new ReservationDTO();
		
		dto.setId(id);
		dto.setServiceName(ad.getServiceName());
		dto.setBookDate(bookDate);
		dto.setReservationStatus(reservationStatus);
		dto.setReviewStatus(reviewStatus);
		
		dto.setAdId(ad.getId());
		dto.setCompanyId(company.getId());
		dto.setUserName(user.getName());
		
		return dto;
	}

}
