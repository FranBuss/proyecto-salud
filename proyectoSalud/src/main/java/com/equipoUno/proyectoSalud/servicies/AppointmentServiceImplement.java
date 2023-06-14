package com.equipoUno.proyectoSalud.servicies;

import com.equipoUno.proyectoSalud.entities.Appointment;
import com.equipoUno.proyectoSalud.entities.Patient;
import com.equipoUno.proyectoSalud.entities.Professional;
import com.equipoUno.proyectoSalud.repositories.AppointmentRepository;
import com.equipoUno.proyectoSalud.repositories.PatientRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.TextStyle;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AppointmentServiceImplement implements AppointmentService {

    private final ModelMapper modelMapper;
    private final AppointmentRepository appointmentRepository;

    private final PatientRepository patientRepository;

    private ProfessionalServiceImplement professionalServiceImplement;

    @Autowired
    public AppointmentServiceImplement(PatientRepository patientRepository,
            ProfessionalServiceImplement professionalServiceImplement, ModelMapper modelMapper,
            AppointmentRepository appointmentRepository) {
        this.modelMapper = modelMapper;
        this.appointmentRepository = appointmentRepository;
        this.professionalServiceImplement = professionalServiceImplement;
        this.patientRepository = patientRepository;
    }

    @Override
    public void assignAppointment(Patient patient, String AppId, String comment) {
        Optional<Appointment> appointmentResponse = appointmentRepository.findById(AppId);
        if (appointmentResponse.isPresent()) {
            Appointment appointment = appointmentResponse.get();
            appointment.setState(false);
            appointment.setPatient(patient);
            appointment.setComments(comment);
            appointmentRepository.save(appointment);
        }
    }

    @Override
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

                appointment.setDay(currentDate.getDayOfWeek().getDisplayName(TextStyle.FULL, new Locale("es", "ES")));

                appointment.setAppointment(appointmentDateTime.toLocalTime());
                appointment.setDate(appointmentDateTime.toLocalDate());
                appointmentRepository.save(appointment);
                appointmentDateTime = appointmentDateTime.plusMinutes(30);
            }
            currentDate = currentDate.plusDays(1);
        }
    }

    @Override
    public List<Appointment> getAppointmentsByProfessionalId(String id) {
        List<Appointment> appointments = appointmentRepository.getAppointmentsByProfessionalId(id);
        return appointments.stream().map(appointment -> modelMapper.map(appointment, Appointment.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<Appointment> getAppointmentsByPatient(String id) {
        List<Appointment> appointments = appointmentRepository.getAppointmentsByPatient(id);
        return appointments.stream().map(appointment -> modelMapper.map(appointment, Appointment.class))
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Appointment> getAppointmentById(String id) {
        return appointmentRepository.findById(id);
    }

    @Override
    @Transactional
    public void deleteUsedAppointmentById(String id) {
        appointmentRepository.deleteAppointmentById(id);
    }

    @Override
    @Transactional
    public void deleteAppointmentAvailable(String id) {
        appointmentRepository.deleteAllAppointmentsAvailable(id);
    }

    @Override
    public String getProfessionalByIdAppointment(String id) {
        try {
            return appointmentRepository.getProfessionalByIdAppointment(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    @Override
    @Transactional
    public void resetAppointmentById(String id) {
        try {
            appointmentRepository.enableAppointment(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // public AppointmentDTO updateAppointmentDate(String id,AppointmentDTO dto,
    // LocalDateTime newTime) throws MiException {
    // List<LocalDateTime> availableAppointments = generateAppointments(dto);
    //
    // if (availableAppointments.contains(newTime)) {
    // Appointment appointment = appointmentRepository.findById(dto.getId())
    // .orElseThrow(()->new MiException("No se encontró el turno"));
    // appointment.setAppointment(newTime);
    // appointmentRepository.save(appointment);
    // return modelMapper.map(appointment, AppointmentDTO.class);
    // } else {
    // throw new MiException("El nuevo horario no está disponible");
    // }
    // }

}
