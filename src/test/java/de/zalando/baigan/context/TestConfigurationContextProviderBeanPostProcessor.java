package de.zalando.baigan.context;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import de.zalando.baigan.provider.ContextProvider;

/**
 *
 * @author mchand
 *
 */
@RunWith(MockitoJUnitRunner.class)
public class TestConfigurationContextProviderBeanPostProcessor {

    @Mock
    private ContextProviderRegistry registry;

    @InjectMocks
    private ConfigurationContextProviderBeanPostprocessor processor;

    @Mock
    private ContextProvider provider;

    @Test
    public void testMessenger() {

        processor.postProcessAfterInitialization(provider, "contextProvider");
        ArgumentCaptor<ContextProvider> contextProviderCaptor = ArgumentCaptor
                .forClass(ContextProvider.class);

        verify(registry, times(1))
        .register(org.mockito.Matchers.any(ContextProvider.class));

        verify(registry).register(contextProviderCaptor.capture());
        assertThat(contextProviderCaptor.getValue(), equalTo(provider));
    }
}
