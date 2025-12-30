package com.botree.model;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@Table(name = "patient")
public class Patient {
	
	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private int pid;
	    private String firstName;
	    private String lastName;
	    private String fatherName;

	    @Temporal(TemporalType.DATE)
	    private Date dob;

	    private String cnicNo;
	    private String gender;
	    private String street;
	    private String town;
	    private String city;
	    
	    @Temporal(TemporalType.TIMESTAMP)
	    private Date regDate;

	   	    public Patient() {
	    }

	    public Patient(String firstName, String lastName, String fatherName,
	                   Date dob, String cnicNo, String gender,
	                   String street, String town, String city, Date regDate) {

	        this.firstName = firstName;
	        this.lastName = lastName;
	        this.fatherName = fatherName;
	        this.dob = dob;
	        this.cnicNo = cnicNo;
	        this.gender = gender;
	        this.street = street;
	        this.town = town;
	        this.city = city;
	        this.regDate = regDate;
	    }


	    public int getPid() {
	        return pid;
	    }

	    public String getFirstName() {
	        return firstName;
	    }

	    public void setFirstName(String firstName) {
	        this.firstName = firstName;
	    }

	    public String getLastName() {
	        return lastName;
	    }

	    public void setLastName(String lastName) {
	        this.lastName = lastName;
	    }

	    public String getFatherName() {
	        return fatherName;
	    }

	    public void setFatherName(String fatherName) {
	        this.fatherName = fatherName;
	    }

	    public Date getDob() {
	        return dob;
	    }

	    public void setDob(Date dob) {
	        this.dob = dob;
	    }

	    public String getCnicNo() {
	        return cnicNo;
	    }

	    public void setCnicNo(String cnicNo) {
	        this.cnicNo = cnicNo;
	    }

	    public String getGender() {
	        return gender;
	    }

	    public void setGender(String gender) {
	        this.gender = gender;
	    }

	    public String getStreet() {
	        return street;
	    }

	    public void setStreet(String street) {
	        this.street = street;
	    }

	    public String getTown() {
	        return town;
	    }

	    public void setTown(String town) {
	        this.town = town;
	    }

	    public String getCity() {
	        return city;
	    }

	    public void setCity(String city) {
	        this.city = city;
	    }

	    public Date getRegDate() {
	        return regDate;
	    }

	    public void setRegDate(Date regDate) {
	        this.regDate = regDate;
	    }
	}


