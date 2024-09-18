package com.project.entities;

import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;


@Entity
@Table(name="transplant")
public class transplant {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int transId;
	
	@Column(name = "dateOfTransplantation")
	@Temporal(TemporalType.DATE)
	private Date dateOfTransplantation;
	
	@Column(name = "success", nullable = false)
	private boolean success;
	
	@OneToOne
	@JoinColumn(name = "organId",  nullable = false)
	private organs Organ;

	@OneToOne
	@JoinColumn(name = "patientId", nullable = false)
	private patient Patient;
	
	@ManyToOne
	@JoinColumn(name = "doctorId",  nullable = false)
	private doctor Doctor;

	
	
	public transplant() {
		super();
		// TODO Auto-generated constructor stub
	}

	public transplant(int transId, Date dateOfTransplantation, boolean success, organs organ, patient patient,
			doctor doctor) {
		super();
		this.transId = transId;
		this.dateOfTransplantation = dateOfTransplantation;
		this.success = success;
		this.Organ = organ;
		this.Patient = patient;
		this.Doctor = doctor;
	}

	public int getTransId() {
		return transId;
	}

	public void setTransId(int transId) {
		this.transId = transId;
	}

	public Date getDateOfTransplantation() {
		return dateOfTransplantation;
	}

	public void setDateOfTransplantation(Date dateOfTransplantation) {
		this.dateOfTransplantation = dateOfTransplantation;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public organs getOrgan() {
		return Organ;
	}

	public void setOrgan(organs organ) {
		Organ = organ;
	}

	public patient getPatient() {
		return Patient;
	}

	public void setPatient(patient patient) {
		Patient = patient;
	}

	public doctor getDoctor() {
		return Doctor;
	}

	public void setDoctor(doctor doctor) {
		Doctor = doctor;
	}

	@Override
	public String toString() {
		return "transplant [transId=" + transId + ", dateOfTransplantation=" + dateOfTransplantation + ", success="
				+ success + ", Organ=" + Organ + ", Patient=" + Patient + ", Doctor=" + Doctor + "]";
	}

	
	
	
}
