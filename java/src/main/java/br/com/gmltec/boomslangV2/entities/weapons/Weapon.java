package br.com.gmltec.boomslangV2.entities.weapons;

public class Weapon implements IWeapon{
	private String id;
	private String domain ;
	private long range ;
	private String type;
	private double precision ;
	private double reliability ;
	private double intensity ;
	
	public Weapon(String id, String domain,
			long range, String type, 
			double precision, double reliability,
			double intensity) {
		super();
		this.id = id;
		this.domain = domain;
		this.range = range;
		this.type = type;
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

	public long getRange() {
		return range;
	}

	public String getType() {
		return type;
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
	
	

}
