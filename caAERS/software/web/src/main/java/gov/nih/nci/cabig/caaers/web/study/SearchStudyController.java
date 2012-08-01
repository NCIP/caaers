package gov.nih.nci.cabig.caaers.web.study;

import gov.nih.nci.cabig.caaers.domain.repository.StudyRepository;
import gov.nih.nci.cabig.caaers.tools.configuration.Configuration;
import gov.nih.nci.cabig.caaers.utils.ConfigProperty;
import gov.nih.nci.cabig.caaers.web.ListValues;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
    private Configuration configuration;

    public SearchStudyController() {
        setCommandClass(SearchStudyCommand.class);
        setFormView("study/study_search");
        setSuccessView("study/study_search");
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