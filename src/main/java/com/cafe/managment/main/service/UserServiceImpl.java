package com.cafe.managment.main.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.stereotype.Service;

import com.cafe.managment.main.config.JwtTokenUtil;
import com.cafe.managment.main.exception.CafeException;
import com.cafe.managment.main.model.UserInfo;
import com.cafe.managment.main.repository.UserRepository;
import com.cafe.managment.main.request.UserRequest;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	public UserRepository userRepository;

	@Qualifier("authentication")
	AuthenticationManager authentication;

	@Autowired
	public CustomUserDetailsServiceImpl customUserDetailsServiceImpl;

	@Autowired
	public JwtTokenUtil jwtTokenUtil;

	@Override
	public UserInfo saveUser(UserRequest userRequest) {

		Optional<UserInfo> optional = userRepository.findByEmailId(userRequest.getEmailId());
		UserInfo user = null;

		if (optional.isPresent()) {

			try {
				user = optional.get();
				throw new CafeException(400, "EmailId is already exist");
			} catch (CafeException e) {
				e.getLocalizedMessage();
			}

		} else {

			if (userRequest != null) {
				user = new UserInfo();

			}

		}

		BeanUtils.copyProperties(userRequest, user);

		user.setStatus("true");
		user.setRole("Admin");
		user.getIsActive();
//		user.getUpdatedDate(new Date(0, 0, 0));
		UserInfo saveUser = userRepository.saveAndFlush(user);

		return saveUser;
	}


	@Override
	public List<UserInfo> getAllUsers() {

		try {
			List<UserInfo> listOfUsers = userRepository.getAllUsers();

			List<UserInfo> getUsers = new ArrayList<UserInfo>();

			for (UserInfo userInfo : listOfUsers) {

				getUsers.add(userInfo);

			}

			if (!getUsers.isEmpty()) {

				return getUsers;
			} else {
				 throw new CafeException(400, "No Records are Fecthching");
			}
		} catch (Exception e) {

			throw new CafeException(400, e.getLocalizedMessage());

		}

	}

	@Override
	public UserRequest getUserById(int id) {

		UserRequest request = null;

		Optional<UserInfo> optionalInfo = userRepository.findById(id);

		UserInfo info = null;

		if (optionalInfo.isPresent()) {

			info = optionalInfo.get();
			BeanUtils.copyProperties(info, request);

		}

		return request;
	}

}
