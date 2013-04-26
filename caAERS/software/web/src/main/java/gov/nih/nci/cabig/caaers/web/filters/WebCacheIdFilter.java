package gov.nih.nci.cabig.caaers.web.filters;

import javax.servlet.*;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * @author Biju Joseph
 *         Created on : 4/26/13 3:07 PM
 */
public class WebCacheIdFilter implements Filter {
    private String webCacheId;
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Calendar calendar = Calendar.getInstance();
        webCacheId = "v=" + new SimpleDateFormat("yyyyMMdd").format(calendar.getTime());
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        servletRequest.setAttribute("webCacheId", webCacheId);
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }
}
