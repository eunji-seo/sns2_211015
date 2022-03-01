package com.sns.user.bo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sns.user.dao.UserDAO;
import com.sns.user.model.User;
@Service
public class UserBO {

	@Autowired
	private UserDAO userDAO;
	
	public boolean existLoginId(String loginId) {
		return userDAO.existLoginId(loginId);
	}
	
	public int addUser(String loginId, String password, String name, String email, String profileImageUrl) {
		return userDAO.insertUser(loginId, password, name, email, profileImageUrl);
	}
	
	public User getLoginByLoginIdPassword(String loginId,String password) {
		return userDAO.selectLoginByLoginIdPassword(loginId, password);
	}
	
	public User getUserById(int userId) {
		return userDAO.selectUserById(userId);
	}
}
