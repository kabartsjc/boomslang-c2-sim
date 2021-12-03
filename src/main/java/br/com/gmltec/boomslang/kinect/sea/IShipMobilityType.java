package br.com.gmltec.boomslang.kinect.sea;

import br.com.gmltec.boomslang.core.entities.IEntityType;

public class IShipMobilityType implements IEntityType {
	
	private long id;
	private String name;
	
	private double divingSpeed;
	private double surfaceCruizeSpeed;
	private double underwaterCruizeSpeed;
	private double surfacingSpeed;
	
	private double divingRate;
	private double surfacingRate;
	
	private double consume;
	
	public long getID() {
		return id;
	}
	
	public String getName() {
		return name;
	}
	
	public double getDivingSpeed_ms() {
		return divingSpeed;
	};
	
	public double getSurfaceCruizeSpeed_ms() {
		return surfaceCruizeSpeed;
	}
	
	public double getUnderwaterCruizeSpeed_ms() {
		return underwaterCruizeSpeed;
	}
	
	public double getSurfacingSpeed_ms() {
		return surfacingSpeed;
	}
	
	public double getDivingRate_ms() {
		return divingRate;
	}
	
	public double getSurfacingRate_ms() {
		return surfacingRate;
	}
	
	
	public double getConsume_ms() {
		return consume;
	}
	

}
