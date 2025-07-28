package com.example.points_of_sales.repository

import com.example.points_of_sales.entity.CarteAssuree
import org.springframework.data.jpa.repository.JpaRepository

interface CarteAssureeRepository : JpaRepository<CarteAssuree, Long> {
    fun findByNumeroCarte(numero: String) : CarteAssuree?
}