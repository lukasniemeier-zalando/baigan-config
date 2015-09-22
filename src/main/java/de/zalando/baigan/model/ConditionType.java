package de.zalando.baigan.model;

import java.io.Serializable;

import javax.annotation.Nullable;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
@JsonSubTypes({ @Type(value = Equals.class, name = "Equals") })
public abstract class ConditionType implements Serializable {

    private static final long serialVersionUID = 8948546383560656976L;

    public abstract boolean eval(@Nullable  String paramValue);

}
