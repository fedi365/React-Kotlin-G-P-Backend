package com.example.points_of_sales.controller

import com.example.points_of_sales.entity.Patient
import com.example.points_of_sales.services.PatientService
import org.springframework.http.HttpStatus
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
@CrossOrigin(origins = ["http://localhost:19000", "exp://192.168.1.100:19000"])
@RequestMapping("/patients")
class PatientController(private val patientService: PatientService) {

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun createPatient(@RequestBody patient: Patient): Patient {
        return patientService.createPatient(patient)
    }

    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @GetMapping
    fun findAllPatients(): List<Patient> {
        return patientService.getallpatient()
    }

}
