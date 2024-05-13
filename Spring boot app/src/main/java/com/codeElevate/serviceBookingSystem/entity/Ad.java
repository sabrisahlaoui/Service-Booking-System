package com.codeElevate.serviceBookingSystem.entity;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.codeElevate.serviceBookingSystem.dto.AdDTO;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "ads")
@Data
public class Ad {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY )
	private Long id;
	
	private String serviceName;
	
	private String description;
	
	private Double price;
	
	private byte[] img;
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "user_id")
	@OnDelete(action = OnDeleteAction.CASCADE)
	private User user;
	
	
	public AdDTO getAdDto() {
		
		AdDTO adDTO = new AdDTO();
		
		adDTO.setId(id);
		adDTO.setServiceName(serviceName);
		adDTO.setDescription(description);
		adDTO.setPrice(price);
		adDTO.setCompanyName(user.getName());
		adDTO.setReturnedImg(img);
		
		return adDTO;
	}

}
