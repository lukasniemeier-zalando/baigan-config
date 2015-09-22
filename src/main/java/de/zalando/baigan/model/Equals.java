package de.zalando.baigan.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Implementation of ConditionType that evaluates to true if the context param matches the configured value..
 * @author mchand
 *
 */
public class Equals extends ConditionType {

    private static final long serialVersionUID = 5346539855029708345L;

    @JsonProperty("onValue")
    private final String onValue;

    @JsonCreator
    public Equals(@JsonProperty("onValue") final String onValue) {
        this.onValue = onValue;
    }

    @Override
    public boolean eval(final String forValue) {
        return onValue.equalsIgnoreCase(forValue);
    }

}
