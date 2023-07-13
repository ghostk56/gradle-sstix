package com.sstixbackend.service;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import com.sstixbackend.core.response.RestfulResponse;

@SpringBootTest
public class EventsServiceTests {

	@Autowired
	private EventsService eventsService;

	@Test
	void selectAllEvents() {
		ResponseEntity<RestfulResponse<?>> selectAllEvents = eventsService.selectAllEvents("", 0);
		assertNotNull(selectAllEvents);
	}
	
}
