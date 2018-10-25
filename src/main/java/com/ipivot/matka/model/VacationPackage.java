package com.ipivot.matka.model;

import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.ipivot.matka.service.util.JsonDateSerializer;

public class VacationPackage {

	private Integer id;
	private Resort resort;
	private Flight flight;
	private BigDecimal totalCost;
	private String noOfDays;
	private Date startDate;
	private Date endDate;
	
	private String title;
	private String duration;
	private String briefDesc;
	// might be just a flag to indicate the state of a package; might apply for
	// a logged in user when he adds this as favorite. we want to show this as
	// invalid
	private Boolean validity;
	private String rating;
	// placeholder for day-wise details
	private String notes;
	private BigDecimal price;

	public VacationPackage() {

	}

	public VacationPackage(Integer id, String title, Resort resort, String duration, String briefDesc, String desc,
			Boolean validity, String rating) {
		this.id = id;
		this.title = title;
		this.resort = resort;
		this.duration = duration;
		this.briefDesc = briefDesc;
		this.validity = validity;
		this.rating = rating;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Resort getResort() {
		return resort;
	}

	public void setResort(Resort resort) {
		this.resort = resort;
	}

	public Flight getFlight() {
		return flight;
	}

	public void setFlight(Flight flight) {
		this.flight = flight;
	}

	public BigDecimal getTotalCost() {
		return totalCost;
	}

	public void setTotalCost(BigDecimal totalCost) {
		this.totalCost = totalCost;
	}

	public String getNoOfDays() {
		return noOfDays;
	}

	public void setNoOfDays(String noOfDays) {
		this.noOfDays = noOfDays;
	}
	
	@JsonSerialize(using=JsonDateSerializer.class)
	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	
	@JsonSerialize(using=JsonDateSerializer.class)
	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDuration() {
		return duration;
	}

	public void setDuration(String duration) {
		this.duration = duration;
	}

	public String getBriefDesc() {
		return briefDesc;
	}

	public void setBriefDesc(String briefDesc) {
		this.briefDesc = briefDesc;
	}

	public Boolean getValidity() {
		return validity;
	}

	public void setValidity(Boolean validity) {
		this.validity = validity;
	}

	public String getRating() {
		return rating;
	}

	public void setRating(String rating) {
		this.rating = rating;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "VacationPackage [id=" + id + ", resort=" + resort + ", flight=" + flight + ", totalCost=" + totalCost
				+ ", noOfDays=" + noOfDays + ", startDate=" + startDate + ", endDate=" + endDate + ", title=" + title
				+ ", duration=" + duration + ", briefDesc=" + briefDesc + ", validity=" + validity + ", rating="
				+ rating + ", notes=" + notes + ", price=" + price + "]";
	}


}
