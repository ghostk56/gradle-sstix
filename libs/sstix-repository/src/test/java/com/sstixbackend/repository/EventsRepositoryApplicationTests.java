package com.sstixbackend.repository;

import static org.junit.jupiter.api.Assertions.assertFalse;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.sstixbackend.model.Events;

@SpringBootTest
class EventsRepositoryApplicationTests {

	@Autowired
	private EventsRepository eventsRepository;

	@Test
	void findAll() {
		List<Events> events = eventsRepository.findAll();
		assertFalse(events.isEmpty());
	}

}
