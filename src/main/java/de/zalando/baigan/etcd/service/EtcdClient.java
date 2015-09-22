package de.zalando.baigan.etcd.service;

import java.io.IOException;
import java.net.URL;

import javax.annotation.Nonnull;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.guava.GuavaModule;
import com.google.common.base.Optional;

import de.zalando.baigan.etcd.model.KeyResultNode;

public final class EtcdClient {

    private final Logger LOG = LoggerFactory.getLogger(EtcdClient.class);

    private final ObjectMapper mapper = new ObjectMapper()
            .registerModule(new GuavaModule());

    private final String etcdUrl;

    public EtcdClient(final String url) {
        etcdUrl = url;
    }

    public final Optional<String> get(@Nonnull final String key) {
        try {
            final URL url = new URL(etcdUrl + key);

            final KeyResultNode resultNode = mapper.readValue(url,
                    KeyResultNode.class);
            final String response = resultNode.getNode().getValue();

            return Optional.fromNullable(response);
        } catch (IOException e) {
            LOG.warn("There was an exception trying to get key: " + key, e);
        } catch (NullPointerException npe) {
            LOG.warn("There was an exception trying to get key: " + key);
        }
        return Optional.absent();
    }
}
