package com.equipoUno.proyectoSalud.servicies;

import com.equipoUno.proyectoSalud.dto.AppointmentDTO;

import com.equipoUno.proyectoSalud.entities.Appointment;
import com.equipoUno.proyectoSalud.entities.Professional;
import com.equipoUno.proyectoSalud.exceptions.MiException;
import com.equipoUno.proyectoSalud.repositories.AppointmentRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Service
public class AppointmentServiceImplement implements AppointmentService{

    private final ModelMapper modelMapper;
    private final AppointmentRepository appointmentRepository;

    private ProfessionalServiceImplement professionalServiceImplement;

    @Autowired
    public AppointmentServiceImplement(ProfessionalServiceImplement professionalServiceImplement ,ModelMapper modelMapper, AppointmentRepository appointmentRepository){
        this.modelMapper = modelMapper;
        this.appointmentRepository = appointmentRepository;
        this.professionalServiceImplement = professionalServiceImplement;
    }

//    public AppointmentDTO addAppointment(AppointmentDTO dto) throws MiException{
//
//        List<LocalDateTime> availableAppointments = generateAppointments(dto);
//
//        if (availableAppointments.contains(dto.getAppointment())) {
//            Appointment appointment = modelMapper.map(dto, Appointment.class);
//            appointment.setState("ocupado");
//            appointmentRepository.save(appointment);
//            return modelMapper.map(appointment, AppointmentDTO.class);
//        } else {
//            throw new MiException("El turno no esta disponible");
//
//        }
//    }

    public void generateAppointments(Professional professional){

        LocalTime entryTime = professional.getEntryTime();
        LocalTime exitTime = professional.getExitTime();

        LocalDate currentDate = LocalDate.now();

        LocalDateTime appointmentDateTime = LocalDateTime.of(currentDate, entryTime);


        while (appointmentDateTime.toLocalTime().isBefore(exitTime)){
            if (appointmentDateTime.getDayOfWeek().getValue() >= DayOfWeek.MONDAY.getValue()
                    && appointmentDateTime.getDayOfWeek().getValue() <= DayOfWeek.FRIDAY.getValue()){
                Appointment appointment = new Appointment();
                appointment.setProfessional(professional);
                appointment.setState(false);
//                appointment.setDay(appointmentDateTime.getDayOfWeek());
                System.out.println(appointmentDateTime.getDayOfWeek().getDisplayName(TextStyle.FULL, Locale.getDefault()));

                appointmentRepository.save(appointment);
            }

            appointmentDateTime = appointmentDateTime.plusMinutes(30);

            if (appointmentDateTime.toLocalTime().isAfter(exitTime)) {
                currentDate = getNextBusinessDay(currentDate);
                appointmentDateTime = LocalDateTime.of(currentDate, entryTime);
            }
        }
    }

    private LocalDate getNextBusinessDay(LocalDate currentDate){
        LocalDate nextDay = currentDate.plusDays(1);
        if (nextDay.getDayOfWeek() == DayOfWeek.SATURDAY) {
            return nextDay.plusDays(2);
        } else if (nextDay.getDayOfWeek() == DayOfWeek.SUNDAY) {
            return nextDay.plusDays(1);
        }
        return nextDay;
    }


    //        while (appointmentTime.isBefore(exitTime)){
//
//            Appointment appointment = new Appointment();
//
//            appointment.setProfessional(professional);
//            appointment.setState(false);
//
//            appointmentRepository.save(appointment);
//
//            appointmentTime = appointmentTime.plusMinutes(30);
//
//        }


//    public List<LocalDateTime> generateAppointments(AppointmentDTO dto) {
//        List<LocalDateTime> appointments = new ArrayList<>();
//        LocalDateTime currentDateTime = LocalDateTime.of(LocalDate.now(), dto.getProfessional().getEntryTime());
//        LocalDateTime endDateTime = LocalDateTime.of(LocalDate.now(), dto.getProfessional().getExitTime());
//
//        while(currentDateTime.isBefore(endDateTime)) {
//            if(isAppointmentAvailable(dto)){
//                appointments.add(currentDateTime);
//            }
//            currentDateTime = currentDateTime.plusMinutes(30);
//        }
//
//        return appointments;
//    }

//    public boolean isAppointmentAvailable(AppointmentDTO dto) {
//        if(dto.getAvailableDays().contains(dto.getAppointment().getDayOfWeek()) && dto.getState().equals("disponible")){
//            LocalTime entryTime = dto.getProfessional().getEntryTime();
//            LocalTime exitTime = dto.getProfessional().getExitTime();
//            LocalTime appointmentTime = dto.getAppointment().toLocalTime();
//
//            return appointmentTime.isAfter(entryTime) && appointmentTime.isBefore(exitTime);
//
//        }
//        return false;
//    }



//    public List<AppointmentDTO> occupiedAppointmentsDTO(){
//
//        List<Appointment> allAppointments = appointmentRepository.findAll();
//        List<AppointmentDTO> occupiedAppointmentsDTO = new ArrayList<>();
//
//        for (Appointment appointment : allAppointments){
//            if(appointment.getState().equals("ocupado")) {
//                AppointmentDTO appointmentDTO = modelMapper.map(appointment, AppointmentDTO.class);
//                occupiedAppointmentsDTO.add(appointmentDTO);
//            }
//        }
//        return occupiedAppointmentsDTO;
//    }


    public void deleteAppointment(String id) {
        appointmentRepository.deleteById(id);
    }


//    public AppointmentDTO updateAppointmentDate(String id,AppointmentDTO dto, LocalDateTime newTime) throws MiException {
//        List<LocalDateTime> availableAppointments = generateAppointments(dto);
//
//        if (availableAppointments.contains(newTime)) {
//            Appointment appointment = appointmentRepository.findById(dto.getId())
//                    .orElseThrow(()->new MiException("No se encontró el turno"));
//            appointment.setAppointment(newTime);
//            appointmentRepository.save(appointment);
//            return modelMapper.map(appointment, AppointmentDTO.class);
//        } else {
//            throw new MiException("El nuevo horario no está disponible");
//        }
//    }



//    public List<AppointmentDTO> availableAppointments() {
//
//
//        List<Appointment> appointmentList = appointmentRepository.findAll();
//        List<AppointmentDTO> availableAppointmentsDTO = new ArrayList<>();
//
//
//        for (Appointment appointment : appointmentList){
//            AppointmentDTO appointmentDTO = modelMapper.map(appointment, AppointmentDTO.class);
//            if(isAppointmentAvailable(appointmentDTO)) {
//                availableAppointmentsDTO.add(appointmentDTO);
//            }
//        }
//        return availableAppointmentsDTO;
//
//    }





}
