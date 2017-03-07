package io.unicorn;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Created by Abderrazak BOUADMA
 * on 07/03/2017.
 */
@ConfigurationProperties(prefix = "accesslog.params")
public class ModuleConfiguration {

    private boolean enabled = false;

}
