package com.example.repository.model;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table
public class City {

	@Id
	private Integer cityId;

	private String city;

	private Integer countryId;

	private Date lastUpdate;
}
