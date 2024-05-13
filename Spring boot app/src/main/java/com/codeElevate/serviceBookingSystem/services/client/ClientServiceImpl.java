package com.codeElevate.serviceBookingSystem.services.client;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codeElevate.serviceBookingSystem.dto.AdDTO;
import com.codeElevate.serviceBookingSystem.dto.AdDetailsForClientDTO;
import com.codeElevate.serviceBookingSystem.dto.ReservationDTO;
import com.codeElevate.serviceBookingSystem.dto.ReviewDTO;
import com.codeElevate.serviceBookingSystem.entity.Ad;
import com.codeElevate.serviceBookingSystem.entity.Reservation;
import com.codeElevate.serviceBookingSystem.entity.Review;
import com.codeElevate.serviceBookingSystem.entity.User;
import com.codeElevate.serviceBookingSystem.enums.ReservationStatus;
import com.codeElevate.serviceBookingSystem.enums.ReviewStatus;
import com.codeElevate.serviceBookingSystem.repository.AdRepository;
import com.codeElevate.serviceBookingSystem.repository.ReservationRepository;
import com.codeElevate.serviceBookingSystem.repository.ReviewRepository;
import com.codeElevate.serviceBookingSystem.repository.UserRepository;

@Service
public class ClientServiceImpl implements ClientService {
	
	Logger logger = LoggerFactory.getLogger(ClientServiceImpl.class);
	
	@Autowired
	private AdRepository adRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private ReservationRepository reservationRepository;
	
	@Autowired
	private ReviewRepository reviewRepository;
	
	
	
	@Override
	public List<AdDTO> getAllAds() {
		return adRepository.findAll().stream().map(Ad::getAdDto).collect(Collectors.toList());
	}
	
	@Override
	public List<AdDTO> searchAdByName(String name) {
		return adRepository.findAllByServiceNameContaining(name).stream().map(Ad::getAdDto).collect(Collectors.toList());
	}
	
	@Override
	public boolean bookService(ReservationDTO reservationDTO) {
		Optional<Ad> optionalAd = adRepository.findById(reservationDTO.getAdId());
		Optional<User> optionalUser = userRepository.findById(reservationDTO.getUserId());
		
		if(optionalAd.isPresent() && optionalUser.isPresent()) {
			Reservation reservation = new Reservation();
			
			reservation.setBookDate(reservationDTO.getBookDate());
			reservation.setReservationStatus(ReservationStatus.PENDING);
			reservation.setUser(optionalUser.get());
			
			reservation.setAd(optionalAd.get());
			reservation.setCompany(optionalAd.get().getUser());
			reservation.setReviewStatus(ReviewStatus.FALSE);
			
			reservationRepository.save(reservation);
			return true;
		}
		
		return false;
 	}
	
	@Override
	public AdDetailsForClientDTO getAdDetailsById(Long adId) {
		Optional<Ad> optionalAd = adRepository.findById(adId);
		AdDetailsForClientDTO adDetailsForClientDTO = new AdDetailsForClientDTO();
		if(optionalAd.isPresent()) {
			adDetailsForClientDTO.setAdDTO(optionalAd.get().getAdDto());
			
			List<Review> reviewList = reviewRepository.findAllByAdId(adId);
			adDetailsForClientDTO.setReviewDTOList(reviewList.stream().map(Review::getDto).collect(Collectors.toList()));
		}
		return adDetailsForClientDTO;
	}
	
	@Override
	public List<ReservationDTO> getAllBookingsByUserId(Long userId) {
		return reservationRepository.findAllByUserId(userId)
				.stream().map(Reservation::getReservationDto).collect(Collectors.toList());
	}
	
	@Override
	public boolean giveReview(ReviewDTO reviewDTO) {
		Optional<User> optionalUser = userRepository.findById(reviewDTO.getUserId());
		Optional<Reservation> optionalBooking = reservationRepository.findById(reviewDTO.getBookId());
		
		if(optionalUser.isPresent() && optionalBooking.isPresent()) {
			Review review = new Review();
			
			review.setReviewDate(new Date());
			review.setReview(reviewDTO.getReview());
			review.setRating(reviewDTO.getRating());
			
			review.setUser(optionalUser.get());
			review.setAd(optionalBooking.get().getAd());
			
			reviewRepository.save(review);
			
			Reservation booking = optionalBooking.get();
			booking.setReviewStatus(ReviewStatus.TRUE);
			
			reservationRepository.save(booking);
			
 			return true;
		}
		return false;
	}

}
