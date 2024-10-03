package gimeast.hellospring.payment;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.Clock;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;

import static org.assertj.core.api.Assertions.*;

public class PaymentTest {
    private ExRateProvider exRateProvider;
    private Clock clock;

    @BeforeEach
    void setUp() {
        this.exRateProvider = new ExRateProviderStub(BigDecimal.valueOf(1_000));
        this.clock = Clock.fixed(Instant.now(), ZoneId.systemDefault());
    }

    @Test
    void createPrepared() {

        Payment payment = Payment.createPrepared(
                1L, "USD", BigDecimal.TEN, this.exRateProvider, this.clock
        );

        assertThat(payment.getConvertedAmount()).isEqualByComparingTo(BigDecimal.valueOf(10_000));
        assertThat(payment.getValidUntil()).isEqualTo(LocalDateTime.now(clock).plusMinutes(30));
    }

    @Test
    void isValid() {

        Payment payment = Payment.createPrepared(
                1L, "USD", BigDecimal.TEN, this.exRateProvider, this.clock
        );

        assertThat(payment.isValid(clock)).isTrue();
        assertThat(payment.isValid(Clock.offset(clock, Duration.of(30, ChronoUnit.MINUTES)))).isFalse();
    }
}
