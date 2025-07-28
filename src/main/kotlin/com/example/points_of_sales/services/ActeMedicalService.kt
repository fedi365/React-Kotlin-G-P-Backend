package com.example.points_of_sales.services


import com.example.points_of_sales.repository.ActeMedicalRepository
import org.springframework.stereotype.Service

@Service
class ActeMedicalService(
    private val acteMedicalRepository: ActeMedicalRepository
) {
    fun getAll() = acteMedicalRepository.findAll()
}
