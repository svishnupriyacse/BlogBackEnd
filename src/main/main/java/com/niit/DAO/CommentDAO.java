package com.niit.DAO;

import java.util.List;

import com.niit.Model.Comment;

public interface CommentDAO {

	public List<Comment> list();
	
	public List<Comment> get(int fid);
	
	public void saveOrUpdate(Comment comment);
		
	public void delete(String userId);
}
