package com.botree.bean;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.botree.dao.EncounterDao;
import com.botree.dao.PatientDao;

import com.botree.dao.TPRPDao;
import com.botree.model.EmerEncounter;
import com.botree.model.Patient;
import com.botree.model.TPRB;

import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;

@Component
@Scope("session")
public class TprbBean {

	@Autowired
	private TPRPDao tprpDao;

	@Autowired
	private PatientDao patientDao;

	@Autowired
	private EncounterDao encounterDao;

	private String searchName;
	private Patient patient;
	private boolean showPatient;
	private boolean showTPRB;

	private int bpMin;
	private int bpMax;
	private int pulseRate;
	private int temperature;
	private int respirationRate;

	private EmerEncounter encounter;

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

	public boolean isShowPatient() {
		return showPatient;
	}

	public void setShowPatient(boolean showPatient) {
		this.showPatient = showPatient;
	}

	public boolean isShowTPRB() {
		return showTPRB;
	}

	public void setShowTPRB(boolean showTPRB) {
		this.showTPRB = showTPRB;
	}

	public int getBpMin() {
		return bpMin;
	}

	public void setBpMin(int bpMin) {
		this.bpMin = bpMin;
	}

	public int getBpMax() {
		return bpMax;
	}

	public void setBpMax(int bpMax) {
		this.bpMax = bpMax;
	}

	public int getPulseRate() {
		return pulseRate;
	}

	public void setPulseRate(int pulseRate) {
		this.pulseRate = pulseRate;
	}

	public int getTemperature() {
		return temperature;
	}

	public void setTemperature(int temperature) {
		this.temperature = temperature;
	}

	public int getRespirationRate() {
		return respirationRate;
	}

	public void setRespirationRate(int respirationRate) {
		this.respirationRate = respirationRate;
	}

	public EmerEncounter getEncounter() {
		return encounter;
	}

	public void setEncounter(EmerEncounter encounter) {
		this.encounter = encounter;
	}

	public void searchPatient() {
		var list = patientDao.searchByName(searchName);
		if (!list.isEmpty()) {
			patient = list.get(0);
			showPatient = true;
			showTPRB = false;
		}
	}

	public void openTPRB() {
		encounter = new EmerEncounter();
		encounter.setPatient(patient);
		encounter.setEncounterDateTime(new Date());
		encounter.setStatus("OPEN");

		encounterDao.save(encounter);
		showTPRB = true;
	}

	public void saveTPRB() {

		if (patient == null || encounter == null) {
			FacesContext.getCurrentInstance().addMessage("msgs",
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Patient or Encounter not selected"));
			return;
		}

		TPRB tprb = new TPRB(bpMin, bpMax, pulseRate, temperature, respirationRate, patient, encounter, new Date());

		boolean success = tprpDao.save(tprb);

		if (success) {
			FacesContext.getCurrentInstance().addMessage("msgs",
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Success", "TPRB details saved successfully"));
			resetTPRB();
		} else {
			FacesContext.getCurrentInstance().addMessage("msgs",
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Failed", "Unable to save TPRB details"));
		}
	}

	private void resetTPRB() {
		bpMin = 0;
		bpMax = 0;
		pulseRate = 0;
		temperature = 0;
		respirationRate = 0;
		showTPRB = false;
	}
}
