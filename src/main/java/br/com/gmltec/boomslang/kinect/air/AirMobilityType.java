package br.com.gmltec.boomslang.kinect.air;

import br.com.gmltec.boomslang.core.entities.IEntityType;

public class AirMobilityType implements IEntityType {
	
	private long id;
	private String name;
	private String imageName;
	private double descendentSpeed;
	private double cruizeSpeed;
	private double ascedentSpeed;

	private double ascedentRate;
	private double descendentRate;
	
	private double autonomy;
	
	public AirMobilityType(long id, String name, String imageName,double descendentSpeed, double cruizeSpeed, double ascedentSpeed,
			double ascedentRate, double descendentRate, double autonomy) {
		super();
		this.id = id;
		this.name = name;
		this.imageName=imageName;
		this.descendentSpeed = descendentSpeed;
		this.cruizeSpeed = cruizeSpeed;
		this.ascedentSpeed = ascedentSpeed;
		this.ascedentRate = ascedentRate;
		this.descendentRate = descendentRate;
		this.autonomy = autonomy;
	}

	public long getID() {
		return id;
	}
	
	public String getName() {
		return name;
	}
	
	public double getDescedentSpeed_ms() {
		return descendentSpeed;
	}
	
	public double getCruizeSpeed_ms() {
		return cruizeSpeed;
	}
	
	public double getAscedentSpeed_ms() {
		return ascedentSpeed;
	}
	
	public double getAscedentRate_ms() {
		return ascedentRate;
	}
	
	public double getDescendentRate_ms() {
		return descendentRate;
	}
	
	public double getAutonomy_sec() {
		return autonomy;
	}

	@Override
	public String getImageName() {
		return imageName;
	}
	
}
