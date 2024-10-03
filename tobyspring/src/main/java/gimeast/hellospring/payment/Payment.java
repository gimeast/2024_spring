package gimeast.hellospring.payment;

import lombok.Getter;
import lombok.ToString;

import java.math.BigDecimal;
import java.time.Clock;
import java.time.LocalDateTime;

@Getter
@ToString
public class Payment {
    private Long orderId;
    private String currency; //외국 통화 종류
    private BigDecimal foreignCurrencyAmount; //외국 통화기준 결재금액
    private BigDecimal exRate; //적용환율
    private BigDecimal convertedAmount; //환산된 금액
    private LocalDateTime validUntil;

    public Payment(Long orderId, String currency, BigDecimal foreignCurrencyAmount, BigDecimal exRate, BigDecimal convertedAmount, LocalDateTime validUntil) {
        this.orderId = orderId;
        this.currency = currency;
        this.foreignCurrencyAmount = foreignCurrencyAmount;
        this.exRate = exRate;
        this.convertedAmount = convertedAmount;
        this.validUntil = validUntil;
    }

    public static Payment createPrepared(Long orderId, String currency, BigDecimal foreignCurrencyAmount, ExRateProvider exRateProvider, Clock clock) {
        BigDecimal exRate = exRateProvider.getExchangeRate(currency);
        BigDecimal convertedAmount = foreignCurrencyAmount.multiply(exRate);
        LocalDateTime validUntil = LocalDateTime.now(clock).plusMinutes(30);

        return new Payment(orderId, currency, foreignCurrencyAmount, exRate, convertedAmount, validUntil);
    }

    public boolean isValid(Clock clock) {
        return LocalDateTime.now(clock).isBefore(validUntil);
    }
}
