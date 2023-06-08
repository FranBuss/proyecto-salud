package com.equipoUno.proyectoSalud.servicies;

import com.equipoUno.proyectoSalud.dto.MedicalRecordDTO;
import com.equipoUno.proyectoSalud.entities.MedicalRecord;
import com.equipoUno.proyectoSalud.entities.Patient;
import com.equipoUno.proyectoSalud.repositories.MedicalRecordRepository;
import com.equipoUno.proyectoSalud.repositories.PatientRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MedicalRecordImplement implements MedicalRecordService {


    private final ModelMapper modelMapper;
    private final MedicalRecordRepository medicalRecordRepository;

    private final PatientRepository patientRepository;

    @Autowired
    public MedicalRecordImplement(PatientRepository patientRepository, MedicalRecordRepository medicalRecordRepository, ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
        this.medicalRecordRepository = medicalRecordRepository;
        this.patientRepository = patientRepository;
    }

    public MedicalRecordDTO createMedicalRecord(Patient patient, MedicalRecordDTO medicalRecordDTO) {
        MedicalRecord medicalRecord = modelMapper.map(medicalRecordDTO, MedicalRecord.class);
        medicalRecord.setPatientName(patient.getUser().getName() + " " + patient.getUser().getSurname());
        medicalRecord.setDate(LocalDate.now());
        medicalRecord.setHealthInsurance(patient.getHealthInsurance());
        medicalRecord.setPatient(patient);
        medicalRecordRepository.save(medicalRecord);
        patient.getMedicalRecords().add(medicalRecord);
        patientRepository.save(patient);

        return modelMapper.map(medicalRecord, MedicalRecordDTO.class);
    }

    public List<MedicalRecord> getMedicalRecordsByPatient(String id) {
        List<MedicalRecord> medicalRecords = medicalRecordRepository.getMedicalRecordsByPatient(id);
        return medicalRecords.stream().map(medicalRecord -> modelMapper.map(medicalRecord, MedicalRecord.class))
                .collect(Collectors.toList());
    }

}
