package com.springrest.loginv1.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.springrest.loginv1.exception.InvestmentNotFoundException;
import com.springrest.loginv1.model.InvestmentAmount;
import com.springrest.loginv1.repository.AmountRepository;



@RestController
public class InvestmentController {
	
	private AmountRepository repository;
	
	public InvestmentController(AmountRepository repository) {
		super();
		this.repository = repository;
	}
	
	//get all the amount
	@GetMapping("/investments")
	public List<InvestmentAmount> getalluser(){
		return repository.findAll();
	}
	
	//creating a investment amount
	@PostMapping("/investments")
	public ResponseEntity<InvestmentAmount> createUser(@RequestBody InvestmentAmount amount) {
		InvestmentAmount savedAmount = repository.save(amount);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(savedAmount.getId())
				.toUri();
		 return ResponseEntity.created(location).build();	
	}
	
	//get mapping /investments/{id}
	@GetMapping("/investments/{id}")
	public Optional<InvestmentAmount> getOne(@PathVariable int id) throws InvestmentNotFoundException {
		Optional<InvestmentAmount> amount = repository.findById(id);
	
		if(amount.isEmpty())
				throw new InvestmentNotFoundException("id : "  + id);

		return amount;
	}
	
}
