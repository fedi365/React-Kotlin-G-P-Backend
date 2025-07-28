package com.example.points_of_sales.repository

import com.example.points_of_sales.entity.Facture
import org.springframework.data.jpa.repository.JpaRepository

interface FactureRepository : JpaRepository<Facture, Long> {
}