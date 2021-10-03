package com.caresoft.clinicapp;
import java.util.Date;
import java.util.ArrayList;
import java.util.HashSet;


public class Physician extends User implements PHIAdminCompliant, PHICompliantUser {
    
    private HashSet<Patient> patients;
    private ArrayList<String> securityIncidents;
    
    // ... you see other existing member variables. ...
    
    @Override
	public boolean assignPin(int pin) {
		if (String.valueOf(pin).length() != 6) {
		return false;
		} else {
			this.setPin(pin);
			return true;
		}
	}

    @Override
	public boolean isAuthorized(Integer confirmedAuthID) {
    	if (this.id != confirmedAuthID) {
    		this.authIncident();
    		return false;
    		} else {
    			return true;
    		}
	}

	@Override
	public ArrayList<String> reportSecurityIncidents() {
		return this.securityIncidents;
	}
    
    // TO DO: Constructor
    
    public void prescribeRXTo(Patient patient, Integer rxNumber) {
        patient.currentPrescriptionsByRX.add(rxNumber);
    }
    
    
    /**
	 * @param patients
	 * @param securityIncidents
	 */
	public Physician(HashSet<Patient> patients, ArrayList<String> securityIncidents) {
		super();
		this.patients = patients;
		this.securityIncidents = securityIncidents;
	}

	public void newIncident(String notes) {
        String report = String.format(
            "Datetime Submitted: %s \n,  Reported By ID: %s\n Notes: %s \n", 
            new Date(), this.id, notes
        );
        securityIncidents.add(report);
    }
    public void authIncident() {
        String report = String.format(
            "Datetime Submitted: %s \n,  ID: %s\n Notes: %s \n", 
            new Date(), this.id, "AUTHORIZATION ATTEMPT FAILED FOR THIS USER"
        );
        securityIncidents.add(report);
    }

	/**
	 * @return the patients
	 */
	public HashSet<Patient> getPatients() {
		return patients;
	}

	/**
	 * @param patients the patients to set
	 */
	public void setPatients(HashSet<Patient> patients) {
		this.patients = patients;
	}

	/**
	 * @return the securityIncidents
	 */
	public ArrayList<String> getSecurityIncidents() {
		return securityIncidents;
	}

	/**
	 * @param securityIncidents the securityIncidents to set
	 */
	public void setSecurityIncidents(ArrayList<String> securityIncidents) {
		this.securityIncidents = securityIncidents;
	}
    
    // TO DO: Setters & Getters
    
}
