package com.equipoUno.proyectoSalud.controllers;

import com.equipoUno.proyectoSalud.dto.AppointmentDTO;
import com.equipoUno.proyectoSalud.entities.Appointment;
import com.equipoUno.proyectoSalud.exceptions.MiException;
import com.equipoUno.proyectoSalud.repositories.AppointmentRepository;
import com.equipoUno.proyectoSalud.servicies.AppointmentServiceImplement;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/appointment")
public class AppointmentController {

    private final AppointmentServiceImplement appointmentService;
    private final AppointmentRepository appointmentRepository;

    @Autowired
    public AppointmentController(AppointmentServiceImplement appointmentService, AppointmentRepository appointmentRepository){
        this.appointmentService = appointmentService;
        this.appointmentRepository = appointmentRepository;
    }


    @GetMapping("/appointments/{professionalId}")
    public String appointmentsList(@PathVariable("professionalId") String professionalId, ModelMap model){
        List<Appointment> appointments = appointmentRepository.findByProfessionalId(professionalId);
        model.addAttribute("appointments", appointments);
        return "appointments_list";
    }


    @GetMapping("/create")
    public String appointmentCreate(Model model){
        AppointmentDTO appointmentDTO = new AppointmentDTO();
        model.addAttribute("appointmentDTO", appointmentDTO);
        return "appointment_form";
    }

    @PostMapping("/create")
    public String create(@ModelAttribute("appointmentDTO") AppointmentDTO appointmentDTO) throws MiException {
        appointmentService.addAppointment(appointmentDTO);
        return "redirect:../patient/appointments";
    }



}
