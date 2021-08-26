package com.n26.configs;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "app.transaction")
@Getter
@Setter
public class TransactionConfigProperties {
    private int thresholdSeconds;
}
