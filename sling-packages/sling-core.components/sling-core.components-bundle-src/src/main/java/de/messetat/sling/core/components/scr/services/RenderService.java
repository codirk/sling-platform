package de.messetat.sling.core.components.scr.services;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.resource.Resource;

public interface RenderService {

    String renderAttribute(SlingHttpServletRequest slingHttpServletRequest, SlingHttpServletResponse slingHttpServletResponse, Resource resource, String attributeName);

    /**
     *
     * @param slingHttpServletRequest SlingHttpServletRequest
     * @param slingHttpServletResponse SlingHttpServletResponse (the result will not rendered to this response)
     * @param resource      The resource to render
     * @param resourceType  The type of resource used for rendering
     * @param selector      (optional)
     * @return the rendered/captured output
     */
    String renderResource(SlingHttpServletRequest slingHttpServletRequest, SlingHttpServletResponse slingHttpServletResponse, Resource resource, String resourceType, String selector);

}
