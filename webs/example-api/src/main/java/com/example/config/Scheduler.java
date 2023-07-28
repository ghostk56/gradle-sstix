package com.example.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;

//@Configuration
public class Scheduler {

	@Value("${schedule.enable}")
	private Boolean scheduleEnable;

	@Bean
	public ScheduleConfig scheduleConfig() {
		if (scheduleEnable) {
			return new ScheduleConfig();
		} else {
			return null;
		}
	}
}
