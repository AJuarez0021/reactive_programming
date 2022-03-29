package com.atomic.config;

import org.springframework.boot.autoconfigure.web.WebProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * The Class ResourceWebPropertiesConfig.
 *
 * @author ajuarez
 */
@Configuration
public class ResourceWebPropertiesConfig {
    
    /**
     * Resources.
     *
     * @return the web properties. resources
     */
    @Bean
    public WebProperties.Resources resources() {
        return new WebProperties.Resources();
    }
}
