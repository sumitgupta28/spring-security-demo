package com.spring.demo.security.controller;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;
import javax.validation.constraints.Min;

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
import com.spring.demo.security.exception.BookNotFoundException;
import com.spring.demo.security.exception.BookUnSupportedFieldPatchException;

@RestController
@Validated
public class BookController {

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
	User newBook(@Valid @RequestBody User newBook) {
		return repository.save(newBook);
	}

	// Find
	@GetMapping("/users/{id}")
	User findOne(@PathVariable @Min(1) Long id) {
		return repository.findById(id).orElseThrow(() -> new BookNotFoundException(id));
	}

	// Save or update
	@PutMapping("/users/{id}")
	User saveOrUpdate(@RequestBody User newBook, @PathVariable Long id) {

		return repository.findById(id).map(x -> {
			x.setFirstName(newBook.getFirstName());
			x.setLastName(newBook.getLastName());
			x.setAge(newBook.getAge());
			return repository.save(x);
		}).orElseGet(() -> {
			newBook.setId(id);
			return repository.save(newBook);
		});
	}

	// update age only
	@PatchMapping("/users/{id}")
	User patch(@RequestBody Map<String, String> update, @PathVariable Long id) {

		return repository.findById(id).map(x -> {

			String age = update.get("age");
			if (!StringUtils.isEmpty(age)) {
				x.setAge(Integer.valueOf(age));

				// better create a custom method to update a value = :newValue
				// where id = :id
				return repository.save(x);
			} else {
				throw new BookUnSupportedFieldPatchException(update.keySet());
			}

		}).orElseGet(() -> {
			throw new BookNotFoundException(id);
		});

	}

	@DeleteMapping("/users/{id}")
	void deleteBook(@PathVariable Long id) {
		repository.deleteById(id);
	}

}
