package com.equipoUno.proyectoSalud.repositories;

import com.equipoUno.proyectoSalud.entities.MedicalRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MedicalRecordRepository extends JpaRepository<MedicalRecord, String> {

    @Query("SELECT m FROM MedicalRecord m WHERE m.patient.id = :patientId ORDER BY m.date")
    public List<MedicalRecord> getMedicalRecordsByPatient(@Param("patientId") String id);
}
