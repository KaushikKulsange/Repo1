package com.app.dto;

import java.time.LocalTime;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import io.micrometer.common.lang.NonNull;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class RestaurantDTO {
	@JsonProperty(access = Access.READ_ONLY)
	private Long id;
	
	@NonNull
	@NotBlank
	@Email(message = "Invalid Email Format",regexp = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\\\.[A-Za-z0-9_-]+)*@[^-][A-Za-z0-9-]+(\\\\.[A-Za-z0-9-]+)*(\\\\.[A-Za-z]{2,})$")
	private String email;
	@JsonProperty(access = Access.WRITE_ONLY)
	
	@NonNull
	@NotBlank(message = "Password Can Not Be Blank")
	@Min(value=8,message = "Minimum Password Length Is 8")
	@Max(message ="Maximum Password Length Is 15" ,value = 15)
	private String password;
	
	@NonNull
	@NotBlank(message = "Please Enter Name")
	@Min(value = 2,message = "Invalid Name. Please Enter Correct Name")
	private String name;
	
	@NonNull
	@NotBlank(message = "Enter Correct Address")
	private String address;
	
//	@NonNull
//	@NotBlank(message="Please Mention Opening Time")
//	private LocalTime openingTime;

//	@NonNull
//	@NotBlank(message="Please Mention Closing Time")
//	private LocalTime closingTime;
	
	@JsonProperty(access = Access.WRITE_ONLY)
	private String fassaiId;
	
	@JsonProperty(access = Access.READ_ONLY)
	private String status;
	
	@NonNull
	@NotBlank(message="Please Mention Contact Number")
	private String contactNo;
	
	@NonNull
	@NotBlank(message="Please Mention Food Type")
	private String foodType;
	
}
