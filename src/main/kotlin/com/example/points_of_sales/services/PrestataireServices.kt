package com.example.points_of_sales.services

import com.example.points_of_sales.entity.Prestataire
import com.example.points_of_sales.repository.PrestataireRepository
import org.springframework.stereotype.Service

@Service
class PrestataireServices(val prestataireRepository: PrestataireRepository) {
    fun getAllPrestataires(): List<Prestataire> {
        return prestataireRepository.findAll()
    }
    fun CreatePrestataire(prestataire: Prestataire): Prestataire {
         return prestataireRepository.save(prestataire)
    }
    fun getPrestataireById(id: Long): Prestataire {
        return prestataireRepository.findById(id).get()
    }
}