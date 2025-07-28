package com.example.points_of_sales.repository

import com.example.points_of_sales.entity.ActeMedical
import org.springframework.data.jpa.repository.JpaRepository
import java.util.Optional

interface ActeMedicalRepository : JpaRepository<ActeMedical, Long> {
   fun findActeMedicalById(id: Long): Optional<ActeMedical>
}