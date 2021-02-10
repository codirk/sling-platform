package de.messetat.sling.core.components.scr.servlets;

import org.apache.sling.testing.mock.sling.junit.SlingContext;
import org.apache.sling.testing.mock.sling.servlet.MockSlingHttpServletRequest;
import org.apache.sling.testing.mock.sling.servlet.MockSlingHttpServletResponse;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import javax.servlet.ServletException;
import java.io.IOException;

import static junit.framework.Assert.assertEquals;
import static org.mockito.Mockito.when;

public class TestJSONPServlet {


    private JSONPServlet servlet;

    @Rule
    public SlingContext context = new SlingContext();

    @Before
    public void setup() {
        servlet = new JSONPServlet();
    }

    @Test
    public void testDoGetWithoutCommandParameters() throws IOException, ServletException {
        MockSlingHttpServletRequest request = context.request();
        MockSlingHttpServletResponse response = context.response();

        servlet.doGet(request, response);

        assertEquals(200, response.getStatus());
    }

    public void testDoGetWithCommandParameters() throws IOException, ServletException {
        MockSlingHttpServletRequest request = context.request();
        MockSlingHttpServletResponse response = context.response();
        when(request.getPathInfo()).thenReturn("/some/path");
        request.setQueryString("callback=callbackFunction");
        servlet.doGet(request, response);

        assertEquals(200, response.getStatus());
    }


}
