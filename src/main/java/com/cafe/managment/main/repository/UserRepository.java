package com.cafe.managment.main.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cafe.managment.main.model.User;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {

}
