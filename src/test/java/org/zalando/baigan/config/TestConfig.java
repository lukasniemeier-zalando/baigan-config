package org.zalando.baigan.config;

import org.springframework.context.annotation.Import;
import org.zalando.baigan.annotation.BaiganConfig;
import org.zalando.baigan.proxy.ConfigurationBeanDefinitionRegistrar;

@BaiganConfig
@Import(ConfigurationBeanDefinitionRegistrar.class)
public interface TestConfig {
    public String enableXyzFeature();
}
