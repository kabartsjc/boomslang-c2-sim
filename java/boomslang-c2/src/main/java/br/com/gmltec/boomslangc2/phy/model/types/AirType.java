package br.com.gmltec.boomslangc2.phy.model.types;

import br.com.gmltec.boomslangc2.phy.model.geo.Coordinate;

public class AirType extends IEntityType{
	protected double speed;
	protected double autonomy;
	protected double climb_rate;
	
	public AirType(String id, 
			double lethality_factor,double resilience_factor,double vulnerability_factor,
			double speed, double autonomy, double climb_rate, double cost) {
		super.id=id;
		super.class_type="AF";
		super.lethality_factor=lethality_factor;
		super.resilience_factor=resilience_factor;
		super.vulnerability_factor=vulnerability_factor;
		this.speed=speed;
		this.autonomy=autonomy;
		this.climb_rate=climb_rate;
		super.cost=cost;
	}
	
	public AirType(String id, 
			double lethality_factor,double resilience_factor,
			double vulnerability_factor, double cost) {
		super.id=id;
		super.class_type="AF";
		super.lethality_factor=lethality_factor;
		super.resilience_factor=resilience_factor;
		super.vulnerability_factor=vulnerability_factor;
		this.speed=0;
		this.autonomy=-1;
		super.cost=cost;
		this.climb_rate=-1;
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

	public double getClimbRate() {
		return climb_rate;
	}

	protected Coordinate move(double bearing, double target_altitude, long delta_time) {
		return null;
	}

}
