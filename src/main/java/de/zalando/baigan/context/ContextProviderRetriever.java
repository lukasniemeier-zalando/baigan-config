package de.zalando.baigan.context;

import java.util.Collection;

import javax.annotation.Nonnull;

import de.zalando.baigan.provider.ContextProvider;

/**
 * This interface offers method to retrieve registered {@link ContextProvider} for a
 * {@link ConfigurationContext}.
 *
 * @author mchand
 *
 */
public interface ContextProviderRetriever {

    @Nonnull
    Collection<ContextProvider> getProvidersFor(@Nonnull final String contextName );
}
