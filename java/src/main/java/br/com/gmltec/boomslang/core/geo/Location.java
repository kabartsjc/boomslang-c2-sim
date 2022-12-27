package br.com.gmltec.boomslang.core.geo;

public class Location implements ILocation{
	private String name;
	private Coordinate coord;
	
	public Location(String name, Coordinate coord) {
		this.name=name;
		this.coord=coord;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public Coordinate getCoordinate() {
		return coord;
	}
	

}
