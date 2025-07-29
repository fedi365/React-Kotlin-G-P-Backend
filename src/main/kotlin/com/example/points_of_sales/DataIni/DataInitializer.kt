package com.example.points_of_sales.DataIni

import com.example.points_of_sales.Security.CustomUserDetailsService
import com.example.points_of_sales.Security.Role
import com.example.points_of_sales.Security.RoleRepository
import com.example.points_of_sales.Security.User
import com.example.points_of_sales.Security.UserRepository
import com.example.points_of_sales.Security.UserService
import org.springframework.boot.CommandLineRunner
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Component

@Configuration
@Component
class DataInitializer(
    private val roleRepository: RoleRepository,
    private val userService: UserService ,
    private val customUserDetailsService: CustomUserDetailsService
) {
//
//    @Bean
//    fun testUserDetailsService(): CommandLineRunner {
//        return CommandLineRunner {
//            try {
//                val username = "user1" // Remplace par un nom d'utilisateur valide dans ta DB
//                val userDetails = customUserDetailsService.loadUserByUsername(username)
//
//                println("=== Résultat de loadUserByUsername ===")
//                println("Username: ${userDetails.username}")
//                println("Password: ${userDetails.password}")
//                println("Authorities: ${userDetails.authorities}")
//            } catch (e: Exception) {
//                println("Erreur: ${e.message}")
//            }
//        }
//    }
}
//) : CommandLineRunner {
//    override fun run(vararg args: String?) {
//        if(!roleRepository.existsByName("USER")){
//            roleRepository.save(Role(name = "USER"))
//        }
//        if (!roleRepository.existsByName("ADMIN")) {
//            roleRepository.save(Role(name = "ADMIN"))
//        }
//
//        userService.createUser("user1", "pass", listOf("USER"))
//        userService.createUser("admin1", "pass", listOf("ADMIN"))
//    }
//}

//class DataInitializer {
//
//    @Bean
//    fun initData(userRepo: UserRepository, encoder: PasswordEncoder): CommandLineRunner {
//        return CommandLineRunner {
//            if (userRepo.findByUsername("admin") == null) {
//                val user = User(
//                    username = "admin",
//                    password = encoder.encode("1234"),
//                    roles = "ADMIN,USER" // Ajout des rôles
//                )
//                userRepo.save(user)
//                println("🟢 Admin user created with username=admin and password=1234")
//            }
//
//            if (userRepo.findByUsername("user") == null) {
//                val user = User(
//                    username = "user",
//                    password = encoder.encode("1234"),
//                    roles = "USER" // Rôle de base
//                )
//                userRepo.save(user)
//                println("🟢 Standard user created with username=user and password=1234")
//            }
//        }
//    }
