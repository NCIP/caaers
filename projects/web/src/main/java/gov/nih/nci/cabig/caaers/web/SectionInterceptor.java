package gov.nih.nci.cabig.caaers.web;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.util.UrlPathHelper;
import org.springframework.util.AntPathMatcher;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

import gov.nih.nci.cabig.caaers.web.chrome.Section;

/**
 * @author Rhett Sutphin
 */
/* TODO: this class has been copied into CTMS commons.  Remove it from here. */
public class SectionInterceptor extends HandlerInterceptorAdapter {
    private List<Section> sections;
    private String attributePrefix;
    private UrlPathHelper urlPathHelper = new UrlPathHelper();
    private AntPathMatcher pathMatcher = new AntPathMatcher();

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Section current = findSection(urlPathHelper.getPathWithinServletMapping(request));
        request.setAttribute(prefix("currentSection"), current);
        request.setAttribute(prefix("sections"), getSections());
        return true;
    }

    private Section findSection(String controllerPath) {
        for (Section section : getSections()) {
            for (String pattern : section.getPathMappings()) {
                if (pathMatcher.match(pattern, controllerPath)) {
                    return section;
                }
            }
        }
        return null;
    }

    private String prefix(String attr) {
        if (getAttributePrefix() == null) {
            return attr;
        } else {
            return getAttributePrefix() + attr;
        }
    }

    public List<Section> getSections() {
        return sections;
    }

    public void setSections(List<Section> sections) {
        this.sections = sections;
    }

    public String getAttributePrefix() {
        return attributePrefix;
    }

    public void setAttributePrefix(String attributePrefix) {
        this.attributePrefix = attributePrefix;
    }
}
