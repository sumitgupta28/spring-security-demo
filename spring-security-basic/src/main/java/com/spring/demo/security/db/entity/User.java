package com.spring.demo.security.db.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
public class User {
	@Id
	@GeneratedValue
	private Long id;

	@NotEmpty(message = "Please provide a firstName")
	private String firstName;

	@NotEmpty(message = "Please provide a lastName")
	private String lastName;

	@NotNull(message = "Please provide a age")
	private Integer age;

	// avoid this "No default constructor for entity"
	public User() {
	}

	public User(Long id, @NotEmpty(message = "Please provide a firstName") String firstName,
			@NotEmpty(message = "Please provide a lastName") String lastName,
			@NotNull(message = "Please provide a age") @Min(10) Integer age) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.age = age;
	}

	public User(@NotEmpty(message = "Please provide a firstName") String firstName,
			@NotEmpty(message = "Please provide a lastName") String lastName,
			@NotNull(message = "Please provide a age") @Min(10) Integer age) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.age = age;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	@Override
	public String toString() {
		return "User [" + (id != null ? "id=" + id + ", " : "")
				+ (firstName != null ? "firstName=" + firstName + ", " : "")
				+ (lastName != null ? "lastName=" + lastName + ", " : "") + (age != null ? "age=" + age : "") + "]";
	}
	
	

}