package gov.nih.nci.cabig.caaers.web.filters;

import gov.nih.nci.cabig.caaers.web.utils.WebUtils;
import gov.nih.nci.cabig.ctms.tools.BuildInfo;

import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Biju Joseph
 *         Created on : 4/22/13 5:49 AM
 */
public class BrowserCacheInvalidationRequestFilter implements Filter {
    FilterConfig config;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
       config = filterConfig;
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        String buildName = servletRequest.getAttribute("buildInfo") != null ? ((BuildInfo)servletRequest.getAttribute("buildInfo")).getBuildName() : "unknown";
        if(servletRequest instanceof HttpServletRequest){
            HttpServletRequest request = (HttpServletRequest) servletRequest;
            HttpServletResponse response = (HttpServletResponse) servletResponse;
            String cachedBuild = WebUtils.getBuildInfoCookie(request);

            if(cachedBuild == null){
               WebUtils.setBuildInfoCookie(response, buildName);
            } else if (!cachedBuild.equals(buildName)) {
               request.setAttribute("cachedBuild", cachedBuild);
               config.getServletContext().getRequestDispatcher("/WEB-INF/views/cleanCache.jsp").forward(request, response);

               response.flushBuffer();
               return;
            }

        }
        //continue in the chain
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }
}
