package com.cafe.managment.main.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cafe.managment.main.model.UserInfo;

@Repository
public interface UserRepository extends JpaRepository<UserInfo,Integer> {

 
 Optional<UserInfo> findByEmailId(String emailId);

 @Query(value ="select email_id,id,password,status,role,user_name from user u where u.email_id=?", nativeQuery=true)
UserInfo findByyEmailId(String emailId);
 

Optional<UserInfo> findByUserName(String username);

}
