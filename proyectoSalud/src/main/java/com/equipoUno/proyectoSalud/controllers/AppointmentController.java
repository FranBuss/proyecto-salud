package com.equipoUno.proyectoSalud.controllers;

import com.equipoUno.proyectoSalud.dto.AppointmentDTO;
import com.equipoUno.proyectoSalud.entities.Appointment;
import com.equipoUno.proyectoSalud.entities.Patient;
import com.equipoUno.proyectoSalud.entities.Professional;
import com.equipoUno.proyectoSalud.entities.User;
import com.equipoUno.proyectoSalud.enumerations.Specialization;
import com.equipoUno.proyectoSalud.exceptions.MiException;
import com.equipoUno.proyectoSalud.servicies.AppointmentService;
import com.equipoUno.proyectoSalud.servicies.AppointmentServiceImplement;
import com.equipoUno.proyectoSalud.servicies.PatientServiceImplement;
import com.equipoUno.proyectoSalud.servicies.ProfessionalServiceImplement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequestMapping("/appointment")
public class AppointmentController {

    private final AppointmentServiceImplement appointmentService;
    private final ProfessionalServiceImplement profesionalService;
    private final PatientServiceImplement patientServiceImplement;

    @Autowired
    AppointmentController(PatientServiceImplement patientServiceImplement ,AppointmentServiceImplement appointmentService, ProfessionalServiceImplement professionalService) {
        this.appointmentService = appointmentService;
        this.profesionalService = professionalService;
        this.patientServiceImplement = patientServiceImplement;
    }

    @GetMapping("/getProfessionals")
    public String getProfessionalsBySpecialty(@RequestParam Specialization specialization, ModelMap model) {
        List<Professional> professionals = profesionalService.searchProfessionalsBySpecialization(specialization.toString());
        if (!professionals.isEmpty()) {
            model.put("professionals", professionals);
        } else {
            model.put("professionals", null);
        }
        model.put("page", "getAppointment");
        return "appointments";
    }

    @GetMapping("/allAppointments/{id}")
    public String listAppointments(@PathVariable String id, ModelMap model) {
        List<Appointment> appointments = appointmentService.getAppointmentsByProfessional(id);
        model.put("appointments", appointments);
        model.put("page", "getAppointment2");
        return "appointments";
    }

    @GetMapping("/confirmAppointment/{id}")
    public String confirmAppointment(@PathVariable String id, ModelMap model) {
        Appointment appointment = appointmentService.getAppointmentById(id);
        model.put("appointment", appointment);
        model.put("page", "getAppointment3");
        return "appointments";
    }

    @PostMapping("/assignAppointments/{appId}")
    public String assignAppointment(@PathVariable String appId, @ModelAttribute("appointmentDTO") AppointmentDTO appointmentDTO, HttpSession session, ModelMap model){
        User user = (User) session.getAttribute("userSession");
        Patient patient = patientServiceImplement.getPatientByUserId(user.getId());
        appointmentService.assignAppointment(patient, appId);
        model.put("exito", "El turno ha sido confirmado.");
        model.put("page", "getAppointmentSuccess");
        return "appointments";
    }

//    @PostMapping("/addAppointment")
//    public String addAppointment(@ModelAttribute("appointmentDTO") AppointmentDTO appointmentDto, Model model) throws MiException {
//        try {
//            AppointmentDTO createdAppointment = appointmentService.addAppointment(appointmentDto);
//            model.addAttribute("createdAppointment", createdAppointment);
//            return "appointment-success";
//        } catch (MiException e) {
//            model.addAttribute("error", e.getMessage());
//            return "appointment-error";
//        }
//    }
//
//    @PostMapping("/{id}/date")
//    public ResponseEntity<AppointmentDTO> updateAppointment(@PathVariable String id, @ModelAttribute("appointmentDTO") AppointmentDTO appointmentDTO, @RequestParam LocalDateTime newDate, Model model) {
//        try {
//            AppointmentDTO updateAppointment = appointmentService.updateAppointmentDate(id, appointmentDTO, newDate);
//            model.addAttribute("exito", "El turno se actualizó correctamente");
//        } catch (MiException e) {
//            model.addAttribute("error", "Error al actualizar el turno");
//        }
//        return null; //Vista del form para cambiar el turno
//    }
//
//    @DeleteMapping("/{id}")
//    public ResponseEntity<Void> deleteAppointment(@PathVariable String id, Model model) {
//        appointmentService.deleteAppointment(id);
//        model.addAttribute("exito", "El turno se eliminó correctamente");
//        return null; // vista de la lista de turnos
//    }

//    @GetMapping("/occupiedAppointments")
//    public String occupiedAppointments(ModelMap model) {
//        List<AppointmentDTO> occupiedAppointments = appointmentService.occupiedAppointmentsDTO();
//        model.addAttribute("appointments", occupiedAppointments);
//        return "occupiedAppointments";
//    }
}