package gov.nih.nci.cabig.caaers.web.study;

import gov.nih.nci.cabig.caaers.domain.repository.StudyRepository;
import gov.nih.nci.cabig.caaers.utils.ConfigProperty;
import gov.nih.nci.cabig.caaers.web.ListValues;
import org.extremecomponents.table.context.Context;
import org.extremecomponents.table.context.HttpServletRequestContext;
import org.extremecomponents.table.core.TableModel;
import org.extremecomponents.table.core.TableModelImpl;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Ion C. Olaru
 */
public class SearchAdEERSStudyController extends SimpleFormController {

    private StudyRepository studyRepository;
    private ListValues listValues;
    private ConfigProperty configurationProperty;

    public SearchAdEERSStudyController() {
        setCommandClass(SearchStudyCommand.class);
        setFormView("study/study_adeers_search");
        setSuccessView("study/study_adeers_search");
    }

    protected Map<String, Object> referenceData(HttpServletRequest request) throws Exception {
        Map<String, Object> refdata = new HashMap<String, Object>();
        refdata.put("studySearchType", listValues.getStudySearchType());
        return refdata;
    }

    @Override
    protected Object formBackingObject(HttpServletRequest request) throws ServletException {
        SearchStudyCommand sCommand = new SearchStudyCommand();
        sCommand.addSearchCriterion(new SearchCommand());
        return sCommand;
    }

    @Override
    protected ModelAndView onSubmit(HttpServletRequest request, HttpServletResponse response, Object oCommand, BindException errors) throws Exception {
/*
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

        map.put("studySearchType", listValues.getStudySearchType());
*/
        Map map = errors.getModel();
        ModelAndView modelAndView = new ModelAndView(getSuccessView(), map);
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