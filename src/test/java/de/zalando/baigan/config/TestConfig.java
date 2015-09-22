package de.zalando.baigan.config;

import org.springframework.context.annotation.Import;

import de.zalando.baigan.annotation.BaiganConfig;
import de.zalando.baigan.proxy.ConfigurationBeanDefinitionRegistrar;

@BaiganConfig
@Import(ConfigurationBeanDefinitionRegistrar.class)
public interface TestConfig {
    public String enableXyzFeature();
}
