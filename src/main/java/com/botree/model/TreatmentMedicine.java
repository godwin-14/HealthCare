package com.botree.model;

import jakarta.persistence.*;

@Entity
public class TreatmentMedicine {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    
    @ManyToOne
    private Treatment treatment;
    
    
    private int prescribedQty;
    private int issuedQty;
    private boolean fullyIssued;
   
    
    @ManyToOne
    @JoinColumn(name = "medicine_id")
    private Medicine medicine;

    private String timings;
    private int quantity;
    private int period;
    private String comments;
    
    
    
  
    public Treatment getTreatment() {
		return treatment;
	}

	public void setTreatment(Treatment treatment) {
		this.treatment = treatment;
	}

	public int getPrescribedQty() {
		return prescribedQty;
	}

	public void setPrescribedQty(int prescribedQty) {
		this.prescribedQty = prescribedQty;
	}

	public int getIssuedQty() {
		return issuedQty;
	}

	public void setIssuedQty(int issuedQty) {
		this.issuedQty = issuedQty;
	}

	public boolean isFullyIssued() {
		return fullyIssued;
	}

	public void setFullyIssued(boolean fullyIssued) {
		this.fullyIssued = fullyIssued;
	}

	public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
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

	public TreatmentMedicine( Medicine medicine, String timings, int quantity, int period, String comments) {
		super();
		this.medicine = medicine;
		this.timings = timings;
		this.quantity = quantity;
		this.period = period;
		this.comments = comments;
	}

	public TreatmentMedicine() {
		super();
	}
    
    
    
}
