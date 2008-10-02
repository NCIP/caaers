package gov.nih.nci.cabig.caaers.tools.spring.tabbedflow;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.validation.Errors;
import org.springframework.web.servlet.ModelAndView;

import org.apache.commons.lang.StringUtils;
import gov.nih.nci.cabig.caaers.web.utils.DefaultObjectPropertyReader;

/**
 * @author Rhett Sutphin
 */
public class InPlaceEditableTab<C> extends WorkFlowTab<C> {

    private static final String IN_PLACE_PARAM_NAME = "_ajaxInPlaceEditParam";
    private static final String PATH_TO_GET = "_pathToGet";

    public InPlaceEditableTab() {

    }

    public InPlaceEditableTab(String longTitle, String shortTitle, String viewName) {
        super(longTitle, shortTitle, viewName);
    }

    public InPlaceEditableTab(String longTitle, String shortTitle) {
        super(longTitle, shortTitle, "");
    }

    public ModelAndView doInPlaceEdit(HttpServletRequest request, Object command, Errors error) throws Exception {
        String name = request.getParameter(IN_PLACE_PARAM_NAME);
        String value = request.getParameter(name);
        return postProcessInPlaceEditing(request, (C) command, name, value);
    }

    protected ModelAndView postProcessInPlaceEditing(HttpServletRequest request, C command, String property, String value) throws Exception {
        Map<String, String> map = new HashMap<String, String>();
        String pathToGet = request.getParameter(PATH_TO_GET);

        if (StringUtils.isNotEmpty(pathToGet)) {
            value = (String) new DefaultObjectPropertyReader(command, pathToGet).getPropertyValueFromPath();
        }

        map.put(getFreeTextModelName(), value);
        return new ModelAndView("", map);
    }
}
