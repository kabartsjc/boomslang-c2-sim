package br.com.gmltec.boomslangV2.core.engines;

import br.com.gmltec.boomslangV2.core.geo.Coordinate;
import br.com.gmltec.boomslangV2.core.utils.GeoUtils;
import br.com.gmltec.boomslangV2.entities.IEntity;
import br.com.gmltec.boomslangV2.entities.IEntity.STATUS;
import br.com.gmltec.boomslangV2.entities.types.MobilityEntity;

public class KinectEngine {
	private static final double AIR_HOR_ERR = 300;
	private static final double AIR_VERT_ERR = 304; // 100ft
	
	private static final double NAVY_HOR_ERR = 300;
	private static final double NAVY_VERT_ERR = 304; // 100ft
	
	private static final double ARMY_HOR_ERR = 300;
	private static final double ARMY_VERT_ERR = 304; // 100ft
	
	public static double horizontalErrorDefined(String force) {
		double hor_error = 0;
		
		if (force.equals("AF")){
			hor_error=AIR_HOR_ERR;
		}
		
		else if (force.equals("LF")){
			hor_error=ARMY_HOR_ERR;
		}
		
		else if (force.equals("SS")){
			hor_error=NAVY_HOR_ERR;
		}
		
		else if (force.equals("SB")){
			hor_error=NAVY_HOR_ERR;
		}
		
		return hor_error;
		
	}
	
	public static double verticalErrorDefined(String force) {
		double vert_error = 0;
		
		if (force.equals("AF")){
			vert_error=AIR_VERT_ERR;
		}
		
		else if (force.equals("LF")){
			vert_error=ARMY_VERT_ERR;
		}
		
		else if (force.equals("SS")){
			vert_error=NAVY_VERT_ERR;
		}
		
		else if (force.equals("SB")){
			vert_error=NAVY_VERT_ERR;
		}
		
		return vert_error;
		
	}
	
	public static Coordinate move(IEntity entity,long update_rate) {
		double distance_mt_vertical = 0;
		double distance_mt_horiz = 0;
		double vert_difference = entity.getTargetPosition().getAltitude()-entity.getCurrentPosition().getAltitude();
		
		MobilityEntity mob_entity = (MobilityEntity)entity.getEntType();
		double climb_rate= mob_entity.getClimbRate();
		
		if (entity.getForce().equals("AF") && climb_rate>0) {
			distance_mt_vertical = climb_rate* update_rate;
			distance_mt_horiz = mob_entity.getSpeed() * update_rate;
		}
		else if (entity.getForce().equals("SB") && climb_rate>0) {
			distance_mt_vertical = climb_rate* update_rate;
			distance_mt_horiz = mob_entity.getSpeed() * update_rate;
		}
		
		else {
			distance_mt_horiz = mob_entity.getSpeed() * update_rate;
		}
		
		
		if (vert_difference >0){
			distance_mt_horiz = distance_mt_horiz -distance_mt_horiz*0.2;
		}
		
		else if (vert_difference < 0) {
			distance_mt_vertical = (-1) * distance_mt_vertical;
			
		} 

		double bearing = GeoUtils.calculateBearing(entity.getCurrentPosition(), entity.getTargetPosition());

		Coordinate newPos = GeoUtils.calculateNewPosition(entity.getCurrentPosition(), 
				distance_mt_horiz, distance_mt_vertical,bearing);

		return newPos;
	};
	
	

	
	public static void update(IEntity entity, long update_rate) {
		double hor_error =horizontalErrorDefined(entity.getForce());
		double vert_error = verticalErrorDefined(entity.getForce());
		
		if (entity.getTargetPosition()==null)
			return;
		
		else if (GeoUtils.isSamePosition(entity.getCurrentPosition(), 
				entity.getTargetPosition(), hor_error, vert_error)) {
			
			if (entity.hasMoreWaypoints()) {
				//new waypoint
				entity.nextWaypoint();
				Coordinate newPosition = move(entity, update_rate);
				if (newPosition!=null)
					entity.setCurrentPosition(newPosition);
				
			}
			
			else if (entity.hasMoreTask()) {
				entity.nextTask();
				Coordinate newPosition = move(entity, update_rate);
				if (newPosition!=null)
					entity.setCurrentPosition(newPosition);
			}
			
			else if (entity.getStatus()==STATUS.RETURN) {
				System.out.println(entity.getId()+ "is end");
				entity.setStatus(STATUS.END);
			}
			
			else {
				System.out.println(entity.getId()+ "is returning");
				entity.setStatus(STATUS.RETURN);
				entity.setTargetPosition(entity.getInitialPosition());
				Coordinate newPosition = move(entity, update_rate);
				entity.setCurrentPosition(newPosition);
			}
		}
		
		System.out.println(entity.getId()+":"+entity.getCurrentPosition().getLatitude()
				+","+entity.getCurrentPosition().getLongitude()
				+","+entity.getCurrentPosition().getAltitude());
		
	}

	
}
