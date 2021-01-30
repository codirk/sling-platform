package de.messetat.sling.server.servlets;

import org.apache.felix.scr.annotations.sling.SlingServlet;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@SlingServlet(methods = {"POST","GET"},
        metatype = false,
        paths = {"/crx/packmgr/service"},
        resourceTypes = {"sling/servlet/default"},
        extensions = {"jsp"})
/**
 * This is a bridge servlet to the path /crx/packmgr/service.jsp instead of /bin/core/package.service.html
 */
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


}