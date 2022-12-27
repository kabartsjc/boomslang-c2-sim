package br.com.gmltec.boomslangc2.phy.model.types;

public class CyberType extends IEntityType{
	protected double autonomy;
	
	public CyberType(String id, 
			double lethality_factor,double resilience_factor,
			double vulnerability_factor,double autonomy, double cost) {
		super.id=id;
		super.class_type="CB";
		super.cost=cost;
		super.lethality_factor=lethality_factor;
		super.resilience_factor=resilience_factor;
		super.vulnerability_factor=vulnerability_factor;
		this.autonomy=autonomy;
	}

	public String getClassType() {
		return class_type;
	}

	public double getAutonomy() {
		return autonomy;
	}


	
	
}
