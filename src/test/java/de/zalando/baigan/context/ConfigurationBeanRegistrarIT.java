package de.zalando.baigan.context;

import static org.junit.Assert.assertThat;

import java.util.Set;

import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.google.common.base.Optional;
import com.google.common.collect.ImmutableSet;

import de.zalando.baigan.annotation.ConfigurationServiceScan;
import de.zalando.baigan.config.TestConfig;
import de.zalando.baigan.model.Condition;
import de.zalando.baigan.service.ConditionsProcessor;
import de.zalando.baigan.service.ConfigService;

/**
 *
 * @author mchand
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {
        ConfigurationContextProviderRegistryImpl.class,
        ConfigurationContextProviderBeanPostprocessor.class,
        ConfigurationBeanRegistrarIT.class })
@ConfigurationServiceScan({ "de.zalando.baigan.config" })
@ComponentScan(basePackageClasses = {
        de.zalando.baigan.proxy.handler.ConfigurationMethodInvocationHandler.class,
        TestBeans.class })
public class ConfigurationBeanRegistrarIT {

    @Autowired
    private TestConfig config;

    @Test
    public void testConfiguration() {
        assertThat(config.enableXyzFeature(), Matchers.equalTo("false"));
    }

}

@Configuration
class TestBeans {

    @Bean
    public ConditionsProcessor ConditionsProcessor() {
        return new ConditionsProcessor();
    }

    @Bean
    public ConfigService configService() {
        return new ConfigService() {
            public final static String KEY = "test.config.enable.xyz.feature";

            @Override
            public void put(String key, String value) {
            }

            @Override
            public Optional<de.zalando.baigan.model.Configuration> getConfig(
                    String key) {
                if (KEY.equalsIgnoreCase(key)) {
                    return Optional.of(mockConfiguration(key));
                } else {
                    return Optional.absent();
                }

            }

            private de.zalando.baigan.model.Configuration<Boolean> mockConfiguration(
                    final String key) {
                // TODO: Define conditions/use context params here
                // final Set<Condition<Boolean>> conditions = ImmutableSet
                // .of(new Condition<Boolean>("appdomainid",
                // new Equals("1"), Boolean.TRUE));

                final Set<Condition<Boolean>> conditions = ImmutableSet.of();
                final de.zalando.baigan.model.Configuration<Boolean> configuration = new de.zalando.baigan.model.Configuration<Boolean>(
                        key, "This is a test configuration object.", conditions,
                        Boolean.FALSE);

                return configuration;
            }
        };
    }

}
