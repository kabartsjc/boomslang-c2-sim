package br.com.gmltec.boomslang.kinect.sea;

import br.com.gmltec.boomslang.core.entities.IEntityType;
import br.com.gmltec.boomslang.kinect.IKinectEntity;

public abstract class IBattleShip extends IKinectEntity{
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
	
	
	public SHIP_NAVIG_STATUS getShipNavigationStatus() {
		return nstatus;
	}
	
}
