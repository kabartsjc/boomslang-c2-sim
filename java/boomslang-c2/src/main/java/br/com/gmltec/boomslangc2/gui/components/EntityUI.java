package br.com.gmltec.boomslangc2.gui.components;

import org.jxmapviewer.viewer.DefaultWaypoint;

import br.com.gmltec.boomslangc2.phy.model.geo.Coordinate;

public class EntityUI extends DefaultWaypoint{
	private String id;
	private String team;
	private String entType;
	private String iconName;
	
    
	
	public EntityUI(String id, String team, String entType, String iconName, Coordinate coord) {
		super(coord.getLatitude(),coord.getLongitude());
		this.id = id;
		this.team = team;
		this.entType = entType;
		this.iconName = iconName;
	}
	
	public EntityUI(String id, String team, String entType, Coordinate coord) {
		super(coord.getLatitude(),coord.getLongitude());
		this.id = id;
		this.team = team;
		this.entType = entType; 
		this.iconName="waypoint_white.png";
	}
	

	public String getId() {
		return id;
	}
	
	public String getEntType() {
		return entType;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setTeam(String team) {
		this.team = team;
	}

	public void setEntType(String entType) {
		this.entType = entType;
	}

	public String getIconName() {
		return iconName;
	}

	public void setIconName(String iconName) {
		this.iconName = iconName;
	}

	public String getTeam() {
		return team;
	}
	
	
	
	
	
	
	

}
