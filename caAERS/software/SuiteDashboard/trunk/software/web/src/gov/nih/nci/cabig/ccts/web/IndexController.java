package gov.nih.nci.cabig.ccts.web;

import gov.nih.nci.cabig.ccts.security.WebSSOUser;
import org.acegisecurity.Authentication;
import org.acegisecurity.context.SecurityContextHolder;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.util.Properties;

public class IndexController extends AbstractController {
    @Override
    protected ModelAndView handleRequestInternal(HttpServletRequest req, HttpServletResponse res) throws Exception {
        Properties p = new Properties();
        String urls = req.getSession().getServletContext().getInitParameter("app.properties");
        InputStream is = IndexController.class.getClassLoader().getResourceAsStream("/resources/" + urls);
        p.load(is);
        ModelAndView mvc = new ModelAndView("/WEB-INF/views/index.jsp");
        mvc.addObject("urls", p);

        WebSSOUser user = null;
        Authentication a = SecurityContextHolder.getContext().getAuthentication();
        if (a != null) user = (WebSSOUser)a.getPrincipal();
        mvc.addObject("user", user);
        
        return mvc;
    }
}
