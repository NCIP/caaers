package gov.nih.nci.cabig.caaers.web;

import gov.nih.nci.cabig.caaers.dao.CaaersDao;
import gov.nih.nci.cabig.caaers.dao.GridIdentifiableDao;
import gov.nih.nci.cabig.caaers.tools.editors.EnumByNameEditor;
import gov.nih.nci.cabig.ctms.editors.DaoBasedEditor;
import gov.nih.nci.cabig.ctms.editors.GridIdentifiableDaoBasedEditor;

import java.beans.PropertyEditor;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.web.bind.ServletRequestDataBinder;

/**
 * @author Rhett Sutphin
 */
/* TODO: much of this class is shared with PSC. Refactor into a shared library. */
public class ControllerTools {
    private static ThreadLocal<DateFormat> dateFormat = new ThreadLocal<DateFormat>();

    // TODO: make date format externally configurable
    public static PropertyEditor getDateEditor(boolean required) {
        // note that date formats are not threadsafe, so we have to create a new one each time
        return new CustomDateEditor(createDateFormat(), !required);
    }

    // TODO: make date format externally configurable
    public static DateFormat createDateFormat() {
        return new SimpleDateFormat("MM/dd/yyyy");
    }

    public static String formatDate(Date date) {
        if (dateFormat.get() == null) {
            dateFormat.set(createDateFormat());
        }
        return dateFormat.get().format(date);
    }

    public static void registerDomainObjectEditor(ServletRequestDataBinder binder, String field,
                    CaaersDao<?> dao) {
        binder.registerCustomEditor(dao.domainClass(), field, new DaoBasedEditor(dao));
    }

    public static void registerDomainObjectEditor(ServletRequestDataBinder binder, CaaersDao<?> dao) {
        binder.registerCustomEditor(dao.domainClass(), new DaoBasedEditor(dao));
    }

    public static void registerGridDomainObjectEditor(ServletRequestDataBinder binder,
                    String field, GridIdentifiableDao<?> dao) {
        binder.registerCustomEditor(dao.domainClass(), field, new GridIdentifiableDaoBasedEditor(
                        dao));
    }

    public static <E extends Enum<E>> void registerEnumEditor(ServletRequestDataBinder binder,
                    Class<E> enumClass) {
        binder.registerCustomEditor(enumClass, new EnumByNameEditor<E>(enumClass));
    }

    /**
     * Determine whether the given request was made via an asynchronous request mechanism. Current
     * implementation works for prototype.js-initiated requests only.
     * 
     * @param request
     */
    public static boolean isAjaxRequest(HttpServletRequest request) {
        String header = request.getHeader("X-Requested-With");
        return header != null && "XMLHttpRequest".equals(header);
    }

    private ControllerTools() {
    }
}
