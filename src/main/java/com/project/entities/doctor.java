package com.project.entities;

import java.sql.Date;

import java.util.ArrayList;
import java.util.List;



import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;


@Entity
@Table(name="doctor")
public class doctor {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int doctorId;
	
	@Column(name="doctorName", length=50, nullable=false)
	private String doctorName;
	
	@Column(name="qualification", length=50, nullable=false)
	private String qualification;
	
	@Column(name="phoneNo", length=10, nullable=false)
	private long phoneNo;
	
	@Column(name="email", length=50, nullable=false)
	private String email;
	
	@Column(name="since")
	@Temporal(TemporalType.DATE)
	private Date since;
	
	
	@OneToMany(mappedBy = "Doctor", cascade = CascadeType.ALL,  fetch = FetchType.LAZY)
	private List<transplant> transplantList = new ArrayList<>();

	 
	public doctor() {
		super();
		// TODO Auto-generated constructor stub
	}


	public doctor(int doctorId, String doctorName, String qualification, long phoneNo, String email, Date since,
			List<transplant> transplantList) {
		super();
		this.doctorId = doctorId;
		this.doctorName = doctorName;
		this.qualification = qualification;
		this.phoneNo = phoneNo;
		this.email = email;
		this.since = since;
		this.transplantList = transplantList;
	}


	public int getDoctorId() {
		return doctorId;
	}


	public void setDoctorId(int doctorId) {
		this.doctorId = doctorId;
	}


	public String getDoctorName() {
		return doctorName;
	}


	public void setDoctorName(String doctorName) {
		this.doctorName = doctorName;
	}


	public String getQualification() {
		return qualification;
	}


	public void setQualification(String qualification) {
		this.qualification = qualification;
	}


	public long getPhoneNo() {
		return phoneNo;
	}


	public void setPhoneNo(long phoneNo) {
		this.phoneNo = phoneNo;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public Date getSince() {
		return since;
	}


	public void setSince(Date since) {
		this.since = since;
	}


	public List<transplant> getTransplantList() {
		return transplantList;
	}


	public void setTransplantList(List<transplant> transplantList) {
		this.transplantList = transplantList;
	}


	@Override
	public String toString() {
		return "doctor [doctorId=" + doctorId + ", doctorName=" + doctorName + ", qualification=" + qualification
				+ ", phoneNo=" + phoneNo + ", email=" + email + ", since=" + since + ", transplantList="
				+ transplantList + "]";
	}

	
	
}

