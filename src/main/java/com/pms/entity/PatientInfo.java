package com.pms.entity;


import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.annotation.Nonnull;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Data;




//use database patient_info_db
@Entity
@Data // gives all lombok
@Table(name = "patient")
@Builder
public class PatientInfo {

	@Id
	@GeneratedValue(generator = CustomIdGenerator.GENERATOR_NAME)
	@GenericGenerator(name = CustomIdGenerator.GENERATOR_NAME, strategy = "com.pms.entity.CustomIdGenerator", parameters = {
	@Parameter(name = CustomIdGenerator.PREFIX_PARAM, value = "PA00") })
	@Column(name = "patient_id")
	private String patientId;
	@Nonnull
	@Column(name = "email", unique = true)
	private String email;
	private String title;
	@Column(name = "first_name")
	private String firstName;
	@Column(name = "last_name")
	private String lastName;	
	@Column(nullable = false, updatable = false)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private String dob;
	@Column(name = "contact_number")
	private String contactNumber;
	@Nonnull
	private String password;
	private String gender;
	//@Column(name = "languages_known")
	//private List<String> languagesKnown;
	private String address;
	
	
	public PatientInfo(String patientId, String email, String title, String firstName, String lastName, String dob,
			String contactNumber, String password, String gender, String address) {
		super();
		this.patientId = patientId;
		this.email = email;
		this.title = title;
		this.firstName = firstName;
		this.lastName = lastName;
		this.dob = dob;
		this.contactNumber = contactNumber;
		this.password = password;
		this.gender = gender;
		this.address = address;
	}


	public PatientInfo() {
		super();
	}
	
	
	
	
}