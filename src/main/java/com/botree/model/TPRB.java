package com.botree.model;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
public class TPRB {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    private Patient patient;

    @ManyToOne
    private EmerEncounter encounter;

    private int bpMin;
    private int bpMax;
    private int pulseRate;
    private int temperature;
    private int respirationRate;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;

    public TPRB() {
    }

    public TPRB(int bpMin, int bpMax, int pulseRate, int temperature,
                int respirationRate, Patient patient,
                EmerEncounter encounter, Date createdDate) {

        this.bpMin = bpMin;
        this.bpMax = bpMax;
        this.pulseRate = pulseRate;
        this.temperature = temperature;
        this.respirationRate = respirationRate;
        this.patient = patient;
        this.encounter = encounter;
        this.createdDate = createdDate;
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

    public EmerEncounter getEncounter() {
        return encounter;
    }

    public void setEncounter(EmerEncounter encounter) {
        this.encounter = encounter;
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

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }
}
