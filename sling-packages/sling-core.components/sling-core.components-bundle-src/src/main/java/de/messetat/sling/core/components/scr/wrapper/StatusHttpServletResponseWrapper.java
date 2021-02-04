package de.messetat.sling.core.components.scr.wrapper;

import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.wrappers.SlingHttpServletResponseWrapper;

import java.io.IOException;
import java.net.HttpURLConnection;

public class StatusHttpServletResponseWrapper extends SlingHttpServletResponseWrapper {
    private int status = HttpURLConnection.HTTP_OK;

    public StatusHttpServletResponseWrapper(SlingHttpServletResponse httpServletResponse) {
        super(httpServletResponse);
    }

    @Override
    public void sendRedirect(String location) throws IOException {
        // super.sendRedirect(location);
        this.status = SC_MOVED_PERMANENTLY;
    }

    @Override
    public void sendError(int sc) throws IOException {
        // super.sendError(sc);
        this.status = sc;
    }

    @Override
    public void sendError(int sc, String msg) throws IOException {
        // super.sendError(sc, msg);
        this.status = sc;
    }

    @Override
    public void setStatus(int sc, String sm) {
        super.setStatus(sc, sm);
        this.status = sc;
    }

    @Override
    public void setStatus(int sc) {
        super.setStatus(sc);
        this.status = sc;
    }

    public int getStatus() {
        return status;
    }
}