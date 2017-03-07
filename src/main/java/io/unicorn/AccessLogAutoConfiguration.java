package io.unicorn;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by Abderrazak BOUADMA
 * on 07/03/2017.
 */
@Configuration
@EnableConfigurationProperties(ModuleConfiguration.class)
@ConditionalOnProperty(value = "accesslog.params.enabled", havingValue = "true")
public class AccessLogAutoConfiguration {

    private final ModuleConfiguration configuration;

    @Autowired
    public AccessLogAutoConfiguration(ModuleConfiguration configuration) {
        this.configuration = configuration;
    }

    @Bean
    public FilterRegistrationBean filterRegistrationBean() {
        FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        registrationBean.setFilter(new SpyFilter());
        return registrationBean;
    }

    @Bean
    public InMemoryEndPoint inMemoryEndPoint(){
        return new InMemoryEndPoint();
    }
}
