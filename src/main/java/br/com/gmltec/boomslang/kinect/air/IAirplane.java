package br.com.gmltec.boomslang.kinect.air;

import br.com.gmltec.boomslang.core.entities.IEntityType;
import br.com.gmltec.boomslang.core.geo.Coordinate;
import br.com.gmltec.boomslang.core.geo.ILocation;
import br.com.gmltec.boomslang.kinect.IMobileEntity;

public abstract class IAirplane extends IMobileEntity{
	
	public enum AIR_NAVIG_STATUS {
		LANDED,
		CLIMB,
		HOVER,
		LANDING,
		CRUIZE
	}
	
	protected IAirMobilityType type;
	protected AIR_NAVIG_STATUS fstatus;
	
	
	
	@Override
	public IEntityType getType() {
		return type;
	}
	
	public void setStartLocation(ILocation start_location) {
		this.startLocation=start_location;
		this.currentPosition=startLocation.getCoordinate();
	}

	public ILocation getStartLocation() {
		return startLocation;
	}

	@Override
	public Coordinate getInitPosition() {
		return startLocation.getCoordinate();
	}
	
	
	public AIR_NAVIG_STATUS getFlightStatus() {
		return fstatus;
	}
	
	
}
