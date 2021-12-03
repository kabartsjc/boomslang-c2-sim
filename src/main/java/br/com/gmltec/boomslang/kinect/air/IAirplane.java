package br.com.gmltec.boomslang.kinect.air;

import br.com.gmltec.boomslang.core.entities.IEntityType;
import br.com.gmltec.boomslang.kinect.IKinectEntity;

public abstract class IAirplane extends IKinectEntity{
	public enum AIR_NAVIG_STATUS {
		LANDED,
		CLIMB,
		HOVER,
		LANDING,
		CRUIZE
	}
	
	protected AirMobilityType type;
	protected AIR_NAVIG_STATUS fstatus;
	
	
	@Override
	public IEntityType getType() {
		return type;
	}
	

	
	public AIR_NAVIG_STATUS getFlightStatus() {
		return fstatus;
	}
	
	
}
