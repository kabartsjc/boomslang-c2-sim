package br.com.gmltec.boomslangc2.phy.model.geo;

import org.jxmapviewer.viewer.GeoPosition;

public class Coordinate extends GeoPosition{
	private static final long serialVersionUID = -5416349930738708135L;
	private double altitude;

	public Coordinate(double latitude, double longitude, double altitude) {
		super(latitude, longitude);
		this.altitude = altitude;
	}

	public double getLatitude() {
		return super.getLatitude();
	}

	public double getLongitude() {
		return super.getLongitude();
	}

	public double getAltitude() {
		return altitude;
	}

}
