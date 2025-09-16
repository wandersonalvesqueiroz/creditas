package com.creditas.credit_simulator.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:config.properties")
@Getter
@Setter
public class ConfigProperties {

    @Value("${annual.rate.up.to.25}")
    private double annualRateUpTo25;

    @Value("${annual.rate.from.26.to.40}")
    private double annualRateFrom26To40;

    @Value("${annual.rate.from.41.to.60}")
    private double annualRateFrom41To60;

    @Value("${annual.rate.above.60}")
    private double annualRateAbove60;

    @Value("${spring.mail.from}")
    private String fromEmail;
}
