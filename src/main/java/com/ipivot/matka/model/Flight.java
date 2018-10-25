package com.ipivot.matka.model;

public class Flight {

	private String name;
	private String type;
	private String sourceDepartureTime;
	private String destinationArrivalTime;
	private String destinationDepartureTime;
	private String sourceArrivalTime;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getSourceDepartureTime() {
		return sourceDepartureTime;
	}

	public void setSourceDepartureTime(String sourceDepartureTime) {
		this.sourceDepartureTime = sourceDepartureTime;
	}

	public String getDestinationArrivalTime() {
		return destinationArrivalTime;
	}

	public void setDestinationArrivalTime(String destinationArrivalTime) {
		this.destinationArrivalTime = destinationArrivalTime;
	}

	public String getDestinationDepartureTime() {
		return destinationDepartureTime;
	}

	public void setDestinationDepartureTime(String destinationDepartureTime) {
		this.destinationDepartureTime = destinationDepartureTime;
	}

	public String getSourceArrivalTime() {
		return sourceArrivalTime;
	}

	public void setSourceArrivalTime(String sourceArrivalTime) {
		this.sourceArrivalTime = sourceArrivalTime;
	}

	@Override
	public String toString() {
		return "Flight [name=" + name + ", type=" + type + ", sourceDepartureTime=" + sourceDepartureTime
				+ ", destinationArrivalTime=" + destinationArrivalTime + ", destinationDepartureTime="
				+ destinationDepartureTime + ", sourceArrivalTime=" + sourceArrivalTime + "]";
	}

}
