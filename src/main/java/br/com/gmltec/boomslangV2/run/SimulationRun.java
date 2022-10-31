package br.com.gmltec.boomslangV2.run;

import java.util.HashMap;
import java.util.Map;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.gmltec.boomslangV2.exe.Exercise;
import br.com.gmltec.boomslangV2.sim.SimulationManager;

@SpringBootApplication
@EnableAutoConfiguration
//@ComponentScan(basePackages = "br.com.gml.simu.service")
public class SimulationRun {
	public static void main (String args[]) {
		Exercise exe = new Exercise();
		
		System.out.println("starting the simulation....");
		SimulationManager simManager = SimulationManager.getInstance(exe);
		simManager.init();
		Thread th = new Thread (simManager);
		
		try {
			Thread.sleep(50);
			SpringApplication app = new SpringApplication(SimulationRun.class);
			
			Map<String, Object> map = new HashMap<>();
			map.put("server.port", exe.getSimuPort());
			map.put("server.address", exe.getSimuAddr());
			
			app.setDefaultProperties(map);
			app.run(args);
			
			th.start();
			

		} catch (InterruptedException e) {
			
			e.printStackTrace();
		}
		
	}

}
