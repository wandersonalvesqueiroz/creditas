package com.creditas.credit_simulator.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:messages.properties")
@Getter
@Setter
public class MessagesProperties {

    @Value("${message.log.age.factory}")
    private String messageLogAgeFactory;

    @Value("${message.log.save.simulation.repository}")
    private String messageLogSaveSimulationRepository;

    @Value("${message.log.values.calculated}")
    private String messageLogValuesCalculated;

    @Value("${message.log.publish.simulation}")
    private String messageLogPublishSimulation;

    @Value("${message.log.error.publish.simulation}")
    private String messageLogErrorPublishSimulation;

    @Value("${message.error.publish}")
    private String messageErrorPublish;

    @Value("${message.error.age.strategy}")
    private String messageErrorAgeStrategy;

    @Value("${message.log.process}")
    private String messageLogProcess;

    @Value("${message.log.consume}")
    private String messageLogConsume;

    @Value("${message.log.error.consume}")
    private String messageLogErrorConsume;

    @Value("${message.error.consume}")
    private String messageErrorConsume;

    @Value("${message.subject.mail}")
    private String messageSubjectMail;

    @Value("${message.body.mail}")
    private String messageBodyMail;

    @Value("${message.log.success.mail}")
    private String messageLogSuccessMail;

    @Value("${message.log.error.mail}")
    private String messageLogErrorMail;

    @Value("${message.error.mail}")
    private String messageErrorMail;

    @Value("${message.log.error.pesistence}")
    private String messageLogErrorPersistence;

    @Value("${message.error.pesistence}")
    private String messageErrorPersistence;
}
