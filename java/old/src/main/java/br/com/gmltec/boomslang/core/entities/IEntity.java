package br.com.gmltec.boomslang.core.entities;

public interface IEntity {
	
	public enum DOMAIN {
		SEA,
		GROUND,
		AIR,
		SPACE,
		CYBER
	}
	
	public enum STATUS {
		CREATED,
		RUNNING,
		IDLE,
		DESTROYED,
		FINISHED
	}
	
	public long getID();
	public String getName();
	public IEntityType getType();
	public DOMAIN getDomain();
	
	public STATUS getStatus();
	
	public long getStartTime();
	
	public long getCurrentTime();
	public void setStartTime(long time_sec);
	

}
