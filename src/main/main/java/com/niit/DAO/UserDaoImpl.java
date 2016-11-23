package com.niit.DAO;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.util.JSONPObject;

import com.niit.Model.User;
@Component
public class UserDaoImpl implements UserDao{

	@Autowired
	private SessionFactory sessionFactory;
	
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public UserDaoImpl(SessionFactory sessionFactory) {
	
		this.sessionFactory = sessionFactory;
	}

	@Transactional
	public List list() {
		// TODO Auto-generated method stub
		@SuppressWarnings({ "deprecation", "unchecked" })
		List<User> listUser = (List<User>)
		sessionFactory.getCurrentSession().createCriteria(User.class)
		.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
		return listUser;
		 
	}

	
	@Transactional	
	public User create(User users) {
		// TODO Auto-generated method stub
		users.setIsOnline("N");
		users.setStatus("N");
		sessionFactory.getCurrentSession().saveOrUpdate(users);
		return users;
	}
	@Transactional	
	public void delete(Long id) {
		User userToDelete = new User();
		userToDelete.setUser_id(id);
		sessionFactory.getCurrentSession().delete(userToDelete);
	}
	@Transactional	
	public User update(Long id, User users) {
		sessionFactory.getCurrentSession().saveOrUpdate(users);
		return users;
	}
	@Transactional	
	public User get(String username) {
		String hql = "from User where userName ='" + username + "'";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		@SuppressWarnings("unchecked")
		List<User> listUser = (List<User>) query.list();
		
		if (listUser != null && !listUser.isEmpty()){
			return listUser.get(0);
		}
		return null;
}

	

@Transactional
	public User validate(String username, String password) {
		// TODO Auto-generated method stub
		String hql = "from User where userName ='" + username + "' AND password = '" + password +"'";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		User user = (User) query.uniqueResult();
		return user;
	}
}