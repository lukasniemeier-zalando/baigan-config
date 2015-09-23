package org.zalando.baigan.service;

import javax.annotation.Nonnull;

import org.zalando.baigan.model.Configuration;

import com.google.common.base.Optional;

public interface ConfigService {

    @Nonnull
    Optional<Configuration> getConfig(@Nonnull final String key) ;

    void put(@Nonnull final String key, @Nonnull final String value);

}
