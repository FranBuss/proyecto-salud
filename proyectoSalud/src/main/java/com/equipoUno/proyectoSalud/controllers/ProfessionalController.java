package com.equipoUno.proyectoSalud.controllers;

import com.equipoUno.proyectoSalud.dto.MedicalRecordDTO;
import com.equipoUno.proyectoSalud.dto.ProfessionalDTO;
import com.equipoUno.proyectoSalud.entities.*;
import com.equipoUno.proyectoSalud.enumerations.BloodType;
import com.equipoUno.proyectoSalud.enumerations.Gender;
import com.equipoUno.proyectoSalud.enumerations.Specialization;
import com.equipoUno.proyectoSalud.exceptions.MiException;
import com.equipoUno.proyectoSalud.repositories.AppointmentRepository;
import com.equipoUno.proyectoSalud.servicies.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/professional")
public class ProfessionalController {

    private final ProfessionalService professionalService;
    private final UserServiceImplement userService;
    private final AppointmentServiceImplement appointmentService;
    private final MedicalRecordImplement medicalRecordService;
    private final PatientServiceImplement patientService;

    @Autowired
    public ProfessionalController(AppointmentServiceImplement appointmentService, MedicalRecordImplement medicalRecordService,
                                  ProfessionalService professionalService, UserServiceImplement userService, PatientServiceImplement patientService) {
        this.professionalService = professionalService;
        this.userService = userService;
        this.medicalRecordService = medicalRecordService;
        this.appointmentService = appointmentService;
        this.patientService = patientService;
    }

    @GetMapping("/dashboard")
    public String professionalDashboard(HttpSession session, ModelMap model){
        model = userService.getUserData(session, model);
        User user = (User) session.getAttribute("userSession");
        Professional professional = professionalService.getProfessionalByUserId(user.getId());
        System.out.println(professional.getUser().getName());
        List<Appointment> appointments = professionalService.getAssignAppointment(professional.getId());
        if (!appointments.isEmpty()) {
            model.put("appointments", appointments);
        } else {
            model.put("appointments", null);
        }
        model.put("page", "listProfAppointments");
        return "professional_dash";
    }

    @GetMapping("/appointment/view/{id}")
    public String viewAppointment(@PathVariable String id, ModelMap model, HttpSession session) {
        model = userService.getUserData(session, model);
        Optional<Appointment> appointmentResponse = appointmentService.getAppointmentById(id);
        if (appointmentResponse.isPresent()) {
            Appointment appointment = appointmentResponse.get();
            model.put("appointment", appointment);
            model.put("page", "listProfAppointments1");
        }
        return "professional_dash";
    }

    @GetMapping("/medicalRecord/form/{appId}")
    public String medicalRecordForm(@PathVariable String appId, ModelMap model, HttpSession session){
        model = userService.getUserData(session, model);
        Optional<Appointment> appointmentResponse = appointmentService.getAppointmentById(appId);
        if (appointmentResponse.isPresent()) {
            Appointment appointment = appointmentResponse.get();
            model.put("appointment", appointment);
        }
        MedicalRecordDTO medicalRecordDTO = new MedicalRecordDTO();
        BloodType[] bloodTypes = BloodType.values();
        Gender[] genders = Gender.values();
        model.put("medicalRecordDTO", medicalRecordDTO);
        model.put("bloodTypes", bloodTypes);
        model.put("genders", genders);
        model.put("page", "listProfAppointments2");
        return "professional_dash";
    }

    @PostMapping("/medicalRecord/create/{appId}")
    public String assignMedicalRecord(@PathVariable("appId") String appId, @RequestParam("dni") Long dni, @ModelAttribute("medicalRecordDTO") MedicalRecordDTO medicalRecordDTO,
                                      HttpSession session, ModelMap model){
        model = userService.getUserData(session, model);
        Optional<Appointment> appointmentRes = appointmentService.getAppointmentById(appId);
        User user = (User) session.getAttribute("userSession");
        Professional professional = professionalService.getProfessionalByUserId(user.getId());
        if (appointmentRes.isPresent()) {
            Patient patient = appointmentRes.get().getPatient();
            medicalRecordService.createMedicalRecord(dni, patient, professional, medicalRecordDTO);
            appointmentService.deleteUsedAppointmentById(appId);
        }
        model.put("page", "listProfAppointments3");
        model.put("success", "La ficha se ha cargado con exito.");
        return "professional_dash";
    }

    @GetMapping("/medicalHistory/patients/view")
    public String viewPatientsFromMedicalHistory(ModelMap model, HttpSession session) {
        model = userService.getUserData(session, model);
        List<Patient> patients = patientService.getPatientsWithMedicalRecord();
        if (!patients.isEmpty()) {
            model.put("patients", patients);
        } else {
            model.put("patients", null);
            model.put("alert", "No existen fichas cargadas.");
        }
        model.put("page", "medicalHistory");
        return "medicalHistory";
    }

    @GetMapping("/medicalHistory/patient/{id}")
    public String listMedicalRecords(@PathVariable String id, ModelMap model, HttpSession session){
        model = userService.getUserData(session, model);
        List<MedicalRecord> medicalRecords = medicalRecordService.getMedicalRecordsByPatient(id);
        model.put("medicalRecords", medicalRecords);
        model.put("page", "medicalHistory1");
        return "medicalHistory";
    }

    @GetMapping("/medicalHistory/medicalRecord/{id}")
    public String viewMedicalRecord(@PathVariable String id, ModelMap model, HttpSession session) {
        model = userService.getUserData(session, model);
        MedicalRecord medicalRecord = medicalRecordService.getMedicalRecordById(id);
        model.put("medicalRecord", medicalRecord);
        model.put("page", "medicalHistory2");
        return "medicalHistory";
    }

    //Update a Professional
    @PostMapping("/update/{id}")
    public String update(@PathVariable("id") String id, @ModelAttribute("professionalDTO") ProfessionalDTO professionalDTO) throws MiException {
       professionalService.updateProfessional(id, professionalDTO);
       return "redirect:/profile";
    }


    @PostMapping(value = ("/delete/{id}"))
    public String delete(@RequestBody @PathVariable String id) throws MiException{
        professionalService.deleteProfessional(id);
        return "/admin/professionals";
    }

    @PostMapping(value = "/updateDropOut/{id}")
    public String updateDropOut(@PathVariable("id") String id)  throws MiException {
        professionalService.updateDropOut(id);
        return "redirect:/professional/dashboard";
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @GetMapping("/changeRolToProfessional/{userId}")
    public String assignProfessionalUser(@PathVariable String userId, HttpSession session, ModelMap model){
        model = userService.getUserData(session, model);
        model.put("newProfessional", userService.getOne(userId));
        ProfessionalDTO professionalDTO = new ProfessionalDTO();
        model.put("professionalDTO", professionalDTO);
        Specialization[] specializations = Specialization.values();
        model.addAttribute("specializations", specializations);
        return "professional_form";
    }

    @PostMapping("/changeRol/{userId}")
    public String assignProfessionalUser(@PathVariable("userId") String userId,@ModelAttribute("professionalDTO") ProfessionalDTO professionalDTO) {
        userService.assignProfessionalUser(userId, professionalDTO);
        return "redirect:/admin/users";
    }

    @GetMapping("/{id}")
    public String getProfessional(@PathVariable String id, ModelMap model) {
        ProfessionalDTO professionalDTO = professionalService.getProfessional(id);
        if (professionalDTO != null){
            model.put("professionalDTO", professionalDTO);
            return "professional_view";
        }
        return null;
    }
}
