package br.com.gmltec.boomslangc2.phy.utils;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import br.com.gmltec.boomslangc2.phy.model.Entity;

public class MissionUtils {

	@SuppressWarnings("unchecked")
	public static boolean persistMission(String missionName, long sim_time, List<Entity> entList) {
		JSONObject general_simu = new JSONObject();
		general_simu.put("name", missionName);
		general_simu.put("duration_time_sec", sim_time);

		JSONArray simu_info = new JSONArray();
		simu_info.add(general_simu);

		for (Entity ent : entList) {
			JSONObject entObj = new JSONObject();

			String id = ent.getId();
			entObj.put("id", id);

			String team = ent.getTeam();// {BLUE, RED, GREEN}
			entObj.put("team", team);

			String force = ent.getForce();
			entObj.put("force", force);

			String behavior_mode = ent.getBehavior_mode();// { 0 = neutral, 1 = reactive, and -1 = aggressive}.
			entObj.put("behavior_mode", behavior_mode);

			String position = ent.getPosition().toString();
			entObj.put("position", position);

			String entType = ent.getEntType().getId();
			entObj.put("type", entType);

			String entUIID = ent.getEntityUIID();
			entObj.put("UIID", entUIID);

			simu_info.add(entObj);

		}

		try (FileWriter file = new FileWriter("src/main/resources/mission/mission-" + missionName + ".json")) {

			file.write(simu_info.toJSONString());
			file.flush();
			return true;

		} catch (IOException e) {
			return false;
		}
	}

}
