package br.com.gmltec.boomslang.kinect.impl;

import br.com.gmltec.boomslang.core.entities.IEntityType;
import br.com.gmltec.boomslang.core.geo.Coordinate;
import br.com.gmltec.boomslang.core.geo.ILocation;
import br.com.gmltec.boomslang.kinect.IMobileEntity;
import br.com.gmltec.boomslang.kinect.IAirMobilityType;
import br.com.gmltec.boomslang.kinect.INavPlan;

public class Aircraft implements IMobileEntity{
	
	private long id;
	private String name;
	private IAirMobilityType type;
	private DOMAIN domain;
	private STATUS status;
	
	private long currentTime;
	private long startTime;
	
	private ILocation startLocation;
	private Coordinate currentPosition;
	
	private double bearing;
	
	private double wFactor;
	
	private INavPlan navPlan;
	
	public Aircraft(long id, String name, 
			IAirMobilityType type) {
		super();
		this.id = id;
		this.name = name;
		this.type = type;
		this.domain = DOMAIN.AIR;
		this.status = STATUS.CREATED;
	}
	
	@Override
	public long getID() {
		return id;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public IEntityType getType() {
		return type;
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

	@Override
	public void setStartLocation(ILocation start_location) {
		this.startLocation=start_location;
		this.currentPosition=startLocation.getCoordinate();
	}

	@Override
	public ILocation getStartLocation() {
		return startLocation;
	}

	@Override
	public Coordinate getInitPosition() {
		return startLocation.getCoordinate();
	}

	@Override
	public Coordinate getCurrentPosition() {
		return currentPosition;
	}


	@Override
	public double getWeatherConstraint() {
		return wFactor;
	}

	@Override
	public void setWeatherConstraint(double factor) {
		this.wFactor=factor;
	}
	
	@Override
	public INavPlan getNavigationPlan() {
		return navPlan;
	}

	@Override
	public void setNavigationPlan(INavPlan navPlan) {
		this.navPlan=navPlan;
	}
	
	@Override
	public double getBearing() {
		return bearing;
	}

	@Override
	public void update(long update_interval_sec) {
		// TODO Auto-generated method stub
		
	}

	
	

}
