package by.grsu.liceum.config;

import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.actuate.autoconfigure.metrics.MeterRegistryCustomizer;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("${classpath:application.properties}")
@ConditionalOnProperty(name = "spring.is-bean-enabled", matchIfMissing = false)
public class MicrometerConfiguration {
    @Bean
    MeterRegistryCustomizer<MeterRegistry> configurer(
            @Value("${spring.application.name}") String applicationName
    ) {
        return (registry) -> registry.config().commonTags("application", applicationName);
    }
}
