package com.in28minutes.microservices.currencyexchangeservice;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CurrencyExchangeRepository extends JpaRepository<CurrencyExchange, Long> {
	//spring will automatically write this "where and" query for us - based upon the column names that we have used here as variable names
	CurrencyExchange findByFromAndTo(String from, String to);
}
