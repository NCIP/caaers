package gov.nih.nci.cabig.caaers.web.search;

import gov.nih.nci.cabig.caaers.dao.AdverseEventDao;
import gov.nih.nci.cabig.caaers.dao.CtcCategoryDao;
import gov.nih.nci.cabig.caaers.dao.ExpeditedAdverseEventReportDao;
import gov.nih.nci.cabig.caaers.dao.InvestigatorDao;
import gov.nih.nci.cabig.caaers.dao.OrganizationDao;
import gov.nih.nci.cabig.caaers.dao.ParticipantDao;
import gov.nih.nci.cabig.caaers.dao.ResearchStaffDao;
import gov.nih.nci.cabig.caaers.dao.StudyDao;
import gov.nih.nci.cabig.caaers.domain.repository.StudyRepository;
import gov.nih.nci.cabig.caaers.utils.ConfigProperty;
import gov.nih.nci.cabig.caaers.web.ListValues;
import gov.nih.nci.cabig.caaers.web.study.SearchStudyCommand;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.extremecomponents.table.context.Context;
import org.extremecomponents.table.context.HttpServletRequestContext;
import org.extremecomponents.table.core.TableModel;
import org.extremecomponents.table.core.TableModelImpl;
import org.springframework.web.servlet.mvc.SimpleFormController;

/**
 * @author Krikor Krumlian
 */
public abstract class SearchController extends SimpleFormController {

    private StudyRepository studyRepository;

    private ConfigProperty configurationProperty;

    private ListValues listValues;

    private CtcCategoryDao ctcCategoryDao;

    private StudyDao studyDao;

    private ParticipantDao participantDao;

    private OrganizationDao organizationDao;

    private ResearchStaffDao researchStaffDao;

    private ExpeditedAdverseEventReportDao expeditedDao;

    private AdverseEventDao adverseEventDao;

    private InvestigatorDao investigatorDao;

    public SearchController() {
        setCommandClass(SearchStudyCommand.class);
        setFormView("search/study_search");
        setSuccessView("search/study_search");
    }

    @Override
    protected Map<String, Object> referenceData(final HttpServletRequest request) throws Exception {
        Map<String, Object> refdata = new HashMap<String, Object>();
        refdata.put("genders", listValues.getParticipantGender());
        refdata.put("ethnicity", listValues.getParticipantEthnicity());
        refdata.put("ctcCategories", ctcCategoryDao.getAll());
        return refdata;
    }

    // TODO: I really do not like the way I am implementing this I need to find a better way
    protected void buildSearchResultTable(final HttpServletRequest request, final String prop, final String value, final int x) throws Exception {

        SearchStudyAjaxFacade searchFacade = new SearchStudyAjaxFacade(studyDao, participantDao, adverseEventDao, expeditedDao, organizationDao);
        searchFacade.setResearchStaffDao(researchStaffDao);
        searchFacade.setInvestigatorDao(investigatorDao);
        Context context = new HttpServletRequestContext(request);

        TableModel model = new TableModelImpl(context);
        Object viewData = null;
        try {
            switch (x) {
                case 0:
                    // viewData = searchFacade.build(model, new ArrayList());
                    viewData = searchFacade.getTable(null, prop, value, request);
                    break;
                case 1:
                    viewData = searchFacade.getParticipantTable(null, prop, value, request);
                    break;
                case 2:
                    viewData = searchFacade.getAdverseEventTable(null, prop, value, request);
                    break;
                case 3:
                    viewData = searchFacade.getExpeditedReportTable(null, prop, value, request);
                    break;
                case 4:
                    break;
                case 5:
                    viewData = searchFacade.getINDTable(null, prop, value, request);
                    break;
                case 6:
                    viewData = searchFacade.getOrganizationTable(null, prop, value, request);
                    break;

                case 7:
                    viewData = searchFacade.getResearchStaffTable(null, prop, value, request);
                    break;

                case 8:
                    viewData = searchFacade.buildParticipantTable(null, prop, value, request);
                    break;
                case 9:
                    viewData = searchFacade.getInvestigatorTable(null, prop, value, request);
                    break;

                default:
                    viewData = searchFacade.build(model, new ArrayList());
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


        request.setAttribute("assembler", viewData);
    }

    public StudyRepository getStudyRepository() {
        return studyRepository;
    }

    public void setStudyRepository(final StudyRepository studyRepository) {
        this.studyRepository = studyRepository;
    }

    public ConfigProperty getConfigurationProperty() {
        return configurationProperty;
    }

    public void setConfigurationProperty(final ConfigProperty configurationProperty) {
        this.configurationProperty = configurationProperty;
    }

    public ListValues getListValues() {
        return listValues;
    }

    public void setListValues(final ListValues listValues) {
        this.listValues = listValues;
    }

    public void setCtcCategoryDao(final CtcCategoryDao ctcCategoryDao) {
        this.ctcCategoryDao = ctcCategoryDao;
    }

    public CtcCategoryDao getCtcCategoryDao() {
        return ctcCategoryDao;
    }

    public StudyDao getStudyDao() {
        return studyDao;
    }

    public void setStudyDao(final StudyDao studyDao) {
        this.studyDao = studyDao;
    }

    public AdverseEventDao getAdverseEventDao() {
        return adverseEventDao;
    }

    public void setAdverseEventDao(final AdverseEventDao adverseEventDao) {
        this.adverseEventDao = adverseEventDao;
    }

    public ExpeditedAdverseEventReportDao getExpeditedDao() {
        return expeditedDao;
    }

    public void setExpeditedDao(final ExpeditedAdverseEventReportDao expeditedDao) {
        this.expeditedDao = expeditedDao;
    }

    public ParticipantDao getParticipantDao() {
        return participantDao;
    }

    public void setParticipantDao(final ParticipantDao participantDao) {
        this.participantDao = participantDao;
    }

    public void setOrganizationDao(final OrganizationDao organizationDao) {
        this.organizationDao = organizationDao;
    }


    public void setResearchStaffDao(final ResearchStaffDao researchStaffDao) {
        this.researchStaffDao = researchStaffDao;
    }

    public void setInvestigatorDao(final InvestigatorDao investigatorDao) {
        this.investigatorDao = investigatorDao;
    }
}