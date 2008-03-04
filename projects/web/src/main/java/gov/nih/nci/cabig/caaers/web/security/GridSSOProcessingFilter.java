/**
 * 
 */
package gov.nih.nci.cabig.caaers.web.security;

import gov.nih.nci.security.acegi.grid.Utils;
import gov.nih.nci.security.acegi.grid.authentication.GlobusCredentialAuthenticationToken;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.acegisecurity.Authentication;
import org.acegisecurity.AuthenticationException;
import org.acegisecurity.AuthenticationManager;
import org.acegisecurity.context.SecurityContextHolder;
import org.acegisecurity.ui.AuthenticationEntryPoint;
import org.acegisecurity.ui.rememberme.RememberMeServices;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.util.Assert;

/**
 * @author <a href="mailto:joshua.phillips@semanticbits.com">Joshua Phillips</a>
 * 
 */
public class GridSSOProcessingFilter implements Filter, InitializingBean {

    private static final Log logger = LogFactory.getLog(GridSSOProcessingFilter.class);

    private AuthenticationRequestPopulator authenticationRequestPopulator;

    private AuthenticationEntryPoint authenticationEntryPoint;

    private AuthenticationManager authenticationManager;

    private RememberMeServices rememberMeServices;

    private boolean ignoreFailure = false;

    public AuthenticationEntryPoint getAuthenticationEntryPoint() {
        return authenticationEntryPoint;
    }

    public void setAuthenticationEntryPoint(AuthenticationEntryPoint authenticationEntryPoint) {
        this.authenticationEntryPoint = authenticationEntryPoint;
    }

    public AuthenticationManager getAuthenticationManager() {
        return authenticationManager;
    }

    public void setAuthenticationManager(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    public boolean isIgnoreFailure() {
        return ignoreFailure;
    }

    public void setIgnoreFailure(boolean ignoreError) {
        this.ignoreFailure = ignoreError;
    }

    public RememberMeServices getRememberMeServices() {
        return rememberMeServices;
    }

    public void setRememberMeServices(RememberMeServices rememberMeServices) {
        this.rememberMeServices = rememberMeServices;
    }

    /*
     * (non-Javadoc)
     * 
     * @see javax.servlet.Filter#destroy()
     */
    public void destroy() {
        // nothing to do here
    }

    /*
     * (non-Javadoc)
     * 
     * @see javax.servlet.Filter#doFilter(javax.servlet.ServletRequest,
     *      javax.servlet.ServletResponse, javax.servlet.FilterChain)
     */
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
                    throws IOException, ServletException {
        if (!(request instanceof HttpServletRequest)) {
            throw new ServletException("Can only process HttpServletRequest");
        }
        if (!(response instanceof HttpServletResponse)) {
            throw new ServletException("Can only process HttpServletResponse");
        }

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        Authentication existingAuth = SecurityContextHolder.getContext().getAuthentication();
        if (existingAuth != null) {
            // logger.debug("Existing Authentication found for " + existingAuth.getName() + ".
            // Proceeeding with filter.");
            chain.doFilter(request, response);
        } else {
            // logger.debug("No Authentication found.");
            AuthenticationException error = null;
            Authentication authResult = null;
            try {
                // logger.debug("Populating authentication request...");
                Authentication authRequest = getAuthenticationRequestPopulator().populate(
                                httpRequest);
                authResult = getAuthenticationManager().authenticate(authRequest);
                // logger.debug("...done");
            } catch (AuthenticationException ex) {
                // logger.info("Authentication request failed: " + ex.getMessage());
                error = ex;
            }
            if (error != null) {
                // logger.debug("Error encountered.");
                SecurityContextHolder.getContext().setAuthentication(null);
                if (getRememberMeServices() != null) {
                    getRememberMeServices().loginFail(httpRequest, httpResponse);
                }
                if (isIgnoreFailure()) {
                    // logger.debug("Ignoring failure.");
                    chain.doFilter(request, response);
                } else {
                    // logger.debug("Commencing AuthenticationEntryPoint");
                    getAuthenticationEntryPoint().commence(request, response, error);
                }

            } else {
                // logger
                // .debug("Authentication success: "
                // + authResult.toString());
                SecurityContextHolder.getContext().setAuthentication(authResult);

                /*
                 * TODO: remove hack
                 * 
                 * This is a hack to get the gridProxy into the session so that the SSO-link
                 * functionality works. It should be removed when the final SSO framework is in
                 * place.
                 * 
                 */
                // begin hack
                try {
                    // logger.debug("Doing gridProxy hack...");
                    GlobusCredentialAuthenticationToken gcToken = (GlobusCredentialAuthenticationToken) authResult;
                    httpRequest.getSession().setAttribute("gridProxy",
                                    Utils.toString(gcToken.getGlobusCredential()));
                    // logger.debug("...done hack.");
                } catch (Exception ex) {
                    // logger.warn("Failed to do gridProxy hack: " + ex.getMessage(), ex);
                }
                // end hack

                if (getRememberMeServices() != null) {
                    getRememberMeServices().loginSuccess(httpRequest, httpResponse, authResult);
                }
                // logger.debug("Proceeding with filter after successful authentication.");
                chain.doFilter(request, response);
            }
        }

    }

    /*
     * (non-Javadoc)
     * 
     * @see javax.servlet.Filter#init(javax.servlet.FilterConfig)
     */
    public void init(FilterConfig arg0) throws ServletException {
        // nothing to do here
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.springframework.beans.factory.InitializingBean#afterPropertiesSet()
     */
    public void afterPropertiesSet() throws Exception {
        Assert.notNull(this.authenticationEntryPoint, "An AuthenticationEntryPoint is required.");
        Assert.notNull(this.authenticationManager, "An AuthenticationManager is required.");
        Assert.notNull(this.authenticationRequestPopulator,
                        "An AuthenticationRequestPopulator is required.");
    }

    public AuthenticationRequestPopulator getAuthenticationRequestPopulator() {
        return authenticationRequestPopulator;
    }

    public void setAuthenticationRequestPopulator(
                    AuthenticationRequestPopulator authenticationRequestPopulator) {
        this.authenticationRequestPopulator = authenticationRequestPopulator;
    }

}
