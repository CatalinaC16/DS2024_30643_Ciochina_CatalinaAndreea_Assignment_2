package com.example.MCMicroService.services;

import com.example.MCMicroService.dtos.deviceDTOs.DeviceDTO;
import com.example.MCMicroService.dtos.mappers.DeviceMapper;
import com.example.MCMicroService.dtos.mappers.MeasurementMapper;
import com.example.MCMicroService.dtos.measurementDTOs.MeasurementDTO;
import com.example.MCMicroService.entities.Device;
import com.example.MCMicroService.entities.Measurement;
import com.example.MCMicroService.repositories.DeviceRepository;
import com.example.MCMicroService.repositories.MeasurementRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MeasurementService {

    private final MeasurementRepository measurementRepository;

    private final DeviceRepository deviceRepository;

    private final MeasurementMapper measurementMapper;

    private final DeviceMapper deviceMapper;

    @Transactional
    public void createMeasurement(MeasurementDTO measurementDTO) {
        Optional<Device> deviceOptional = this.deviceRepository.findById(measurementDTO.getDevice_id());
        if (deviceOptional.isEmpty()) {
            DeviceDTO deviceDTO = new DeviceDTO();
            deviceDTO.setId(measurementDTO.getDevice_id());
            Device device = this.deviceMapper.convertToEntity(deviceDTO);
            this.deviceRepository.save(device);
        }
        Measurement measurement = this.measurementMapper.convertToEntity(measurementDTO);
        measurementRepository.save(measurement);
        System.out.println("Measurement saved: " + measurement);
    }
}
