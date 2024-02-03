package com.VECRUD.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.VECRUD.Model.user;

public interface userRepository extends JpaRepository< user, Long> {

}
