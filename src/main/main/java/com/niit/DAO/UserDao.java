package com.niit.DAO;

import java.util.List;

import com.niit.Model.User;

public interface UserDao {
	public List list();
	public User get(String username);
	public User create(User user);
	public void delete(Long id);
	public User update(Long id, User user);
	public User validate(String username , String password);
}
