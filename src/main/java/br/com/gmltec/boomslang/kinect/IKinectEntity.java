package br.com.gmltec.boomslang.kinect;

import br.com.gmltec.boomslang.core.entities.IEntity;

public interface IKinectEntity extends IEntity{
	
	public enum STATUS {
		CREATED,
		RUNNING,
		IDLE,
		DESTROYED,
		FINISHED
	}
	
	
	public STATUS getStatus();
	public void setStatus (STATUS status);

}
