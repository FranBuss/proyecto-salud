package com.equipoUno.proyectoSalud.controllers;

import com.equipoUno.proyectoSalud.entities.Appointment;
import com.equipoUno.proyectoSalud.entities.Patient;
import com.equipoUno.proyectoSalud.entities.Professional;
import com.equipoUno.proyectoSalud.entities.User;
import com.equipoUno.proyectoSalud.enumerations.Specialization;
import com.equipoUno.proyectoSalud.repositories.AppointmentRepository;
import com.equipoUno.proyectoSalud.servicies.AppointmentServiceImplement;
import com.equipoUno.proyectoSalud.servicies.PatientServiceImplement;
import com.equipoUno.proyectoSalud.servicies.ProfessionalServiceImplement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/appointment")
public class AppointmentController {

    private final AppointmentServiceImplement appointmentService;
    private final ProfessionalServiceImplement professionalService;
    private final PatientServiceImplement patientService;

    @Autowired
    AppointmentController(PatientServiceImplement patientService, AppointmentServiceImplement appointmentService,
            ProfessionalServiceImplement professionalService) {
        this.appointmentService = appointmentService;
        this.professionalService = professionalService;
        this.patientService = patientService;
    }

    @GetMapping("/getProfessionals")
    public String getProfessionalsBySpecialty(@RequestParam Specialization specialization, ModelMap model) {
        List<Professional> professionals = professionalService
                .searchProfessionalsBySpecialization(specialization.toString());
        if (!professionals.isEmpty()) {
            model.put("professionals", professionals);
        } else {
            model.put("professionals", null);
        }
        model.put("page", "getAppointment1");
        return "getAppointment";
    }

    @GetMapping("/allAppointments/{id}")
    public String listAppointments(@PathVariable String id, ModelMap model) {
        List<Appointment> appointments = appointmentService.getAppointmentsByProfessional(id);
        model.put("appointments", appointments);
        model.put("page", "getAppointment2");
        return "getAppointment";
    }

    @GetMapping("/confirmAppointment/{id}")
    public String confirmAppointment(@PathVariable String id, ModelMap model) {
        Optional<Appointment> appointmentResponse = appointmentService.getAppointmentById(id);
        if (appointmentResponse.isPresent()) {
            Appointment appointment = appointmentResponse.get();
            model.put("appointment", appointment);
        } else {
            model.put("appointment", null);
        }
        model.put("page", "getAppointment3");
        return "getAppointment";
    }

    @PostMapping("/assignAppointments/{appId}")
    public String assignAppointment(@PathVariable String appId, HttpSession session, ModelMap model) {
        User user = (User) session.getAttribute("userSession");
        Patient patient = patientService.getPatientByUserId(user.getId());
        appointmentService.assignAppointment(patient, appId);
        model.put("success", "El turno ha sido confirmado.");
        model.put("page", "getAppointmentSuccess");
        return "getAppointment";
    }

    @GetMapping("/patientAppointments/{action}")
    public String listPatientAppointments(@PathVariable String action, HttpSession session, ModelMap model) {
        User user = (User) session.getAttribute("userSession");
        Patient patient = patientService.getPatientByUserId(user.getId());
        List<Appointment> appointments = appointmentService.getAppointmentsByPatient(patient.getId());
        if (!appointments.isEmpty()) {
            model.put("appointments", appointments);
        } else {
            model.put("appointments", null);
        }
        if (action.equals("list")) {
            model.put("page", "listAppointments");
            return "appointments";
        } else if (action.equals("modify")) {
            model.put("page", "modifyAppointment");
            return "editAppointment";
        }
        return null;
    }

    @GetMapping("/modify/{id}")
    public String modifyAppointment(@PathVariable String id) {
        String professionalID = appointmentService.getProfessionalByIdAppointment(id);
        appointmentService.resetAppointmentById(id);
        String url = "redirect:/appointment/allAppointments/" + professionalID;
        return url;
    }

    @GetMapping("/delete/{id}")
    public String deleteAppointment(@PathVariable String id) {
        return "redirect:/appointment/patientAppointments/list";
    }

    // @PostMapping("/addAppointment")
    // public String addAppointment(@ModelAttribute("appointmentDTO") AppointmentDTO
    // appointmentDto, Model model) throws MiException {
    // try {
    // AppointmentDTO createdAppointment =
    // appointmentService.addAppointment(appointmentDto);
    // model.addAttribute("createdAppointment", createdAppointment);
    // return "appointment-success";
    // } catch (MiException e) {
    // model.addAttribute("error", e.getMessage());
    // return "appointment-error";
    // }
    // }
    //
    // @PostMapping("/{id}/date")
    // public ResponseEntity<AppointmentDTO> updateAppointment(@PathVariable String
    // id, @ModelAttribute("appointmentDTO") AppointmentDTO appointmentDTO,
    // @RequestParam LocalDateTime newDate, Model model) {
    // try {
    // AppointmentDTO updateAppointment =
    // appointmentService.updateAppointmentDate(id, appointmentDTO, newDate);
    // model.addAttribute("exito", "El turno se actualizó correctamente");
    // } catch (MiException e) {
    // model.addAttribute("error", "Error al actualizar el turno");
    // }
    // return null; //Vista del form para cambiar el turno
    // }
    //
    // @DeleteMapping("/{id}")
    // public ResponseEntity<Void> deleteAppointment(@PathVariable String id, Model
    // model) {
    // appointmentService.deleteAppointment(id);
    // model.addAttribute("exito", "El turno se eliminó correctamente");
    // return null; // vista de la lista de turnos
    // }

    // @GetMapping("/occupiedAppointments")
    // public String occupiedAppointments(ModelMap model) {
    // List<AppointmentDTO> occupiedAppointments =
    // appointmentService.occupiedAppointmentsDTO();
    // model.addAttribute("appointments", occupiedAppointments);
    // return "occupiedAppointments";
    // }
}