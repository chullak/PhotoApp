package com.code.challenge.model;

import javax.persistence.Embeddable;

@Embeddable
public class Geolocation {
	String lat;
	String lng;

	public String getLat() {
		return lat;
	}

	public void setLat(String lat) {
		this.lat = lat;
	}

	public String getLng() {
		return lng;
	}

	public void setLng(String lng) {
		this.lng = lng;
	}
}
