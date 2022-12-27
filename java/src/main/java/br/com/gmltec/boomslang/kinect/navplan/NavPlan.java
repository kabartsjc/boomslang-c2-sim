package br.com.gmltec.boomslang.kinect.navplan;

import java.util.ArrayList;
import java.util.List;

import br.com.gmltec.boomslang.core.geo.Coordinate;

public class NavPlan implements INavPlan{
	
	protected List<Coordinate> coordList;
	protected Coordinate targetPosition;
	
	
	public NavPlan() {
		coordList = new ArrayList<>();
	}

	@Override
	public Coordinate getNextPosition() {
		Coordinate nextPos = coordList.get(0);
		coordList.remove(0);
		return nextPos;
	}

	@Override
	public Coordinate getEndPosition() {
		return targetPosition;
	}
	
	
	@Override
	public void addNewPosition(Coordinate pos) {
		coordList.add(pos);
	}

	@Override
	public void addTargetPosition(Coordinate pos) {
		this.targetPosition=pos;
	}
	
}
