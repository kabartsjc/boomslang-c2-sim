package br.com.gmltec.boomslang.kinect.sea;

import br.com.gmltec.boomslang.core.entities.IEntityType;
import br.com.gmltec.boomslang.core.entities.IEntity.DOMAIN;
import br.com.gmltec.boomslang.core.geo.Coordinate;
import br.com.gmltec.boomslang.core.geo.ILocation;
import br.com.gmltec.boomslang.core.simu.DistributionUtils;
import br.com.gmltec.boomslang.kinect.IMobileEntity;
import br.com.gmltec.boomslang.kinect.IKinectEntity.STATUS;
import br.com.gmltec.boomslang.kinect.air.IAirMobilityType;
import br.com.gmltec.boomslang.kinect.air.IAirplane.AIR_NAVIG_STATUS;
import br.com.gmltec.boomslang.kinect.navplan.INavPlan;

public abstract class INavyShip extends IMobileEntity{
	public enum SHIP_NAVIG_STATUS {
		DOCKED,
		DIVING,
		SURFACING,
		SURFACE_CRUIZE, 
		UNDERWATER_CRUIZE
	}
	
	protected IShipMobilityType type;
	protected SHIP_NAVIG_STATUS nstatus;
	
	
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
	
	
	public SHIP_NAVIG_STATUS getShipNavigationStatus() {
		return nstatus;
	}
	
}
