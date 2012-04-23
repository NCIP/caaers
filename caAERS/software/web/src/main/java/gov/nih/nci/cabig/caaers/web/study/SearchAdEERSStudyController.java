package gov.nih.nci.cabig.caaers.web.study;

import gov.nih.nci.cabig.caaers.api.impl.DefaultStudyService;
import gov.nih.nci.cabig.caaers.domain.OrganizationAssignedIdentifier;
import gov.nih.nci.cabig.caaers.domain.Study;
import gov.nih.nci.cabig.caaers.domain.repository.StudyRepository;
import gov.nih.nci.cabig.caaers.service.AdeersIntegrationFacade;
import gov.nih.nci.cabig.caaers.tools.Messages;
import gov.nih.nci.cabig.caaers.utils.ConfigProperty;
import gov.nih.nci.cabig.caaers.web.ListValues;
import org.apache.commons.lang.StringUtils;
import org.extremecomponents.table.context.Context;
import org.extremecomponents.table.context.HttpServletRequestContext;
import org.extremecomponents.table.core.TableModel;
import org.extremecomponents.table.core.TableModelImpl;

import org.springframework.beans.factory.annotation.Required;
import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

/**
 * @author Ion C. Olaru
 */
public class SearchAdEERSStudyController extends SimpleFormController {

    private StudyRepository studyRepository;
    private ListValues listValues;
    private ConfigProperty configurationProperty;
    AdeersIntegrationFacade adeersIntegrationFacade;
    private DefaultStudyService defaultStudyService;

    public SearchAdEERSStudyController() {
        setCommandClass(SearchCommand.class);
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
        SearchCommand sCommand = new SearchCommand();
        return sCommand;
    }

    @Override
    protected ModelAndView onSubmit(HttpServletRequest request, HttpServletResponse response, Object oCommand, BindException errors) throws Exception {

        SearchCommand c = (SearchCommand)oCommand;
        Map map = errors.getModel();

        if (StringUtils.isEmpty(c.getSearchText())) {
            errors.reject("", Messages.get("ERR_searchStudyCharacters"));
        } else {
            List<Study> studies = adeersIntegrationFacade.searchStudies(c.getSearchText());
            List<Study> caaersStudies = studyRepository.getAllStudiesByShortTitleOrIdentifiers(c.getSearchText());

            System.out.println("Found in adEERS: " + studies.size());
            System.out.println("Found in caAERS: " + caaersStudies.size());

            defaultStudyService.searchAdEERSStudiesInCaAERS(studies, caaersStudies);

            map.put("studies", studies);
        }

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

    public AdeersIntegrationFacade getAdeersIntegrationFacade() {
        return adeersIntegrationFacade;
    }

    public void setAdeersIntegrationFacade(AdeersIntegrationFacade adeersIntegrationFacade) {
        this.adeersIntegrationFacade = adeersIntegrationFacade;
    }

    public DefaultStudyService getDefaultStudyService() {
        return defaultStudyService;
    }

    public void setDefaultStudyService(DefaultStudyService defaultStudyService) {
        this.defaultStudyService = defaultStudyService;
    }
}