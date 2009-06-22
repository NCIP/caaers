package gov.nih.nci.cabig.caaers.tools.spring.tabbedflow;

import gov.nih.nci.cabig.caaers.domain.Retireable;
import gov.nih.nci.cabig.caaers.web.utils.DefaultObjectPropertyReader;
import gov.nih.nci.cabig.ctms.domain.AbstractMutableDomainObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.validation.Errors;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.WebUtils;

/**
 * @author Rhett Sutphin
 */
public abstract class RowManagableTab<C> extends ReflexiveAjaxableTab<C> {

    protected String getSoftDeleteParamName() {
        return "softDelete";
    }

    protected String getDeleteIndexParamName() {
        return "deleteIndex";
    }

    protected String getDeleteHashCodeParamName() {
        return "deleteHashCode";
    }

    protected String getCollectionParamName() {
        return "collection";
    }

    // override this method for soft deletes
    protected boolean shouldDelete(HttpServletRequest request, Object command, Errors error) {
        return !WebUtils.hasSubmitParameter(request, this.getSoftDeleteParamName());
    }

    public RowManagableTab() {
        super.paramTypes = new Class[] { HttpServletRequest.class, Object.class, Errors.class };
    }

    public RowManagableTab(String longTitle, String shortTitle, String viewName) {
        super(longTitle, shortTitle, viewName, new Class[] { HttpServletRequest.class,
                Object.class, Errors.class });
    }

    public RowManagableTab(String longTitle, String shortTitle, String viewName, Class[] params) {
        super(longTitle, shortTitle, viewName);
        super.paramTypes = params;
    }

    public ModelAndView deleteRow(HttpServletRequest request, Object command, Errors error)
                    throws Exception {
        String listPath = request.getParameter(getCollectionParamName());
        List col = (List) new DefaultObjectPropertyReader(command, listPath)
                        .getPropertyValueFromPath();
        Integer index = null;

        String deletionIdStr = request.getParameter(getDeleteHashCodeParamName());
        if (deletionIdStr.startsWith("ID#")) {
            deletionIdStr = deletionIdStr.substring(3);
            int id = Integer.parseInt(deletionIdStr);
            for (int i = 0; i < col.size(); i++) {
                AbstractMutableDomainObject amdo;
                if (col.get(i) instanceof AbstractMutableDomainObject) {
                    amdo = (AbstractMutableDomainObject) col.get(i);
                    if (amdo.getId() == id) {
                        index = i;
                        break;
                    }
                }
            }
        }
        else if (deletionIdStr.startsWith("HC#")) {
            deletionIdStr = deletionIdStr.substring(3);
            int hashCode = Integer.parseInt(deletionIdStr);
            for (int i = 0; i < col.size(); i++) {
                if (col.get(i).hashCode() == hashCode) {
                    index = i;
                    break;
                }
            }
        }

        Map<String, String> map = new HashMap<String, String>();
        if (this.shouldDelete(request, command, error)) {
            if (index == null) {
                map.put(getFreeTextModelName(), "Unmatched hashCode/Id");
            }
            else {
                col.remove(index.intValue());
                map.put(getFreeTextModelName(), "deletedIndex="
                                + request.getParameter(getDeleteIndexParamName()) + "||hashCode="
                                + request.getParameter(getDeleteHashCodeParamName()) + "||");
            }
        }
        else {
            // Enabling the retitred_indicator
            Retireable obj = (Retireable) col
                            .get(index);
            obj.retire();
        }

        return new ModelAndView("", map);
    }
}
