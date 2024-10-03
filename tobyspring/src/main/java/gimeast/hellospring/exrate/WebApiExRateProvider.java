package gimeast.hellospring.exrate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import gimeast.hellospring.payment.ExRateProvider;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.stream.Collectors;

//@Component
public class WebApiExRateProvider implements ExRateProvider {

    @Override
    public BigDecimal getExchangeRate(String currency) {
        String url = "https://open.er-api.com/v6/latest/" + currency;

        URI uri;
        try {
            uri = new URI(url);
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }

        String collect;
        try {
            HttpURLConnection connection = (HttpURLConnection) uri.toURL().openConnection();

            try(BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
                collect = br.lines().collect(Collectors.joining());
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            ExRateData data = objectMapper.readValue(collect, ExRateData.class);
            return data.rates().get("KRW");
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

    }
}
