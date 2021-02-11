package de.messetat.sling.core.components.util;

import com.drew.lang.annotations.NotNull;
import org.apache.sling.api.adapter.Adaptable;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ValueMap;
import org.apache.sling.models.factory.InvalidAdaptableException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.jcr.Node;

public final class AdapterHelper {

    private static final Logger LOG = LoggerFactory.getLogger(AdapterHelper.class);

    private AdapterHelper() {
    }

    public static @NotNull
    <T> T adaptTo(@NotNull Adaptable adaptable, @NotNull Class<T> type) {
        LOG.trace("<<< adaptTo '{}' '{}'", adaptable, type);
        T adapterType = adaptable.adaptTo(type);
        if (adapterType == null) {
            LOG.error("Can not adapt '{}' to '{}'", adaptable, type);
            throw new InvalidAdaptableException("Unable to adapt " + adaptable.getClass().getName() + " to " + type.getName());
        }
        LOG.trace(">>> adaptTo '{}'", adapterType);
        return adapterType;
    }


    public static @NotNull
    Node adaptToNode(@NotNull Resource resource) {
        LOG.trace("<<< adaptToNode '{}'", resource);
        Node returnValue = adaptTo(resource, Node.class);
        LOG.trace(">>> adaptToNode '{}'", returnValue);
        return returnValue;
    }

    public static @NotNull
    ValueMap adaptToValueMap(@NotNull Resource resource) {
        LOG.trace("<<< adaptToNode '{}'", resource);
        ValueMap returnValue = adaptTo(resource, ValueMap.class);
        LOG.trace(">>> adaptToNode '{}'", returnValue);
        return returnValue;
    }


}
