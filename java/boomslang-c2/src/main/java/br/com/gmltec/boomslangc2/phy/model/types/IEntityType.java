package br.com.gmltec.boomslangc2.phy.model.types;

public abstract class IEntityType {
	protected String id;
	protected String class_type;//{SP - Space, LF - Land, AF  - Air, SS - Sea, CB - Cyber}
	protected double lethality_factor;
	protected double resilience_factor;
	protected double vulnerability_factor;
	protected double cost;
	

	public String getId() {
		return id;
	}
	
	public double getCost() {
		return cost;
	}
	
	public String getClassType() {
		return class_type;
	}
	
	public double getLethalityFactor() {
		return lethality_factor;
	}

	public double getResilienceFactor() {
		return resilience_factor;
	}

	public double getVulnerabilityFactor() {
		return vulnerability_factor;
	}

}
