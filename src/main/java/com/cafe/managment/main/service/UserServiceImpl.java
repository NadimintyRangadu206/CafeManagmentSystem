package com.cafe.managment.main.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.cafe.managment.main.config.JwtTokenUtil;
import com.cafe.managment.main.constatnts.ErrorMessages;
import com.cafe.managment.main.exception.CafeException;
import com.cafe.managment.main.model.UserInfo;
import com.cafe.managment.main.repository.UserRepository;
import com.cafe.managment.main.request.ChangeUserPasswordRequest;
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

	@Autowired
	public BCryptPasswordEncoder bCryptPasswordEncoder;

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

	@Override
	public UserInfo changeUserPassword(ChangeUserPasswordRequest changeUserPasswordRequest) {

		UserInfo userInfo = null;

		try {

			if (changeUserPasswordRequest.getOldPassword() == null
					|| changeUserPasswordRequest.getOldPassword().isEmpty()) {
				throw new CafeException(400, ErrorMessages.PLEASE_ENTER_OLD_PASSWORD);
			}
			if (changeUserPasswordRequest.getNewPassword() == null
					|| changeUserPasswordRequest.getNewPassword().isEmpty()) {
				throw new CafeException(400, ErrorMessages.PLEASE_ENTER_NEW_PASSWORD);
			}
			if (changeUserPasswordRequest.getConfirmPassword() == null
					|| changeUserPasswordRequest.getConfirmPassword().isEmpty()) {
				throw new CafeException(400, ErrorMessages.PLEASE_ENTER_CONFIRM_PASSWORD);
			}

			Optional<UserInfo> optional = userRepository.findById(changeUserPasswordRequest.getId());

			if (optional.isPresent()) {

				userInfo = optional.get();

				if (bCryptPasswordEncoder.matches(changeUserPasswordRequest.getOldPassword(), userInfo.getPassword())) {

					if (changeUserPasswordRequest.getNewPassword()
							.contentEquals(changeUserPasswordRequest.getConfirmPassword())) {

						String encodePassword = bCryptPasswordEncoder
								.encode(changeUserPasswordRequest.getNewPassword());
						userInfo.setPassword(encodePassword);
					} else {
						throw new CafeException(400, ErrorMessages.NEW_AND_CONFIRM_PASSWORD_ARE_NOT_MATCHED);
					}
				} else {
					throw new CafeException(0, ErrorMessages.INVALID_OLD_PASSWORD);
				}

				userInfo = userRepository.saveAndFlush(userInfo);

			} else {
				throw new CafeException(0, ErrorMessages.USER_ID_NOT_FOUND);
			}

		} catch (CafeException e) {
			throw new CafeException(400, e.getMessage());
		}

		return userInfo;
	}

}
