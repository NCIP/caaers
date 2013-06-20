package gov.nih.nci.cabig.caaers.web.filters;

import gov.nih.nci.cabig.caaers.security.CTEPIAMAuthenEntryPoint;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: vijendhar
 * Date: 4/18/13
 * Time: 1:14 PM
 * To change this template use File | Settings | File Templates.
 */
public class CTEPIAMAuthenFilter  implements Filter {

    public void doFilter(ServletRequest req, ServletResponse res,
                         FilterChain chain) throws IOException, ServletException {
        if (req instanceof HttpServletRequest) {
            String url = ((HttpServletRequest)req).getRequestURL().toString();
            boolean chainFlag = true;
            if ( url != null && url.contains("ctepLogin")) {
                CTEPIAMAuthenEntryPoint ctepIAMAuthenEntryPoint = new CTEPIAMAuthenEntryPoint();
                if ( ctepIAMAuthenEntryPoint != null )
                    ctepIAMAuthenEntryPoint.authenticateRequest(((HttpServletRequest)req), (HttpServletResponse)res);
                chainFlag = false;

            }

            if ( isSAMLResponse(((HttpServletRequest)req))) {
                CTEPIAMAuthenEntryPoint ctepIAMAuthenEntryPoint = new CTEPIAMAuthenEntryPoint();
                ctepIAMAuthenEntryPoint.receiveResponse(((HttpServletRequest)req), (HttpServletResponse)res);
                chainFlag = true;
            }

            if ( chainFlag )
                chain.doFilter(req,res);
        }
    }

    private boolean isSAMLResponse(HttpServletRequest request)  {
        return request.getParameter("SAMLResponse") != null;
    }

    public void init(FilterConfig config) throws ServletException {

    }
    public void destroy() {
        //add code to release any resource

    }
}
