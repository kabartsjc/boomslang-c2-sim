package br.com.gmltec.boomslangV2.entities;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.com.gmltec.boomslangV2.core.geo.Coordinate;
import br.com.gmltec.boomslangV2.entities.sensors.ISensor;
import br.com.gmltec.boomslangV2.entities.types.IEntityType;
import br.com.gmltec.boomslangV2.entities.weapons.IWeapon;
import br.com.gmltec.boomslangV2.plan.ITask;
import br.com.gmltec.boomslangV2.plan.Task;

public class Entity implements IEntity {

	protected String id;
	protected IEntity.STATUS status;
	protected long behavior_mode;
	protected String team;
	protected String force;
	protected String type;
	protected double cost;
	protected double health;
	
	
	protected IEntityType entType;

	protected Coordinate current_position;
	protected Coordinate start_position;
	
	protected Coordinate target_position;
	
	protected Task currentTask;
	protected List<Coordinate>currentRoute;
	
	protected List<ISensor> sensorL;
	
	protected Map<IWeapon,Integer>weaponDb;
	
	protected List<ITask> taskL;
	
	
	public Entity(String id, long behavior_mode, String team, String force,double cost,
			String type,IEntityType entType, Coordinate current_position) {
		super();
		this.id = id;
		this.status = IEntity.STATUS.INIT;
		this.behavior_mode = behavior_mode;
		this.team = team;
		this.force=force;
		this.cost = cost;
		this.health = 1.0;
		this.type = type;
		this.entType=entType;
		this.current_position = current_position;
		
		this.sensorL = new ArrayList<>();
		this.weaponDb=new HashMap<>();
		
		this.taskL=new ArrayList<>();
		
	}
	
	public void init() {
		nextTask();
	}

	public String getId() {
		return id;
	}

	public IEntity.STATUS getStatus() {
		return status;
	}
	
	public void setStatus(IEntity.STATUS status) {
		this.status=status;
	}
	
	public long getBehaviorMode() {
		return behavior_mode;
	}

	public String getTeam() {
		return team;
	}
	
	public String getForce() {
		return force;
	}

	public double getCost() {
		return cost;
	}

	public double getHealth() {
		return health;
	}
	
	public void setHealth(double health) {
		this.health=health;
	}

	public IEntityType getEntType() {
		return entType;
	}
	
	public String getType() {
		return type;
	}

	public Coordinate getCurrentPosition() {
		return current_position;
	}
	
	public void setCurrentPosition(Coordinate currentPos) {
		this.current_position=currentPos;
	}

	public Coordinate getTargetPosition() {
		return target_position;
	}
	
	public void setTargetPosition(Coordinate target_pos) {
		this.target_position=target_pos;
	}
	
	public Coordinate getInitialPosition() {
		return this.start_position;
	}
	
	public List<ISensor> getSensors(){
		return sensorL;
	}
	
	public void addSensor(ISensor sensor) {
		this.sensorL.add(sensor);
	}
	
	public void addWeapon(IWeapon weapon, int qtd) {
		this.weaponDb.put(weapon, Integer.valueOf(qtd));
	}
	
	public void decrementWeapon(IWeapon weapon) {
		int qtd = this.weaponDb.get(weapon);
		qtd = qtd-1;
		this.weaponDb.replace(weapon, Integer.valueOf(qtd));
	}
	
	public List<IWeapon> getWeaponList(){
		List<IWeapon> weaponList= new ArrayList<IWeapon>();
		weaponList.addAll(weaponDb.keySet());
		return weaponList;
	}
	
	public void addTask(ITask task) {
		this.taskL.add(task);
	}
	
	public List<ITask> getTasks(){
		return taskL;
	}
	
	public boolean hasMoreWaypoints() {
		if (currentRoute.size()==0)
			return false;
		else
			return true;
	}
	
	public void nextWaypoint() {
		this.target_position= this.currentRoute.get(0);
		this.currentRoute.remove(0);
	}
	
	public boolean hasMoreTask() {
		if (taskL.size()==0)
			return false;
		else
			return true;
	}
	
	public void nextTask() {
		start_position=new Coordinate(current_position.getLatitude(),
				current_position.getLongitude(),
				current_position.getAltitude());
		
		if (taskL.size()>0) {
			currentTask = (Task) this.taskL.get(0);
			this.taskL.remove(0);
			
			this.currentRoute=currentTask.getRoutes();
			
			this.target_position= this.currentRoute.get(0);
			this.currentRoute.remove(0);
		}
	}

}
