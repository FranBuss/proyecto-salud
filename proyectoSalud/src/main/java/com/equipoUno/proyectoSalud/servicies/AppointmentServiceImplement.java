package com.equipoUno.proyectoSalud.servicies;

import com.equipoUno.proyectoSalud.dto.AppointmentDTO;

import com.equipoUno.proyectoSalud.entities.Appointment;
import com.equipoUno.proyectoSalud.entities.Patient;
import com.equipoUno.proyectoSalud.entities.Professional;
import com.equipoUno.proyectoSalud.entities.User;
import com.equipoUno.proyectoSalud.exceptions.MiException;
import com.equipoUno.proyectoSalud.repositories.AppointmentRepository;
import com.equipoUno.proyectoSalud.repositories.PatientRepository;
import com.equipoUno.proyectoSalud.repositories.UserRepository;
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
import java.util.stream.Collectors;

@Service
public class AppointmentServiceImplement implements AppointmentService {

    private final ModelMapper modelMapper;
    private final AppointmentRepository appointmentRepository;

    private final PatientRepository patientRepository;

    private ProfessionalServiceImplement professionalServiceImplement;

    @Autowired
    public AppointmentServiceImplement(PatientRepository patientRepository, ProfessionalServiceImplement professionalServiceImplement ,ModelMapper modelMapper, AppointmentRepository appointmentRepository){

        this.modelMapper = modelMapper;
        this.appointmentRepository = appointmentRepository;
        this.professionalServiceImplement = professionalServiceImplement;
        this.patientRepository = patientRepository;
    }

    public AppointmentDTO assignAppointment(AppointmentDTO dto, String patientId) throws MiException{
        Patient patient = patientRepository.findById(patientId).orElse(null);

        Appointment appointment = modelMapper.map(dto, Appointment.class);
        appointment.setState(false);
        appointment.setPatient(patient);
        appointmentRepository.save(appointment);
        return modelMapper.map(appointment, AppointmentDTO.class);
    }

    public void generateAppointments(Professional professional) {
        LocalTime entryTime = professional.getEntryTime();
        LocalTime exitTime = professional.getExitTime();
        LocalDate currentDate = LocalDate.now();

        while (currentDate.getDayOfWeek() != DayOfWeek.MONDAY) {
            currentDate = currentDate.plusDays(1);
        }
        while (currentDate.getDayOfWeek().getValue() <= DayOfWeek.FRIDAY.getValue()) {
            LocalDateTime appointmentDateTime = LocalDateTime.of(currentDate, entryTime);

            while (appointmentDateTime.toLocalTime().isBefore(exitTime)) {
                Appointment appointment = new Appointment();
                appointment.setProfessional(professional);
                appointment.setState(true);
              
                appointment.setDay(currentDate.getDayOfWeek().getDisplayName(TextStyle.FULL, new Locale("es","ES")));


                appointment.setAppointment(appointmentDateTime.toLocalTime());
                appointment.setDate(appointmentDateTime.toLocalDate());
                appointmentRepository.save(appointment);
                appointmentDateTime = appointmentDateTime.plusMinutes(30);
            }
            currentDate = currentDate.plusDays(1);
        }
    }

    @Override
    public List<Appointment> getAppointmentsByProfessional(String id) {
        List<Appointment> appointments = appointmentRepository.getAppointmentsByProfessional(id);
        return appointments.stream().map(appointment -> modelMapper.map(appointment, Appointment.class))
                .collect(Collectors.toList());
    }

    @Override
    public void deleteAppointment(String id) {
        appointmentRepository.deleteById(id);
    }

    @Override
    public void deleteAppointmentAvailable(String id){
        appointmentRepository.deleteAllAppointmentsAvailable(id);
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

//    @Override
//    public List<Appointment> getAllAppointments(String id){
//        List<Appointment> appointments = appointmentRepository.getAllAppointmentsByOrder();
//        return appointments.stream().map(appointment -> modelMapper.map(appointment, Appointment.class))
//                .collect(Collectors.toList());
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
