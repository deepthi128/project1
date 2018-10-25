package com.ipivot.matka.model;

import java.util.List;

public class Resort {

	private String name;
	private String rating;
	private String street;
	private String city;
	private String state;
	private String country;
	private String zipCode;
	private String features;
	private List<String> images;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRating() {
		return rating;
	}

	public void setRating(String rating) {
		this.rating = rating;
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

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public String getFeatures() {
		return features;
	}

	public void setFeatures(String features) {
		this.features = features;
	}

	public List<String> getImages() {
		return images;
	}

	public void setImages(List<String> images) {
		this.images = images;
	}

	@Override
	public String toString() {
		return "Resort [name=" + name + ", rating=" + rating + ", street=" + street + ", city=" + city + ", state="
				+ state + ", country=" + country + ", zipCode=" + zipCode + ", features=" + features + ", images="
				+ images + "]";
	}

}
