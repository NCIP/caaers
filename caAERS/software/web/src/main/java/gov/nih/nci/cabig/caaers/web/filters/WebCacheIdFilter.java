package gov.nih.nci.cabig.caaers.web.filters;

import javax.servlet.*;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * @author Biju Joseph
 *         Created on : 4/26/13 3:07 PM
 */
public class WebCacheIdFilter implements Filter {
    private String webCacheId;

    public void init(FilterConfig filterConfig) throws ServletException {
        Calendar calendar = Calendar.getInstance();
        webCacheId = "v=v" + new SimpleDateFormat("yyyyMMdd").format(calendar.getTime());
    }


    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        servletRequest.setAttribute("webCacheId", webCacheId);
        if(servletResponse instanceof HttpServletResponse){
            HttpServletResponse httpRes = (HttpServletResponse) servletResponse;
            httpRes.setHeader("Connection", "Keep-Alive");
            httpRes.setHeader("Keep-Alive", "timeout=15, max=40");

        }

        filterChain.doFilter(servletRequest, servletResponse);
    }


    public void destroy() {

    }
}
