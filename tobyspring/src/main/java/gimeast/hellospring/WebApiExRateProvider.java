package gimeast.hellospring;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.stream.Collectors;

//@Component
public class WebApiExRateProvider implements ExRateProvider{

    @Override
    public BigDecimal getExchangeRate(String currency) throws IOException {
        URL url = new URL("https://open.er-api.com/v6/latest/" + currency);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        InputStreamReader inputStreamReader = new InputStreamReader(connection.getInputStream());
        BufferedReader br = new BufferedReader(inputStreamReader);
        String collect = br.lines().collect(Collectors.joining());
        br.close();

        ObjectMapper objectMapper = new ObjectMapper();
        ExRateData data = objectMapper.readValue(collect, ExRateData.class);

        System.out.println("API exchangeRate: " + data.rates().get("KRW"));

        return data.rates().get("KRW");
    }
}
