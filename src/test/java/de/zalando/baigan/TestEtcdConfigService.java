package de.zalando.baigan;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.io.StringWriter;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.guava.GuavaModule;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;

import de.zalando.baigan.model.Condition;
import de.zalando.baigan.model.Configuration;
import de.zalando.baigan.model.Equals;
import de.zalando.baigan.service.ConditionsProcessor;

@RunWith(JUnit4.class)
public class TestEtcdConfigService {

    private final static ObjectMapper mapper = new ObjectMapper()
            .registerModule(new GuavaModule());

    private Configuration<Boolean> configuration;

    private String buffer = null;

    @Before
    public void init()
            throws JsonMappingException, JsonGenerationException, IOException {

        final Condition<Boolean> condition = new Condition<Boolean>("appdomain",
                new Equals("1"), true);

        final Set<Condition<Boolean>> conditions = ImmutableSet.of(condition);
        configuration = new Configuration("express.feature.toggle",
                "Feature toggle", conditions, Boolean.FALSE);

        StringWriter writer = new StringWriter();
        mapper.writeValue(writer, configuration);
        buffer = writer.toString();
    }

    private void testConfiguration(final Configuration<Boolean> configuration) {
        assertTrue(ConditionsProcessor.process(configuration,
                ImmutableMap.of("appdomain", "1")));

        assertFalse(ConditionsProcessor.process(configuration,
                ImmutableMap.of("appdomain", "2")));
    }

    @Test
    public void testBooleanConfiguration() throws Exception {
        testConfiguration(configuration);
    }

    @Test
    public void testDeserialize() throws Exception {
        final Configuration deserializedConfiguration = mapper.readValue(buffer,
                Configuration.class);
        testConfiguration(deserializedConfiguration);
    }

}
