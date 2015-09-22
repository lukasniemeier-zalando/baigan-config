package de.zalando.baigan.service;

import javax.annotation.Nonnull;

import com.google.common.base.Optional;

import de.zalando.baigan.model.Configuration;

public interface ConfigService {

    @Nonnull
    Optional<Configuration> getConfig(@Nonnull final String key) ;

    void put(@Nonnull final String key, @Nonnull final String value);

}
