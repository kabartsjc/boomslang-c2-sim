package br.com.gmltec.boomslangc2.phy.model;

import br.com.gmltec.boomslangc2.phy.model.geo.Coordinate;
import br.com.gmltec.boomslangc2.phy.model.types.IEntityType;

public class Entity {
	private String id;
	private int status;//identifies the quality of the entity and can have the following values: {0 = no started, 1 = started, 2 = finished, and -1 = destroyed).
	private String team;//{BLUE, RED, GREEN}
	private String force;
	private String behavior_mode;// { 0 = neutral, 1 = reactive, and -1 = aggressive}.
	private Coordinate position;
	private double health;//can be a value between 0 and 1. 
	private IEntityType entType;
	
	private String entUIID;
	

	public Entity(String id, String entUIId, String team, String force, String behavior_mode, 
			Coordinate position, IEntityType entType) {
		super();
		this.id = id;
		this.entUIID = entUIId;
		this.status = 0;
		this.team = team;
		this.force = force;
		this.behavior_mode = behavior_mode;
		this.position = position;
		this.health = 1.0;
		this.entType = entType;
	}

	public String getId() {
		return id;
	}
	
	public String getEntityUIID() {
		return entUIID;
	}

	public int getStatus() {
		return status;
	}
	
	public String getForce() {
		return force;
	}

	public String getTeam() {
		return team;
	}

	public String getBehavior_mode() {
		return behavior_mode;
	}

	public Coordinate getPosition() {
		return position;
	}

	
	public double getHealth() {
		return health;
	}

	public IEntityType getEntType() {
		return entType;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public void setTeam(String team) {
		this.team = team;
	}

	public void setForce(String force) {
		this.force = force;
	}

	public void setBehavior_mode(String behavior_mode) {
		this.behavior_mode = behavior_mode;
	}

	public void setPosition(Coordinate position) {
		this.position = position;
	}

	public void setHealth(double health) {
		this.health = health;
	}

	public void setEntType(IEntityType entType) {
		this.entType = entType;
	}

	public void setEntUIID(String entUIID) {
		this.entUIID = entUIID;
	}
	
	
	
	
	

}
