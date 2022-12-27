package br.com.gmltec.boomslangV2.core.geo;

public class Coordinate {
	
	private double latitude;
	private double longitude;
	private double altitude;
	
	public Coordinate() {
		
	}

	public Coordinate(double latitude, double longitude, double altitude) {
		super();
		this.latitude = latitude;
		this.longitude = longitude;
		this.altitude = altitude;
	}

	public double getLatitude() {
		return latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public double getAltitude() {
		return altitude;
	}
	
	public void setAltitude(double alt) {
		this.altitude=alt;
	}

	@Override
	public String toString() {
		return "Coordinate [latitude=" + latitude + ", longitude=" + longitude + ", altitude=" + altitude + "]";
	}
	
	


}
