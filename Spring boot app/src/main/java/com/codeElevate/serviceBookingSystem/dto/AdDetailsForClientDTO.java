package com.codeElevate.serviceBookingSystem.dto;

import java.util.List;

import lombok.Data;

@Data
public class AdDetailsForClientDTO {
	
	private AdDTO adDTO;
	
	private List<ReviewDTO> reviewDTOList;

}
