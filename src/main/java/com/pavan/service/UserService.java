package com.pavan.service;

import org.springframework.stereotype.Service;

import com.pavan.beans.ApiResponse;
import com.pavan.beans.UserBean;

@Service
public interface UserService {

	public void saveUser(UserBean user) throws Exception;

	public ApiResponse getUsers();

	public ApiResponse getUser(Long id);

	public ApiResponse deleteUser(Long id);

	public ApiResponse deleteUsers();

}
