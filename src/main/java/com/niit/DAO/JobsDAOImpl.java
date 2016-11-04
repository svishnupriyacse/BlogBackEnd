package com.niit.DAO;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.niit.Model.Jobs;

public class JobsDAOImpl implements JobsDAO {

	@Autowired
	private SessionFactory sessionFactory;

	public JobsDAOImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	@Transactional
	public List<Jobs> list() {
		@SuppressWarnings({ "unchecked" })
		List<Jobs> listJobs = (List<Jobs>) sessionFactory.getCurrentSession().createCriteria(Jobs.class)
				.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
		return listJobs;
	}
	@Transactional
	public Jobs get(Long jobId) {
		String hql = "from Jobs where jobId ='" + jobId + "'";
		org.hibernate.Query query = sessionFactory.getCurrentSession().createQuery(hql);
		@SuppressWarnings("unchecked")
		List<Jobs> listJobs = (List<Jobs>) query.list();
		if (listJobs != null && !listJobs.isEmpty()) {
			return listJobs.get(0);
		}
		return null;
	}
	@Transactional
	public Jobs saveOrUpdate(Jobs jobs) {
		sessionFactory.getCurrentSession().saveOrUpdate(jobs);
		return jobs;

	}
	@Transactional
	public void delete(Long jobId) {
		Jobs jobsToDelete = new Jobs();
		jobsToDelete.setJobId(jobId);
		sessionFactory.getCurrentSession().delete(jobsToDelete);
	}

}
