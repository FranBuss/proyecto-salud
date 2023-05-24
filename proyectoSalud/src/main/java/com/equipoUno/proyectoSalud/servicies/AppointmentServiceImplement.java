package com.equipoUno.proyectoSalud.servicies;

import com.equipoUno.proyectoSalud.dto.AppointmentDTO;
import com.equipoUno.proyectoSalud.dto.PatientDTO;
import com.equipoUno.proyectoSalud.dto.ProfessionalDTO;
import com.equipoUno.proyectoSalud.entities.Appointment;
import com.equipoUno.proyectoSalud.entities.Patient;
import com.equipoUno.proyectoSalud.entities.Professional;
import com.equipoUno.proyectoSalud.repositories.AppointmentRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.time.LocalDateTime;

@Service
public class AppointmentServiceImplement implements AppointmentService{

    private final ModelMapper modelMapper;

    private final AppointmentRepository appointmentRepository;

    @Autowired
    public AppointmentServiceImplement(ModelMapper modelMapper, AppointmentRepository appointmentRepository){
        this.modelMapper = modelMapper;
        this.appointmentRepository = appointmentRepository;
    }

    public AppointmentDTO addAppointment(AppointmentDTO dto){

        Appointment appointment = modelMapper.map(dto, Appointment.class);
//        appointment.setState("confirmado");
        appointmentRepository.save(appointment);
        return modelMapper.map(appointment, AppointmentDTO.class);

    //modificar estado
    //cancelar turno
    //listar turnos reservados
    //listar turnos libres

    }





}
