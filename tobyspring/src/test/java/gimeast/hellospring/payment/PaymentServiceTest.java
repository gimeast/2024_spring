package gimeast.hellospring.payment;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import static java.math.BigDecimal.*;
import static org.assertj.core.api.Assertions.*;

class PaymentServiceTest {

    @Test
    void convertedAmount() throws IOException {
        testAmount(valueOf(500), valueOf(5000));
        testAmount(valueOf(1000), valueOf(10_000));
        testAmount(valueOf(3000), valueOf(30_000));

        //원화 환산금액 유효시간 계산
//        assertThat(payment.getValidUntil()).isAfter(LocalDateTime.now());
//        assertThat(payment.getValidUntil()).isBefore(LocalDateTime.now().plusMinutes(30));
    }

    private static void testAmount(BigDecimal exRate, BigDecimal convertedAmount) throws IOException {
        //given
        PaymentService paymentService = new PaymentService(new ExRateProviderStub(exRate));

        //when
        Payment payment = paymentService.prepare(1L, "USD", TEN);

        //then
        //환율정보 가져온다.
        assertThat(payment.getExRate()).isEqualByComparingTo(exRate);

        //원화 환산금액 계산
        assertThat(payment.getConvertedAmount()).isEqualByComparingTo(convertedAmount);
    }


}