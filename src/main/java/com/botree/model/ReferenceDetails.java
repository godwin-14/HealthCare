package com.botree.model;

import jakarta.persistence.*;

@Entity
public class ReferenceDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int referenceId;


    private String referenceName;     

    private String referenceNotes;    

    private String referencePhone;    
    

    @OneToOne
    @JoinColumn(name = "encounter_id", nullable = false, unique = true)
    private EmerEncounter emergencyEncounter;  
    

    public ReferenceDetails() {
    }

    public ReferenceDetails(EmerEncounter emergencyEncounter,
                            String referenceName,
                            String referenceNotes,
                            String referencePhone) {
        this.emergencyEncounter = emergencyEncounter;
        this.referenceName = referenceName;
        this.referenceNotes = referenceNotes;
        this.referencePhone = referencePhone;
    }

    // ---------- Getters & Setters ----------

    public int getReferenceId() {
        return referenceId;
    }

    public EmerEncounter getEmergencyEncounter() {
        return emergencyEncounter;
    }

    public void setEmergencyEncounter(EmerEncounter emergencyEncounter) {
        this.emergencyEncounter = emergencyEncounter;
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

    public String getReferencePhone() {
        return referencePhone;
    }

    public void setReferencePhone(String referencePhone) {
        this.referencePhone = referencePhone;
    }
}
