package br.com.gmltec.boomslangV2.plan;

import java.util.ArrayList;
import java.util.List;

import br.com.gmltec.boomslangV2.core.geo.Coordinate;
import br.com.gmltec.boomslangV2.entities.IEntity;

public class Task implements ITask {
	protected String id;
	protected IEntity target;
	protected double effectiveness;
	protected String type;
	
	protected List<Coordinate>route;
	
	protected boolean is_time_on_target;
	protected double time_on_target;
	protected List<String> requiredSensor;
	protected List<String> requiredWeapons;
	
	public Task(String id, IEntity target, String type,double effectiveness) {
		super();
		this.id = id;
		this.target = target;
		this.type=type;
		this.effectiveness = effectiveness;
		this.route= new ArrayList<>();
		this.requiredSensor=new ArrayList<>();
		this.requiredWeapons=new ArrayList<>();
	}

	public String getId() {
		return id;
	}

	public IEntity getTarget() {
		return target;
	}
	
	public String getType() {
		return type;
	}

	public double getEffectiveness() {
		return effectiveness;
	}

	public List<Coordinate> getRoutes() {
		return route;
	}
	public boolean isIs_time_on_target() {
		return is_time_on_target;
	}

	public double getTime_on_target() {
		return time_on_target;
	}

	public List<String> getRequiredSensor() {
		return requiredSensor;
	}

	public List<String> getRequiredWeapons() {
		return requiredWeapons;
	}

	public void setIs_time_on_target(boolean is_time_on_target) {
		this.is_time_on_target = is_time_on_target;
	}

	public void setTime_on_target(double time_on_target) {
		this.time_on_target = time_on_target;
	}
	
	public void addRoute(Coordinate coord) {
		this.route.add(coord);
	}
	
	public void addRequiredWeapon(String wp) {
		this.requiredWeapons.add(wp);
	}
	
	public  void addRequiredSensor(String sens) {
		this.requiredSensor.add(sens);
	}
	
	
	
	
	
	
	
	

}
