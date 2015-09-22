package de.zalando.baigan.context;

import static org.junit.Assert.assertEquals;

import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import com.google.common.collect.ImmutableSet;

import de.zalando.baigan.provider.ContextProvider;

/**
 *
 * @author mchand
 *
 */
@RunWith(MockitoJUnitRunner.class)
public class TestConfigurationContextProviderRegistryImpl {

    final String TEST_CONTEXT_PARAM_VALUE = "99";

    final String CONTEXT_PARAM = ConfigurationContext.APPDOMAIN.name();

    @Test
    public void testRegister() {
        final ConfigurationContextProviderRegistryImpl registry = new ConfigurationContextProviderRegistryImpl();

        ContextProvider providerMock = Mockito.mock(ContextProvider.class);
        Mockito.when(providerMock.getProvidedContexts()).thenReturn(
                ImmutableSet.of(ConfigurationContext.APPDOMAIN.name()));
        Mockito.when(providerMock.getContextParam(CONTEXT_PARAM))
        .thenReturn(TEST_CONTEXT_PARAM_VALUE);

        registry.register(providerMock);

        final Collection<ContextProvider> providers = registry
                .getProvidersFor(ConfigurationContext.APPDOMAIN.name());
        assertEquals(1, providers.size());
        final ContextProvider provider = providers.iterator().next();

        final String contextParamValue = provider
                .getContextParam(ConfigurationContext.APPDOMAIN.name());
        assertEquals(TEST_CONTEXT_PARAM_VALUE, contextParamValue);

    }
}
