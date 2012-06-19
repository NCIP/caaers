package gov.nih.nci.cabig.caaers.web.filters;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.util.WebUtils;

import edu.emory.mathcs.backport.java.util.Arrays;


/**
 * 
 * The Class CsrfPreventionFilter. This filter runs for all the pages. Sets the token in the session if its not already present. 
 * This filter looks for the CSRF token in the request parameters for POST requests. 
 * Only if the token is present is the request allowed to go through. 
 * 
 * Note that, as of now, we allow dwr requests to go through.
 * 
 * @author Vinay G
 * 
 */
public class CsrfPreventionFilter implements Filter {
	
	protected static final Log logger = LogFactory.getLog(CsrfPreventionFilter.class);

    public static final String CSRF_TOKEN = "CSRF_TOKEN";
    public static final String CSRF_TOKEN_HEADER = "X-CSRF-Token";

    /**
     * The attribute that determines whether or not to skip a url for filtering.
     */
    private List<String> allowURIs = new ArrayList<String>();

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;
        initializeCsfrToken(request);
        
        if (request.getMethod().toUpperCase().equals("POST") && !isAllowedURI(request.getRequestURI())) {
            boolean isValidRequest = isValidCsrfToken(request);
            if (!isValidRequest) {
                response.sendError(403);
                return;
            }
        }

        chain.doFilter(request, response);
    }

 
    private void initializeCsfrToken(HttpServletRequest req) {
       String csrfToken = req.getSession().getAttribute(CSRF_TOKEN) == null ? "" : req.getSession().getAttribute(CSRF_TOKEN).toString();
       if(StringUtils.isEmpty(csrfToken)) {
    	   req.getSession().setAttribute(CSRF_TOKEN, generateCsrfToken());
       }
    }
     
    private String generateCsrfToken() {
        long seed = System.currentTimeMillis();
        Random r = new Random();
        r.setSeed(seed);
        return Long.toString(seed) + Long.toString(Math.abs(r.nextLong()));
    }
     
    protected boolean isValidCsrfToken(HttpServletRequest req) {
        String csrfParamToken = getCSRFToken(req);
        String csrfSessionToken = req.getSession().getAttribute(CSRF_TOKEN).toString();
        if(!StringUtils.isEmpty(csrfParamToken) && !StringUtils.isEmpty(csrfSessionToken) && csrfParamToken.equals(csrfSessionToken)) {
            return true;
        } else {
            //Log this as this can be a security threat
        	logger.warn("Invalid security Token. Supplied token: " + csrfParamToken + ". Session token: " + csrfSessionToken + ". IP: " + req.getRemoteAddr());
            return false;
        }
    }
    
    protected String getCSRFToken(HttpServletRequest request){
    	if(WebUtils.hasSubmitParameter(request, CSRF_TOKEN)){
    		return request.getParameter(CSRF_TOKEN);
    	}
    	if(!StringUtils.isEmpty(request.getHeader(CSRF_TOKEN_HEADER))){
    		return request.getHeader(CSRF_TOKEN_HEADER);
    	}
    	return null;
    }
    

    public void init(FilterConfig filterConfig) throws ServletException {
    	String allowString = filterConfig.getInitParameter("allowURIs");
    	if(!StringUtils.isBlank(allowString)){
    		this.allowURIs = Arrays.asList(allowString.split(",\\s*"));
    	}
    }
    
    private boolean isAllowedURI(String url){
    	for(String allowed : this.allowURIs){
    		if(StringUtils.containsIgnoreCase(url, allowed)){
    			return true;
    		}
    	}
    	return false;
    }
    
    public void destroy() {
    }

}
