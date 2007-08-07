package gov.nih.nci.cabig.caaers.web.study;

import gov.nih.nci.cabig.caaers.CaaersSystemException;
import gov.nih.nci.cabig.caaers.dao.AgentDao;
import gov.nih.nci.cabig.caaers.dao.DiseaseCategoryDao;
import gov.nih.nci.cabig.caaers.dao.DiseaseTermDao;
import gov.nih.nci.cabig.caaers.dao.InvestigationalNewDrugDao;
import gov.nih.nci.cabig.caaers.dao.OrganizationDao;
import gov.nih.nci.cabig.caaers.dao.ResearchStaffDao;
import gov.nih.nci.cabig.caaers.dao.SiteInvestigatorDao;
import gov.nih.nci.cabig.caaers.domain.Agent;
import gov.nih.nci.cabig.caaers.domain.DiseaseCategory;
import gov.nih.nci.cabig.caaers.domain.DiseaseTerm;
import gov.nih.nci.cabig.caaers.domain.InvestigationalNewDrug;
import gov.nih.nci.cabig.caaers.domain.Investigator;
import gov.nih.nci.cabig.caaers.domain.Organization;
import gov.nih.nci.cabig.caaers.domain.OrganizationAssignedIdentifier;
import gov.nih.nci.cabig.caaers.domain.ResearchStaff;
import gov.nih.nci.cabig.caaers.domain.SiteInvestigator;
import gov.nih.nci.cabig.caaers.domain.Study;
import gov.nih.nci.cabig.caaers.domain.StudyAgentINDAssociation;
import gov.nih.nci.cabig.caaers.domain.SystemAssignedIdentifier;
import gov.nih.nci.cabig.caaers.tools.ObjectTools;
import gov.nih.nci.cabig.caaers.web.fields.InputFieldGroup;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.directwebremoting.WebContext;
import org.directwebremoting.WebContextFactory;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.web.servlet.mvc.AbstractFormController;

/**
 * @author Rhett Sutphin
 * @author Krikor Krumlian
 */
public class CreateStudyAjaxFacade {

	public static final String AJAX_REQUEST_PARAMETER = "_isAjax";

	public static final String AJAX_INDEX_PARAMETER = "index";

	public static final String AJAX_SUBVIEW_PARAMETER = "_subview";

	public static final String CREATE_STUDY_FORM_NAME = CreateStudyController.class.getName() + ".FORM.command";

	public static final String EDIT_STUDY_FORM_NAME = EditStudyController.class.getName() + ".FORM.command";

	private static final Log log = LogFactory.getLog(CreateStudyAjaxFacade.class);

	private AgentDao agentDao;

	private DiseaseCategoryDao diseaseCategoryDao;

	private DiseaseTermDao diseaseTermDao;

	private SiteInvestigatorDao siteInvestigatorDao;

	private ResearchStaffDao researchStaffDao;

	private OrganizationDao organizationDao;

	private InvestigationalNewDrugDao investigationalNewDrugDao;

	public List<SiteInvestigator> matchSiteInvestigator(String text, int indexId) {
		String[] arr = new String[] { text };
		Study study = getStudyCommand(getHttpServletRequest());
		int siteId = study.getStudySites().get(indexId).getOrganization().getId();
		List<SiteInvestigator> siteInvestigators = siteInvestigatorDao.getBySubnames(arr, siteId);

		return ObjectTools.reduceAll(siteInvestigators, new ObjectTools.Initializer<SiteInvestigator>() {
			public void initialize(SiteInvestigator instance) {
				instance.setInvestigator(new Investigator());
			}
		}, "id", "investigator.firstName", "investigator.lastName");
	}

	public List<ResearchStaff> matchResearch(String text) {
		List<ResearchStaff> researchStaff = researchStaffDao.getBySubnames(new String[] { text });
		return ObjectTools.reduceAll(researchStaff, "id", "firstName", "lastName");
	}

	private Study getStudyCommand(HttpServletRequest request) {
		Study study = (Study) request.getSession().getAttribute(CREATE_STUDY_FORM_NAME);
		if (study == null) {
			study = (Study) request.getSession().getAttribute(EDIT_STUDY_FORM_NAME);
		}
		request.setAttribute(AbstractFormController.DEFAULT_COMMAND_NAME, study);
		return study;
	}

	public List<Agent> matchAgents(String text) {
		List<Agent> agents = agentDao.getBySubnames(extractSubnames(text));
		return ObjectTools.reduceAll(agents, "id", "name", "nscNumber", "description");
	}

	public List<InvestigationalNewDrug> matchINDs(String text) {
		List<InvestigationalNewDrug> inds = investigationalNewDrugDao.findByIds(new String[] { text });
		return ObjectTools.reduceAll(inds, "id", "strINDNo");
	}

	public List<Organization> matchOrganization(String text) {
		List<Organization> orgs = organizationDao.getBySubnames(extractSubnames(text));
		return ObjectTools.reduceAll(orgs, "id", "name");
	}

	private String[] extractSubnames(String text) {
		return text.split("\\s+");
	}

	public List<DiseaseCategory> matchDiseaseCategories(String text, Integer categoryId) {
		List<DiseaseCategory> diseaseCategories = diseaseCategoryDao.getBySubname(extractSubnames(text), categoryId);
		return diseaseCategories;
	}

	public List<DiseaseCategory> matchDiseaseCategoriesByParentId(Integer parentCategoryId) {
		List<DiseaseCategory> diseaseCategories = diseaseCategoryDao.getByParentId(parentCategoryId);
		return diseaseCategories;
	}

	public List<DiseaseTerm> matchDiseaseTermsByCategoryId(Integer categoryId) {
		List<DiseaseTerm> diseaseTerms = diseaseTermDao.getByCategoryId(categoryId);
		return diseaseTerms;
	}

	public String addStudySite(int index) {
		HttpServletRequest request = getHttpServletRequest();
		getStudyCommand(request);
		request.setAttribute(AJAX_INDEX_PARAMETER, index);
		request.setAttribute(AJAX_SUBVIEW_PARAMETER, "studySiteSection");
		request.setAttribute(AJAX_REQUEST_PARAMETER, "AJAX");
		String url = getCurrentPageContextRelative(WebContextFactory.get());
		return getOutputFromJsp(url);
	}

	public boolean deleteStudySite(int index) {
		Study study = getStudyCommand(getHttpServletRequest());
		return study.getStudySites().remove(index) != null;
	}

	public String addIdentifier(final int index, final int type) {
		HttpServletRequest request = getHttpServletRequest();
		Study study = getStudyCommand(request);

		if (type == 1) {
			study.getIdentifiersLazy().add(new SystemAssignedIdentifier());
		}
		else if (type == 2) {
			study.getIdentifiersLazy().add(new OrganizationAssignedIdentifier());
		}

		request.setAttribute(AJAX_INDEX_PARAMETER, index);
		request.setAttribute(AJAX_SUBVIEW_PARAMETER, "studyIdentifierSection");
		request.setAttribute(AJAX_REQUEST_PARAMETER, "AJAX");
		String url = getCurrentPageContextRelative(WebContextFactory.get());
		return getOutputFromJsp(url);

	}

	public boolean deleteIdentifier(int index) {
		Study study = getStudyCommand(getHttpServletRequest());
		return study.getIdentifiersLazy().remove(index) != null;
	}

	public String addStudyAgent(int index) {
		HttpServletRequest request = getHttpServletRequest();
		Study study = getStudyCommand(request);
		// pre-initialize the agent at index
		study.getStudyAgents().get(index);
		request.setAttribute(AJAX_INDEX_PARAMETER, index);
		request.setAttribute(AJAX_SUBVIEW_PARAMETER, "studyAgentSection");
		request.setAttribute(AJAX_REQUEST_PARAMETER, "AJAX");
		String url = getCurrentPageContextRelative(WebContextFactory.get());
		return getOutputFromJsp(url);
	}

	public boolean deleteStudyAgent(int index) {
		Study study = getStudyCommand(getHttpServletRequest());
		return study.getStudyAgents().remove(index) != null;
	}

	/**
	 * A row of IND is needed to display
	 */
	public String addIND(int index, int indIndex) {
		HttpServletRequest request = getHttpServletRequest();

		Study study = getStudyCommand(request);
		// pre-initialize the StudyAgentINDAssociation object at indIndex.
		StudyAgentINDAssociation sia = study.getStudyAgents().get(index).getStudyAgentINDAssociations().get(indIndex);
		AgentsTab agentTab = new AgentsTab();
		Map<String, InputFieldGroup> fieldGrpMap = agentTab.createFieldGroups(study);
		request.setAttribute("fieldGroups", fieldGrpMap);
		// request.setAttribute(AbstractFormController.DEFAULT_COMMAND_NAME, study);
		request.setAttribute(AJAX_INDEX_PARAMETER, index);
		request.setAttribute("indIndex", indIndex);
		// request.setAttribute(AJAX_SUBVIEW_PARAMETER, "studyAgentINDSection");
		// request.setAttribute(AJAX_REQUEST_PARAMETER, "AJAX");
		String url = "/pages/study/studyAgentIND";// getCurrentPageContextRelative(WebContextFactory.get());
		return getOutputFromJsp(url);
	}

	// using existing list editor
	public String addChooseStudySite(int index, int siteIndex, String context) {
		HttpServletRequest request = getHttpServletRequest();
		Study study = getStudyCommand(request);
		study.setStudySiteIndex(siteIndex);
		if (siteIndex < 0) {
			return " "; // a space, incase of a reset of the select box.
		}
		String subView = "";
		if (StringUtils.equals("Personnel", context)) {
			subView = "studySitePersonnelsSection";
			study.getStudySites().get(siteIndex).getStudyPersonnels().get(0); // make sure one item is there
		}
		else {
			subView = "studySiteInvestigatorsSection";
			study.getStudySites().get(siteIndex).getStudyInvestigators().get(0); // make sure on entry is there.
		}
		request.setAttribute(AJAX_INDEX_PARAMETER, siteIndex);
		request.setAttribute(AJAX_SUBVIEW_PARAMETER, subView);
		request.setAttribute(AJAX_REQUEST_PARAMETER, "AJAX");

		String url = getCurrentPageContextRelative(WebContextFactory.get());
		return getOutputFromJsp(url);
	}

	public String addInvestigator(int index) {
		HttpServletRequest request = getHttpServletRequest();
		getStudyCommand(request);
		request.setAttribute(AJAX_INDEX_PARAMETER, index);
		request.setAttribute(AJAX_SUBVIEW_PARAMETER, "studySiteInvestigatorSection");
		request.setAttribute(AJAX_REQUEST_PARAMETER, "AJAX");
		String url = getCurrentPageContextRelative(WebContextFactory.get());
		return getOutputFromJsp(url);
	}

	public boolean deleteInvestigator(int index) {
		Study study = getStudyCommand(getHttpServletRequest());
		return study.getStudySites().get(study.getStudySiteIndex()).getStudyInvestigators().remove(index) != null;
	}

	public String addStudyPersonnel(int index) {
		HttpServletRequest request = getHttpServletRequest();
		getStudyCommand(request);
		request.setAttribute(AJAX_INDEX_PARAMETER, index);
		request.setAttribute(AJAX_SUBVIEW_PARAMETER, "studySitePersonnelSection");
		request.setAttribute(AJAX_REQUEST_PARAMETER, "AJAX");
		String url = getCurrentPageContextRelative(WebContextFactory.get());
		return getOutputFromJsp(url);
	}

	public boolean deleteStudyPersonnel(int index) {
		Study study = getStudyCommand(getHttpServletRequest());
		return study.getStudySites().get(study.getStudySiteIndex()).getStudyPersonnels().remove(index) != null;
	}

	private String getOutputFromJsp(String jspResource) {
		String html = "Error in rendering...";
		try {
			html = WebContextFactory.get().forwardToString(jspResource);
		}
		catch (ServletException e) {
			throw new CaaersSystemException(e.getMessage(), e);
		}
		catch (IOException e) {
			throw new CaaersSystemException(e.getMessage(), e);
		}
		return html;
	}

	private String getCurrentPageContextRelative(WebContext webContext) {
		String contextPath = webContext.getHttpServletRequest().getContextPath();
		String page = webContext.getCurrentPage();
		if (contextPath == null) {
			log.debug("context path not set");
			return page;
		}
		else if (!page.startsWith(contextPath)) {
			log.debug(page + " does not start with context path " + contextPath);
			return page;
		}
		else {
			return page.substring(contextPath.length());
		}
	}

	private HttpServletRequest getHttpServletRequest() {
		return WebContextFactory.get().getHttpServletRequest();
	}

	// //// CONFIGURATION

	@Required
	public AgentDao getAgentDao() {
		return agentDao;
	}

	@Required
	public void setAgentDao(AgentDao agentDao) {
		this.agentDao = agentDao;
	}

	@Required
	public DiseaseCategoryDao getDiseaseCategoryDao() {
		return diseaseCategoryDao;
	}

	@Required
	public void setDiseaseCategoryDao(DiseaseCategoryDao diseaseCategoryDao) {
		this.diseaseCategoryDao = diseaseCategoryDao;
	}

	@Required
	public DiseaseTermDao getDiseaseTermDao() {
		return diseaseTermDao;
	}

	@Required
	public void setDiseaseTermDao(DiseaseTermDao diseaseTermDao) {
		this.diseaseTermDao = diseaseTermDao;
	}

	public ResearchStaffDao getResearchStaffDao() {
		return researchStaffDao;
	}

	public void setResearchStaffDao(ResearchStaffDao researchStaffDao) {
		this.researchStaffDao = researchStaffDao;
	}

	public OrganizationDao getOrganizationDao() {
		return organizationDao;
	}

	public void setOrganizationDao(OrganizationDao organizationDao) {
		this.organizationDao = organizationDao;
	}

	public SiteInvestigatorDao getSiteInvestigatorDao() {
		return siteInvestigatorDao;
	}

	public void setSiteInvestigatorDao(SiteInvestigatorDao siteInvestigatorDao) {
		this.siteInvestigatorDao = siteInvestigatorDao;
	}

	/**
	 * @return the investigationalNewDrugDao
	 */
	public InvestigationalNewDrugDao getInvestigationalNewDrugDao() {
		return investigationalNewDrugDao;
	}

	/**
	 * @param investigationalNewDrugDao the investigationalNewDrugDao to set
	 */
	public void setInvestigationalNewDrugDao(InvestigationalNewDrugDao investigationalNewDrugDao) {
		this.investigationalNewDrugDao = investigationalNewDrugDao;
	}

}
