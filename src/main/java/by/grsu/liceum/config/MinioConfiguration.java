package by.grsu.liceum.config;

import io.minio.MinioClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:minio.properties")
@ConditionalOnProperty(name = "spring.is-bean-enabled", matchIfMissing = false)
public class MinioConfiguration {
    @Value("${spring.minio.host}")
    private String MINIO_HOST;
    @Value("${spring.minio.username}")
    private String MINIO_USERNAME;
    @Value("${spring.minio.password}")
    private String MINIO_PASSWORD;

    @Bean
    public MinioClient minioClient(){
        return MinioClient.builder()
                .endpoint(MINIO_HOST)
                .credentials(MINIO_USERNAME, MINIO_PASSWORD)
                .build();
    }
}
