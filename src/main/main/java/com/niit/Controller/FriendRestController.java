package com.niit.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
	
	@GetMapping("/friends/{username}")
	public ResponseEntity<List> getfriends(@PathVariable("username") String username) {
		List<FriendList> frndlist = friendlist.list(username);
		return new ResponseEntity(frndlist,HttpStatus.OK);
	}
	@PostMapping("/friends")
	public ResponseEntity createfriends(@RequestBody FriendList friend){
		
		friendlist.sendRequest(friend);
		return new ResponseEntity(friend, HttpStatus.OK);
		
	}
	@GetMapping("/myfriends/{username}")
	public ResponseEntity<List> getmyfriends(@PathVariable("username") String username) {
		List<FriendList> frndlist = friendlist.get(username);
		return new ResponseEntity(frndlist,HttpStatus.OK);
	}

}
