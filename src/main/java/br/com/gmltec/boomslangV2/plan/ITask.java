package br.com.gmltec.boomslangV2.plan;

import java.util.List;

import br.com.gmltec.boomslangV2.core.geo.Coordinate;
import br.com.gmltec.boomslangV2.entities.IEntity;

public interface ITask {
	
	public enum STATUS {
		INIT, FAIL, SUCESSFUL
	}
	
	public String getId() ;
	
	public STATUS getStatus();
	
	public void setStatus(STATUS status);

	public IEntity getTarget() ;
	
	public String getType() ;

	public double getEffectiveness() ;

	public List<Coordinate> getRoutes();
	
	public boolean isIs_time_on_target() ;

	public double getTime_on_target() ;

	public List<String> getRequiredSensor() ;

	public List<String> getRequiredWeapons() ;

	public void setIs_time_on_target(boolean is_time_on_target) ;
	
	public void setTime_on_target(double time_on_target) ;
	
	public void addRoute(Coordinate coord) ;
	
	public void addRequiredWeapon(String wp);
	
	public  void addRequiredSensor(String sens) ;
	
	
	

}
