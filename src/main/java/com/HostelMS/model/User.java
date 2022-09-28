package com.HostelMS.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

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
	private String userName;
	private String userPhone;
	private String userPassword;
	private String userAddress;
	private int userFee;
	@ManyToOne
	private Room userRoom;
}
