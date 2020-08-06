package com.spring.demo.security.controller;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;
import javax.validation.constraints.Min;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.spring.demo.security.db.entity.User;
import com.spring.demo.security.db.repo.UserRepository;
import com.spring.demo.security.exception.UserNotFoundException;
import com.spring.demo.security.exception.UserUnSupportedFieldPatchException;

@RestController
@Validated
public class UserController {
	
	
	Logger logger = LoggerFactory.getLogger(UserController.class);

	@Autowired
	private UserRepository repository;

	// Find
	@GetMapping("/users")
	List<User> findAll() {
		return repository.findAll();
	}

	// Save
	@PostMapping("/users")
	@ResponseStatus(HttpStatus.CREATED)
	User newBook(@Valid @RequestBody User user) {
		logger.debug(" New User {} " , user.toString());
		return repository.save(user);
	}

	// Find
	@GetMapping("/users/{id}")
	User findOne(@PathVariable @Min(1) Long id) {
		return repository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
	}

	// Save or update
	@PutMapping("/users/{id}")
	User saveOrUpdate(@RequestBody User user, @PathVariable Long id) {
		logger.debug(" saveOrUpdate {} " , user.toString());
		return repository.findById(id).map(x -> {
			x.setFirstName(user.getFirstName());
			x.setLastName(user.getLastName());
			x.setAge(user.getAge());
			return repository.save(x);
		}).orElseGet(() -> {
			user.setId(id);
			return repository.save(user);
		});
	}

	// update age only
	@PatchMapping("/users/{id}")
	User patch(@RequestBody Map<String, String> update, @PathVariable Long id) {
		logger.debug(" patch {} " , update);
		return repository.findById(id).map(x -> {

			String age = update.get("age");
			if (!StringUtils.isEmpty(age)) {
				x.setAge(Integer.valueOf(age));

				// better create a custom method to update a value = :newValue
				// where id = :id
				return repository.save(x);
			} else {
				throw new UserUnSupportedFieldPatchException(update.keySet());
			}

		}).orElseGet(() -> {
			throw new UserNotFoundException(id);
		});

	}

	@DeleteMapping("/users/{id}")
	void deleteUser(@PathVariable Long id) {
		logger.debug(" deleteUser {} " , id);
		repository.deleteById(id);
	}

}
