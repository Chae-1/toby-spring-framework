package tobyspring.hellospring.exrate;


import java.math.BigDecimal;
import tobyspring.hellospring.api.ApiTemplate;
import tobyspring.hellospring.payment.ExRateProvider;

public class WebApiExRateProvider implements ExRateProvider {

	private final ApiTemplate template;

	public WebApiExRateProvider(ApiTemplate template) {
		this.template = template;
	}

	@Override
	public BigDecimal getExRate(String currency) {
		String url = "https://open.er-api.com/v6/latest/" + currency;

		return template.getExRate(url);
	}
}
