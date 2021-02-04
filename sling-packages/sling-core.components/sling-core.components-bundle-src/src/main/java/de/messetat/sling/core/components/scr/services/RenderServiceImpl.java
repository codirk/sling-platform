package de.messetat.sling.core.components.scr.services;

import org.apache.felix.scr.annotations.Service;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.request.RequestDispatcherOptions;
import org.apache.sling.api.request.RequestPathInfo;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceWrapper;
import org.apache.sling.api.resource.ValueMap;
import org.apache.sling.api.wrappers.SlingHttpServletRequestWrapper;
import org.apache.sling.api.wrappers.ValueMapDecorator;
import org.apache.sling.engine.SlingRequestProcessor;
import org.apache.sling.scripting.core.servlet.CaptureResponseWrapper;
import org.apache.felix.scr.annotations.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import org.apache.felix.scr.annotations.Reference;

@Component(immediate = true, metatype = false)
@Service(value = {RenderServiceImpl.class})
public class RenderServiceImpl {

    private static final Logger LOG = LoggerFactory.getLogger(RenderServiceImpl.class);

    @Reference
    private transient SlingRequestProcessor requestProcessor;

    public String renderAttribute(SlingHttpServletRequest slingHttpServletRequest, SlingHttpServletResponse slingHttpServletResponse, Resource resource, String attributeName) {
        Resource resourceWrapper = getResourceWrapper(resource, attributeName);
        return renderResource(slingHttpServletRequest, slingHttpServletResponse, resourceWrapper, "dam/components/util/attributeRenderer", "");
    }

    public String renderResource(SlingHttpServletRequest slingHttpServletRequest, SlingHttpServletResponse slingHttpServletResponse, Resource resource, String resourceType, String selectors) {
        String renderedContent = "";
        RequestDispatcherOptions options = new RequestDispatcherOptions();
        if (resourceType == null) {
            options.setForceResourceType(resource.getResourceType());
        } else {
            options.setForceResourceType(resourceType);
        }
        options.setReplaceSelectors(selectors);
        SlingHttpServletRequestWrapper slingHttpServletRequestWrapper = new SlingHttpServletRequestWrapper(slingHttpServletRequest) {
            @Override
            public RequestPathInfo getRequestPathInfo() {
                return new RequestPathInfoWrapper(super.getRequestPathInfo(), "html");
            }
        };
        RequestDispatcher dispatcher = slingHttpServletRequestWrapper.getRequestDispatcher(resource, options);
        CaptureResponseWrapper captureResponseWrapper = new CaptureResponseWrapper(slingHttpServletResponse);

        try {
            if (dispatcher != null) {
                dispatcher.include(slingHttpServletRequestWrapper, captureResponseWrapper);
            }
            renderedContent = captureResponseWrapper.getCapturedCharacterResponse();
        } catch (ServletException | IOException e) {
            LOG.error("Unable to render resource", e);
        }
        return renderedContent;
    }

    private Resource getResourceWrapper(Resource resource, String attributeName) {
        Map<String, Object> mergedProperties = new HashMap<>();
        mergedProperties.putAll(resource.getValueMap());
        mergedProperties.put("xhtmlText", resource.getValueMap().get(attributeName));
        final ValueMap newValueMap = new ValueMapDecorator(mergedProperties);
        return new ResourceWrapper(resource) {
            @Override
            public ValueMap getValueMap() {
                return newValueMap;
            }
        };
    }

    private static class RequestPathInfoWrapper implements RequestPathInfo {

        private RequestPathInfo wrappedRequestPathInfo;
        private String extension;

        public RequestPathInfoWrapper(RequestPathInfo wrappedRequestPathInfo, String extension) {
            this.wrappedRequestPathInfo = wrappedRequestPathInfo;
            this.extension = extension;
        }

        @Override
        public String getResourcePath() {
            return wrappedRequestPathInfo.getResourcePath();
        }

        @Override
        public String getExtension() {
            return extension;
        }

        @Override
        public String getSelectorString() {
            return wrappedRequestPathInfo.getSelectorString();
        }

        @Override
        public String[] getSelectors() {
            return wrappedRequestPathInfo.getSelectors();
        }

        @Override
        public String getSuffix() {
            return wrappedRequestPathInfo.getSuffix();
        }

        @Override
        public Resource getSuffixResource() {
            return wrappedRequestPathInfo.getSuffixResource();
        }
    }


}
