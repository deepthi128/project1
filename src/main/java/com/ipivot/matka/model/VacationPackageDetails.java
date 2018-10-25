package com.ipivot.matka.model;

public class VacationPackageDetails {

	private VacationPackage vacationPackage;
	private String longDesc;

	public VacationPackageDetails() {
		
	}
	
	public VacationPackageDetails(int id, String title, Resort resort, String duration, String briefDesc, String desc, Boolean validity, String rating) {
		this.vacationPackage = new VacationPackage(id, title, resort, duration, briefDesc, desc, validity, rating);
		this.longDesc = desc;
		
	}

	public VacationPackage getVacationPackage() {
		return vacationPackage;
	}

	public void setVacationPackage(VacationPackage vacationPackage) {
		this.vacationPackage = vacationPackage;
	}

	public String getLongDesc() {
		return longDesc;
	}

	public void setLongDesc(String longDesc) {
		this.longDesc = longDesc;
	}

	@Override
	public String toString() {
		return "VacationPackageDetails [vacationPackage=" + vacationPackage + ", longDesc=" + longDesc + "]";
	}

}
