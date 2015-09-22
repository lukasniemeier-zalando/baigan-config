package de.zalando.baigan.proxy;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.AbstractFactoryBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import com.google.common.base.Preconditions;
import com.google.common.reflect.Reflection;

import de.zalando.baigan.annotation.BaiganConfig;
import de.zalando.baigan.proxy.handler.AbstractConfigurationMethodInvocationHandler;
import de.zalando.baigan.service.ConfigService;

/**
 * Factory class that creates the proxy implementations for the interfaces
 * marked with {@link BaiganConfig}.
 *
 * @author mchand
 *
 */
public class ConfigurationServiceBeanFactory extends AbstractFactoryBean<Object>
        implements ApplicationContextAware {

    private Class<?> candidateInterface;

    private ConfigService configService;

    private ApplicationContext applicationContext;

    public void setCandidateInterface(final Class<?> candidateInterface) {

        this.candidateInterface = candidateInterface;
    }

    /**
     * Returns a proxy that implements the given interface.
     *
     * @param appConfigServiceInterface
     *            Must have AppConfigService annotation
     *
     * @return A proxy that implements the given interface.
     *
     * @throws AppConfigServiceException
     *             if something was wrong with the interface
     */

    protected Object createInstance() {

        final BaiganConfig beanConfig = candidateInterface
                .getAnnotation(BaiganConfig.class);
        Preconditions.checkNotNull(beanConfig,
                "This BeanFactory could only create Beans for classes annotated with "
                        + BaiganConfig.class.getName());

        final AbstractConfigurationMethodInvocationHandler invocationHandler = applicationContext
                .getBean(AbstractConfigurationMethodInvocationHandler.class);
        return Reflection.newProxy(candidateInterface, invocationHandler);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext)
            throws BeansException {
        this.applicationContext = applicationContext;

    }

    @Override
    public Class<?> getObjectType() {
        return this.candidateInterface;
    }

    public void setConfigService(final ConfigService configService) {
        this.configService = configService;
    }

    public ConfigService getService() {
        return this.configService;
    }
}
