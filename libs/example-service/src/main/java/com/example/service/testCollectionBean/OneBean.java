package com.example.service.testCollectionBean;

import org.springframework.stereotype.Component;

@Component
public class OneBean extends BaseBean {

	@Override
	public void printType() {
		System.out.println("OneBean");
	}

}
