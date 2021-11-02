package br.com.gmltec.boomslang.kinect;

import br.com.gmltec.boomslang.core.geo.Coordinate;
import br.com.gmltec.boomslang.core.geo.ILocation;

public interface IMobileEntity extends IKinectEntity {
	
	public void setStartLocation (ILocation start_location);
	public ILocation getStartLocation();
	public Coordinate getInitPosition();
	
	public double getBearing();
	
	public Coordinate getCurrentPosition();
	
	public double getWeatherConstraint();
	public void setWeatherConstraint(double factor);
	
	public INavPlan getNavigationPlan();
	public void setNavigationPlan(INavPlan navPlan);

}
