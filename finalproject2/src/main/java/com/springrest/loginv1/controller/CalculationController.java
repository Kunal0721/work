package com.springrest.loginv1.controller;

import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.springrest.loginv1.model.Investment;
import com.springrest.loginv1.repository.CalculationRepository;


@RestController
public class CalculationController {
		
	private CalculationRepository repository;
	
	 public CalculationController(CalculationRepository repository) {
		super();
		this.repository = repository;
	}

	@GetMapping("/investments/{id}/interest")
	public ResponseEntity<Double> calculateInterest(@PathVariable long id) {
        Optional<Investment> optionalInvestment = repository.findById(id);

        if (optionalInvestment.isPresent()) {
            Investment investment = optionalInvestment.get();

            // Calculate interest using your formula
            double principal = investment.getPrincipal();
            double rate = investment.getRate();
            int time = investment.getTime();
            double interest = principal * rate * time;

            // Update the interest field in the entity
            investment.setInterest(interest);
            repository.save(investment); // Save the updated entity
            
            return ResponseEntity.ok(interest);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
