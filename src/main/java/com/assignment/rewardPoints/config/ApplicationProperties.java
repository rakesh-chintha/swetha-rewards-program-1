package com.assignment.rewardPoints.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Properties specific to demo application.
 * Properties are configured in the {@code application.yml} file.
 */
@ConfigurationProperties
@Data
public class ApplicationProperties {
    @Value("${demo.service.contact-name}")
    private String contactName;

    @Value("${demo.service.contact-email}")
    private String contactEmail;

    @Value("${demo.service.title}")
    private String title;

    @Value("${demo.service.description}")
    private String description;

}
