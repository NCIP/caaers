/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.web.filters;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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

import edu.emory.mathcs.backport.java.util.Arrays;

public class XSSFilter implements Filter {
	
	protected static final Log logger = LogFactory.getLog(XSSFilter.class);
	
	/**
     * The attribute that determines whether or not to skip a url for filtering.
     */
    private List<String> allowURIs = new ArrayList<String>();
	 
    public void init(FilterConfig filterConfig) throws ServletException {
        
        // Parse the Filter's init parameters.
        String allowString = filterConfig.getInitParameter("allowURIs");
    	if(!StringUtils.isBlank(allowString)){
    		this.allowURIs = Arrays.asList(allowString.split(",\\s*"));
    	}
    }
 
    public void destroy() {
    }
 
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain)
        throws IOException, ServletException {
    	// Skip filtering for non-HTTP requests and responses.
        if (!(request instanceof HttpServletRequest) ||
            !(response instanceof HttpServletResponse)) {
            filterChain.doFilter(request, response);
            return;
        }
        
        if (isAllowedURI(((HttpServletRequest)request).getRequestURI())) {
        	logger.debug("Skip filtering: '"+((HttpServletRequest)request).getRequestURI()+"'");
            filterChain.doFilter(request, response);
            return;
        }
        filterChain.doFilter(new XSSRequestWrapper((HttpServletRequest) request), response);
    }
    
    private boolean isAllowedURI(String url){
    	for(String allowed : this.allowURIs){
    		//if(StringUtils.containsIgnoreCase(url, allowed)){
    		if(url.endsWith(allowed)){
    			return true;
    		}
    	}
    	return false;
    }
 
}
