package br.com.gmltec.boomslangV2.core.utils;

public class Variables {
	
	//DIRECTORIES AND FILES
	public static final String SENSOR_FILE = "resources/model/sensor.json";
	public static final String WEAPON_FILE = "resources/model/weapon.json";
	public static final String ENT_STATIC_TYPE_FILE = "resources/model/static_entity.json";
	public static final String ENT_MOBILE_TYPE_FILE = "resources/model/mobile_entity.json";
	
	public static final String B_ENTITY_FILE = "resources/exercise/b_entities.json";
	public static final String R_ENTITY_FILE = "resources/exercise/r_entities.json";
	public static final String G_ENTITY_FILE = "resources/exercise/g_entities.json";
	
	public static final String B_MISSION_FILE = "resources/exercise/b_mission.json";
	public static final String R_MISSION_FILE = "resources/exercise/r_mission.json";
	public static final String G_MISSION_FILE = "resources/exercise/g_mission.json";
	
	public static final String EXE_FILE = "resources/exercise/exe.json";
	
	
	
	
	
	
	
	public static final String AIRPORT_JSON_FILE = "resources/atc/airports.json";
	public static final String HELIPORT_JSON_FILE = "resources/atc/heliports.json";
	public static final String UTMPORT_JSON_FILE = "resources/atc/utmports.json";
	public static final String SECTOR_JSON_FILE = "resources/atc/sectors.json";
	
	
	public static final String AIRCRAFT_MODEL_FILE = "/models/aircrafts_model.json";
	public static final String HELI_MODEL_FILE = "/models/helli_model.json";
	public static final String UAS_MODEL_FILE = "/models/uas_model.json";
	
	public static final String AIRCRAFT_PLANS_JSON = "/aircraft_plans.json";
	public static final String UAM_PLANS_JSON = "/helicopter_plans.json";
	public static final String UAS_PLANS_JSON = "/uas_plans.json";
	
	
	public static final int MAXIMUM_TAKEOFF_CONTROL= 10;

	
	
	//air restrictions
	public static final double ATC_MIN_HOR_DISTANCE_METERS = 9260;
	public static final double UAM_MIN_HOR_DISTANCE_METERS = 1852;
	public static final double UAS_MIN_HOR_DISTANCE_METERS = 926;
	
	
	public static final double ATC_MIN_VERT_DISTANCE_METERS = 1000;
	public static final double UAM_MIN_VERT_DISTANCE_METERS = 60;
	public static final double UAS_MIN_VERT_DISTANCE_METERS = 60;
	
	
	
	
	
	public static final double UAS_MAX_VERT_ALT = 121.92;
	public static final double UAM_MAX_VERT_ALT = 1981.2;
	
	//atc levels
	public static final int AIRCRAFT_MIN_ROUTE_LEVELS=1524;
	public static final int AIRCRAFT_MAX_ROUTE_LEVELS=9144;
	
	public static final double UAM_LEVELS_360_179[]= {304.8, 426.72, 548.64, 670.56, 792.48, 914.4};
	public static final double UAM_LEVELS_180_359[]= {365.76, 487.68, 609.6, 731.52, 853.44, 975.36};
	
	public static final double UAS_LEVELS_360_179[]= {30.48, 42.672, 54.864, 67.056, 79.248, 91.44};
	public static final double UAS_LEVELS_180_359[]= {36.576, 48.768, 60.96, 73.152, 85.344,97.536};
	

	public static final double UAS_MAX_DIST_HOR = 3500;
	
	
	
	
	

	public static final String GEOFENCE_FILE = "resources/scenario1/geofences.json";


	public static final String MET_FORMATION_FILE = "/metformations.json";


	//MET CONSTRAINTS --> pg 85 tbl 14 e comentario
	public static final int MIN_MET_FORMATION = 2;
	public static final int MAX_MET_FORMATION = 15;
	public static final double MIN_MET_RANGE = 10;
	public static final double MAX_MET_RANGE = 300;

	public static final long MAXIMUM_TIME_SIM = Long.MAX_VALUE;
	public static final int MAXIMUM_TRIAL = 10;

	
	


}
