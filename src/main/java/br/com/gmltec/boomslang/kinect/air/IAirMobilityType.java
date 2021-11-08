package br.com.gmltec.boomslang.kinect.air;

import br.com.gmltec.boomslang.core.entities.IEntityType;

public interface IAirMobilityType extends IEntityType {
	public double getDescedentSpeed();
	public double getCruizeSpeed();
	public double getAscedentSpeed();
	
	public double ascedentRate();
	public double descendentRate();
	
	public double getAutonomy();
	
	public double getConsume(long time_sec);
	
	public double getMaximumSpeed();
	public double getStallSpeed();
	public double getMaximumThrottle();
}
