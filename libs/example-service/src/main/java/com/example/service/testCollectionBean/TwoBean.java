package com.example.service.testCollectionBean;

import org.springframework.stereotype.Component;

@Component
public class TwoBean extends BaseBean {

	@Override
	public void printType() {
		System.out.println("TwoBean");
	}

}
