package br.com.gmltec.boomslangV2.entities.sensors;

import br.com.gmltec.boomslangV2.entities.IEntity;

public class Sensor implements ISensor {
	private String id;
	private String domain;
	private double range;
	private double precision;
	private double reliability;
	private double intensity;
	
	public Sensor(String id, String domain, double range, double precision, double reliability, double intensity) {
		super();
		this.id = id;
		this.domain = domain;
		this.range = range;
		this.precision = precision;
		this.reliability = reliability;
		this.intensity = intensity;
	}

	public String getId() {
		return id;
	}

	public String getDomain() {
		return domain;
	}

	public double getRange() {
		return range;
	}

	public double getPrecision() {
		return precision;
	}

	public double getReliability() {
		return reliability;
	}

	public double getIntensity() {
		return intensity;
	}

	@Override
	public boolean isSense(IEntity ent) {
		// TODO Auto-generated method stub
		return false;
	}

}
