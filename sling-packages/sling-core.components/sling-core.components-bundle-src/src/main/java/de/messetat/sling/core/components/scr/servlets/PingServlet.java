package de.messetat.sling.core.components.scr.servlets;

import org.apache.felix.scr.annotations.sling.SlingServlet;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.servlets.ServletResolverConstants;
import org.apache.sling.api.servlets.SlingSafeMethodsServlet;

import javax.servlet.ServletException;
import java.io.IOException;

@SlingServlet(
        methods = {"GET"},
        metatype = false,
        //       paths = {"/startImport"},
        selectors = {"src"},
        resourceTypes = {
                ServletResolverConstants.DEFAULT_RESOURCE_TYPE
        },
        extensions = {"ping"})
public class PingServlet extends SlingSafeMethodsServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(final SlingHttpServletRequest request,
            final SlingHttpServletResponse response) throws ServletException, IOException {

        final Resource resource = request.getResource();
        response.getWriter().println("pong");
        // ResourceResolverWrapper resourceResolverWrapper;
        // resourceResolverWrapper.move();

    }
    public void foo(ResourceResolver rr, ResourceResolver res)  {

    }

    private void movePageChildren(ResourceResolver rr, ResourceResolver res)  {
        Resource source;
        /*
        source = rr.getResource(getSourcePath());
        try {
            if (source != null && source.hasChildren()) {
                for (Resource child : source.getChildren()) {
                    if (!hasChild(child.getPath())) {
                        String childDestination = child.getPath().replaceAll(getSourcePath(), getDestinationPath());
                        String childDestinationParent = StringUtils.substringBeforeLast(childDestination, "/");
                        if (!resourceExists(res, childDestination)) {
                            waitUntilResourceFound(res, childDestinationParent);
                            res.move(child.getPath(), childDestination);
                        }
                    }
                }
                res.commit();
            }
        } catch (PersistenceException e) {
            throw new MovingException(getSourcePath(), e);
        }
        */
    }
}
