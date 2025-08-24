package tobyspring.hellospring.payment;


import static java.math.BigDecimal.*;
import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.Clock;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PaymentServiceTest {
    Clock clock;

    @BeforeEach
    void beforeEach() {
        clock = Clock.fixed(Instant.now(), ZoneId.systemDefault());
    }

    @Test
    @DisplayName("prepare 메서드가 요구사항 3가지를 잘 충족했는지 검증")
    void convertedAmount() {

        testAmount(valueOf(500), valueOf(5_000), clock);
        testAmount(valueOf(1_000), valueOf(10_000), clock);
        testAmount(valueOf(3_000), valueOf(30_000), clock);
    }

    @Test
    void validUntil() {
        ExRateProvider exRateProvider = new ExRateProviderStub(valueOf(1_000));
        PaymentService paymentService = new PaymentService(exRateProvider, clock);

        Payment payment = paymentService.prepare(1L, "USD", TEN);

        // valid until이 prepare() 30분 뒤로 설정됐는가?
        LocalDateTime now = LocalDateTime.now(clock);
        LocalDateTime expectedValidUntil = now.plusMinutes(30);

        Assertions.assertThat(payment.getValidUntil()).isEqualTo(expectedValidUntil);
    }

    private void testAmount(BigDecimal exRate, BigDecimal convertedAmount, Clock fixedClock) {
        ExRateProvider exRateProvider = new ExRateProviderStub(exRate);
        PaymentService paymentService = new PaymentService(exRateProvider, fixedClock);

        Payment payment = paymentService.prepare(1L, "USD", TEN);
        assertThat(payment.getExRate()).isEqualByComparingTo(exRate);
        assertThat(payment.getConvertedAmount())
                .isEqualByComparingTo(convertedAmount);
    }
}