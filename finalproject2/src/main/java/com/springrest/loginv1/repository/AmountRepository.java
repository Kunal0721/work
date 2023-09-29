package com.springrest.loginv1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.springrest.loginv1.model.InvestmentAmount;

@Repository
public interface AmountRepository  extends JpaRepository<InvestmentAmount, Integer>{
	
}
