package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.repository.CityRepository;

@Service
public class GradleDemoService {

	@Autowired
	private CityRepository cityRepository;

	public Integer CityCount() {
		return cityRepository.selectCityCount().getCityCount();
	}
}
