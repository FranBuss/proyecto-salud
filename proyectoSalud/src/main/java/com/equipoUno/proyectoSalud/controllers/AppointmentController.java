package com.equipoUno.proyectoSalud.controllers;

import com.equipoUno.proyectoSalud.dto.AppointmentDTO;
import com.equipoUno.proyectoSalud.dto.PatientDTO;
import com.equipoUno.proyectoSalud.exceptions.MiException;
import com.equipoUno.proyectoSalud.servicies.AppointmentService;
import com.equipoUno.proyectoSalud.servicies.AppointmentServiceImplement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequestMapping("/appointment")
public class AppointmentController {

    private final AppointmentServiceImplement appointmentService;

    @Autowired
    AppointmentController(AppointmentServiceImplement appointmentService){
        this.appointmentService = appointmentService;
    }
    @PostMapping("/addAppointment")
    public ResponseEntity<AppointmentDTO> addAppointment(@RequestBody AppointmentDTO appointmentDto) throws MiException {
        try {
            AppointmentDTO createdAppointment = appointmentService.addAppointment(appointmentDto);
            return ResponseEntity.ok(createdAppointment);
        } catch (MiException e){
            return ResponseEntity.badRequest().body(null);
        }
    }

    @PostMapping("/{id}/date")
    public ResponseEntity<AppointmentDTO> updateAppointment(@PathVariable String id, @RequestParam LocalDateTime newDate, Model model){
        try {
            AppointmentDTO updateAppointment = appointmentService.updateAppointmentDate(id, newDate);
            model.addAttribute("exito", "El turno se actualizó correctamente")
        } catch (MiException e){
            model.addAttribute("error", "Error al actualizar el turno");
        }
        return
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAppointment(@PathVariable String id, Model model) {
        try {
            appointmentService.deleteAppointment(id);
            model.addAttribute("exito", "El turno se eliminó correctamente")
        }catch (MiException e){
            model.addAttribute("error", "Error al eliminar el turno")
        }
        return null;
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
        return "occupiedApointments";
    }

}
