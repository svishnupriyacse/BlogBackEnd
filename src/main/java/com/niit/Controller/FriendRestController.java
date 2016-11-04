package com.niit.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.niit.DAO.FriendListDAO;
import com.niit.Model.FriendList;

@RestController
public class FriendRestController {
	@Autowired
	FriendListDAO friendlist;

	public FriendRestController(FriendListDAO friendlist) {
		super();
		this.friendlist = friendlist;
	}

	public FriendListDAO getFriendlist() {
		return friendlist;
	}

	public void setFriendlist(FriendListDAO friendlist) {
		this.friendlist = friendlist;
	}
	
	@GetMapping("/friends")
	public ResponseEntity<List> getfriends() {
		List<FriendList> frndlist = friendlist.list();
		return new ResponseEntity(frndlist,HttpStatus.OK);
	}

}
