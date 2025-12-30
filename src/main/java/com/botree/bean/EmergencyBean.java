package com.botree.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.primefaces.event.SelectEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.botree.dao.EncounterDao;
import com.botree.dao.PatientDao;
import com.botree.model.EmerEncounter;
import com.botree.model.Patient;

import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;

@Component
@Scope("session")
public class EmergencyBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private String searchName;
	private List<Patient> searchResults = new ArrayList<>();
	private Patient patient;

	private String patientName;
	private int patientId;
	private String fatherName;
	private String address;
	private String gender;
	private String cnicNo;
	private int age;

	private String mlc;
	private String broughtBy;
	private String phoneNumber;
	private String isReferred;

	private String refName;
	private String refNotes;
	private String refPhone;
	private String fileId;

	private Date encounterDate;
	
	private List<EmerEncounter> encounterList = new ArrayList<>();


	@Autowired
	private EncounterDao encounterDao;

	@Autowired
	private PatientDao patientDao;

	@PostConstruct
	public void init() {
		encounterDate = new Date();
		fileId = generateFileId();
	}

	public void findPatient() {
		if (searchName == null || searchName.trim().isEmpty()) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_WARN, "Please enter patient name", ""));
			searchResults.clear();
			return;
		}

		searchResults = patientDao.searchByName(searchName.trim());

		if (searchResults.isEmpty()) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_WARN, "No patient found for", searchName));
		}

		patient = null;
	}

	public void onRowSelect(SelectEvent<Patient> event) {
		Patient p = event.getObject();
		this.patient = p;
		this.patientName = p.getFirstName() + " " + p.getLastName();
		this.patientId = p.getPid();
		this.fatherName = p.getFatherName();
		this.address = p.getStreet() + ", " + p.getTown() + ", " + p.getCity();
		this.gender = p.getGender();
		this.cnicNo = p.getCnicNo();
		this.age = (p.getDob() != null) ? calculateAge(p.getDob()) : 0;

		this.mlc = null;
		this.broughtBy = "";
		this.phoneNumber = "";
		this.isReferred = null;
		this.refName = "";
		this.refNotes = "";
		this.refPhone = "";
		this.fileId = generateFileId();
		
		this.encounterList = encounterDao.getEncountersByPatientId(patientId);

	}

	public void saveCounter() {
		if (patient == null) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Select a patient first"));
			return;
		}

		EmerEncounter encounter = new EmerEncounter(patient, encounterDate, mlc, broughtBy, phoneNumber,
				"Yes".equals(isReferred), fileId);

		if ("Yes".equals(isReferred)) {
			encounter.setReferenceName(refName);
			encounter.setReferenceNotes(refNotes);
			encounter.setReferencePhoneNumber(refPhone);
		}

		boolean success = encounterDao.saveCounter(encounter);

		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(success ? FacesMessage.SEVERITY_INFO : FacesMessage.SEVERITY_ERROR,
						success ? "Success" : "Failed", success ? "Emergency patient saved" : "Cannot save encounter"));
	}

	private int calculateAge(Date dob) {
		return (dob == null) ? 0
				: java.time.Period.between(dob.toInstant().atZone(java.time.ZoneId.systemDefault()).toLocalDate(),
						java.time.LocalDate.now()).getYears();
	}

	
	public List<EmerEncounter> getEncounterList() {
		return encounterList;
	}

	public void setEncounterList(List<EmerEncounter> encounterList) {
		this.encounterList = encounterList;
	}

	private String generateFileId() {
		return "FILE-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
	}

	public String getSearchName() {
		return searchName;
	}

	public void setSearchName(String searchName) {
		this.searchName = searchName;
	}

	public List<Patient> getSearchResults() {
		return searchResults;
	}

	public void setSearchResults(List<Patient> searchResults) {
		this.searchResults = searchResults;
	}

	public Patient getPatient() {
		return patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	public String getPatientName() {
		return patientName;
	}

	public void setPatientName(String patientName) {
		this.patientName = patientName;
	}

	public int getPatientId() {
		return patientId;
	}

	public void setPatientId(int patientId) {
		this.patientId = patientId;
	}

	public String getFatherName() {
		return fatherName;
	}

	public void setFatherName(String fatherName) {
		this.fatherName = fatherName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getCnicNo() {
		return cnicNo;
	}

	public void setCnicNo(String cnicNo) {
		this.cnicNo = cnicNo;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getMlc() {
		return mlc;
	}

	public void setMlc(String mlc) {
		this.mlc = mlc;
	}

	public String getBroughtBy() {
		return broughtBy;
	}

	public void setBroughtBy(String broughtBy) {
		this.broughtBy = broughtBy;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getIsReferred() {
		return isReferred;
	}

	public void setIsReferred(String isReferred) {
		this.isReferred = isReferred;
	}

	public String getRefName() {
		return refName;
	}

	public void setRefName(String refName) {
		this.refName = refName;
	}

	public String getRefNotes() {
		return refNotes;
	}

	public void setRefNotes(String refNotes) {
		this.refNotes = refNotes;
	}

	public String getRefPhone() {
		return refPhone;
	}

	public void setRefPhone(String refPhone) {
		this.refPhone = refPhone;
	}

	public String getFileId() {
		return fileId;
	}

	public void setFileId(String fileId) {
		this.fileId = fileId;
	}

	public Date getEncounterDate() {
		return encounterDate;
	}

	public void setEncounterDate(Date encounterDate) {
		this.encounterDate = encounterDate;
	}
	

}

