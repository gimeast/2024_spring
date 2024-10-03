package gimeast.hellospring.exrate;

import com.fasterxml.jackson.core.JsonProcessingException;
import gimeast.hellospring.api.ApiExecutor;
import gimeast.hellospring.api.ErApiExRateExtractor;
import gimeast.hellospring.api.ExRateExtractor;
import gimeast.hellospring.api.SimpleApiExecutor;
import gimeast.hellospring.payment.ExRateProvider;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URI;
import java.net.URISyntaxException;

public class WebApiExRateProvider implements ExRateProvider {

    @Override
    public BigDecimal getExchangeRate(String currency) {
        String url = "https://open.er-api.com/v6/latest/" + currency;
        return runApiForExRate(url, new SimpleApiExecutor(), new ErApiExRateExtractor());
    }

    private static BigDecimal runApiForExRate(String url, ApiExecutor apiExecutor, ExRateExtractor exRateExtractor) {
        URI uri;
        try {
            uri = new URI(url);
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }

        String response;
        try {
            response = apiExecutor.execute(uri);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        try {
            return exRateExtractor.extractExRate(response);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

}
