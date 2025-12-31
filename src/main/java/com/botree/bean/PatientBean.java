package com.botree.bean;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.botree.dao.PatientDao;
import com.botree.model.Patient;

import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;

@Component
@Scope("session")
public class PatientBean {

    @Autowired
    private PatientDao patientDao;

    private String searchName;

    private Patient patient;


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


    public void updatePatient() {
        if (patient == null || patient.getPid() == 0) {
            FacesContext.getCurrentInstance().addMessage("msg",
                    new FacesMessage(FacesMessage.SEVERITY_ERROR,
                            "Error", "No patient selected"));
            return;
        }

        patientDao.update(patient);

        FacesContext.getCurrentInstance().addMessage("msg",
                new FacesMessage(FacesMessage.SEVERITY_INFO,
                        "Success", "Patient updated successfully"));
    }

 
}
