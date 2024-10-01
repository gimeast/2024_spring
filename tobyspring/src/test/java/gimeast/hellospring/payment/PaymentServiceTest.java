package gimeast.hellospring.payment;

import gimeast.hellospring.exrate.WebApiExRateProvider;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class PaymentServiceTest {

    @Test
    @DisplayName("prepare 메소드가 요구사항 3가지를 잘 충족했는지 검증")
    void prepare() throws IOException {
        //given
        PaymentService paymentService = new PaymentService(new WebApiExRateProvider());

        //when
        Payment payment = paymentService.prepare(1L, "USD", BigDecimal.TEN);

        //then
        //환율정보 가져온다.
        assertThat(payment.getExRate()).isNotNull();

        //원화 환산금액 계산
        assertThat(payment.getConvertedAmount())
                .isEqualTo(payment.getForeignCurrencyAmount().multiply(payment.getExRate()));

        //원화 환산금액 유효시간 계산
        assertThat(payment.getValidUntil()).isAfter(LocalDateTime.now());
        assertThat(payment.getValidUntil()).isBefore(LocalDateTime.now().plusMinutes(30));
    }
}