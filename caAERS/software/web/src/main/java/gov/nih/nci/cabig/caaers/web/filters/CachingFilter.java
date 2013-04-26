package gov.nih.nci.cabig.caaers.web.filters;


/**
 * @author Biju Joseph Created on : 4/21/13 10:10 PM
 *  Provides caching
 */

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;


import gov.nih.nci.cabig.ctms.tools.BuildInfo;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 *  The caching behaviour is quite different in differnt browsers
 *  Eg:- IE kind of ingnores the Expire and LastModified and issues a request to server.
 *         <b>This can be prevented using ETags</b>
 *       FireFox - Will issue request to server IF <code>ETag</code> is present.
 *         <b>This can be prevented by removing ETag from header</b>
 *
 * */
public class CachingFilter implements Filter {

    private static final Log log = LogFactory.getLog(CachingFilter.class.getName());
    private String expiryDate;
    private String lastModifiedDate;

    protected boolean isIE(HttpServletRequest request) {
        return request.getHeader("User-Agent").contains("MSIE");
    }


    public void init(FilterConfig filterConfig) throws ServletException {
        //set far future expiry date
        Calendar calendar = Calendar.getInstance();

        calendar.add(Calendar.YEAR, 30);
        expiryDate = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss z").format(calendar.getTime());

        Calendar lastModified = Calendar.getInstance();
        lastModified.add(Calendar.YEAR, -2);

        lastModifiedDate = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss z").format(lastModified.getTime());

        log.info(String.format("Initializing caching filter with expiry date of %s ", expiryDate));
        log.info(String.format("Initializing caching filter with lastModifiedDate of %s ", lastModifiedDate));

    }

    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        if( !( (servletRequest instanceof HttpServletRequest) && (servletResponse instanceof HttpServletResponse)) )    {
            //not able to process no http requests
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }


        HttpServletRequest httpReq = (HttpServletRequest) servletRequest;
        HttpServletResponse httpRes = (HttpServletResponse) servletResponse;

        String uri = httpReq.getRequestURI();

        String lastModified = lastModifiedDate;
        String expires = expiryDate;
        String cacheControl = String.format("public,max-age=1261440000");  //max-age=60 * 60 * 24 * 365 * 40  (40 years)
        httpRes.setHeader("Expires", expires);
        httpRes.setHeader("Last-Modified", lastModified);
        httpRes.setHeader("Cache-Control", cacheControl);  //max-age=60 * 60 * 24 * 365 * 40  (40 years)

        if(isIE(httpReq)){
            //add the ETag header

            String build = httpReq.getAttribute("buildInfo") != null ? ((BuildInfo)httpReq.getAttribute("buildInfo")).getBuildName() : "unknown";
            final String etTag = build + "-" + uri.substring(uri.lastIndexOf('/') + 1);
            httpRes.setHeader("ETag", etTag);

            if(log.isDebugEnabled()) log.debug("URI : " + uri + ", ETag : " + etTag + ", expiry : " + expiryDate + " lastModified : " + lastModifiedDate);
            String previousETag =  httpReq.getHeader("If-None-Match");
            //if ETag is not modified, return SC_NOT_MODIFIED error
            if(StringUtils.equals(previousETag, etTag)){
                //unmodified - so return unmodified-error
                httpRes.sendError(HttpServletResponse.SC_NOT_MODIFIED);
                return;
            }
        }


        //continue the processing, also make sure other filters cannot modify ETag
        HttpServletResponseWrapper wrappedRes = new HttpServletResponseWrapper(httpRes) {
            @Override
            public void setHeader(String name, String value) {
                //ignore known headers
               if(name.equals("ETag") || name.equals("Last-Modified") || name.equals("Expires") || name.equals("Cache-Control") ) return;
               super.setHeader(name, value);
            }
        };

        if(log.isDebugEnabled()) log.debug("URI : " + uri + ", expiry : " + expiryDate + " lastModified : " + lastModifiedDate);

        //continue the filter chain.
        filterChain.doFilter(servletRequest, wrappedRes);

    }

    public void destroy() {

    }
}


