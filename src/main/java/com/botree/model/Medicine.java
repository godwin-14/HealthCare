package com.botree.model;

import jakarta.persistence.*;

@Entity
public class Medicine {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String medicineName;
    private int availableQty;

    
	
	public int getAvailableQty() {
		return availableQty;
	}

	public void setAvailableQty(int availableQty) {
		this.availableQty = availableQty;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getMedicineName() {
		return medicineName;
	}

	public void setMedicineName(String medicineName) {
		this.medicineName = medicineName;
	}

	public Medicine(int id, String medicineName) {
		super();
		this.id = id;
		this.medicineName = medicineName;
	}

	public Medicine() {
		super();
	}
	
	
}
