package gov.nih.nci.cabig.caaers.web.study;

import gov.nih.nci.cabig.caaers.domain.repository.StudyRepository;
import gov.nih.nci.cabig.caaers.utils.ConfigProperty;
import gov.nih.nci.cabig.caaers.web.ListValues;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.extremecomponents.table.context.Context;
import org.extremecomponents.table.context.HttpServletRequestContext;
import org.extremecomponents.table.core.TableModel;
import org.extremecomponents.table.core.TableModelImpl;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.validation.BindException;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;

/**
 * @author Kulasekaran
 * @author Priyatam
 */
public class SearchStudyController extends SimpleFormController {

    private StudyRepository studyRepository;

    private ListValues listValues;

    private ConfigProperty configurationProperty;

    public SearchStudyController() {
        setCommandClass(SearchStudyCommand.class);
        setFormView("study/study_search");
        setSuccessView("study/study_search");
    }

    protected Map<String, Object> referenceData(HttpServletRequest request) throws Exception {
        Map<String, Object> refdata = new HashMap<String, Object>();
        refdata.put("studySearchType", listValues.getStudySearchType());
        // refdata.put("studySearchType",
        // getConfigurationProperty().getMap().get("studySearchType"));
        return refdata;
    }

    protected void initBinder(HttpServletRequest request, ServletRequestDataBinder binder) throws Exception {
        super.initBinder(request, binder);
        Enumeration en = request.getParameterNames();

        if (request.getMethod().equals(METHOD_GET)) {
            SearchStudyAjaxFacade studyFacade = new SearchStudyAjaxFacade();
            Context context = null;
            context = new HttpServletRequestContext(request);

            TableModel model = new TableModelImpl(context);
            Object viewData = null;
            try {
                viewData = studyFacade.build(model, new ArrayList());
            } catch (Exception e) {
                e.printStackTrace();
            }

            request.setAttribute("assembler", viewData);
        }
    }

    @Override
    protected Object formBackingObject(HttpServletRequest request) throws ServletException {
        SearchStudyCommand sCommand = new SearchStudyCommand();
        sCommand.addSearchCriterion(new SearchCommand());
        return sCommand;
    }

    @Override
    protected ModelAndView onSubmit(HttpServletRequest request, HttpServletResponse response, Object oCommand, BindException errors) throws Exception {
        SearchStudyAjaxFacade studyFacade = new SearchStudyAjaxFacade();
        Context context = null;
        context = new HttpServletRequestContext(request);

        TableModel model = new TableModelImpl(context);
        Object viewData = null;
        try {
            viewData = studyFacade.build(model, new ArrayList());
        } catch (Exception e) {
            e.printStackTrace();
        }
        request.setAttribute("assembler", viewData);

        /*
         * String action = request.getParameter("_action");
         * 
         * 
         * if("addCriteria".equals(action)) {
         * ((SearchStudyCommand)oCommand).getSearchCriteria().add(new SearchCommand()); } else if
         * ("removeCriteria".equals(action)) { int index =
         * Integer.parseInt(request.getParameter("_selected"));
         * ((SearchStudyCommand)oCommand).getSearchCriteria().remove(index); }
         */

        Map map = errors.getModel();
        map.put("studySearchType", listValues.getStudySearchType());
        ModelAndView modelAndView = new ModelAndView(getSuccessView(), map);

        // needed for saving session state
        request.getSession().setAttribute(getFormSessionAttributeName(), oCommand);

        return modelAndView;
    }


    public void setListValues(final ListValues listValues) {
        this.listValues = listValues;
    }

    public ConfigProperty getConfigurationProperty() {
        return configurationProperty;
    }

    public void setConfigurationProperty(ConfigProperty configurationProperty) {
        this.configurationProperty = configurationProperty;
    }

    @Required
    public void setStudyRepository(StudyRepository studyRepository  ) {
        this.studyRepository =studyRepository;
    }
}