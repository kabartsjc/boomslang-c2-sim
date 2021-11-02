package br.com.gmltec.boomslang.kinect.impl;

import br.com.gmltec.boomslang.core.entities.IEntityType;
import br.com.gmltec.boomslang.core.geo.Coordinate;
import br.com.gmltec.boomslang.core.geo.ILocation;
import br.com.gmltec.boomslang.kinect.IStaticEntity;

public class CyberRanger implements IStaticEntity {
	private long id;
	private String name;
	private IEntityType type;
	private DOMAIN domain;
	private STATUS status;
	
	private long currentTime;
	private long startTime;
	private long endTime;
	
	private ILocation startLocation;
	
	public CyberRanger(long id, String name, IEntityType type) {
		super();
		this.id = id;
		this.name = name;
		this.type = type;
		this.domain = DOMAIN.CYBER;
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
	public long getStartTime() {
		return startTime;
	}
	
	@Override
	public void setStartTime(long time_sec) {
		this.startTime=time_sec;
	}

	@Override
	public long getCurrentTime() {
		return currentTime;
	}
	
	@Override
	public long getEndTime() {
		return endTime;
	}

	@Override
	public void setEndTime(long endTime) {
		this.endTime=endTime;
	}

	@Override
	public void setStartLocation(ILocation start_location) {
		this.startLocation=start_location;
	}

	@Override
	public ILocation getLocation() {
		return startLocation;
	}

	@Override
	public Coordinate getLocationPosition() {
		return startLocation.getCoordinate();
	}
	

	@Override
	public void update(long update_interval_sec) {
		currentTime=currentTime+update_interval_sec;
		
		if (currentTime>=endTime) {
			status = STATUS.FINISHED;
		}
	}

}
