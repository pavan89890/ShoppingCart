package com.pavan.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.pavan.beans.ApiResponse;
import com.pavan.beans.UserBean;
import com.pavan.modal.User;
import com.pavan.repository.UserRespository;
import com.pavan.service.UserService;
import com.pavan.util.Utility;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserRespository usersRepository;

	private String message = "";

	@Override
	public ApiResponse getUsers() {
		if (usersRepository.findAll() == null) {
			return new ApiResponse(HttpStatus.NOT_FOUND, "No data found", null);
		}
		return new ApiResponse(HttpStatus.OK, null, usersRepository.findAll());
	}

	@Override
	public void saveUser(UserBean userBean) throws Exception {

		if (!validData(userBean)) {
			throw new Exception(message);
		}

		User user = new User();
		if (userBean.getId() != null) {
			user.setId(userBean.getId());
		}
		user.setName(userBean.getName());
		user.setMobile(userBean.getMobile());

		User c = usersRepository.findByMobile(user.getMobile());

		if (c != null) {
			if ((user.getId() == null) || (user.getId() != c.getId())) {
				message = "Mobile Number Already Exists";
				throw new Exception(message);
			}
		}

		usersRepository.save(user);

	}

	private boolean validData(UserBean bean) {

		if (Utility.isEmpty(bean.getName())) {
			message = "Please Enter Name";
			return false;
		}

		if (Utility.isEmpty(bean.getMobile())) {
			message = "Please Enter Mobile";
			return false;
		}

		return true;
	}

	@Override
	public ApiResponse getUser(Long id) {
		if (id == null || id == 0) {
			return new ApiResponse(HttpStatus.INTERNAL_SERVER_ERROR, "No data found", null);
		} else {

			Optional<User> userOp = usersRepository.findById(id);
			if (userOp.isPresent()) {
				User user = userOp.get();
				return new ApiResponse(HttpStatus.OK, null, user);
			} else {
				return new ApiResponse(HttpStatus.NO_CONTENT, "No data found", null);
			}
		}
	}

	@Override
	public ApiResponse deleteUser(Long id) {
		if (getUser(id).getData() == null) {
			return new ApiResponse(HttpStatus.INTERNAL_SERVER_ERROR, "No data found", null);
		} else {
			usersRepository.delete((User) getUser(id).getData());
			message = "User deleted successfully";
			return new ApiResponse(HttpStatus.OK, message, null);
		}
	}

	@Override
	public ApiResponse deleteUsers() {
		usersRepository.deleteAll();
		message = "Users deleted successfully";
		return new ApiResponse(HttpStatus.OK, message, null);
	}

}
