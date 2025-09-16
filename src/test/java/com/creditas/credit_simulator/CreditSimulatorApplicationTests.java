package com.creditas.credit_simulator;

import com.creditas.model.SimulateLoanRequest;
import com.creditas.model.SimulateLoanResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
class CreditSimulatorApplicationTests {

    private static final String URL = "http://localhost:";
    private static final String ENDPOINT = "/simulate-loan";
    private static final double LOAN_AMOUNT = 100.00;
    private static final Date BIRTH_DATE = Date.from(Instant.parse("1990-01-01T00:00:00Z"));
    private static final int PAYMENT_TERM_MONTHS = 10;
    private static final BigDecimal INSTALLMENT_NO_RATE = BigDecimal.valueOf(10);
    private static final BigDecimal LOAN_AMOUNT_NO_RATE = BigDecimal.valueOf(LOAN_AMOUNT);
    private static final BigDecimal ZERO = BigDecimal.ZERO;

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    @DisplayName("Teste integrado retornando OK")
    public void simulateLoan_shouldReturnCorrectValues() {
        SimulateLoanRequest request = new SimulateLoanRequest();
        request.setLoanAmount(LOAN_AMOUNT);
        request.setBirthDate(BIRTH_DATE);
        request.setPaymentTermMonths(PAYMENT_TERM_MONTHS);

        HttpEntity<SimulateLoanRequest> entity = new HttpEntity<>(request);

        ResponseEntity<SimulateLoanResponse> response = restTemplate
                .exchange(URL + port + ENDPOINT,
                        HttpMethod.POST, entity, SimulateLoanResponse.class);

        assertThat(response.getStatusCode().is2xxSuccessful()).isTrue();

        SimulateLoanResponse body = response.getBody();
        assertThat(body).isNotNull();
        assertThat(body.getMonthlyInstallment()).isGreaterThan(INSTALLMENT_NO_RATE);
        assertThat(body.getTotalAmount()).isGreaterThan(LOAN_AMOUNT_NO_RATE);
        assertThat(body.getTotalRate()).isGreaterThan(ZERO);
    }
}
