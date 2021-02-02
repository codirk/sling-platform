package de.messetat.sling.core.components.wrapper;

import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.wrappers.SlingHttpServletResponseWrapper;

import javax.servlet.ServletOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * Same as a org.apache.sling.scripting.core.servlet.CaptureResponseWrapper but as an SlingHttpServletResponseWrapper
 */
public class SlingCaptureResponseWrapper extends SlingHttpServletResponseWrapper {
    private boolean isBinaryResponse = false;
    private PrintWriter writer;
    private StringWriter stringWriter;

    public SlingCaptureResponseWrapper(SlingHttpServletResponse response) {
        super(response);
    }

    public boolean isBinaryResponse() {
        return this.isBinaryResponse;
    }

    public void flushBuffer() throws IOException {
        if (this.isBinaryResponse()) {
            this.getResponse().getOutputStream().flush();
        } else {
            this.writer.flush();
        }

    }

    public ServletOutputStream getOutputStream() throws IOException {
        if (this.writer != null) {
            throw new IOException("'getWriter()' has already been invoked for a character data response.");
        } else {
            this.isBinaryResponse = true;
            return this.getResponse().getOutputStream();
        }
    }

    public PrintWriter getWriter() throws IOException {
        if (this.writer != null) {
            return this.writer;
        } else if (this.isBinaryResponse) {
            throw new IOException("'getOutputStream()' has already been invoked for a binary data response.");
        } else {
            this.stringWriter = new StringWriter();
            this.writer = new PrintWriter(this.stringWriter);
            return this.writer;
        }
    }



    public String getCapturedCharacterResponse() throws IOException {
        if (this.stringWriter == null) {
            throw new IOException("no character response data captured");
        } else {
            this.writer.flush();
            return this.stringWriter.toString();
        }
    }
}