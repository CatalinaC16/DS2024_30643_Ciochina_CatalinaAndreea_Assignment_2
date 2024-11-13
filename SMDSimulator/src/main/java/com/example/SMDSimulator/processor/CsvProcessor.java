package com.example.SMDSimulator.processor;

import com.example.SMDSimulator.rabbitMQ.MessageProducer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.concurrent.TimeUnit;

@Service
public class CsvProcessor {

    @Autowired
    private MessageProducer messageProducer;

    private static final ObjectMapper objectMapper = new ObjectMapper();

    public void readAndSendData(String deviceId) {
        try (InputStream inputStream = getClass().getResourceAsStream("/sensor.csv");
             BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {

            String line;
            long timeMillis = System.currentTimeMillis();
            while ((line = reader.readLine()) != null) {
                if (line.startsWith("#")) {
                    continue;
                }

                float measurementValue = Float.parseFloat(line);
                ObjectNode jsonNode = objectMapper.createObjectNode();
                jsonNode.put("timestamp", timeMillis += 1000 * 60 * 5);
                jsonNode.put("device_id", deviceId);
                jsonNode.put("measure", measurementValue);
                String message = jsonNode.toString();

                messageProducer.sendMessage(message);
                TimeUnit.MILLISECONDS.sleep(1000);
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}

