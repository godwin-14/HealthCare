package com.botree.bean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.botree.dao.PatientDao;
import com.botree.model.Patient;

import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;

@Component
@Scope("session")
public class HealthCardBean {

	@Autowired
	private PatientDao patientDao;

	private Integer patientId;
	private Patient patient;

	public Integer getPatientId() {
		return patientId;
	}

	public void setPatientId(Integer patientId) {
		this.patientId = patientId;
	}

	public Patient getPatient() {
		return patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	public void findPatient() {

		if (patientId == null) {
			FacesContext.getCurrentInstance().addMessage("msgs",
					new FacesMessage(FacesMessage.SEVERITY_WARN, "Input Required", "Please enter Patient ID"));
			return;
		}

		var list = patientDao.searchById(patientId);

		if (list!=null) {
			patient = list;
		} else {
			patient = null;
			FacesContext.getCurrentInstance().addMessage("msgs",
					new FacesMessage(FacesMessage.SEVERITY_WARN, "Not Found", "No patient found with ID " + patientId));
		}
	}

}
