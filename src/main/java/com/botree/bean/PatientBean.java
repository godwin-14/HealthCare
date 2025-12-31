package com.botree.bean;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.botree.dao.PatientDao;

import com.botree.model.Patient;

import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;


@Component
@Scope("session")
public class PatientBean {

	@Autowired
	private PatientDao patientDao;
		
	 @PostConstruct
	    public void init() {
		 // testing update
	        patient = new Patient();
	    }
	
	private String searchName; 
    private Patient patient; 
    
    
    private int pid;
    private String firstName;
    private String lastName;
    private String fatherName;
    private Date dob;
    private String cnicNo;
    private String gender;
    private String street;
    private String town;
    private String city;
    private Date regDate;
    
    
    public String getSearchName() {
        return searchName;
    }

    public void setSearchName(String searchName) {
        this.searchName = searchName;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }
    
    
	public int getPid() {
		return pid;
	}
	public void setPid(int pid) {
		this.pid = pid;
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
	
	
	
	public void updatePatient() { 
	    if (patient != null) {
	        patientDao.update(patient);

	        FacesContext.getCurrentInstance().addMessage(null,
	            new FacesMessage(
	                FacesMessage.SEVERITY_INFO,
	                "Updated",
	                "Patient updated successfully"
	            )
	        );
	    }
  } 

}   

