package br.com.gmltec.boomslangc2.phy.model.types;

import br.com.gmltec.boomslangc2.phy.model.geo.Coordinate;

public class SpaceType extends IEntityType{
	protected double speed;
	protected double autonomy;
	
	public SpaceType(String id, 
			double lethality_factor,double resilience_factor,double vulnerability_factor,
			double speed, double autonomy, double cost) {
		super.id=id;
		super.class_type="SP";
		super.lethality_factor=lethality_factor;
		super.resilience_factor=resilience_factor;
		super.vulnerability_factor=vulnerability_factor;
		this.speed=speed;
		super.cost=cost;
		this.autonomy=autonomy;
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
	
	protected Coordinate move(double bearing, double target_altitude, long delta_time) {
		return null;
	}

}
