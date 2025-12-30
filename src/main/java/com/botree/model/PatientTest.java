package com.botree.model;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class PatientTest {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    private Patient patient;

    @ManyToOne
    private PathologyTest test;

    private Date requestDate;
    private double discount;
    private double charge;
    private boolean feePaid;

    private String sampleId;
    private Date sampleDate;
    private boolean sampleRejected;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Patient getPatient() {
		return patient;
	}
	public void setPatient(Patient patient) {
		this.patient = patient;
	}
	public PathologyTest getTest() {
		return test;
	}
	public void setTest(PathologyTest test) {
		this.test = test;
	}
	public Date getRequestDate() {
		return requestDate;
	}
	public void setRequestDate(Date requestDate) {
		this.requestDate = requestDate;
	}
	public double getDiscount() {
		return discount;
	}
	public void setDiscount(double discount) {
		this.discount = discount;
	}
	public double getCharge() {
		return charge;
	}
	public void setCharge(double charge) {
		this.charge = charge;
	}
	public boolean isFeePaid() {
		return feePaid;
	}
	public void setFeePaid(boolean feePaid) {
		this.feePaid = feePaid;
	}
	public String getSampleId() {
		return sampleId;
	}
	public void setSampleId(String sampleId) {
		this.sampleId = sampleId;
	}
	public Date getSampleDate() {
		return sampleDate;
	}
	public void setSampleDate(Date sampleDate) {
		this.sampleDate = sampleDate;
	}
	public boolean isSampleRejected() {
		return sampleRejected;
	}
	public void setSampleRejected(boolean sampleRejected) {
		this.sampleRejected = sampleRejected;
	}
    
    
    
}
