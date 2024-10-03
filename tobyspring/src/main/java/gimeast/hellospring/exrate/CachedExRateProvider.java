package gimeast.hellospring.exrate;

import gimeast.hellospring.payment.ExRateProvider;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class CachedExRateProvider implements ExRateProvider {

    private final ExRateProvider target;

    private BigDecimal cachedExRate;
    private LocalDateTime cacheExpireTime;

    public CachedExRateProvider(ExRateProvider target) {
        this.target = target;
    }


    @Override
    public BigDecimal getExchangeRate(String currency) {
        if (cachedExRate == null || cacheExpireTime.isBefore(LocalDateTime.now())) {
            cachedExRate = this.target.getExchangeRate(currency);
            cacheExpireTime = LocalDateTime.now().plusSeconds(3);

            System.out.println("Cache Updated");
        }

        return cachedExRate;
    }
}
