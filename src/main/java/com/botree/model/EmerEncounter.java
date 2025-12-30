package com.botree.model;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
public class EmerEncounter {
	
	   
	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private int encounterId;
	    
	    
	    @Temporal(TemporalType.TIMESTAMP)
	    private Date encounterDateTime;          

	    
	    private String mlcType;                  
	    
	    private String broughtBy;                

	    private String phoneNumber;

	    private Boolean isReferred;   
	    
	    
	    private String referenceName;
	    private String referenceNotes;
	    private String referencePhoneNumber;

	    private String fileId;                   
  
	    
	    @ManyToOne
	    @JoinColumn(name = "patient_id", nullable = false)
	    private Patient patient; 

	    private Date encounterDate;

	    private String status;
	    
	     

	    public Date getEncounterDate() {
			return encounterDate;
		}

		public void setEncounterDate(Date encounterDate) {
			this.encounterDate = encounterDate;
		}

		public String getStatus() {
			return status;
		}

		public void setStatus(String status) {
			this.status = status;
		}

		public EmerEncounter() {
			super();
		}

		public EmerEncounter( Patient patient, Date encounterDateTime, String mlcType, String broughtBy,
				String phoneNumber, Boolean isReferred, String fileId) {
			super();
			this.patient = patient;
		    this.encounterDateTime = encounterDateTime;
		    this.mlcType = mlcType;
		    this.broughtBy = broughtBy;
		    this.phoneNumber = phoneNumber;
		    this.isReferred = isReferred;
		    this.referenceName = referenceName;
		    this.referenceNotes = referenceNotes;
		    this.referencePhoneNumber = referencePhoneNumber;
		    this.fileId = fileId;
		}

		public int getEncounterId() {
	        return encounterId;
	    }

	    public Patient getPatient() {
	        return patient;
	    }

	    public void setPatient(Patient patient) {
	        this.patient = patient;
	    }

	    public Date getEncounterDateTime() {
	        return encounterDateTime;
	    }

	    public void setEncounterDateTime(Date encounterDateTime) {
	        this.encounterDateTime = encounterDateTime;
	    }

	    public String getMlcType() {
	        return mlcType;
	    }

	    public void setMlcType(String mlcType) {
	        this.mlcType = mlcType;
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

	    public Boolean getIsReferred() {
	        return isReferred;
	    }

	    public void setIsReferred(Boolean isReferred) {
	        this.isReferred = isReferred;
	    }

	    public String getFileId() {
	        return fileId;
	    }

	    public void setFileId(String fileId) {
	        this.fileId = fileId;
	    }

		public String getReferenceName() {
			return referenceName;
		}

		public void setReferenceName(String referenceName) {
			this.referenceName = referenceName;
		}

		public String getReferenceNotes() {
			return referenceNotes;
		}

		public void setReferenceNotes(String referenceNotes) {
			this.referenceNotes = referenceNotes;
		}

		public String getReferencePhoneNumber() {
			return referencePhoneNumber;
		}

		public void setReferencePhoneNumber(String referencePhoneNumber) {
			this.referencePhoneNumber = referencePhoneNumber;
		}

		public void setEncounterId(int encounterId) {
			this.encounterId = encounterId;
		}
	    
		
		
	}



