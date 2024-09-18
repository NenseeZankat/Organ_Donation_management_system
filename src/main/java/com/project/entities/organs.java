package com.project.entities;

import java.sql.Date;



import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
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
@Table(name="organs")

public class organs {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int organId;
	
	@Column(name = "organName", length = 50, nullable = false)
	private String organName;
	
	@Column(name="available", nullable= false)
	private boolean available;
	
	@Column(name = "reasonOfDonation", length = 100)
	private String reasonOfDonation;
	
	
	@Column(name = "dateOfDonation")
	@Temporal(TemporalType.DATE)
	private Date dateOfDonation;
	
	
	@ManyToOne(cascade= {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.DETACH}, fetch = FetchType.LAZY)
	//@JsonManagedReference
	//@JsonIgnore
	@JoinColumn(name = "donorId", nullable = false)
	private donor Donor;
	
	@OneToOne(mappedBy = "Organ", cascade = CascadeType.ALL)
	private transplant Transplant;

	public organs() {
		super();
		// TODO Auto-generated constructor stub
	}

	public organs(int organId, String organName, boolean available, String reasonOfDonation, Date dateOfDonation,
			donor donor, transplant transplant) {
		super();
		this.organId = organId;
		this.organName = organName;
		this.available = available;
		this.reasonOfDonation = reasonOfDonation;
		this.dateOfDonation = dateOfDonation;
		this.Donor = donor;
		this.Transplant = transplant;
	}

	public int getOrganId() {
		return organId;
	}

	public void setOrganId(int organId) {
		this.organId = organId;
	}

	public String getOrganName() {
		return organName;
	}

	public void setOrganName(String organName) {
		this.organName = organName;
	}

	public boolean isAvailable() {
		return available;
	}

	public void setAvailable(boolean available) {
		this.available = available;
	}

	public String getReasonOfDonation() {
		return reasonOfDonation;
	}

	public void setReasonOfDonation(String reasonOfDonation) {
		this.reasonOfDonation = reasonOfDonation;
	}

	public Date getDateOfDonation() {
		return dateOfDonation;
	}

	public void setDateOfDonation(Date dateOfDonation) {
		this.dateOfDonation = dateOfDonation;
	}

	public donor getDonor() {
		return Donor;
	}

	public void setDonor(donor donor) {
		Donor = donor;
	}

	public transplant getTransplant() {
		return Transplant;
	}

	public void setTransplant(transplant transplant) {
		Transplant = transplant;
	}

	@Override
	public String toString() {
		return "organs [organId=" + organId + ", organName=" + organName + ", available=" + available
				+ ", reasonOfDonation=" + reasonOfDonation + ", dateOfDonation=" + dateOfDonation + ", Donor=" + Donor
				+ ", Transplant=" + Transplant + "]";
	}

	
	
}	
