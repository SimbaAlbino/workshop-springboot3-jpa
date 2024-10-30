package com.educandoweb.course.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.educandoweb.course.entities.User;

//por herdar do jparepository, é opicional colocar @Repository por já ser explícito no jpa
public interface UserRepository extends JpaRepository<User, Long> {

}	
