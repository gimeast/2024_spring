package gimeast.hellospring;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Builder
@ToString
public class Payment {
    private Long orderId;
    private String currency; //외국 통화 종류
    private BigDecimal foreignCurrencyAmount; //외국 통화기준 결재금액
    private BigDecimal exRate; //적용환율
    private BigDecimal convertedAmount; //환산된 금액
    private LocalDateTime validUntil;
}
