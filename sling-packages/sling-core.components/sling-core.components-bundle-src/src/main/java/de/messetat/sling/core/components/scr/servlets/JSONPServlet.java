package de.messetat.sling.core.components.scr.servlets;

import org.apache.commons.lang3.StringUtils;
import org.apache.felix.scr.annotations.Reference;
import org.apache.felix.scr.annotations.sling.SlingServlet;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.apache.sling.api.wrappers.SlingHttpServletResponseWrapper;
import org.apache.sling.engine.SlingRequestProcessor;
import org.apache.sling.scripting.core.servlet.CaptureResponseWrapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

@SlingServlet(methods = {"GET"}, metatype = false, resourceTypes = {"sling/servlet/default"}, extensions = {"jsonp"})
public class JSONPServlet extends SlingAllMethodsServlet {
    private String PN_CALLBACK = "callback";

    private static final String UTF_8 = "UTF-8";
    private static final Logger LOG = LoggerFactory.getLogger(JSONPServlet.class);

    @Reference
    private transient SlingRequestProcessor requestProcessor;

    @Override
    protected void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response) throws IOException, ServletException {
        LOG.trace("<<< doGet '{}' '{}'", request, response);

        String callback = request.getParameter(PN_CALLBACK);
        if (StringUtils.isBlank(callback)) {
            LOG.info("no callback set");
        } else {
            HttpServletRequestWrapper requestWrapper = new HttpServletRequestWrapper(request);
            requestWrapper.setCharacterEncoding("UTF-8");
            String requestPath = StringUtils.replace(request.getPathInfo(), "jsonp", "json");
            //req.getParameterMap().putAll(request.getParameterMap());
            final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            CaptureResponseWrapper captureResponseWrapper = new CaptureResponseWrapper(response);

            SlingHttpServletResponseWrapper slingHttpServletResponseWrapper1 = new SlingHttpServletResponseWrapper(response);
            requestProcessor.processRequest(request, captureResponseWrapper, request.getResourceResolver());
            response.getOutputStream().write((callback + "(").getBytes());
            response.getOutputStream().write(captureResponseWrapper.getCapturedCharacterResponse().getBytes());
            response.getOutputStream().write(");".getBytes());

            response.setContentType("text/javascript");

            response.setCharacterEncoding(UTF_8);
        }
        LOG.trace(">>> doGet");
    }


}