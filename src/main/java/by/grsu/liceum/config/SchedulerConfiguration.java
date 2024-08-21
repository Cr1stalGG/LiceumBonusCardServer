package by.grsu.liceum.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.scheduling.annotation.EnableScheduling;

@Configuration
@EnableScheduling
@PropertySource(("classpath:application.properties"))
@ConditionalOnProperty(name = "spring.scheduler.enabled", matchIfMissing = true)
public class SchedulerConfiguration {
}
