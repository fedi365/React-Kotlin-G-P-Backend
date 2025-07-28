package com.example.points_of_sales.repository

import com.example.points_of_sales.entity.Assurance
import org.springframework.data.jpa.repository.JpaRepository

interface AssuranceRepository : JpaRepository<Assurance, Long>{

}