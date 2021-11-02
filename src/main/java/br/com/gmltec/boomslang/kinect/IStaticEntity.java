package br.com.gmltec.boomslang.kinect;

import br.com.gmltec.boomslang.core.geo.Coordinate;
import br.com.gmltec.boomslang.core.geo.ILocation;

public interface IStaticEntity extends IKinectEntity {
	public void setStartLocation (ILocation start_location);
	public ILocation getLocation();
	public Coordinate getLocationPosition();
	
	public long getEndTime();
	public void setEndTime(long endTime);
	

}
