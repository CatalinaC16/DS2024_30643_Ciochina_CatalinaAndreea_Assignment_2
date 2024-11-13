package com.example.SMDSimulator;

import com.example.SMDSimulator.service.DeviceSimulatorService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.CommandLineRunner;
import org.springframework.beans.factory.annotation.Autowired;

@SpringBootApplication
public class SmdSimulatorApplication implements CommandLineRunner {

	@Autowired
	private DeviceSimulatorService deviceSimulatorService;

	public static void main(String[] args) {
		SpringApplication.run(SmdSimulatorApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		if (args.length < 1) {
			System.out.println("Usage: java -jar SmdSimulatorApplication.jar <device_id>");
			System.exit(1);
		}

		String deviceId = args[0];
		deviceSimulatorService.startSimulation(deviceId);
	}
}
