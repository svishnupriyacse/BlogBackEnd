package com.niit.DAO;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.niit.Model.FriendList;



public class FriendListDAOImpl implements FriendListDAO {

	
	@Autowired
	private SessionFactory sessionFactory;

	public FriendListDAOImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	@Transactional
	public List<FriendList> list(String username) {
		@SuppressWarnings({ "unchecked" })
		List<FriendList> listFriendList = (List<FriendList>) sessionFactory.getCurrentSession().createQuery("from User where userName !='"+ username +"'")
				.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
		return listFriendList;
	}
	@Transactional  
	public List<FriendList> get(String userId) {
		String hql = "from FriendList where friendId ='" + userId + "'";
		org.hibernate.Query query = sessionFactory.getCurrentSession().createQuery(hql);
		@SuppressWarnings("unchecked")
		List<FriendList> listFriendList = (List<FriendList>) query.list();
		if (listFriendList != null && !listFriendList.isEmpty()) {
			return listFriendList;
		}
		return null;
	}
	@Transactional
	public void sendRequest(FriendList FriendId) {
		sessionFactory.getCurrentSession().saveOrUpdate(FriendId);

	}
	@Transactional
	public void delete(String friendId) {
		FriendList friendListToDelete = new FriendList();
		friendListToDelete.setFriendId(friendId);
		sessionFactory.getCurrentSession().delete(friendListToDelete);
	}
	

}
