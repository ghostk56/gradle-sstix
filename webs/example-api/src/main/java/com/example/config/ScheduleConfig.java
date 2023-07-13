package com.example.config;

import org.springframework.scheduling.annotation.Scheduled;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ScheduleConfig {

	private Integer checkSystemNumber = 1;

	private Integer fixedRateNumber = 1;

	@Scheduled(cron = "${schedule.check.system}")
	public void exampleScheduleCheckSystem() {
		log.info("Schedule Check System: " + checkSystemNumber++);
	}

	@Scheduled(fixedRate = 10000)
	public void exampleFixedRate() {
		log.info("Schedule Fixed Rate: " + fixedRateNumber++);
	}

}
