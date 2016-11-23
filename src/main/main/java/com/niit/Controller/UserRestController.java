package com.niit.Controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.niit.DAO.FriendListDAO;
import com.niit.DAO.UserDao;
import com.niit.Model.FriendList;
import com.niit.Model.User; 



@RestController
public class UserRestController {
	@Autowired
	private UserDao userDao;
	
	@Autowired
	FriendListDAO friendlist;
	
	public UserDao getUserDao() {
		return userDao;
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	@GetMapping("/customers")
	public ResponseEntity<List> getCustomers() {
		List listuser = userDao.list();
		return new ResponseEntity(listuser,HttpStatus.OK);
	}

	@GetMapping("/customers/{username}")
	public ResponseEntity getCustomer(@PathVariable("username") String username) {

		User user = userDao.get(username);
		if (user == null) {
			return new ResponseEntity("No Customer found for ID " + username, HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity(user, HttpStatus.OK);
	}

	@PostMapping(value = "/customers")
	public ResponseEntity createCustomer(@RequestBody User customer) {

		userDao.create(customer);

		return new ResponseEntity(customer, HttpStatus.OK);
	}

	/*@DeleteMapping("/customers/{id}")
	public ResponseEntity deleteCustomer(@PathVariable Long id) {

		if (userDAO.delete(id)) {
			return new ResponseEntity("No Customer found for ID " + id, HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity(id, HttpStatus.OK);

	}*/

	@PutMapping("/customers/{id}")
	public ResponseEntity updateCustomer(@PathVariable Long id, @RequestBody User customer) {

		customer = userDao.update(id, customer);

		if (null == customer) {
			return new ResponseEntity("No Customer found for ID " + id, HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity(customer, HttpStatus.OK);
	}
	
	@PostMapping("/authenticate/{username}/{password}")
	public ResponseEntity<User> login(@PathVariable("username") String username,@PathVariable("password") String password,HttpSession session){
		
		User user = userDao.validate(username, password);
		
		if(user == null){
			return new ResponseEntity("No user found" + user.getUser_id(),HttpStatus.NOT_FOUND);
		}else{
			List<FriendList> friends = friendlist.get(username);
			if(friends!=null){
				for(int i=0;i<friends.size();i++){
					FriendList friend = friends.get(i);
					friend.setIsonline('Y');
					friendlist.sendRequest(friend);
				}
			}
			user.setIsOnline("Y");
			userDao.update(user.getUser_id(), user);
			session.setAttribute("loggedInUserId", user.getUserName());
			System.out.println(session.getAttribute("loggedInUserId"));
			return new ResponseEntity(user,HttpStatus.OK);
		}
		
	}
	@GetMapping("/logout")
	public ResponseEntity<User> logout(HttpSession session){
		String LoggedInUser = (String) session.getAttribute("loggedInUserId");
		System.out.println(LoggedInUser);
		User user = userDao.get(LoggedInUser);
		
		if(LoggedInUser == null){
			return new ResponseEntity("No User found",HttpStatus.NOT_FOUND);
			
		}else{
			List<FriendList> friends = friendlist.get(LoggedInUser);
			if(friends!=null){
				for(int i=0;i<friends.size();i++){
					FriendList friend = friends.get(i);
					friend.setIsonline('N');
					friendlist.sendRequest(friend);
				}
			}
			user.setIsOnline("N");
			userDao.update(user.getUser_id(), user);
			return new ResponseEntity<User>(user,HttpStatus.OK);
			
		}
		
	}

}
