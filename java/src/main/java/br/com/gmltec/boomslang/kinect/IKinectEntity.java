package br.com.gmltec.boomslang.kinect;

import br.com.gmltec.boomslang.core.entities.IEntity;
import br.com.gmltec.boomslang.core.entities.IEntityType;
import br.com.gmltec.boomslang.core.geo.Coordinate;
import br.com.gmltec.boomslang.core.geo.ILocation;
import br.com.gmltec.boomslang.core.simu.DistributionUtils;
import br.com.gmltec.boomslang.kinect.navplan.INavPlan;

public abstract class IKinectEntity implements IEntity{
	
	protected long id;
	protected String name;
	protected DOMAIN domain;
	protected STATUS status;
	
	protected long currentTime;
	protected long startTime;
	
	protected ILocation startLocation;
	
	protected Coordinate currentPosition;
	protected Coordinate targetPosition;
	
	protected double autonomy;
	
	//measure the resilience of aircraft to Kinect extreme events -> close to 1 is more resilient
	protected double kinect_resilience; 
	
	protected INavPlan navPlan;
	
	protected DistributionUtils distUtils;
	
	
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
	
	public void setDistributionUtils(DistributionUtils distUtils) {
		this.distUtils=distUtils;
	}
	

	public void setStartLocation(ILocation start_location) {
		this.startLocation=start_location;
		this.currentPosition=startLocation.getCoordinate();
	}

	public ILocation getStartLocation() {
		return startLocation;
	}
	
	public Coordinate getInitPosition() {
		return startLocation.getCoordinate();
	}
	
	public abstract IEntityType getType();
	
	public abstract void update(long update_interval_sec, double kinect_cond_factor);

	
	

}
