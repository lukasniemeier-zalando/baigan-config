package de.zalando.baigan.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.context.annotation.Import;

import de.zalando.baigan.proxy.ConfigurationBeanDefinitionRegistrar;

/**
 * Use this annotation to enable the Baigan configuration.
 *
 * @author mchand
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
@Import(ConfigurationBeanDefinitionRegistrar.class)
@Inherited
public @interface ConfigurationServiceScan {

    /**
     * Alias for the {@link #basePackages()} attribute. Allows for more concise
     * annotation declarations e.g.:
     * {@code @EnableConfigService("de.zalando.shop")} instead of
     * {@code @EnableConfigService(basePackages= "de.zalando.shop"})}.
     */
    String[] value() default {};

    /**
     * Base packages to scan for AppConfigService interfaces.
     */
    String[] basePackages() default {};
}