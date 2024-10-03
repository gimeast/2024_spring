package gimeast.hellospring.exrate;

import gimeast.hellospring.api.ApiTemplate;
import gimeast.hellospring.api.ErApiExRateExtractor;
import gimeast.hellospring.api.HttpClientApiExecutor;
import gimeast.hellospring.payment.ExRateProvider;

import java.math.BigDecimal;

public class WebApiExRateProvider implements ExRateProvider {
    ApiTemplate apiTemplate = new ApiTemplate();

    @Override
    public BigDecimal getExchangeRate(String currency) {
        String url = "https://open.er-api.com/v6/latest/" + currency;

        return apiTemplate.getExRate(
                url, new HttpClientApiExecutor(), new ErApiExRateExtractor()
        );
    }
}
