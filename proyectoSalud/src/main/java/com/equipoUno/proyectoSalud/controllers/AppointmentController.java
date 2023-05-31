



package com.equipoUno.proyectoSalud.controllers;

import com.equipoUno.proyectoSalud.dto.AppointmentDTO;
import com.equipoUno.proyectoSalud.exceptions.MiException;
import com.equipoUno.proyectoSalud.servicies.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequestMapping("/appointment")
public class AppointmentController {

    private final AppointmentService appointmentService;

    @Autowired
    AppointmentController(AppointmentService appointmentService){
        this.appointmentService = appointmentService;
    }

    @PostMapping("/addAppointment")
    public String addAppointment(@ModelAttribute("appointmentDTO") AppointmentDTO appointmentDto, Model model) throws MiException {
        try {
            AppointmentDTO createdAppointment = appointmentService.addAppointment(appointmentDto);
            model.addAttribute("createdAppointment", createdAppointment);
            return "appointment-success";
        } catch (MiException e){
            model.addAttribute("error", e.getMessage());
            return "appointment-error";
        }
    }

    @PostMapping("/{id}/date")
    public ResponseEntity<AppointmentDTO> updateAppointment(@PathVariable String id,@ModelAttribute("appointmentDTO") AppointmentDTO appointmentDTO, @RequestParam LocalDateTime newDate, Model model){
        try {
            AppointmentDTO updateAppointment = appointmentService.updateAppointmentDate(id, appointmentDTO, newDate);
            model.addAttribute("exito", "El turno se actualizó correctamente");
        } catch (MiException e){
            model.addAttribute("error", "Error al actualizar el turno");
        }
        return null; //Vista del form para cambiar el turno
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAppointment(@PathVariable String id, Model model) {
        appointmentService.deleteAppointment(id);
        model.addAttribute("exito", "El turno se eliminó correctamente");
        return null; // vista de la lista de turnos
    }


    @GetMapping ("/allAppointments")
    public String listAppointments(ModelMap model) {
        List<AppointmentDTO> appointments = appointmentService.availableAppointments();
        model.addAttribute("appointments", appointments);
        return "appointment_list";
    }

    @GetMapping ("/occupiedAppointments")
    public String occupiedAppointments(ModelMap model) {
        List<AppointmentDTO> occupiedAppointments = appointmentService.occupiedAppointmentsDTO();
        model.addAttribute("appointments", occupiedAppointments);
        return "occupiedAppointments";
    }

}