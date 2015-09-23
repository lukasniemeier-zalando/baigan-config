package org.zalando.baigan.context;

import javax.annotation.Nonnull;

import org.zalando.baigan.provider.ContextProvider;

/**
 *
 * Interface used to register the {@link ContextProvider}.
 *
 * @author mchand
 */
public interface ContextProviderRegistry {
    void register(@Nonnull final ContextProvider contextProvider);
}
