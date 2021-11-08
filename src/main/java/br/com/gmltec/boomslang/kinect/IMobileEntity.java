package br.com.gmltec.boomslang.kinect;

import br.com.gmltec.boomslang.core.entities.IEntityType;
import br.com.gmltec.boomslang.core.geo.Coordinate;
import br.com.gmltec.boomslang.core.geo.ILocation;
import br.com.gmltec.boomslang.core.simu.DistributionUtils;
import br.com.gmltec.boomslang.kinect.air.IAirMobilityType;
import br.com.gmltec.boomslang.kinect.navplan.INavPlan;

public abstract class IMobileEntity implements IKinectEntity {
	protected long id;
	protected String name;
	protected IAirMobilityType type;
	protected DOMAIN domain;
	protected STATUS status;
	
	protected long currentTime;
	protected long startTime;
	
	protected ILocation startLocation;
	
	protected Coordinate currentPosition;
	protected Coordinate targetPosition;
	
	//measure the resilience of aircraft to Kinect extreme events -> close to 1 is more resilient
	protected double kinect_resilience; 
	
	protected INavPlan navPlan;
	
	protected double bearing;
	
	protected DistributionUtils distUtils;
	
	protected double autonomy;
	
	@Override
	public long getID() {
		return id;
	}

	@Override
	public String getName() {
		return name;
	}
	
	@Override
	public DOMAIN getDomain() {
		return domain;
	}
	
	@Override
	public STATUS getStatus() {
		return status;
	}
	
	@Override
	public void setStatus(STATUS status) {
		this.status=status;
	}
	
	@Override
	public long getCurrentTime() {
		return currentTime;
	}
	
	@Override
	public void setStartTime(long time_sec) {
		this.startTime=time_sec;
	}

	@Override
	public long getStartTime() {
		return startTime;
	}

	public Coordinate getCurrentPosition() {
		return currentPosition;
	}

	public Coordinate getTargetPosition() {
		return targetPosition;
	}

	public double getKinectResilience() {
		return kinect_resilience;
	}
	
	public INavPlan getNavigationPlan() {
		return navPlan;
	}

	public void setNavigationPlan(INavPlan navPlan) {
		this.navPlan=navPlan;
	}
	
	public double getBearing() {
		return bearing;
	}
	
	public void setDistributionUtils(DistributionUtils distUtils) {
		this.distUtils=distUtils;
	}
	
	
	public abstract Coordinate getInitPosition();
	public abstract IEntityType getType();
	
	public abstract void update(long update_interval_sec, double kinect_cond_factor);

	
	

}
