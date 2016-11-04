package com.niit.DAO;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.niit.Model.Blog;
import com.niit.Model.Forum;

public class ForumDAOImpl implements ForumDAO {
	
	public ForumDAOImpl(SessionFactory sessionFactory) {
		super();
		this.sessionFactory = sessionFactory;
	}

	@Autowired
	private SessionFactory sessionFactory;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	@Transactional
	public List<Forum> list() {
		// TODO Auto-generated method stub
		@SuppressWarnings({ "unchecked" })
		List<Forum> listForum = sessionFactory.getCurrentSession().createCriteria(Forum.class)
				.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
		return listForum;
	}

	
	
	public void delete(String forumid) {
		// TODO Auto-generated method stub

	}

	public Forum get(String forum_title) {
		// TODO Auto-generated method stub
		return null;
	}
	@Transactional
	public void saveOrUpdate(Forum forum) {
		// TODO Auto-generated method stub
		sessionFactory.getCurrentSession().saveOrUpdate(forum);
	}

}
