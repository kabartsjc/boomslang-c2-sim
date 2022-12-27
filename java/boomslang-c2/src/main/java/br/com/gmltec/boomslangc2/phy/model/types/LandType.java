package br.com.gmltec.boomslangc2.phy.model.types;

import br.com.gmltec.boomslangc2.phy.model.geo.Coordinate;

public class LandType extends IEntityType{
	protected double speed;
	protected double autonomy;
	
	public LandType(String id, 
			double lethality_factor,double  resilience_factor,double vulnerability_factor,
			double speed, double autonomy, double cost) {
		super.id=id;
		super.class_type="LF";
		super.lethality_factor=lethality_factor;
		super.resilience_factor=resilience_factor;
		super.vulnerability_factor=vulnerability_factor;
		this.speed=speed;
		this.autonomy=autonomy;
		super.cost=cost;
	}
	
	public LandType(String id, 
			double lethality_factor,double resilience_factor, 
			double vulnerability_factor, double cost) {
		super.id=id;
		super.class_type="LF";
		super.cost=cost;
		super.lethality_factor=lethality_factor;
		super.resilience_factor=resilience_factor;
		super.vulnerability_factor=vulnerability_factor;
		this.speed=-1;
		this.autonomy=-1;
	}
	
	public double getSpeed() {
		return speed;
	}

	public String getClassType() {
		return class_type;
	}

	public double getAutonomy() {
		return autonomy;
	}
	
	protected Coordinate move(double bearing, long delta_time) {
		return null;
	}

}
