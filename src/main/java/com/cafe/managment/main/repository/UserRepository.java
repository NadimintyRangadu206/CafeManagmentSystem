package com.cafe.managment.main.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cafe.managment.main.model.UserInfo;
import com.cafe.managment.main.request.UserRequest;

import jakarta.transaction.Transactional;

@Repository
public interface UserRepository extends JpaRepository<UserInfo, Integer> {

	Optional<UserInfo> findByEmailId(String emailId);

	@Query(value = "select email_id,id,password,status,role,user_name from user u where u.email_id=?", nativeQuery = true)
	UserInfo findByyEmailId(String emailId);

	Optional<UserInfo> findByUserName(String username);

	@Transactional
	@Query(value = "Select * from user u", nativeQuery = true)
	List<UserInfo> getAllUsers();

}
