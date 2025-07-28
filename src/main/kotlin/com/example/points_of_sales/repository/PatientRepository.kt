package com.example.points_of_sales.repository


import com.example.points_of_sales.entity.Patient
import org.springframework.data.jpa.repository.JpaRepository


interface PatientRepository : JpaRepository<Patient, Long> {
    fun findPatientById(id: Long): Patient

}