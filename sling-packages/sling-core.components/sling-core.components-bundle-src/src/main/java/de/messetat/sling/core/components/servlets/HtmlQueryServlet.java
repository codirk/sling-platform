package de.messetat.sling.core.components.servlets;


import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.servlet.Servlet;
import javax.servlet.ServletException;

import de.messetat.sling.core.components.services.RenderServiceImpl;
import org.apache.felix.scr.annotations.*;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.servlets.SlingSafeMethodsServlet;
import org.apache.sling.engine.SlingRequestProcessor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component
@Service({Servlet.class})
@Properties({@Property(
        name = "service.description",
        value = {"Default Query Servlet"}
), @Property(
        name = "service.vendor",
        value = {"The Apache Software Foundation"}
), @Property(
        name = "sling.servlet.resourceTypes",
        value = {"sling/servlet/default"}
), @Property(
        name = "sling.servlet.extensions",
        value = {"html"}
), @Property(
        name = "sling.servlet.selectors",
        value = {"queryS"}
), @Property(
        name = "sling.servlet.prefix",
        intValue = {-1},
        propertyPrivate = true
)})
public class HtmlQueryServlet extends SlingSafeMethodsServlet {
    private static final long serialVersionUID = 1L;
    private final Logger log = LoggerFactory.getLogger(HtmlQueryServlet.class);
    public static final String STATEMENT = "statement";
    public static final String QUERY_TYPE = "queryType";
    public static final String OFFSET = "offset";
    public static final String ROWS = "rows";
    public static final String PROPERTY = "property";
    public static final String EXCERPT_PATH = "excerptPath";
    private static final String REP_EXCERPT = "rep:excerpt()";

    @Reference
    private transient SlingRequestProcessor requestProcessor;
    @Reference
    private transient RenderServiceImpl renderService;

    public HtmlQueryServlet() {
    }

    protected boolean isTidy(SlingHttpServletRequest req) {
        String[] arr$ = req.getRequestPathInfo().getSelectors();
        int len$ = arr$.length;

        for(int i$ = 0; i$ < len$; ++i$) {
            String selector = arr$[i$];
            if ("tidy".equals(selector)) {
                return true;
            }
        }

        return false;
    }

    protected void doGet(SlingHttpServletRequest req, SlingHttpServletResponse resp) throws IOException, ServletException {
        this.dumpResult(req, resp);
    }

    protected String getQueryType(SlingHttpServletRequest req) {
        return req.getParameter("queryType");
    }

    protected String getStatement(SlingHttpServletRequest req, String queryType) {
        return req.getParameter("statement");
    }

    /**
     * http://localhost:8080/content.query.html?queryType=xpath&statement=//content/*[@jcr:primaryType=%27sling:Folder%27]
     *
     http://localhost:8080/content.query.json?queryType=xpath&statement=//content//*[@sling:resourceType='dam/components/atoms/image/v1/image']
     *
     * @throws IOException
     * @throws ServletException
     */
    private void dumpResult(SlingHttpServletRequest request, SlingHttpServletResponse response) throws IOException, ServletException {
            ResourceResolver resolver = request.getResourceResolver();
            String queryType = this.getQueryType(request);
            String statement = this.getStatement(request, queryType);
            Iterator<Map<String, Object>> result = resolver.queryResources(statement, queryType);
            if (request.getParameter("offset") != null) {
                for(long skip = Long.parseLong(request.getParameter("offset")); skip > 0L && result.hasNext(); --skip) {
                    result.next();
                }
            }

            response.setContentType(request.getResponseContentType());
            response.setCharacterEncoding("UTF-8");


            long count = -1L;
            if (request.getParameter("rows") != null) {
                count = Long.parseLong(request.getParameter("rows"));
            }

            List<String> properties = new ArrayList();
            if (request.getParameterValues("property") != null) {
                String[] arr$ = request.getParameterValues("property");
                int len$ = arr$.length;

                for(int i$ = 0; i$ < len$; ++i$) {
                    String property = arr$[i$];
                    properties.add(property);
                }
            }

            String exerptPath = "";
            if (request.getParameter("excerptPath") != null) {
                exerptPath = request.getParameter("excerptPath");
            }

            while(result.hasNext() && count != 0L) {
                Map<String, Object> row = (Map)result.next();
                String path = row.get("jcr:path").toString();

                Resource resource = resolver.getResource(path);

                String capture=  renderService.renderResource(request, response, resource,null,"");

                response.getOutputStream().write(capture.getBytes());

                --count;
            }


    }



}