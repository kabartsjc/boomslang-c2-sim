package br.com.gmltec.boomslang.kinect.air;

import br.com.gmltec.boomslangV2.core.utils.GeoUtils;

public class Aircraft extends IAirplane{
	private final double hor_error_unit_mt = 300;
	private final double vert_error_unit_mt = 304; // 100ft
	
	public Aircraft(long id, String name, 
			AirMobilityType type, double kinectResilience) {
		super();
		this.id = id;
		this.name = name;
		this.type = type;
		this.domain = DOMAIN.AIR;
		this.status = STATUS.CREATED;
		this.fstatus=AIR_NAVIG_STATUS.LANDED;
		this.kinect_resilience=kinectResilience;
		this.autonomy=type.getAutonomy_sec();
	}

	@Override
	public void update(long update_interval_sec, double air_weather_factor) {
		this.currentTime = currentTime + update_interval_sec;
		
		if ((currentTime >= startTime)  && (status!=STATUS.FINISHED || status!=STATUS.DESTROYED) ) {
			status = STATUS.RUNNING;
			double reducer = 0;
			//check the resilience factor
			if (air_weather_factor>this.kinect_resilience) {
				reducer = distUtils.calculateProbability(air_weather_factor);
			}
				
			
			if (fstatus == AIR_NAVIG_STATUS.LANDED)
				takeoff(update_interval_sec, reducer);
				
			else if (fstatus == AIR_NAVIG_STATUS.CLIMB) {
				climb(update_interval_sec, reducer);
			}
			
			else if (fstatus == AIR_NAVIG_STATUS.CRUIZE) {
				cruize(update_interval_sec, reducer);
			}
			
			else if (fstatus == AIR_NAVIG_STATUS.LANDING) {
				landing(update_interval_sec, reducer);
			}
			
			else if (fstatus == AIR_NAVIG_STATUS.LANDED) {
				status = STATUS.FINISHED;
			}
			
			autonomy = autonomy - (update_interval_sec);
			
			if (autonomy<=0 && status!=STATUS.FINISHED)
				status = STATUS.DESTROYED;
		}
		
	}
	
	public void takeoff(long update_interval_sec, double reducer) {
		fstatus = AIR_NAVIG_STATUS.CLIMB;
		double hSpeed = this.type.getAscedentSpeed_ms();
		double vSpeed = this.type.getAscedentRate_ms();
		targetPosition = this.navPlan.getNextPosition();
		
		double distance_mt_horiz = hSpeed * update_interval_sec * (1-reducer);
		
		double distance_mt_vert = vSpeed * update_interval_sec;
		
		double bearing = GeoUtils.calculateBearing(currentPosition, targetPosition);
		
		currentPosition= GeoUtils.calculateNewPosition(currentPosition, distance_mt_horiz, distance_mt_vert, bearing);
	}
	
	
	public void climb(long update_interval_sec, double reducer) {
		double hSpeed = 0;
		double vSpeed=0;
		
		// check is in the cruize level
		double delta_mt_vert = Math.abs(currentPosition.getAltitude()-targetPosition.getAltitude());
		if (delta_mt_vert<=this.vert_error_unit_mt) {
			fstatus = AIR_NAVIG_STATUS.CRUIZE;
			hSpeed = this.type.getAscedentSpeed_ms();
			vSpeed = 0;
		}
		
		else {
			hSpeed = this.type.getAscedentSpeed_ms();
			vSpeed = this.type.getAscedentRate_ms();
		}
		
		double distance_mt_horiz = hSpeed * update_interval_sec* (1-reducer);
		double distance_mt_vert = vSpeed * update_interval_sec;
		double bearing = GeoUtils.calculateBearing(currentPosition, targetPosition);
		currentPosition= GeoUtils.calculateNewPosition(currentPosition, distance_mt_horiz, distance_mt_vert, bearing);
	}
	
	public void cruize(long update_interval_sec, double reducer) {
		double hSpeed = 0;
		double vSpeed=0;
		
		//check is in the target position
		double delta_mt_horiz = Math.abs(GeoUtils.calculateHorizontalDistanceMeters(targetPosition, currentPosition));
		if (delta_mt_horiz<=this.hor_error_unit_mt) {
			targetPosition = this.navPlan.getNextPosition();
			
			if (targetPosition == null) {
				fstatus = AIR_NAVIG_STATUS.LANDING;
				targetPosition = navPlan.getEndPosition();
				
				hSpeed = this.type.getDescedentSpeed_ms();
				vSpeed = (-1)*this.type.getDescendentRate_ms();
			}
		}
		
		else {
			hSpeed = this.type.getAscedentSpeed_ms();
			vSpeed = 0;
		}
		
		double distance_mt_horiz = hSpeed * update_interval_sec* (1-reducer);
		double distance_mt_vert = vSpeed * update_interval_sec;
		double bearing = GeoUtils.calculateBearing(currentPosition, targetPosition);
		currentPosition= GeoUtils.calculateNewPosition(currentPosition, distance_mt_horiz, distance_mt_vert, bearing);
	}
	
	public void landing(long update_interval_sec, double reducer) {
		double hSpeed = 0;
		double vSpeed=0;
		
		//check is in the target position
		double delta_mt_horiz = Math.abs(GeoUtils.calculateHorizontalDistanceMeters(targetPosition, currentPosition));
		if (delta_mt_horiz<=this.hor_error_unit_mt) {
			fstatus = AIR_NAVIG_STATUS.LANDED;
		}
		
		else {
			// check is in the cruize level
			double delta_mt_vert = Math.abs(currentPosition.getAltitude()-targetPosition.getAltitude());
			if (delta_mt_vert<=this.vert_error_unit_mt) {
				hSpeed = this.type.getAscedentSpeed_ms();
				vSpeed = 0;
			}
			
			else {
				hSpeed = this.type.getDescedentSpeed_ms();
				vSpeed = (-1)*this.type.getDescendentRate_ms();
			}
			
			double distance_mt_horiz = hSpeed * update_interval_sec* (1-reducer);
			double distance_mt_vert = vSpeed * update_interval_sec;
			double bearing = GeoUtils.calculateBearing(currentPosition, targetPosition);
			currentPosition= GeoUtils.calculateNewPosition(currentPosition, distance_mt_horiz, distance_mt_vert, bearing);	
		}	
	}
}
