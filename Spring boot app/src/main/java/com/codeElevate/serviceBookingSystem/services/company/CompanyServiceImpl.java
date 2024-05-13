package com.codeElevate.serviceBookingSystem.services.company;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codeElevate.serviceBookingSystem.dto.AdDTO;
import com.codeElevate.serviceBookingSystem.dto.ReservationDTO;
import com.codeElevate.serviceBookingSystem.entity.Ad;
import com.codeElevate.serviceBookingSystem.entity.Reservation;
import com.codeElevate.serviceBookingSystem.entity.User;
import com.codeElevate.serviceBookingSystem.enums.ReservationStatus;
import com.codeElevate.serviceBookingSystem.repository.AdRepository;
import com.codeElevate.serviceBookingSystem.repository.ReservationRepository;
import com.codeElevate.serviceBookingSystem.repository.UserRepository;

import io.jsonwebtoken.lang.Objects;

@Service
public class CompanyServiceImpl implements CompanyService {
	
	Logger logger = LoggerFactory.getLogger(CompanyServiceImpl.class);
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private AdRepository adRepository;
	
	@Autowired
	private ReservationRepository reservationRepository;
	
	@Override
	public boolean postAd(Long userId, AdDTO adDTO ) throws IOException {
		Optional<User> optionalUser = userRepository.findById(userId);
		if(optionalUser.isPresent()) {
			Ad ad = new Ad();
			ad.setServiceName(adDTO.getServiceName());
			ad.setDescription(adDTO.getDescription());
			ad.setImg(adDTO.getImg().getBytes());
			ad.setPrice(adDTO.getPrice());
			ad.setUser(optionalUser.get());
			
			adRepository.save(ad);
			return true;
		}
		return false;
	}
	
	@Override
	public List<AdDTO> getAllAds(Long userId) {
		return adRepository.findAllByUserId(userId).stream().map(Ad::getAdDto).collect(Collectors.toList());
	}
	
	@Override
	public AdDTO getAdById(Long adId) {
		Optional<Ad> optionalAd = adRepository.findById(adId);
		if(optionalAd.isPresent()) {
			return optionalAd.get().getAdDto();
		}
		return null;
	}
	
	@Override
	public boolean updateAd(Long adId, AdDTO adDTO) throws IOException {
		Optional<Ad> optionalAd = adRepository.findById(adId);
		if(optionalAd.isPresent()) {
			Ad ad = optionalAd.get();
			
			ad.setServiceName(adDTO.getServiceName());
			ad.setDescription(adDTO.getDescription());
			ad.setPrice(adDTO.getPrice());
			
			if(adDTO.getImg() != null) {
				ad.setImg(adDTO.getImg().getBytes());
			}
			
			adRepository.save(ad);
			return true;
		} else {
			return false;
		}
	}
	
	@Override
	public boolean deleteAd(Long adId) {
		Optional<Ad> optionalAd = adRepository.findById(adId);
		if(optionalAd.isPresent()) {
			adRepository.delete(optionalAd.get());
			return true;
		}
		return false;
	}
	
	@Override
	public List<ReservationDTO> getAllAdBookings(Long companyId) {
		return this.reservationRepository.findAllByCompanyId(companyId)
				.stream().map(Reservation::getReservationDto).collect(Collectors.toList());
	}
	
	@Override
	public boolean changeBookingStatus(Long bookingId,String status) {
		Optional<Reservation> optionalReservation = reservationRepository.findById(bookingId);
		if(optionalReservation.isPresent()) {
			Reservation existingReservation = optionalReservation.get();
			if(Objects.nullSafeEquals(status, "Approve")) {
				existingReservation.setReservationStatus(ReservationStatus.APPROVED);
			} else {
				existingReservation.setReservationStatus(ReservationStatus.REJECTED);
			}
			
			reservationRepository.save(existingReservation);
			return true;
		}
		return false;
	}

}
