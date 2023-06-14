package com.equipoUno.proyectoSalud.servicies;

import com.equipoUno.proyectoSalud.dto.ProfessionalDTO;
import com.equipoUno.proyectoSalud.entities.Appointment;
import com.equipoUno.proyectoSalud.entities.Professional;
import com.equipoUno.proyectoSalud.entities.User;
import com.equipoUno.proyectoSalud.enumerations.Specialization;
import com.equipoUno.proyectoSalud.exceptions.MiException;
import com.equipoUno.proyectoSalud.repositories.AppointmentRepository;
import com.equipoUno.proyectoSalud.repositories.ProfessionalRepository;
import com.equipoUno.proyectoSalud.repositories.UserRepository;
import com.equipoUno.proyectoSalud.utils.ProfessionalUtil;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProfessionalServiceImplement implements ProfessionalService{

    private final ProfessionalRepository professionalRepository;

    private final UserRepository userRepository;
    private final AppointmentServiceImplement appointmentService;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;
    private final AppointmentRepository appointmentRepository;

    @Autowired
    public ProfessionalServiceImplement(UserRepository userRepository, ProfessionalRepository professionalRepository,
                                        @Lazy AppointmentServiceImplement appointmentService, ModelMapper modelMapper,
                                        AppointmentRepository appointmentRepository, PasswordEncoder passwordEncoder) {
        this.professionalRepository = professionalRepository;
        this.modelMapper = modelMapper;
        this.passwordEncoder = passwordEncoder;
        this.appointmentService = appointmentService;
        this.userRepository = userRepository;
        this.appointmentRepository = appointmentRepository;
    }

    @Override
    public ProfessionalDTO getProfessional(String id){
        Optional<Professional> optionalProfessional = professionalRepository.findById(id);
        if (optionalProfessional.isPresent()){
            Professional professional = optionalProfessional.get();
            return modelMapper.map(professional, ProfessionalDTO.class);
        }
        return null;
    }

    @Override
    public List<Professional> searchProfessionals() {
        try {
            List<Professional> professionals = professionalRepository.findAll();
            if (professionals == null) {
                throw new MiException("No se han encontrado profesionales");
            }
            return professionals;
        } catch (MiException e) {
            return null;
        }
    }

    @Override
    public List<Professional> searchProfessionalsBySpecialization(String specializationString) {
        Specialization specialization = ProfessionalUtil.getSpecialization(specializationString);
        return this.professionalRepository.searchBySpecialization(specialization);
    }

    @Override
    public ProfessionalDTO createProfessional(ProfessionalDTO dto) {
        Professional professional = modelMapper.map(dto, Professional.class);
        professional = professionalRepository.save(professional);
        return modelMapper.map(professional, ProfessionalDTO.class);
    }

    public ProfessionalDTO updateProfessional(String userId, ProfessionalDTO professionalDTO){
        User user = userRepository.findById(userId).orElse(null);
        Optional<Professional> professionalResponse = professionalRepository.findByUser(user);
        if (professionalResponse.isPresent()){
            Professional professional = professionalResponse.get();
            if (professionalDTO.getEntryTime() != null && professionalDTO.getExitTime() != null){
                appointmentService.deleteAppointmentAvailable(professionalResponse.get().getId());
                professional.setEntryTime(professionalDTO.getEntryTime());
                professional.setExitTime(professionalDTO.getExitTime());
                appointmentService.generateAppointments(professional);
            }
            if (professionalDTO.getCharge() != 0){
                professional.setCharge(professionalDTO.getCharge());
            }
            professionalRepository.save(professional);
        }
        return null;
    }


    @Override
    public void deleteProfessional(String id) {
        professionalRepository.deleteById(id);
    }

    @Override
    public ProfessionalDTO updateDropOut(String id) {
        Optional<Professional> professionalInfo = professionalRepository.findById(id);
        if (professionalInfo.isPresent()) {
            Professional professional = professionalInfo.get();
            if (professional.isDropOut()) {
                professional.setDropOut(false);
            } else {
                professional.setDropOut(true);
            }
            professional = professionalRepository.save(professional);
            return modelMapper.map(professional, ProfessionalDTO.class);
        }
        return null;
    }

    @Override
    public Professional getProfessionalByUserId(String id) {
        Optional<Professional> professionalResponse = professionalRepository.getProfessionalByUserId(id);
        if (professionalResponse.isPresent()){
            return professionalResponse.get();
        }
        return null;
    }

    @Override
    public List<Appointment> getAssignAppointment(String professionalId){
        List<Appointment> appointments = appointmentRepository.getAssignAppointment(professionalId);
        return appointments.stream().map(appointment -> modelMapper.map(appointment, Appointment.class))
                .collect(Collectors.toList());
    }

    @Override
    public Professional getProfessionalById(String id) {
        Optional<Professional> professionalRes = professionalRepository.findById(id);
        if (professionalRes.isPresent()) {
            return professionalRes.get();
        }
        return null;
    }
}
