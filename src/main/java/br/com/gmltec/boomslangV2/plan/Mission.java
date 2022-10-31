package br.com.gmltec.boomslangV2.plan;

import java.util.List;

import br.com.gmltec.boomslangV2.entities.IEntity;

public class Mission implements IMission {
	protected String id;
	protected double effectiveness;
	
	protected List<IEntity>performers;

	public Mission(String id, double effectiveness, List<IEntity> performers) {
		super();
		this.id = id;
		this.effectiveness = effectiveness;
		this.performers = performers;
	}

	public String getId() {
		return id;
	}

	public double getEffectiveness() {
		return effectiveness;
	}

	public List<IEntity> getPerformers() {
		return performers;
	}
	
	
	
	
	
	
	

}
