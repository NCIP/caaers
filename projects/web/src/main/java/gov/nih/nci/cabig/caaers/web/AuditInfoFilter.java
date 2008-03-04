package gov.nih.nci.cabig.caaers.web;

import java.io.IOException;
import java.util.Date;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.acegisecurity.Authentication;
import org.acegisecurity.context.SecurityContextHolder;

public class AuditInfoFilter extends
                gov.nih.nci.cabig.ctms.web.filters.ContextRetainingFilterAdapter {

    @Override
    public void doFilter(final ServletRequest request, final ServletResponse response,
                    final FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpReq = (HttpServletRequest) request;
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null)

        {
            String username = SecurityContextHolder.getContext().getAuthentication().getName();
            // String username = ApplicationSecurityManager.getUser(httpReq);
            if (username != null) {
                gov.nih.nci.cabig.ctms.audit.DataAuditInfo
                                .setLocal(new gov.nih.nci.cabig.ctms.audit.domain.DataAuditInfo(
                                                username, request.getRemoteAddr(), new Date(),
                                                httpReq.getRequestURI()));
            }
        }
        chain.doFilter(request, response);
        edu.nwu.bioinformatics.commons.DataAuditInfo.setLocal(null);
    }
}
