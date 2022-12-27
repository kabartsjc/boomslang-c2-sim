package br.com.gmltec.boomslangc2.phy.model;

import br.com.gmltec.boomslangc2.phy.model.geo.Coordinate;
import br.com.gmltec.boomslangc2.phy.model.types.IEntityType;

public class Entity {
	private String id;
	private int status;//identifies the quality of the entity and can have the following values: {0 = no started, 1 = started, 2 = finished, and -1 = destroyed).
	private String team;//{BLUE, RED, GREEN}
	private String force;
	private int behavior_mode;// { 0 = neutral, 1 = reactive, and -1 = aggressive}.
	private Coordinate position;
	private double health;//can be a value between 0 and 1. 
	private IEntityType entType;
	
	

	public Entity(String id, String team, String force, int behavior_mode, 
			Coordinate position, IEntityType entType) {
		super();
		this.id = id;
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

	public int getStatus() {
		return status;
	}
	
	public String getForce() {
		return force;
	}

	public String getTeam() {
		return team;
	}

	public int getBehavior_mode() {
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
	
	

}
