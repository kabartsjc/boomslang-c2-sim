package br.com.gmltec.boomslangV2.entities.types;

public class StaticEntity extends IEntityType{
	
	public StaticEntity(String id, String force, double lethality_factor, 
			double resiliance_factor,
			double vulnerability_factor) {
		super.id = id;
		super.force = force;
		super.lethality_factor = lethality_factor;
		super.resiliance_factor = resiliance_factor;
		super.vulnerability_factor = vulnerability_factor;
	}
	
	

}
