package com.arpit.dao;

import org.springframework.beans.factory.annotation.Autowired;

import com.arpit.model.User;

public interface Userdao{
	@Autowired
	public void saveOrUpdate(User user);
	public void delete(String username);
	public User getUser(String username);
}