package org.zalando.baigan.service;

import java.util.Map;

import javax.annotation.Nonnull;

import org.springframework.stereotype.Component;
import org.zalando.baigan.model.Condition;
import org.zalando.baigan.model.Configuration;

@Component
public class ConditionsProcessor {

    @Nonnull
    public static <T> T process(Configuration<T> configuration,
            Map<String, String> context) {

        T value = configuration.getDefaultValue();
        for (Condition<T> condition : configuration.getConditions()) {
            if (condition.getConditionType()
                    .eval(context.get(condition.getParamName()))) {
                value = condition.getValue();
            }
        }
        return value;
    }
}