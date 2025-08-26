package tobyspring.hellospring.api;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URI;
import java.net.URISyntaxException;

import com.fasterxml.jackson.core.JsonProcessingException;

public class ApiTemplate {

	private final ApiExecutor apiExecutor;
	private final ExRateExtractor exRateExtractor;

	public ApiTemplate() {
		this.apiExecutor = new HttpClientApiExecutor();
		this.exRateExtractor = new ErApiExRateExtractor();
	}

	public ApiTemplate(ApiExecutor apiExecutor, ExRateExtractor exRateExtractor) {
		this.apiExecutor = apiExecutor;
		this.exRateExtractor = exRateExtractor;
	}

	public BigDecimal getExRate(String url) {
		return this.getExRate(url, this.apiExecutor, this.exRateExtractor);
	}

	public BigDecimal getExRate(String url, ApiExecutor apiExecutor) {
		return this.getExRate(url, apiExecutor, this.exRateExtractor);
	}

	public BigDecimal getExRate(String url,ExRateExtractor exRateExtractor) {
		return this.getExRate(url, apiExecutor, exRateExtractor);
	}


	public BigDecimal getExRate(String url, ApiExecutor apiExecutor, ExRateExtractor exRateExtractor) {

		// 1. url을 기반으로 URI 객체를 생성한다.
		URI uri = null;
		try {
			uri = new URI(url);
		} catch (URISyntaxException e) {
			throw new RuntimeException(e);
		}


		//  2. API를 호출해서 환율 정보를 획득한다.
		// -> API를 호출하는 방법이 다르기 때문에 인터페이스로 추출
		String response;
		try {
			response = apiExecutor.execute(uri);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}

		// 3. 응답 결과를 JSON으로 파싱, 원화 정보를 추출.
		// -> JSON을 기반으로 원화 정보를 추출, API 호출 방법마다 원화 정보를 추출하는 방법이 다르다.
		// -> 인터페이스 추출
		try {
			return exRateExtractor.extractExRate(response);
		} catch (JsonProcessingException e) {
			throw new RuntimeException(e);
		}
	}
}
