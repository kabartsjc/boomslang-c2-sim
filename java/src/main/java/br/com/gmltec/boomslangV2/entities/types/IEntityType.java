package br.com.gmltec.boomslangV2.entities.types;

public abstract class IEntityType {
	protected String id;
	protected String force;
	protected double lethality_factor;
	protected double resiliance_factor;
	protected double vulnerability_factor;
	
	public String getId() {
		return id;
	}

	public String getForce() {
		return force;
	}

	public double getLethalityFactor() {
		return lethality_factor;
	}

	public double getResilianceFactor() {
		return resiliance_factor;
	}

	public double getVulnerabilityFactor() {
		return vulnerability_factor;
	}
	
	
	
	

}
