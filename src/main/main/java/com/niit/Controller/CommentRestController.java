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

import com.niit.DAO.CommentDAO;
import com.niit.Model.Comment;
import com.niit.Model.Forum;
import com.niit.Model.User;

@RestController
public class CommentRestController {
	
	@Autowired
	Comment comment;
	
	@Autowired
	CommentDAO commentDAO;

	public Comment getComment() {
		return comment;
	}

	public void setComment(Comment comment) {
		this.comment = comment;
	}

	public CommentDAO getCommentDAO() {
		return commentDAO;
	}

	public void setCommentDAO(CommentDAO commentDAO) {
		this.commentDAO = commentDAO;
	}
	
	@GetMapping("/comments")
	public ResponseEntity<List> getCustomers() {
		List commentlist = commentDAO.list();
		return new ResponseEntity(commentlist,HttpStatus.OK);
	}

	@GetMapping("/comments/{bid}")
	public ResponseEntity getCustomer(@PathVariable("bid") int cid) {

		List<Comment> comments = commentDAO.get(cid);
		if (comments == null) {
			return new ResponseEntity("No Customer found for ID " + cid, HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity(comments, HttpStatus.OK);
	}
	
	@PostMapping("/comments")
	public ResponseEntity createcomment(@RequestBody Comment comment) {

		commentDAO.saveOrUpdate(comment);

		return new ResponseEntity(comment, HttpStatus.OK);
	}
}
