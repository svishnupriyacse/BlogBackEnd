package com.niit.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.niit.DAO.BlogDAO;
import com.niit.DAO.UserDao;
import com.niit.Model.Blog;

@RestController
public class BlogRestController {

	@Autowired
	private BlogDAO blogDao;

	public BlogDAO getBlogDao() {
		return blogDao;
	}

	public void setBlogDao(BlogDAO blogDao) {
		this.blogDao = blogDao;
	}
	@GetMapping("/bloglist")
	public List getCustomers() {
		return blogDao.list();
	}
	
	@PostMapping("/bloglist")
	public ResponseEntity createblog(@RequestBody Blog blog) {

		blogDao.saveOrUpdate(blog);

		return new ResponseEntity(blog, HttpStatus.OK);
	}
	
}
