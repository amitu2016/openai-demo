package com.amitu.springai.functioncalling;

import java.util.function.Function;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Description;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class Functions {

	@Bean
	@Description("Gets the stock price given a symbol")
	Function<StockRetrievalService.Request, StockRetrievalService.Response> stockRetrievalFunction(WebClient.Builder webClientBuilder) {
		return new StockRetrievalService(webClientBuilder);
	}
	
	@Bean
	@Description("Gets the currency exchange rate given a symbol (e.g. INR, EUR)")
	Function<CurrencyExchangeService.Request, CurrencyExchangeService.Response> currencyExchangeFunction(WebClient.Builder webClientBuilder) {
		return new CurrencyExchangeService(webClientBuilder);
	}
	
	

}