package org.zalando.baigan.context;

import java.util.Collection;
import java.util.function.Consumer;

import javax.annotation.Nonnull;

import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.zalando.baigan.provider.ContextProvider;
import org.zalando.baigan.proxy.ConfigurationBeanDefinitionRegistrar;

import com.google.common.base.Preconditions;
import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;

/**
 * Default implementation for {@link ContextProviderRegistry} and
 * {@link ContextProviderRetriever} interfaces.
 *
 * @author mchand
 *
 */
@Component
public class ConfigurationContextProviderRegistryImpl
        implements ContextProviderRegistry, ContextProviderRetriever {

    private final Multimap<String, ContextProvider> providerMap = HashMultimap
            .create(10, 5);

    @Override
    public void register(@Nonnull final ContextProvider contextProvider) {
        Preconditions.checkNotNull(contextProvider,
                "Attempt to register null as a context provider!");
        contextProvider.getProvidedContexts().forEach(new Consumer<String>() {
            @Override
            public void accept(String context) {
                providerMap.put(context, contextProvider);
            }
        });

    }

    @Override
    @Nonnull
    public Collection<ContextProvider> getProvidersFor(
            @Nonnull final String contextName) {
        Preconditions.checkArgument(StringUtils.hasLength(contextName),
                "Attempt to access value for an empty context name!");
        return providerMap.get(contextName);
    }

}
