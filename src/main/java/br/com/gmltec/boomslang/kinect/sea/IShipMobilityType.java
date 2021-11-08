package br.com.gmltec.boomslang.kinect.sea;

import br.com.gmltec.boomslang.core.entities.IEntityType;

public interface IShipMobilityType extends IEntityType {
	
	public double getDivingSpeed();
	public double getSurfaceCruizeSpeed();
	public double getUnderwaterCruizeSpeed();
	public double getSurfacingSpeed();
	
	public double divingRate();
	public double surfacingRate();
	
	public double getAutonomy();
	
	public double getConsume(long time_sec);
	
	public double getMaximumSpeed();
	public double getMaximumThrottle();

}
