package com.botree.model;

import java.util.Date;
import java.util.List;

import jakarta.persistence.*;

@Entity
public class Treatment {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "patient_id")
    private Patient patient;

    private String mlc;
    
    private String treatedBy;

    @OneToMany(mappedBy = "treatment", cascade = CascadeType.ALL)
    private List<TreatmentMedicine> medicine;
    
    @Temporal(TemporalType.TIMESTAMP)
    private Date encounterDate;

    private String presentingComplaints;

    @ElementCollection
    @CollectionTable(name = "treatment_diseases", joinColumns = @JoinColumn(name = "treatment_id"))
    private List<String> diseaseSet;

    private String provisionalDiagnosis;
    

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "treatment_id")
    private List<TreatmentMedicine> medicines;

    private String referWard;
    
	public String getTreatedBy() {
		return treatedBy;
	}

	public void setTreatedBy(String treatedBy) {
		this.treatedBy = treatedBy;
	}

	public List<TreatmentMedicine> getMedicine() {
		return medicine;
	}

	public void setMedicine(List<TreatmentMedicine> medicine) {
		this.medicine = medicine;
	}

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
	
	
	

	public String getMlc() {
		return mlc;
	}

	public void setMlc(String mlc) {
		this.mlc = mlc;
	}

	public Date getEncounterDate() {
		return encounterDate;
	}

	public void setEncounterDate(Date encounterDate) {
		this.encounterDate = encounterDate;
	}

	public String getPresentingComplaints() {
		return presentingComplaints;
	}

	public void setPresentingComplaints(String presentingComplaints) {
		this.presentingComplaints = presentingComplaints;
	}

	public List<String> getDiseaseSet() {
		return diseaseSet;
	}

	public void setDiseaseSet(List<String> diseaseSet) {
		this.diseaseSet = diseaseSet;
	}

	public String getProvisionalDiagnosis() {
		return provisionalDiagnosis;
	}

	public void setProvisionalDiagnosis(String provisionalDiagnosis) {
		this.provisionalDiagnosis = provisionalDiagnosis;
	}

	

	public List<TreatmentMedicine> getMedicines() {
		return medicines;
	}

	public void setMedicines(List<TreatmentMedicine> medicines) {
		this.medicines = medicines;
	}

	public String getReferWard() {
		return referWard;
	}

	public void setReferWard(String referWard) {
		this.referWard = referWard;
	}

	public Treatment(Patient patient, Date encounterDate, String presentingComplaints, List<String> diseaseSet,
			String provisionalDiagnosis, String referWard) {
		super();
		this.patient = patient;
		this.encounterDate = encounterDate;
		this.presentingComplaints = presentingComplaints;
		this.diseaseSet = diseaseSet;
		this.provisionalDiagnosis = provisionalDiagnosis;
		this.referWard = referWard;
	}

	public Treatment() {
		super();
	}
    
    
    
}
