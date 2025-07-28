package com.example.points_of_sales.services

import com.example.points_of_sales.entity.CarteAssuree
import com.example.points_of_sales.repository.CarteAssureeRepository
import org.springframework.stereotype.Service
import java.time.LocalDate


    @Service
    class CarteAssureeService(
private val carteAssureeRepository: CarteAssureeRepository
) {

    fun getCarteByNumero(numero: String): CarteAssuree {
        return carteAssureeRepository.findByNumeroCarte(numero)
            ?: throw RuntimeException("Carte assurée non trouvée avec le numéro : $numero")
    }
}