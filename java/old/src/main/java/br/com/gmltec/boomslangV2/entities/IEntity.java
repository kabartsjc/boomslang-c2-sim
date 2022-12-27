package br.com.gmltec.boomslangV2.entities;

import java.util.List;

import br.com.gmltec.boomslangV2.core.geo.Coordinate;
import br.com.gmltec.boomslangV2.entities.sensors.ISensor;
import br.com.gmltec.boomslangV2.entities.types.IEntityType;
import br.com.gmltec.boomslangV2.entities.weapons.IWeapon;
import br.com.gmltec.boomslangV2.plan.ITask;

public interface IEntity {

	enum STATUS {
		INIT, RETURN, FAILED, DESTROYED, END
	}

	public void init();

	public String getId();

	public IEntity.STATUS getStatus();

	public void setStatus(IEntity.STATUS status);

	public long getBehaviorMode();

	public String getTeam();

	public String getForce();

	public double getCost();

	public double getHealth();

	public void setHealth(double health);

	public IEntityType getEntType();

	public String getType();

	public Coordinate getCurrentPosition();

	public void setCurrentPosition(Coordinate currentPos);

	public Coordinate getTargetPosition();

	public void setTargetPosition(Coordinate target_pos);

	public Coordinate getInitialPosition();

	public List<ISensor> getSensors();

	public void addSensor(ISensor sensor);

	public void addWeapon(IWeapon weapon, int qtd);

	public int decrementWeapon(IWeapon weapon);
	
	public int getWeaponQuantity(IWeapon weapon);

	public List<IWeapon> getWeaponList();
	
	public void addTask(ITask task);

	public List<ITask> getTasks();

	public boolean hasMoreWaypoints();

	public void nextWaypoint();

	public boolean hasMoreTask();

	public void nextTask();
	
	public ITask getCurrentTask();
	
	public void finishMission();
}
