package com.niit.DAO;

import java.util.List;

import com.niit.Model.Jobs;


public interface JobsDAO {

	
	public List<Jobs> list();
	
	public Jobs get(Long id);
	
	public Jobs saveOrUpdate(Jobs comment);
		
	public void delete(Long jobId);
	
}
