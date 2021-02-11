package de.messetat.sling.core.components.scr.services;

import org.apache.felix.scr.annotations.Component;
import org.apache.felix.scr.annotations.Reference;
import org.apache.felix.scr.annotations.Service;
import org.apache.sling.api.resource.LoginException;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ResourceResolverFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

@Component(immediate = true, metatype = false)
@Service(ServiceResolverFactory.class)
public class ServiceResolverFactory {

    @Reference
    protected ResourceResolverFactory resourceResolverFactory;


    private static final Logger LOG = LoggerFactory.getLogger(ServiceResolverFactory.class);

    public ResourceResolver getServiceResolver() throws LoginException {
        return getServiceResolver(null);
    }

    public ResourceResolver getServiceResolver(String serviceName) throws LoginException {
        Map<String, Object> authInfo = null;
        if (serviceName != null) {
            authInfo = new HashMap<>();
            authInfo.put(ResourceResolverFactory.SUBSERVICE, serviceName);
        }

        LOG.trace("<<< getServiceResolver");
        ResourceResolver serviceResolver = null;
        serviceResolver = resourceResolverFactory.getServiceResourceResolver(authInfo);
        LOG.trace(">>> getServiceResolver '{}'", serviceResolver);
        return serviceResolver;
    }


}
