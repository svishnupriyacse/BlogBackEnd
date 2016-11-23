package com.niit.DAO;

import java.util.List;

import com.niit.Model.Forum;

public interface ForumDAO {
	
	public List<Forum> list();
	
	public Forum get(String forum_title);
	
	public void saveOrUpdate(Forum forum);
		
	public void delete(String forumid);

}
