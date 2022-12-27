package br.com.gmltec.boomslangV2.entities.sensors;

import java.util.Random;

import br.com.gmltec.boomslangV2.core.utils.GeoUtils;
import br.com.gmltec.boomslangV2.entities.IEntity;

public class Sensor implements ISensor {
	private String id;
	private String domain;
	private double range;
	private double precision;
	private double reliability;
	private double intensity;
	private Random rand;
	
	public Sensor(String id, String domain, double range, double precision, double reliability, double intensity) {
		super();
		this.id = id;
		this.domain = domain;
		this.range = range;
		this.precision = precision;
		this.reliability = reliability;
		this.intensity = intensity;
		rand = new Random(System.currentTimeMillis());
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
	public boolean isSense(IEntity ent, IEntity target) {
		double distance = GeoUtils.calculateHorizontalDistanceMeters(ent.getCurrentPosition(), target.getCurrentPosition());
		double sense = precision*intensity*range*rand.nextDouble();
		
		if (sense>distance) {
			return true;
		}
		
		return false;
	}

}
