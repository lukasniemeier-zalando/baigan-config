package de.zalando.baigan.provider;

import java.util.Set;

import javax.annotation.Nonnull;

/**
 * Provides the value of the named context parameter at runtime.
 * 
 * @author mchand
 *
 */
public interface ContextProvider {

    String getContextParam(@Nonnull final String name);

    Set<String> getProvidedContexts();
}
