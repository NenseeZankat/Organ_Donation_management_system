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
import jakarta.persistence.Transient;


@Entity
@Table(name = "donor")
public class donor{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int donorId;

    @Column(name = "donorName", length = 50, nullable = false)
    private String donorName;

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


    @OneToMany(mappedBy = "Donor", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    //@JsonBackReference
    private List<organs> organsList = new ArrayList<>();


	public donor() {
		super();
		// TODO Auto-generated constructor stub
	}


	public donor(int donorId, String donorName, Date dob, int age, char gender, String bloodType, String email,
			long phoneNo, String street, String city, String state, List<organs> organsList) {
		super();
		this.donorId = donorId;
		this.donorName = donorName;
		this.dob = dob;
		this.age = age;
		this.gender = gender;
		this.bloodType = bloodType;
		this.email = email;
		this.phoneNo = phoneNo;
		this.street = street;
		this.city = city;
		this.state = state;
		this.organsList = organsList;
	}


	public int getDonorId() {
		return donorId;
	}


	public void setDonorId(int donorId) {
		this.donorId = donorId;
	}


	public String getDonorName() {
		return donorName;
	}


	public void setDonorName(String donorName) {
		this.donorName = donorName;
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


	public List<organs> getOrgansList() {
		return organsList;
	}


	public void setOrgansList(List<organs> organsList) {
		this.organsList = organsList;
	}


	@Override
	public String toString() {
		return "donor [donorId=" + donorId + ", donorName=" + donorName + ", dob=" + dob + ", age=" + age + ", gender="
				+ gender + ", bloodType=" + bloodType + ", email=" + email + ", phoneNo=" + phoneNo + ", street="
				+ street + ", city=" + city + ", state=" + state + ", organsList=" + organsList + "]";
	}

    
    

	
}
