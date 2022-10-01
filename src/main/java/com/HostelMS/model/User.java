/*
 *  Use of LOMBOK annotation
 *  Use of Validator annotation
 *  use of JPA (java persistence API) annotaions*/
package com.HostelMS.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


//Getter,Setter and To String are LOMBOK annotations (used to remove the boiler plate)
//Entity and Id are JPA, Entity : Table and Id : Primary Key
@Entity
@Getter
@Setter
@ToString
public class User {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int userID;
	@NotNull
	@Size(min = 3, max = 25, message = "!!  User name Should have more that 2 and less than 25 letters")
	private String userName;
	@NotNull
	@Pattern(regexp ="[0-9]{10}", message = "!!  Phone Number Should have 10 digits only" )
	private String userPhone;
	@NotNull
	@Pattern(regexp = "[0-9a-zA-Z@#]{4,}", message = "!!  Password should have more than four characters and can have a-z , A-Z, digits and @ and # symbol")
	private String userPassword;
	@NotNull
	private String userAddress;
	private String userRole;
	private int userFee;
	@ManyToOne
	private Room userRoom;
	
}
