package com.cafe.managment.main.service;

import java.sql.Date;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cafe.managment.main.exception.CafeException;
import com.cafe.managment.main.model.User;
import com.cafe.managment.main.repository.UserRepository;
import com.cafe.managment.main.request.UserRequest;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	public UserRepository userRepository;

	@Override
	public User saveUser(UserRequest userRequest) {

		Optional<User> optional = userRepository.findByEmailId(userRequest.getEmailId());
		User user = null;

		if (optional.isPresent()) {

			try {
				user = optional.get();
				throw new CafeException(400, "EmailId is already exist");
			} catch (CafeException e) {
				e.getLocalizedMessage();
			}

		} else {

			if (userRequest != null) {
				user = new User();
			}

		}

		BeanUtils.copyProperties(userRequest, user);

		user.setStatus("false");
		user.setRole("User");
		user.getIsActive();
		user.getUpdatedDate(new Date(0));
		User saveUser = userRepository.saveAndFlush(user);

		return saveUser;
	}

}
