package com.ipivot.matka.model;

public class PurchaseOrder {

	private long poId;
	private VacationPackage vacationPackage;
	private User user;
	private String departureCity;
	private String userNotes;

	public long getPoId() {
		return poId;
	}

	public void setPoId(long poId) {
		this.poId = poId;
	}

	public VacationPackage getVacationPackage() {
		return vacationPackage;
	}

	public void setVacationPackage(VacationPackage vacationPackage) {
		this.vacationPackage = vacationPackage;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getDepartureCity() {
		return departureCity;
	}

	public void setDepartureCity(String departureCity) {
		this.departureCity = departureCity;
	}

	public String getUserNotes() {
		return userNotes;
	}

	public void setUserNotes(String userNotes) {
		this.userNotes = userNotes;
	}

	@Override
	public String toString() {
		return "PurchaseOrder [poId=" + poId + ", vacationPackage=" + vacationPackage + ", user=" + user
				+ ", departureCity=" + departureCity + ", userNotes=" + userNotes + "]";
	}

}
