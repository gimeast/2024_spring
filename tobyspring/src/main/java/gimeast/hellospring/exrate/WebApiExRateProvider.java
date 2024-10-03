package gimeast.hellospring.exrate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
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
        return runApiForExRate(url);
    }

    private static BigDecimal runApiForExRate(String url) {
        URI uri;
        try {
            uri = new URI(url);
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }

        String response;
        try {
            response = new SimpleApiExecutor().execute(uri);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        try {
            return extractExRate(response);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    private static BigDecimal extractExRate(String response) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        ExRateData data = objectMapper.readValue(response, ExRateData.class);
        return data.rates().get("KRW");
    }

}
