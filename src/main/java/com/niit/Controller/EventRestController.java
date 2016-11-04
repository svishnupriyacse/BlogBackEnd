package com.niit.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.niit.DAO.EventDAO;

@RestController
public class EventRestController {
	@Autowired
	EventDAO event;

	public EventDAO getEvent() {
		return event;
	}

	public void setEvent(EventDAO event) {
		this.event = event;
	}
	
	@GetMapping("/events")
	public ResponseEntity<List> getCustomers() {
		List eventlist = event.list();
		return new ResponseEntity(eventlist,HttpStatus.OK);
	}
}
