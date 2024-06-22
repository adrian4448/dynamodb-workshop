package com.adrianm.dynamodb_crud.config;

import io.awspring.cloud.autoconfigure.core.CredentialsProperties;
import io.awspring.cloud.autoconfigure.core.CredentialsProviderAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.AwsCredentialsProvider;
import software.amazon.awssdk.auth.credentials.DefaultCredentialsProvider;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;

import java.net.URI;

@Configuration
public class DynamoDBClientConfig {

    @Bean
    public DynamoDbClient dynamoDbClient() {
        AwsCredentialsProvider credentialsProvider = StaticCredentialsProvider.create(AwsBasicCredentials.create("ffqk4", "l4an8"));

        return DynamoDbClient
                .builder()
                .credentialsProvider(credentialsProvider)
                .endpointOverride(URI.create("http://localhost:8000"))
                .region(Region.US_EAST_1)
                .build();
    }
}
