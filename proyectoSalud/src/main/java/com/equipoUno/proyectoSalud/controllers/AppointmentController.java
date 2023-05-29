package com.equipoUno.proyectoSalud.controllers;

import com.equipoUno.proyectoSalud.dto.AppointmentDTO;
import com.equipoUno.proyectoSalud.exceptions.MiException;
import com.equipoUno.proyectoSalud.servicies.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@Controller
@RequestMapping("/turno")
public class AppointmentController {

    private final AppointmentService appointmentService;

    @Autowired
    AppointmentController(AppointmentService appointmentService){
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
        return ""; //Vista del form para cambiar el turno
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAppointment(@PathVariable String id, Model model) {
        try {
            appointmentService.deleteAppointment();
            model.addAttribute("exito", "El turno se eliminó correctamente")
        }catch (MiException e){
            model.addAttribute("error", "Error al eliminar el turno")
        }
        return ""; // vista de la lista de turnos

    }

    /*
    @GetMapping("/occupied")
    public ResponseEntity<List<AppointmentDTO>> OccupiedAppintments(){
        List<AppointmentDTO> occupiedAppointments = appointmentService.occupiedAppointmentsDTO();
        return ResponseEntity.ok(occupiedAppointments);
    }

    */
