package com.mins.corona.config;

import org.apache.catalina.connector.Request;
import org.apache.catalina.connector.Response;
import org.apache.catalina.util.TomcatCSS;
import org.apache.catalina.valves.ErrorReportValve;
import org.apache.tomcat.util.ExceptionUtils;

import java.io.IOException;
import java.io.Writer;

/**
 * Custom ErrorReportingValve
 *
 * @author minssogi
 */
public class CustomErrorValve extends ErrorReportValve {

    @Override
    protected void report(Request request, Response response, Throwable throwable) {
        StringBuilder sb = new StringBuilder();

        sb.append("<!doctype html><html lang=\"");
        sb.append("en").append("\">");
        sb.append("<head>");
        sb.append("<title>");
        sb.append("Mins Error Page");
        sb.append("</title>");
        sb.append("<style type=\"text/css\">");
        sb.append(TomcatCSS.TOMCAT_CSS);
        sb.append("</style>");
        sb.append("</head><body>");
        sb.append("<h1>");
        sb.append("Mins Custom Error Page~~").append("</h1>");

        sb.append("</body></html>");

        try {
            try {
                response.setContentType("text/html");
                response.setCharacterEncoding("utf-8");
            } catch (Throwable t) {
                ExceptionUtils.handleThrowable(t);
                if (container.getLogger().isDebugEnabled()) {
                    container.getLogger().debug("status.setContentType", t);
                }
            }
            Writer writer = response.getReporter();
            if (writer != null) {
                // If writer is null, it's an indication that the response has
                // been hard committed already, which should never happen
                writer.write(sb.toString());
                response.finishResponse();
            }
        } catch (IOException e) {
            // Ignore
        } catch (IllegalStateException e) {
            // Ignore
        }
    }
}
