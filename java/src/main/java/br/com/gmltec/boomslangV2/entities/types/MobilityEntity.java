package br.com.gmltec.boomslangV2.entities.types;

public class MobilityEntity extends IEntityType {
	protected double speed;
	protected double climb_rate;
	protected double autononomy;
	
	public MobilityEntity(String id, String force, double lethality_factor, 
			double resiliance_factor,
			double vulnerability_factor,
			double speed, double climb_rate, double autonomy) {
		super.id = id;
		super.force = force;
		super.lethality_factor = lethality_factor;
		super.resiliance_factor = resiliance_factor;
		super.vulnerability_factor = vulnerability_factor;
		this.speed=speed;
		this.climb_rate=climb_rate;
		this.autononomy=autonomy;
	}

	public double getSpeed() {
		return speed;
	}

	public double getClimbRate() {
		return climb_rate;
	}

	public double getAutononomy() {
		return autononomy;
	}
	
	
	

}
