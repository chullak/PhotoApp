package com.journaldev.spring.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "dblock")

public class DBLock {
	@Id
	@Column(name = "id", nullable = false)
	@GeneratedValue
	int id;

}
