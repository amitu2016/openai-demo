package com.amitu.springai.functioncalling;

import java.util.Map;
import java.util.function.Function;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import reactor.core.publisher.Mono;

@Service
public class CurrencyExchangeService implements Function<CurrencyExchangeService.Request, CurrencyExchangeService.Response> {

	private final WebClient webClient;

	private final String API_KEY = "demo";
	private final String BASE_URL = "https://api.currencyfreaks.com";

	public CurrencyExchangeService(WebClient.Builder webClientBuilder) {
		this.webClient = webClientBuilder.baseUrl(BASE_URL).build();
	}

	public record Request(String symbol) {} // e.g., "INR", "EUR", etc.

	public record Response(Double rate) {}

	private record CurrencyApiResponse(
		String date,
		String base,
		Map<String, String> rates
	) {}

	@Override
	public Response apply(Request request) {
		String symbol = request.symbol();

		CurrencyApiResponse apiResponse = webClient.get()
			.uri(uriBuilder -> uriBuilder
				.path("/v2.0/rates/latest")
				.queryParam("apikey", API_KEY)
				.queryParam("symbols", symbol)
				.build())
			.retrieve()
			.bodyToMono(CurrencyApiResponse.class)
			.onErrorResume(e -> Mono.empty())
			.block();

		if (apiResponse == null || apiResponse.rates() == null) {
			return new Response(null);
		}

		String rateStr = apiResponse.rates().get(symbol.toUpperCase());
		Double rate = rateStr != null ? Double.valueOf(rateStr) : null;

		return new Response(rate);
	}
}
