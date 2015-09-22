package de.zalando.baigan.context;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

import de.zalando.baigan.provider.ContextProvider;

/**
 * This class implements the {@link BeanPostProcessor} interface to register all
 * the {@link ContextProvider}s in the default {@link ContextProviderRegistry}
 *
 * @author mchand
 * @since 1.0
 */
@Component
public class ConfigurationContextProviderBeanPostprocessor
        implements BeanPostProcessor {

    @Autowired
    private ContextProviderRegistry registry;

    @Override
    public Object postProcessAfterInitialization(final Object bean,
            final String beanName) throws BeansException {
        if (ContextProvider.class.isInstance(bean)) {
            final ContextProvider contextProvider = (ContextProvider) bean;
            registry.register(contextProvider);
        }
        return bean;
    }

    @Override
    public Object postProcessBeforeInitialization(final Object bean,
            final String beanName) throws BeansException {
        return bean;
    }
}
