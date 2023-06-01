package com.equipoUno.proyectoSalud.servicies;

import com.equipoUno.proyectoSalud.dto.AppointmentDTO;

import com.equipoUno.proyectoSalud.entities.Appointment;
import com.equipoUno.proyectoSalud.exceptions.MiException;
import com.equipoUno.proyectoSalud.repositories.AppointmentRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class AppointmentServiceImplement implements AppointmentService{

    private final ModelMapper modelMapper;
    private final AppointmentRepository appointmentRepository;

    @Autowired
    public AppointmentServiceImplement(ModelMapper modelMapper, AppointmentRepository appointmentRepository){
        this.modelMapper = modelMapper;
        this.appointmentRepository = appointmentRepository;
    }

    public AppointmentDTO addAppointment(AppointmentDTO dto) throws MiException{

        List<LocalDateTime> availableAppointments = generateAppointments(dto);

        if (availableAppointments.contains(dto.getAppointment())) {
            Appointment appointment = modelMapper.map(dto, Appointment.class);
            appointment.setState("ocupado");
            appointmentRepository.save(appointment);
            return modelMapper.map(appointment, AppointmentDTO.class);
        } else {
            throw new MiException("El turno no esta disponible");

        }
    }


    public List<LocalDateTime> generateAppointments(AppointmentDTO dto) {
        List<LocalDateTime> appointments = new ArrayList<>();
        LocalDateTime currentDateTime = LocalDateTime.of(LocalDate.now(), dto.getProfessional().getEntryTime());
        LocalDateTime endDateTime = LocalDateTime.of(LocalDate.now(), dto.getProfessional().getExitTime());

        while(currentDateTime.isBefore(endDateTime)) {
            if(isAppointmentAvailable(dto)){
                appointments.add(currentDateTime);
            }
            currentDateTime = currentDateTime.plusMinutes(30);
        }

        return appointments;
    }

    public boolean isAppointmentAvailable(AppointmentDTO dto) {
        if(dto.getAvailableDays().contains(dto.getAppointment().getDayOfWeek()) && dto.getState().equals("disponible")){
            LocalTime entryTime = dto.getProfessional().getEntryTime();
            LocalTime exitTime = dto.getProfessional().getExitTime();
            LocalTime appointmentTime = dto.getAppointment().toLocalTime();

            return appointmentTime.isAfter(entryTime) && appointmentTime.isBefore(exitTime);


        }
        return false;
    }



    public List<AppointmentDTO> occupiedAppointmentsDTO(){

        List<Appointment> allAppointments = appointmentRepository.findAll();
        List<AppointmentDTO> occupiedAppointmentsDTO = new ArrayList<>();

        for (Appointment appointment : allAppointments){
            if(appointment.getState().equals("ocupado")) {
                AppointmentDTO appointmentDTO = modelMapper.map(appointment, AppointmentDTO.class);
                occupiedAppointmentsDTO.add(appointmentDTO);
            }
        }
        return occupiedAppointmentsDTO;
    }


    public void deleteAppointment(String id) {
        appointmentRepository.deleteById(id);
    }


    public AppointmentDTO updateAppointmentDate(String id,AppointmentDTO dto, LocalDateTime newTime) throws MiException {
        List<LocalDateTime> availableAppointments = generateAppointments(dto);

        if (availableAppointments.contains(newTime)) {
            Appointment appointment = appointmentRepository.findById(dto.getId())
                    .orElseThrow(()->new MiException("No se encontró el turno"));
            appointment.setAppointment(newTime);
            appointmentRepository.save(appointment);
            return modelMapper.map(appointment, AppointmentDTO.class);
        } else {
            throw new MiException("El nuevo horario no está disponible");
        }
    }



    public List<AppointmentDTO> availableAppointments() {


        List<Appointment> appointmentList = appointmentRepository.findAll();
        List<AppointmentDTO> availableAppointmentsDTO = new ArrayList<>();


        for (Appointment appointment : appointmentList){
            AppointmentDTO appointmentDTO = modelMapper.map(appointment, AppointmentDTO.class);
            if(isAppointmentAvailable(appointmentDTO)) {
                availableAppointmentsDTO.add(appointmentDTO);
            }
        }
        return availableAppointmentsDTO;

    }





}
