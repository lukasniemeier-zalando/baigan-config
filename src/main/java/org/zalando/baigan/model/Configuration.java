package org.zalando.baigan.model;

import java.io.Serializable;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Configuration<Type> implements Serializable {

    private static final long serialVersionUID = 9051980436564855711L;

    @JsonProperty
    private String alias;

    @JsonProperty
    private String description;

    @JsonProperty
    private Set<Condition<Type>> conditions;

    @JsonProperty
    private Type defaultValue;

    public Configuration(@JsonProperty("alias") final String alias,
            @JsonProperty("description") final String description,
            @JsonProperty("conditions") final Set<Condition<Type>> conditions,
            @JsonProperty("defaultValue") final Type defaultValue) {
        this.alias = alias;
        this.description = description;
        this.conditions = conditions;
        this.defaultValue = defaultValue;
    }

    public String getAlias() {
        return alias;
    }

    public String getDescription() {
        return description;
    }

    public Set<Condition<Type>> getConditions() {
        return conditions;
    }

    public Type getDefaultValue() {
        return defaultValue;
    }

}
