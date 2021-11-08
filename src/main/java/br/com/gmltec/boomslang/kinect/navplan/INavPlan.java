package br.com.gmltec.boomslang.kinect.navplan;

import br.com.gmltec.boomslang.core.geo.Coordinate;

public interface INavPlan {
	public Coordinate getNextPosition();
	public Coordinate getEndPosition();

}
