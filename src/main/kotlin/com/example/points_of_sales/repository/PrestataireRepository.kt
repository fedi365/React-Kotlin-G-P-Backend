package com.example.points_of_sales.repository

import com.example.points_of_sales.entity.Prestataire
import org.springframework.data.jpa.repository.JpaRepository

interface PrestataireRepository : JpaRepository <Prestataire, Long> {
}