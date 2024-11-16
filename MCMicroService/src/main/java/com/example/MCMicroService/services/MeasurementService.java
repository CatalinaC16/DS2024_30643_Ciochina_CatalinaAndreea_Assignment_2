package com.example.MCMicroService.services;

import com.example.MCMicroService.dtos.mappers.MeasurementMapper;
import com.example.MCMicroService.dtos.measurementDTOs.MeasurementDTO;
import com.example.MCMicroService.entities.Measurement;
import com.example.MCMicroService.repositories.DeviceRepository;
import com.example.MCMicroService.repositories.MeasurementRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MeasurementService {

    private final MeasurementRepository measurementRepository;

    private final DeviceRepository deviceRepository;

    private final MeasurementMapper measurementMapper;

    @Transactional
    public void createMeasurement(MeasurementDTO measurementDTO) {
        Measurement measurement = this.measurementMapper.convertToEntity(measurementDTO);
        measurementRepository.save(measurement);
        System.out.println("Measurement saved: " + measurement);
    }
}
