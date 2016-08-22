package com.exercise.hibernate2.core;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="Address")
public class Address extends PersistentObject{

	@Column(name = "houseNo")
	private int houseNo;

	@Column(name = "street")
	private String street;

	@Column(name = "barangay")
	private String barangay;

	@Column(name = "city")
	private String city;

	@Column(name = "zipCode")
	private int zipCode;

	public Address(){}

	public Address(int houseNo, String street, String barangay, String city, int zipCode) {
		this.houseNo = houseNo;
		this.street = street;
		this.barangay = barangay;
		this.city = city;
		this.zipCode = zipCode;
	}

	public int getHouseNo() {
		return houseNo;
	}

	public void setHouseNo(int houseNo) {
		this.houseNo = houseNo;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getBarangay() {
		return barangay;
	}

	public void setBarangay(String barangay) {
		this.barangay = barangay;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public int getZipCode() {
		return zipCode;
	}

	public void setZipCode(int zipCode) {
		this.zipCode = zipCode;
	}
	
}
