package com.codeElevate.serviceBookingSystem.services.client;

import java.util.List;

import com.codeElevate.serviceBookingSystem.dto.AdDTO;
import com.codeElevate.serviceBookingSystem.dto.AdDetailsForClientDTO;
import com.codeElevate.serviceBookingSystem.dto.ReservationDTO;
import com.codeElevate.serviceBookingSystem.dto.ReviewDTO;

public interface ClientService {

	List<AdDTO> getAllAds();

	List<AdDTO> searchAdByName(String name);

	boolean bookService(ReservationDTO reservationDTO);

	AdDetailsForClientDTO getAdDetailsById(Long adId);

	List<ReservationDTO> getAllBookingsByUserId(Long userId);

	boolean giveReview(ReviewDTO reviewDTO);
	
}
