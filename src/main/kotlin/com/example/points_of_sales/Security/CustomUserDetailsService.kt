package com.example.points_of_sales.Security

import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service


@Service
class CustomUserDetailsService(
    private val userRepository: UserRepository
) : UserDetailsService {

    override fun loadUserByUsername(username: String): UserDetails {
        val user = userRepository.findByUsername(username)
            ?: throw UsernameNotFoundException("User not found")

        val authorities = user.roles.map { SimpleGrantedAuthority("ROLE_${it.name}") }
        return org.springframework.security.core.userdetails.User(user.username, user.password, authorities)
    }
}

//class CustomUserDetailsService(val userRepository: UserRepository) : UserDetailsService {
//    override fun loadUserByUsername(username: String): UserDetails {
//        val user = userRepository.findByUsername(username)
//            ?: throw UsernameNotFoundException("Utilisateur non trouvé")
//
//        val authorities = user.roles.split(",")
//            .map { it.trim() }
//            .map { SimpleGrantedAuthority("ROLE_$it") } // Format standard Spring Security
//
//        return org.springframework.security.core.userdetails.User(
//            user.username,
//            user.password,
//            authorities
//        )
//    }
//}