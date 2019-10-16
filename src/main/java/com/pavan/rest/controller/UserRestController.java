package com.pavan.rest.controller;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pavan.beans.ApiResponse;
import com.pavan.beans.UserBean;
import com.pavan.service.UserService;
import com.pavan.util.Utility;

@RestController
@RequestMapping(path = "/api/users")
@CrossOrigin("*")
public class UserRestController {

	@Autowired
	private UserService userService;
	private String message;

	@PostMapping
	@Transactional(rollbackOn = Exception.class)
	public ApiResponse saveUser(@RequestBody(required = true) UserBean userBean) {

		try {
			userService.saveUser(userBean);

			if (Utility.isEmpty(userBean.getId())) {
				message = "User saved successfully";
			} else {
				message = "User updated successfully";
			}

			return new ApiResponse(HttpStatus.OK, message, null);
		} catch (Exception e) {
			message = "Error-" + e.getMessage();
			return new ApiResponse(HttpStatus.INTERNAL_SERVER_ERROR, message, null);
		}

	}

	@GetMapping
	public ApiResponse users() {
		return userService.getUsers();
	}

	@GetMapping("/{id}")
	public ApiResponse getUser(@PathVariable(value = "id") Long id) {
		return userService.getUser(id);
	}

	@DeleteMapping("/{id}")
	public ApiResponse deleteUser(@PathVariable(value = "id") Long id) {
		return userService.deleteUser(id);
	}

	@DeleteMapping()
	public ApiResponse deleteUsers() {
		return userService.deleteUsers();
	}
}
