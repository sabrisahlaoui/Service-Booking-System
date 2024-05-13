package com.codeElevate.serviceBookingSystem.entity;


import java.util.Date;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.codeElevate.serviceBookingSystem.dto.ReviewDTO;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Review {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private Date reviewDate;
	
	private String review;
	
	private Long rating;
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "user_id", nullable = false)
	@OnDelete(action = OnDeleteAction.CASCADE)
	private User user;
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "ad_id", nullable = false)
	@OnDelete(action = OnDeleteAction.CASCADE)
	private Ad ad;
	
	public ReviewDTO getDto() {
		ReviewDTO reviewDTO = new ReviewDTO();
		
		reviewDTO.setId(id);
		reviewDTO.setReview(review);
		reviewDTO.setRating(rating);
		reviewDTO.setReviewDate(reviewDate);
		reviewDTO.setUserId(user.getId());
		reviewDTO.setClientName(user.getName());
		reviewDTO.setAdId(ad.getId());
		reviewDTO.setServiceName(ad.getServiceName());
		
		return reviewDTO;
	}

}
