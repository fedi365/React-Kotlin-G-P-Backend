package com.example.points_of_sales.Security

import org.springframework.data.jpa.repository.JpaRepository

interface RoleRepository : JpaRepository<Role, Long>{
    fun findByName(name: String): Role?
    fun existsByName(name: String): Boolean
}