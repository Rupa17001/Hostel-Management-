// Room class : assigns one room to many users(Student)
package com.HostelMS.model;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
// Getter,Setter and To String are LOMBOK annotations (used to remove the boiler plate)
// Entity and Id are JPA, Entity : Table and Id : Primary Key
@Entity
@Getter
@Setter
@ToString
public class Room {
	@Id
	private int roomId;
	private String roomName;
	private String roomType;
}
