package de.zalando.baigan.model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 *
 * Represents a rule/condition that if met
 *
 * @param <T>
 */
public class Condition<T> implements Serializable {

    private static final long serialVersionUID = -7919202855689561277L;

    @JsonProperty
    private String paramName;

    @JsonProperty
    private ConditionType conditionType;

    @JsonProperty
    private T value;

    @JsonCreator
    public Condition(@JsonProperty("paramName") final String paramName,
            @JsonProperty("conditionType") ConditionType conditionType,
            @JsonProperty("value") T value) {
        this.paramName = paramName;
        this.conditionType = conditionType;
        this.value = value;
    }

    public String getParamName() {
        return paramName;
    }

    public T getValue() {
        return value;
    }

    public ConditionType getConditionType() {
        return conditionType;
    }

}