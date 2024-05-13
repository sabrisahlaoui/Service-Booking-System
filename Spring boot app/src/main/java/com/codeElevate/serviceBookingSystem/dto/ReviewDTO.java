package com.codeElevate.serviceBookingSystem.dto;

import java.util.Date;

import lombok.Data;

@Data
public class ReviewDTO {

	private Long id;
	
	private Date reviewDate;
	
	private String review;
	
	private Long rating;
	
	private Long userId;
	
	private Long adId;
	
	private String clientName;
	
	private String serviceName;
	
	private Long bookId;
	
}
