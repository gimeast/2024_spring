package gimeast.hellospring.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import gimeast.hellospring.exrate.ExRateData;

import java.math.BigDecimal;

public class ErApiExRateExtractor implements ExRateExtractor{
    @Override
    public BigDecimal extractExRate(String response) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        ExRateData data = objectMapper.readValue(response, ExRateData.class);
        return data.rates().get("KRW");
    }
}
