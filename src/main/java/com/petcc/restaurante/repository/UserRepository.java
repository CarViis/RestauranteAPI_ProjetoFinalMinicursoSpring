package com.petcc.restaurante.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.petcc.restaurante.model.User;
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

}
