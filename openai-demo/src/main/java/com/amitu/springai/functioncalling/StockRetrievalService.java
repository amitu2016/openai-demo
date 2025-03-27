package com.amitu.springai.functioncalling;


import java.util.function.Function;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import com.fasterxml.jackson.annotation.JsonProperty;

@Service
public class StockRetrievalService implements Function<StockRetrievalService.Request, StockRetrievalService.Response> {

	private final WebClient webClient;
	
	

	private final String API_KEY = "demo"; // Replace with your actual API key
	private final String BASE_URL = "https://www.alphavantage.co";

	public StockRetrievalService(WebClient.Builder webClientBuilder) {
		this.webClient = webClientBuilder.baseUrl(BASE_URL).build();
	}

	public record Request(String symbol) {
	}

	public record Response(Double price) {
	}

	private record GlobalQuoteResponse(
		@JsonProperty("Global Quote") GlobalQuote quote
	) {}

	private record GlobalQuote(
		@JsonProperty("05. price") String price
	) {}

	@Override
	public Response apply(Request request) {
		String symbol = request.symbol();

		GlobalQuoteResponse apiResponse = webClient.get()
			.uri(uriBuilder -> uriBuilder
				.path("/query")
				.queryParam("function", "GLOBAL_QUOTE")
				.queryParam("symbol", symbol)
				.queryParam("apikey", API_KEY)
				.build())
			.retrieve()
			.bodyToMono(GlobalQuoteResponse.class)
			.onErrorResume(e -> Mono.empty()) // handle error case
			.block();

		if (apiResponse == null || apiResponse.quote() == null) {
			return new Response(null); // or some default/fallback value
		}

		Double price = Double.valueOf(apiResponse.quote().price());
		return new Response(price);
	}
}
