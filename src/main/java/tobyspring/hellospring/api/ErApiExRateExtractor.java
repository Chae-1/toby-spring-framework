package tobyspring.hellospring.api;

import java.math.BigDecimal;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import tobyspring.hellospring.exrate.ExRateData;

public class ErApiExRateExtractor implements ExRateExtractor {
	@Override
	public BigDecimal extractExRate(String response) throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		ExRateData data = mapper.readValue(response, ExRateData.class);
		return data.rates().get("KRW");
	}
}
