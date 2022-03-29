package com.atomic.client.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.config.EnableWebFlux;
import org.springframework.web.reactive.config.WebFluxConfigurer;
import org.springframework.web.reactive.function.client.WebClient;

import org.springframework.beans.factory.annotation.Value;

/**
 * The Class WebClientConfig.
 *
 * @author ajuarez
 */
@Configuration
@EnableWebFlux
public class WebClientConfig implements WebFluxConfigurer {

    /** The url. */
    @Value("${academy.service.url}")
    private String url;

    /**
     * Gets the web client.
     *
     * @return the web client
     */
    @Bean
    public WebClient getWebClient() {
        return WebClient.builder()
                .baseUrl(url)
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();
    }
}
