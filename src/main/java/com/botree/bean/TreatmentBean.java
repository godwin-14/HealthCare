package com.botree.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.botree.dao.TreatmentDao;
import com.botree.model.*;

import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;

@Component
@Scope("session")
public class TreatmentBean implements Serializable {

	private int patientId;
	private Patient patient;
	private String mlc;
	private TPRB latestTPRB;

	private String presentingComplaints;
	private String diseaseSet;
	private List<String> diseaseList = new ArrayList<>();
	private String provisionalDiagnosis;

	private List<MedicineRow> medicines = new ArrayList<>();

	private String referWard;
	private List<String> wards = List.of("General", "ICU", "Pediatrics", "Surgery");

	private List<Medicine> allMedicines;

	private boolean showPreviousTPRB = false;

	private List<TPRB> previousTPRBList;

	@Autowired
	private TreatmentDao treatmentDao;

	public static class MedicineRow {
		private Medicine medicine;
		private String timings;
		private int qty;
		private int period;
		private String comments;

		public Medicine getMedicine() {
			return medicine;
		}

		public void setMedicine(Medicine medicine) {
			this.medicine = medicine;
		}

		public String getTimings() {
			return timings;
		}

		public void setTimings(String timings) {
			this.timings = timings;
		}

		public int getQty() {
			return qty;
		}

		public void setQty(int qty) {
			this.qty = qty;
		}

		public int getPeriod() {
			return period;
		}

		public void setPeriod(int period) {
			this.period = period;
		}

		public String getComments() {
			return comments;
		}

		public void setComments(String comments) {
			this.comments = comments;
		}
	}

	public int getPatientId() {
		return patientId;
	}

	public void setPatientId(int patientId) {
		this.patientId = patientId;
	}

	public Patient getPatient() {
		return patient;
	}

	public String getMlc() {
		return mlc;
	}

	public void setMlc(String mlc) {
		this.mlc = mlc;
	}

	public TPRB getLatestTPRB() {
		return latestTPRB;
	}

	public String getPresentingComplaints() {
		return presentingComplaints;
	}

	public void setPresentingComplaints(String presentingComplaints) {
		this.presentingComplaints = presentingComplaints;
	}

	public String getDiseaseSet() {
		return diseaseSet;
	}

	public void setDiseaseSet(String diseaseSet) {
		this.diseaseSet = diseaseSet;
		if (diseaseSet != null && !diseaseSet.trim().isEmpty()) {
			diseaseList = List.of(diseaseSet.split(","));
		}
	}

	public String getProvisionalDiagnosis() {
		return provisionalDiagnosis;
	}

	public void setProvisionalDiagnosis(String provisionalDiagnosis) {
		this.provisionalDiagnosis = provisionalDiagnosis;
	}

	public List<MedicineRow> getMedicines() {
		return medicines;
	}

	public String getReferWard() {
		return referWard;
	}

	public void setReferWard(String referWard) {
		this.referWard = referWard;
	}

	public List<String> getWards() {
		return wards;
	}

	public boolean isShowPreviousTPRB() {
		return showPreviousTPRB;
	}

	public List<TPRB> getPreviousTPRBList() {
		return previousTPRBList;
	}

	public void setPreviousTPRBList(List<TPRB> previousTPRBList) {
		this.previousTPRBList = previousTPRBList;
	}

	@PostConstruct
	public void init() {
		allMedicines = treatmentDao.getAllMedicines();
		
		// Add default medicines if none exist
		if (allMedicines == null || allMedicines.isEmpty()) {
			allMedicines = new ArrayList<>();
			allMedicines.add(new Medicine(1, "Paracetamol"));
			allMedicines.add(new Medicine(2, "Aspirin"));
			allMedicines.add(new Medicine(3, "Ibuprofen"));
			allMedicines.add(new Medicine(4, "Amoxicillin"));
			allMedicines.add(new Medicine(5, "Ciprofloxacin"));
		}

		for (int i = 0; i < 3; i++) {
			medicines.add(new MedicineRow());
		}
	}

	public void loadPatient() {
		patient = treatmentDao.getPatientById(patientId);

		if (patient != null) {
			latestTPRB = treatmentDao.getLatestTPRB(patientId);
		} else {
			latestTPRB = null;
		}
	}

	public void addMedicineRow() {
		medicines.add(new MedicineRow());
	}

	public void removeMedicineRow(MedicineRow row) {
		medicines.remove(row);
	}

	public void submitTreatment() {

		if (patient == null) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Select patient first"));
			return;
		}

		Treatment treatment = new Treatment(patient, new Date(), presentingComplaints, diseaseList, provisionalDiagnosis,
				referWard);

		List<TreatmentMedicine> entityMeds = new ArrayList<>();

		for (MedicineRow row : medicines) {

			if (row.getMedicine() == null) {
				continue;
			}

			TreatmentMedicine tm = new TreatmentMedicine(row.getMedicine(), row.getTimings(), row.getQty(),
					row.getPeriod(), row.getComments());

			entityMeds.add(tm);
		}

		treatment.setMedicines(entityMeds);

		boolean success = treatmentDao.saveTreatment(treatment);

		if (success) {
		    FacesContext.getCurrentInstance().addMessage(null,
		        new FacesMessage(FacesMessage.SEVERITY_INFO, "Success", "Treatment saved successfully"));
		} else {
		    FacesContext.getCurrentInstance().addMessage(null,
		        new FacesMessage(FacesMessage.SEVERITY_ERROR, "Failed", "Treatment save failed"));
		}
	}

	public List<Medicine> getAllMedicines() {
		return allMedicines;
	}

	public void loadPreviousTPRB() {
		if (patient == null)
			return;
		previousTPRBList = treatmentDao.getPreviousTPRB(patient.getPid());
		showPreviousTPRB = true;
	}
}
