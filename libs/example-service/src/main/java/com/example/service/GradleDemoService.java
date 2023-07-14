package com.example.service;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.repository.CityRepository;
import com.example.service.testCollectionBean.BaseBean;

@Service
public class GradleDemoService {

	@Autowired
	private CityRepository cityRepository;

	@Autowired
	private Set<BaseBean> basebean;

	public Integer CityCount() {
		testBaseBean();
		return cityRepository.selectCityCount().getCityCount();
	}

	public void testBaseBean() {
		for (BaseBean bean : basebean) {
			bean.printType();
		}
	}
}
