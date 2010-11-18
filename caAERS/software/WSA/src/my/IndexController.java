package my;

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
        String urls = req.getSession().getServletContext().getInitParameter("urls.properties");
        InputStream is = IndexController.class.getResourceAsStream(urls);
        p.load(is);
        ModelAndView mvc = new ModelAndView("/WEB-INF/views/index.jsp");
        mvc.addObject("urls", p);
        return mvc;
    }
}
