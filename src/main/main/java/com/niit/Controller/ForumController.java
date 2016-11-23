package com.niit.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.niit.DAO.ForumDAO;
import com.niit.Model.Forum;

@RestController
public class ForumController {
	
	@Autowired
	ForumDAO forumDao;

	public ForumDAO getForumDao() {
		return forumDao;
	}

	public void setForumDao(ForumDAO forumDao) {
		this.forumDao = forumDao;
	}
	
	@GetMapping("/forum")
	public ResponseEntity<List> getforums() {
		List forumlist = forumDao.list();
		return new ResponseEntity(forumlist,HttpStatus.OK);
	}
	
	@PostMapping("/forum")
	public ResponseEntity createforum(@RequestBody Forum forum) {

		forumDao.saveOrUpdate(forum);

		return new ResponseEntity(forum, HttpStatus.OK);
	}

}
