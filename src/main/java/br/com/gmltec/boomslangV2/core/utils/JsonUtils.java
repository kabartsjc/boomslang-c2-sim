package br.com.gmltec.boomslangV2.core.utils;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import br.com.gmltec.boomslangV2.core.geo.Coordinate;
import br.com.gmltec.boomslangV2.entities.Entity;
import br.com.gmltec.boomslangV2.entities.IEntity;
import br.com.gmltec.boomslangV2.entities.sensors.ISensor;
import br.com.gmltec.boomslangV2.entities.sensors.Sensor;
import br.com.gmltec.boomslangV2.entities.types.IEntityType;
import br.com.gmltec.boomslangV2.entities.types.MobilityEntity;
import br.com.gmltec.boomslangV2.entities.types.StaticEntity;
import br.com.gmltec.boomslangV2.entities.weapons.IWeapon;
import br.com.gmltec.boomslangV2.entities.weapons.Weapon;
import br.com.gmltec.boomslangV2.plan.IMission;
import br.com.gmltec.boomslangV2.plan.ITask;
import br.com.gmltec.boomslangV2.plan.Mission;
import br.com.gmltec.boomslangV2.plan.Task;

public class JsonUtils {

	public static Map<String, ISensor> loadSensors() {
		Map<String, ISensor> sensorDict = new ConcurrentHashMap<>();
		String sensorJson = Variables.SENSOR_FILE;

		try {
			Object obj = new JSONParser().parse(new FileReader(sensorJson));

			JSONArray joArr = (JSONArray) obj;

			for (int i = 0; i < joArr.size(); i++) {
				JSONObject jo = (JSONObject) joArr.get(i);
				String id = (String) jo.get("id");
				String domain = (String) jo.get("domain");
				long range = (long) jo.get("range");
				double precision = (double) jo.get("precision");
				double reliability = (double) jo.get("reliability");
				double intensity = (double) jo.get("intensity");

				ISensor sensor = new Sensor(id, domain, range, precision, reliability, intensity);
				sensorDict.put(id, sensor);
			}

		} catch (IOException | ParseException e) {
			e.printStackTrace();
		}

		return sensorDict;
	}

	public static Map<String, IWeapon> loadWeapons() {
		Map<String, IWeapon> weaponDict = new ConcurrentHashMap<>();
		String weaponJson = Variables.WEAPON_FILE;

		try {
			Object obj = new JSONParser().parse(new FileReader(weaponJson));

			JSONArray joArr = (JSONArray) obj;

			for (int i = 0; i < joArr.size(); i++) {
				JSONObject jo = (JSONObject) joArr.get(i);
				String id = (String) jo.get("id");
				String domain = (String) jo.get("domain");
				long range = (long) jo.get("range");
				String type = (String) jo.get("type");
				double precision = (double) jo.get("precision");
				double reliability = (double) jo.get("reliability");
				double intensity = (double) jo.get("intensity");

				IWeapon weapon = new Weapon(id, domain, range, type, precision, reliability, intensity);
				weaponDict.put(id, weapon);
			}

		} catch (IOException | ParseException e) {
			e.printStackTrace();
		}

		return weaponDict;
	}

	public static Map<String, IEntityType> loadEntityTypes() {
		Map<String, IEntityType> entityTypeDict = new ConcurrentHashMap<>();
		entityTypeDict.clear();

		entityTypeDict.putAll(loadStaticEntityTypes());
		entityTypeDict.putAll(loadMobilityEntityTypes());

		return entityTypeDict;
	}

	public static Map<String, IEntityType> loadStaticEntityTypes() {
		Map<String, IEntityType> entityTypeDict = new ConcurrentHashMap<>();
		String StaticTypeJson = Variables.ENT_STATIC_TYPE_FILE;

		try {
			Object obj = new JSONParser().parse(new FileReader(StaticTypeJson));

			JSONArray joArr = (JSONArray) obj;

			for (int i = 0; i < joArr.size(); i++) {
				JSONObject jo = (JSONObject) joArr.get(i);
				String id = (String) jo.get("id");
				String force = (String) jo.get("force");
				double lethality_factor = (double) jo.get("lethality_factor");
				double resilience_factor = (double) jo.get("resilience_factor");
				double vulnerability_factor = (double) jo.get("vulnerability_factor");

				IEntityType entType = new StaticEntity(id, force, lethality_factor, resilience_factor,
						vulnerability_factor);
				entityTypeDict.put(id, entType);
			}

		} catch (IOException | ParseException e) {
			e.printStackTrace();
		}

		return entityTypeDict;
	}

	public static Map<String, IEntityType> loadMobilityEntityTypes() {
		Map<String, IEntityType> entityTypeDict = new ConcurrentHashMap<>();
		String MobTypeJson = Variables.ENT_MOBILE_TYPE_FILE;

		try {
			Object obj = new JSONParser().parse(new FileReader(MobTypeJson));

			JSONArray joArr = (JSONArray) obj;

			for (int i = 0; i < joArr.size(); i++) {
				JSONObject jo = (JSONObject) joArr.get(i);
				String id = (String) jo.get("id");
				String force = (String) jo.get("force");
				double lethality_factor = (double) jo.get("lethality_factor");
				double resilience_factor = (double) jo.get("resilience_factor");
				double vulnerability_factor = (double) jo.get("vulnerability_factor");
				double speed = (double) jo.get("speed");
				double climb_rate = (double) jo.get("climb_rate");
				double autonomy = (double) jo.get("autonomy");

				IEntityType entType = new MobilityEntity(id, force, lethality_factor, resilience_factor,
						vulnerability_factor, speed, climb_rate, autonomy);
				entityTypeDict.put(id, entType);
			}

		} catch (IOException | ParseException e) {
			e.printStackTrace();
		}

		return entityTypeDict;
	}

	public static Map<String, IEntity> loadEntities(String team_name) {
		Map<String, IEntity> entitiesDict = new ConcurrentHashMap<>();
		String entityJson = null;

		if (team_name.equals("B")) {
			entityJson = Variables.B_ENTITY_FILE;
		} else if (team_name.equals("R")) {
			entityJson = Variables.R_ENTITY_FILE;
		} else {
			entityJson = Variables.G_ENTITY_FILE;
		}

		try {
			Object obj = new JSONParser().parse(new FileReader(entityJson));

			JSONArray joArr = (JSONArray) obj;

			for (int i = 0; i < joArr.size(); i++) {
				JSONObject jo = (JSONObject) joArr.get(i);
				String id = (String) jo.get("id");
				String type = (String) jo.get("type");
				long behavior_mode = (long) jo.get("behavior_mode");
				String team = (String) jo.get("team");
				String force = (String) jo.get("force");

				double cost = (double) jo.get("cost");

				double latitude = (double) jo.get("latitude");
				double longitude = (double) jo.get("longitude");
				double altitude = 0.0;
				if (jo.get("altitude") != null)
					altitude = (double) jo.get("altitude");
				Coordinate currentPosition = new Coordinate(latitude, longitude, altitude);

				String ent_type = (String) jo.get("ent_type");
				Map<String, IEntityType> entityTypeDict = loadEntityTypes();
				IEntityType entType = entityTypeDict.get(ent_type);

				Entity entity = new Entity(id, behavior_mode, team, force, cost, type, entType, currentPosition);

				Map<String, ISensor> sensorMap = loadSensors();
				@SuppressWarnings("unchecked")
				List<String> sensorArr = (ArrayList<String>) jo.get("sensor");
				for (int j = 0; j < sensorArr.size(); j++) {
					String sensorStr = sensorArr.get(j);
					Sensor sensor = (Sensor) sensorMap.get(sensorStr);
					entity.addSensor(sensor);
				}
				Map<String, IWeapon> wpMap = loadWeapons();
				@SuppressWarnings("unchecked")
				List<String> wpArr = (ArrayList<String>) jo.get("weapon");
				for (int j = 0; j < wpArr.size(); j++) {
					String wpStr = wpArr.get(j);
					String[] wpTkn = wpStr.split(":");
					String wpId = wpTkn[0];

					int qt = Integer.parseInt(wpTkn[1]);

					Weapon weapon = (Weapon) wpMap.get(wpId);

					entity.addWeapon(weapon, qt);
				}

				entitiesDict.put(id, entity);

			}

		} catch (IOException | ParseException e) {
			e.printStackTrace();
		}

		return entitiesDict;
	}

	@SuppressWarnings("unchecked")
	public static Map<String, IMission> loadMissions(String team_name, Map<String, 
			IEntity> entityDB ) {
		Map<String, IMission> missionDict = new ConcurrentHashMap<>();
		String missionJson = null;
		Map<String, IEntity> targetMap = null;

		if (team_name.equals("B")) {
			missionJson = Variables.B_MISSION_FILE;
			targetMap = loadEntities("R");
		} else if (team_name.equals("R")) {
			missionJson = Variables.R_MISSION_FILE;
			targetMap = loadEntities("B");
		} else {
			missionJson = Variables.G_MISSION_FILE;
			targetMap = new ConcurrentHashMap<>();
		}

		try {
			Object obj = new JSONParser().parse(new FileReader(missionJson));

			JSONArray joArr = (JSONArray) obj;

			for (int i = 0; i < joArr.size(); i++) {
				JSONObject jo = (JSONObject) joArr.get(i);
				String id = (String) jo.get("id");
				double effectiveness = (double) jo.get("effectiveness");

				List<String> entArr = (ArrayList<String>) jo.get("node_id");
				List<IEntity> performers = new ArrayList<>();
				for (int j = 0; j < entArr.size(); j++) {
					String entityStr = entArr.get(j);
					IEntity entity = (IEntity) entityDB.get(entityStr);
					performers.add(entity);
				}

				Mission mission = new Mission(id, effectiveness, performers);
				List<JSONArray> taskList = (JSONArray) jo.get("task");
				for (int k = 0; k < taskList.size(); k++) {
					Object objJ = taskList.get(k);
					JSONObject jot = (JSONObject) objJ;

					String tid = (String) jot.get("id");
					String target_name = (String) jot.get("target");
					IEntity target = targetMap.get(target_name);
					String task_type = (String) jot.get("type");

					double t_effectiveness = (double) jot.get("effectiveness");

					Task task = new Task(tid, target, task_type, t_effectiveness);

					List<String> routeArr = (ArrayList<String>) jot.get("route");
					for (int z = 0; z < routeArr.size(); z++) {
						String routeStr = routeArr.get(z);
						String[] routeArrStr = routeStr.split(",");
						double latitude = Double.parseDouble(routeArrStr[0]);
						double longitude = Double.parseDouble(routeArrStr[1]);
						Coordinate coord = new Coordinate(latitude, longitude, 0.0);
						task.addRoute(coord);
					}

					List<String> rWeaponArr = (ArrayList<String>) jot.get("required_weapon");
					for (int z = 0; z < rWeaponArr.size(); z++) {
						String weaponName = rWeaponArr.get(z);
						task.addRequiredWeapon(weaponName);
					}

					List<String> rSensorArr = new ArrayList<>();
					if (jot.get("required_sensor") != null)
						rSensorArr = (ArrayList<String>) jot.get("required_sensor");
					for (int z = 0; z < rSensorArr.size(); z++) {
						String sensorName = rSensorArr.get(z);
						task.addRequiredSensor(sensorName);
					}

					for (int w = 0; w < mission.getPerformers().size(); w++) {
						Entity ent = (Entity) mission.getPerformers().get(w);
						ent.addTask(task);
					}
				}

				missionDict.put(id, mission);

			}

		} catch (IOException | ParseException e) {
			e.printStackTrace();
		}

		return missionDict;
	}
	
	public static Object[] loadServerParams() {
		Object[] server_params= null;
		try {
			String exeFile = Variables.EXE_FILE;
			Object obj = new JSONParser().parse(new FileReader(exeFile));

			JSONObject jo = (JSONObject) obj;
			
			double central_lat = (double) jo.get("central_lat");
			double central_long = (double) jo.get("central_long");
			Coordinate map_center = new Coordinate(central_lat, central_long, 0);
			String simu_addr = (String) jo.get("sim_addr");
			int simu_port = (int) ((long)  jo.get("sim_port"));
			
			server_params= new Object[3];
			server_params[0] = simu_addr;
			server_params[1] = simu_port;
			server_params[2] = map_center;
		} catch (IOException | ParseException e) {
			e.printStackTrace();
		}
		
		return server_params;
	}


	/*public static void main(String args[]) {
		Map<String, IMission> missions = loadMissions("B");
		List<IMission> missionL = new ArrayList<IMission>();
		missionL.addAll(missions.values());

		for (IMission missionI : missionL) {
			Mission mission = (Mission) missionI;
			System.out.println("mission - " + mission.getId());
			for (IEntity ent : mission.getPerformers()) {
				Entity entImpl = (Entity) ent;
				System.out.println(entImpl.getId());
				for (ITask task : entImpl.getTasks()) {
					Task taskImpl = (Task) task;
					System.out.println(taskImpl.getRoutes().size() + "routes");
					;
				}
			}
			System.out.println("$$$$$$");

		}
	}*/

	
}
