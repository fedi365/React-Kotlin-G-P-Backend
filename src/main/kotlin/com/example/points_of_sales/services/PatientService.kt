package com.example.points_of_sales.services

import com.example.points_of_sales.entity.Patient
import com.example.points_of_sales.repository.PatientRepository
import org.springframework.stereotype.Service

@Service
class PatientService (private val patientRepository : PatientRepository) {

    fun createPatient(patient: Patient):Patient {
        return patientRepository.save(patient)
    }
    fun getallpatient():List<Patient> = patientRepository.findAll()

    fun updatePatient(id: Long, updatedPatient: Patient): Patient {
        val existingPatient = patientRepository.findById(id).orElseThrow {
            RuntimeException("Patient with id $id not found")
        }

        val updated = existingPatient.copy(
            nom = updatedPatient.nom,
            prenom = updatedPatient.prenom
        )

        return patientRepository.save(updated)
    }
    fun deletePatient(id: Long) {
        patientRepository.deleteById(id)
    }




}