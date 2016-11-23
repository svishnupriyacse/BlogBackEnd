package com.niit.DAO;

import java.util.List;

import com.niit.Model.FriendList;

public interface FriendListDAO {

	public List<FriendList> list(String username);
	
	public List<FriendList> get(String friendId);
	
	public void sendRequest(FriendList FriendId);
	

		
	public void delete(String friendId);
	
	
}
