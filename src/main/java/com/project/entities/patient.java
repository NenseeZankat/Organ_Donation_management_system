package com.project.entities;

import java.sql.Date;

import java.time.LocalDate;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.persistence.Transient;


@Entity
@Table(name="patient")
public class patient{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int patientId;
	
	@Column(name = "patientName", length = 50, nullable = false)
    private String patientName;

    @Column(name = "dob", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date dob;

    @Transient
    private int age; // Calculated based on DOB


    @Column(name = "gender", length = 1, nullable = false)
    private char gender;

    @Column(name = "bloodType", length = 5, nullable = false)
    private String bloodType;

    @Column(name = "email", length = 50)
    private String email;

    @Column(name="phoneNo", length = 10, nullable=false)
	private long phoneNo;
    
    @Column(name = "street", length = 30, nullable = false)
    private String street;

    @Column(name = "city", length = 30, nullable = false)
    private String city;

    @Column(name = "state", length = 30, nullable = false)
    private String state;
    
    @Column(name = "organRequired", length = 50, nullable = false)
	private String organRequired;
    
    @Column(name = "reasonOfProcurement", length = 100)
	private String reasonOfProcurement;
    
    @Column(name = "requestDate")
    private LocalDate requestDate;
    
   
    
    @OneToOne(mappedBy="Patient" , cascade = CascadeType.ALL)
	private transplant transplant;



	public patient() {
		super();
		// TODO Auto-generated constructor stub
	}



	public patient(int patientId, String patientName, Date dob, int age, char gender, String bloodType, String email,
			long phoneNo, String street, String city, String state, String organRequired, String reasonOfProcurement,
			LocalDate requestDate, com.project.entities.transplant transplant) {
		super();
		this.patientId = patientId;
		this.patientName = patientName;
		this.dob = dob;
		this.age = age;
		this.gender = gender;
		this.bloodType = bloodType;
		this.email = email;
		this.phoneNo = phoneNo;
		this.street = street;
		this.city = city;
		this.state = state;
		this.organRequired = organRequired;
		this.reasonOfProcurement = reasonOfProcurement;
		this.requestDate = requestDate;
		this.transplant = transplant;
	}



	public int getPatientId() {
		return patientId;
	}



	public void setPatientId(int patientId) {
		this.patientId = patientId;
	}



	public String getPatientName() {
		return patientName;
	}



	public void setPatientName(String patientName) {
		this.patientName = patientName;
	}



	public Date getDob() {
		return dob;
	}



	public void setDob(Date dob) {
		this.dob = dob;
	}



	public int getAge() {
		return age;
	}



	public void setAge(int age) {
		this.age = age;
	}



	public char getGender() {
		return gender;
	}



	public void setGender(char gender) {
		this.gender = gender;
	}



	public String getBloodType() {
		return bloodType;
	}



	public void setBloodType(String bloodType) {
		this.bloodType = bloodType;
	}



	public String getEmail() {
		return email;
	}



	public void setEmail(String email) {
		this.email = email;
	}



	public long getPhoneNo() {
		return phoneNo;
	}



	public void setPhoneNo(long phoneNo) {
		this.phoneNo = phoneNo;
	}



	public String getStreet() {
		return street;
	}



	public void setStreet(String street) {
		this.street = street;
	}



	public String getCity() {
		return city;
	}



	public void setCity(String city) {
		this.city = city;
	}



	public String getState() {
		return state;
	}



	public void setState(String state) {
		this.state = state;
	}



	public String getOrganRequired() {
		return organRequired;
	}



	public void setOrganRequired(String organRequired) {
		this.organRequired = organRequired;
	}



	public String getReasonOfProcurement() {
		return reasonOfProcurement;
	}



	public void setReasonOfProcurement(String reasonOfProcurement) {
		this.reasonOfProcurement = reasonOfProcurement;
	}



	public LocalDate getRequestDate() {
		return requestDate;
	}



	public void setRequestDate(LocalDate requestDate) {
		this.requestDate = requestDate;
	}



	public transplant getTransplant() {
		return transplant;
	}



	public void setTransplant(transplant transplant) {
		this.transplant = transplant;
	}



	@Override
	public String toString() {
		return "patient [patientId=" + patientId + ", patientName=" + patientName + ", dob=" + dob + ", age=" + age
				+ ", gender=" + gender + ", bloodType=" + bloodType + ", email=" + email + ", phoneNo=" + phoneNo
				+ ", street=" + street + ", city=" + city + ", state=" + state + ", organRequired=" + organRequired
				+ ", reasonOfProcurement=" + reasonOfProcurement + ", requestDate=" + requestDate + ", transplant="
				+ transplant + "]";
	}


	
}
