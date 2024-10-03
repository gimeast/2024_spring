package gimeast.hellospring.payment;


import java.math.BigDecimal;

public interface ExRateProvider {
    BigDecimal getExchangeRate(String currency);
}
