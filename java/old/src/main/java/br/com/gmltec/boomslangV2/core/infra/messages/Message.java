package br.com.gmltec.boomslangV2.core.infra.messages;

import java.io.FileWriter;
import java.io.IOException;

import org.json.simple.JSONObject;

public abstract class Message {
	public abstract JSONObject getMessage();

	public void serialize(String path, String fileName) {
		JSONObject jsonObj = getMessage();
		try (FileWriter file = new FileWriter(path + fileName)) {
			file.write(jsonObj.toJSONString());
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
