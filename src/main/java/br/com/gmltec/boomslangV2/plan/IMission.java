package br.com.gmltec.boomslangV2.plan;

import java.util.List;

import br.com.gmltec.boomslangV2.entities.IEntity;

public interface IMission {
	public String getId();
	public double getEffectiveness() ;
	public List<IEntity> getPerformers();

}
