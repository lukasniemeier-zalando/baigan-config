package de.zalando.baigan.proxy.handler;

import java.lang.reflect.Method;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.base.Optional;
import com.google.common.collect.ImmutableMap;

import de.zalando.baigan.model.Configuration;
import de.zalando.baigan.proxy.ProxyUtils;
import de.zalando.baigan.service.ConditionsProcessor;
import de.zalando.baigan.service.ConfigService;

@Service
public class ConfigurationMethodInvocationHandler
extends AbstractConfigurationMethodInvocationHandler {

    private final Logger LOG = LoggerFactory
            .getLogger(ConfigurationMethodInvocationHandler.class);

    @Autowired
    private ConfigService configService;

    @Autowired
    private ConditionsProcessor conditionsProcessor;

    @Override
    protected Object handleInvocation(Object proxy, Method method,
            Object[] args) throws Throwable {
        final String methodName = method.getName();

        final String nameSpace = method.getDeclaringClass().getSimpleName();
        LOG.info("called " + methodName);

        final String key = ProxyUtils.dottify(nameSpace) + "."
                + ProxyUtils.dottify(methodName);
        return getConfig(key);
    }

    private Object getConfig(final String key) {

        Optional<Configuration> optional = configService.getConfig(key);
        if (!optional.isPresent()) {
            return null;
        }
        final Configuration configuration = optional.get();

        // TODO: get this from the context param providers
        final Map<String, String> context = ImmutableMap.of("appdomain", "1",
                "ip", "169.10.25.1");
        // final Map<String, String> context = multiToSingleValueMap(
        // ui.getQueryParameters());

        return String
                .valueOf(conditionsProcessor.process(configuration, context));

    }
}