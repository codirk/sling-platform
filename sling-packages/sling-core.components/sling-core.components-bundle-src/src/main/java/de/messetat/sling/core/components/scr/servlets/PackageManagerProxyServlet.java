package de.messetat.sling.core.components.scr.servlets;

import org.apache.felix.scr.annotations.sling.SlingServlet;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.servlets.ServletResolverConstants;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
/**
 * This is a bridge servlet to the path /crx/packmgr/service.jsp instead of /bin/core/package.service.html
 * At the moment we get 403 from the org.apache.sling.servlets.get.DefaultGetServlet
 * I dont know why
 */
@SlingServlet(
        methods = {"POST","GET"},
        metatype = false,
        paths = {"/crx/packmgr/service"},
        //selectors = {"src"},
        /*
        resourceTypes = {
                ServletResolverConstants.DEFAULT_RESOURCE_TYPE
        },//*/
        extensions = {"jsp"})
@Deprecated
public class PackageManagerProxyServlet extends SlingAllMethodsServlet {
    private static final Logger LOG = LoggerFactory.getLogger(PackageManagerProxyServlet.class);
    private static final long serialVersionUID = -1L;

    @Override
    protected void doPost(SlingHttpServletRequest request, SlingHttpServletResponse response) throws ServletException, IOException {
        LOG.trace("<<< doPost");
        String uri = request.getRequestURI();
        //RequestDispatcher requestDispatcher = request.getRequestDispatcher("/bin/core/package.service.html");
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/bin/cpm/package.service.html");
        requestDispatcher.forward(request,response);
        LOG.trace(">>> doPost");
    }

    @Override
    protected void doGet(final SlingHttpServletRequest request,
                         final SlingHttpServletResponse response) throws ServletException, IOException {

        response.getWriter().println("pong");

    }


}