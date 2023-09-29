package com.springrest.loginv1.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.springrest.loginv1.dto.DepositRequest;
import com.springrest.loginv1.dto.WithdrawalRequest;
import com.springrest.loginv1.model.Investment;
import com.springrest.loginv1.model.User;
import com.springrest.loginv1.repository.UserRepository;


@RestController
public class UserController {
	
    @Autowired
    private UserRepository userRepository;
    
    @GetMapping("/users/{userId}")
    public ResponseEntity<User> editUser(@PathVariable long userId) {
    	Optional<User> users = userRepository.findById(userId);
    	
    	if(users.isPresent())
    		return new ResponseEntity<>(users.get(), HttpStatus.OK);
    	else
    		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    
    // GET endpoint to retrieve user's investments
    @GetMapping("/users/{userId}/investments")
    public ResponseEntity<List<Investment>> getUserInvestments(@PathVariable Long userId) {
        Optional<User> userOptional = userRepository.findById(userId);

        if (userOptional.isPresent()) {
            User user = userOptional.get();
            List<Investment> investments = user.getInvestments(); // Assuming you have a getter for investments
            return ResponseEntity.ok(investments);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // POST endpoint to deposit funds into the user's account
    @PostMapping("/users/{userId}/deposit")
    public ResponseEntity<User> deposit(@PathVariable Long userId, @RequestBody DepositRequest depositRequest) {
        Optional<User> userOptional = userRepository.findById(userId);

        if (userOptional.isPresent()) {
            User user = userOptional.get();
            double depositAmount = depositRequest.getAmount();

            // Perform deposit operation by updating the account balance
            double currentBalance = user.getBalance();
            double newBalance = currentBalance + depositAmount;
            user.setBalance(newBalance);

            // Save the updated user entity
            userRepository.save(user);

            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // POST endpoint to withdraw funds from the user's account
    @PostMapping("/users/{userId}/withdraw")
    public ResponseEntity<User> withdraw(@PathVariable Long userId, @RequestBody WithdrawalRequest withdrawalRequest) {
        Optional<User> userOptional = userRepository.findById(userId);

        if (userOptional.isPresent()) {
            User user = userOptional.get();
            double withdrawalAmount = withdrawalRequest.getAmount();

            // Check if the user has sufficient funds for the withdrawal
            double currentBalance = user.getBalance();
            if (currentBalance >= withdrawalAmount) {
                double newBalance = currentBalance - withdrawalAmount;
                user.setBalance(newBalance);

                // Save the updated user entity
                userRepository.save(user);

                return ResponseEntity.ok(user);
            } else {
                return ResponseEntity.badRequest().body(user);
            }
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
