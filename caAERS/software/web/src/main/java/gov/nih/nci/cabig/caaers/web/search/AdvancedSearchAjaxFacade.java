package gov.nih.nci.cabig.caaers.web.search;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.acegisecurity.context.SecurityContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.directwebremoting.WebContext;
import org.directwebremoting.WebContextFactory;
import org.springframework.beans.factory.annotation.Required;
import org.xml.sax.ContentHandler;

import gov.nih.nci.cabig.caaers.CaaersSystemException;
import gov.nih.nci.cabig.caaers.dao.CtcTermDao;
import gov.nih.nci.cabig.caaers.dao.SearchDao;
import gov.nih.nci.cabig.caaers.dao.query.ajax.ParticipantAjaxableDomainObjectQuery;
import gov.nih.nci.cabig.caaers.dao.query.ajax.StudySearchableAjaxableDomainObjectQuery;
import gov.nih.nci.cabig.caaers.domain.CtcTerm;
import gov.nih.nci.cabig.caaers.domain.Search;
import gov.nih.nci.cabig.caaers.domain.ajax.ParticipantAjaxableDomainObject;
import gov.nih.nci.cabig.caaers.domain.ajax.StudyAjaxableDomainObject;
import gov.nih.nci.cabig.caaers.domain.repository.ajax.ParticipantAjaxableDomainObjectRepository;
import gov.nih.nci.cabig.caaers.domain.repository.ajax.StudySearchableAjaxableDomainObjectRepository;
import gov.nih.nci.cabig.caaers.security.SecurityUtils;
import gov.nih.nci.cabig.caaers.web.dwr.AjaxOutput;
import gov.nih.nci.cabig.caaers.web.search.ui.AdvancedSearchUi;
import gov.nih.nci.cabig.caaers.web.search.ui.CriteriaParameter;
import gov.nih.nci.cabig.caaers.web.search.ui.DependentObject;
import gov.nih.nci.cabig.caaers.web.search.ui.SaveSearch;
import gov.nih.nci.cabig.caaers.web.search.ui.SearchTargetObject;
import gov.nih.nci.cabig.caaers.web.search.ui.UiAttribute;
import gov.nih.nci.cabig.caaers.web.search.ui.ViewColumn;

public class AdvancedSearchAjaxFacade{
	
	private static Class<?>[] CONTROLLERS = { AdvancedSearchController.class   };
	
	private static final Log log = LogFactory.getLog(AdvancedSearchAjaxFacade.class);
	
	private static AdvancedSearchUi advancedSearchUi;
	
	private StudySearchableAjaxableDomainObjectRepository studySearchableAjaxableDomainObjectRepository;
	
	private ParticipantAjaxableDomainObjectRepository participantAjaxableDomainObjectRepository;
	
	private SearchDao searchDao;
	
	private CtcTermDao ctcTermDao;
	
	static{
		InputStream inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("advancedSearch-ui.xml");

		Unmarshaller unmarshaller;
		try {
			unmarshaller = JAXBContext.newInstance("gov.nih.nci.cabig.caaers.web.search.ui").createUnmarshaller();
			advancedSearchUi = (AdvancedSearchUi) unmarshaller.unmarshal(inputStream);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
	/**
	 * This method is called when the search target object is modified. Following tasks are done on this update-
	 *  a. The dependent objects are identified and one default criteria is added for each of them 
	 *     to the criteriaParameters list.
	 *  b. The attributes for each of these dependent objects are known through the advancedSearchUi object and hence the dropdown
	 *     for the attributes is populated.
	 *  c. The html is created using criteriaDetailsSection.jsp
	 */
	public AjaxOutput updateSearchTargetObject(String targetObjectClassName){
		AjaxOutput ajaxOutput = new AjaxOutput();
		AdvancedSearchCommand command = (AdvancedSearchCommand) extractCommand();
		
		// This is when the user selects "please select" in the searchTargetObject. The searchtargetObject is set to null in the command.
		if(targetObjectClassName.equals("")){
			command.setSearchTargetObject(null);
			command.setCriteriaParameters(null);
			ajaxOutput.setHtmlContent("");
			return ajaxOutput;
		}
		
		SearchTargetObject targetObject = AdvancedSearchUiUtil.getSearchTargetObjectByName(advancedSearchUi, targetObjectClassName);
		command.setSearchTargetObject(targetObject);
		
		// Initialize the criteriaParameters with null.
		command.setCriteriaParameters(null);
		// For each dependent object in the targetObject add a default criteriaParameter to the criteriaParameters list
		for(int i = 0; i < targetObject.getDependentObject().size(); i++){
			AdvancedSearchCriteriaParameter criteriaParameter = new AdvancedSearchCriteriaParameter();
			criteriaParameter.setDeleted(false);
			criteriaParameter.setObjectName(targetObject.getDependentObject().get(i).getClassName());
			command.getCriteriaParameters().add(criteriaParameter);
		}
		
		Map<String, String> params = new LinkedHashMap<String, String>();
		params.put("ajax_action", "updateSearchTargetObject");
		ajaxOutput.setHtmlContent(renderAjaxView("criteriaDetailsSection", params));
		
		return ajaxOutput;
	}
	
	/**
	 * This method is called when an attribute is updated. Here we need to pass the possible operators in a list within the ajaxOutput
	 * value returned. While the html of the value column is returned through ajaxOutput's htmlContent.
	 */
	public AjaxOutput updateAttribute(String attributeName, Integer index){
		AjaxOutput ajaxOutput = new AjaxOutput();
		AdvancedSearchCommand command = (AdvancedSearchCommand) extractCommand();
		
		String dependentObjectName = command.getCriteriaParameters().get(index).getObjectName();
		DependentObject dObject = AdvancedSearchUiUtil.getDependentObjectByName(command.getSearchTargetObject(), dependentObjectName);
		for(UiAttribute uiAttribute: dObject.getUiAttribute()){
			if(uiAttribute.getName().equals(attributeName))
				ajaxOutput.setObjectContent(uiAttribute.getOperator());
		}

		Map<String, String> params = new LinkedHashMap<String, String>();
		params.put("ajax_action", "updateAttribute");
		params.put("index", index.toString());
		params.put("attributeName", attributeName);
		ajaxOutput.setHtmlContent(renderAjaxView("updateValueContent", params));
		
		return ajaxOutput;
	}
	
	/**
	 * This method is called to delete a criteria. The deleted flag in advancedSearchCriteriaParameter object is set to true.
	 */
	public AjaxOutput deleteCriteria(Integer index){
		AdvancedSearchCommand command = (AdvancedSearchCommand) extractCommand();
		command.getCriteriaParameters().get(index).setDeleted(true);
		return null;
	}
	
	/**
	 * This method is called to add a new criteria to a dependent object. The class name of the dependent object is passed as a
	 * parameter to this method.
	 * @param dependentObjectClassName
	 * @return
	 */
	public AjaxOutput addCriteria(String dependentObjectDisplayName){
		AjaxOutput ajaxOutput = new AjaxOutput();
		AdvancedSearchCommand command = (AdvancedSearchCommand) extractCommand();
		DependentObject dObject = AdvancedSearchUiUtil.getDependentObjectByDisplayName(command.getSearchTargetObject(), dependentObjectDisplayName);
		AdvancedSearchCriteriaParameter parameter = new AdvancedSearchCriteriaParameter();
		parameter.setDeleted(false);
		parameter.setObjectName(dObject.getClassName());
		command.getCriteriaParameters().add(parameter);
		
		Map<String, String> params = new LinkedHashMap<String, String>();
		params.put("ajax_action", "addNewCriteria");
		params.put("dependentObjectDisplayName", dObject.getDisplayName());
		ajaxOutput.setHtmlContent(renderAjaxView("addNewCriteria", params));
		
		return ajaxOutput;
	}
	
	public AjaxOutput getRowList(){
		AjaxOutput ajaxOutput = new AjaxOutput();
		AdvancedSearchCommand command = (AdvancedSearchCommand) extractCommand();
		ajaxOutput.setObjectContent(command.getRowList());
		return ajaxOutput;
	}
	
	/**
	 * This method is used to delete the search. The parameter passed to the method is the searchName of the search to be deleted.
	 */
	public AjaxOutput deleteSearch(String searchName){
		AjaxOutput ajaxOutput = new AjaxOutput();
		String loginId = getUserId();
		searchDao.deleteByLoginIdAndName(searchName, loginId);
		
		Map<String, String> params = new LinkedHashMap<String, String>();
		params.put("ajax_action", "deleteSearch");
		ajaxOutput.setHtmlContent(renderAjaxView("savedSearchList", params));
		
		return ajaxOutput;
	}
	
	/*
	 * TODO: Delete this method saveSearch as its being done in the controller.
	 */
	/**
	 * This method is used to save the search. The parameters passed to the method are the searchName and the searchDescription
	 * entered by the user.
	 * @return
	 */
	public AjaxOutput saveSearch(String searchName, String searchDescription){
		AjaxOutput ajaxOutput = new AjaxOutput();
		AdvancedSearchCommand command = (AdvancedSearchCommand) extractCommand();
		String loginId = getUserId();
		// We will now create the criteriaXml attribute of the Search class.
		SaveSearch saveSearch = new SaveSearch();
		saveSearch.setTargetClassName(command.getSearchTargetObject().getClassName());
		List<CriteriaParameter> criteriaParameterList = new ArrayList<CriteriaParameter>();
		CriteriaParameter criteriaParameter = null;
		for(AdvancedSearchCriteriaParameter parameter: command.getCriteriaParameters()){
			criteriaParameter = new CriteriaParameter();
			criteriaParameter.setObjectName(parameter.getObjectName());
			criteriaParameter.setAttributeName(parameter.getAttributeName());
			criteriaParameter.setPredicate(parameter.getPredicate());
			criteriaParameter.setValue(parameter.getValue());
			criteriaParameterList.add(criteriaParameter);
		}
		saveSearch.setCriteriaParameter(criteriaParameterList);
		Search search = new Search();
		search.setCreatedDate(new Date());
		search.setDescription(searchDescription);
		search.setLoginId(loginId);
		search.setName(searchName);
		
		// Marshall the saveSearch object into an xml string.
		Marshaller marshaller;
		try {
			marshaller = JAXBContext.newInstance("gov.nih.nci.cabig.caaers.web.search.ui").createMarshaller();
			StringWriter writer = new StringWriter();
			marshaller.marshal(saveSearch, writer);
			search.setCriteriaXml(writer.toString());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		searchDao.save(search);
		Map<String, String> params = new LinkedHashMap<String, String>();
		
		return ajaxOutput;
	}
	
	public List<ViewColumn> getViewColumnsForDependentObject(String dependentObjectDisplayName){
		AdvancedSearchCommand command = (AdvancedSearchCommand) extractCommand();
		DependentObject dObject = AdvancedSearchUiUtil.getDependentObjectByDisplayName(command.getSearchTargetObject(), dependentObjectDisplayName);
		return dObject.getViewColumn();
	}
	
	public List<StudyAjaxableDomainObject> matchStudies(String text) {

        StudySearchableAjaxableDomainObjectQuery domainObjectQuery = new StudySearchableAjaxableDomainObjectQuery();
        domainObjectQuery.filterStudiesWithMatchingText(text);
        domainObjectQuery.filterByDataEntryStatus(true);
        List<StudyAjaxableDomainObject> studies = studySearchableAjaxableDomainObjectRepository.findStudies(domainObjectQuery);
        return studies;
    }
	
	public List<CtcTerm> matchTerms(String text){
		List<CtcTerm> terms = ctcTermDao.getBySubname(extractSubnames(text), null, null);
        // cut down objects for serialization
        for (CtcTerm term : terms) {
            term.getCategory().setTerms(null);
            term.getCategory().getCtc().setCategories(null);
        }
        while (terms.size() > 20) {
            terms.remove(terms.size() - 1);
        }
        return terms;
	}
	
	private String[] extractSubnames(String text) {
        return text.split("\\s+");
    }
	
	public List<ParticipantAjaxableDomainObject> matchParticipants(String text) {

        ParticipantAjaxableDomainObjectQuery query = new ParticipantAjaxableDomainObjectQuery();
        query.filterParticipantsWithMatchingText(text);

        List<ParticipantAjaxableDomainObject> participantAjaxableDomainObjects = participantAjaxableDomainObjectRepository.findParticipants(query);
        return participantAjaxableDomainObjects;
    }

	
	public Class<?>[] controllers() {
		return CONTROLLERS;
	}
	
	protected String renderAjaxView(String viewName, Map<String, String> params) {
        WebContext webContext = WebContextFactory.get();

        params.put(AdvancedSearchController.AJAX_SUBVIEW_PARAMETER, viewName);

        String url = String.format("%s?%s", getCurrentPageContextRelative(webContext), createQueryString(params));
        log.debug("Attempting to return contents of " + url);
        try {
            String html = webContext.forwardToString(url);
            if (log.isDebugEnabled()) log.debug("Retrieved HTML:\n" + html);
            return html;
        } catch (ServletException e) {
            throw new CaaersSystemException(e);
        } catch (IOException e) {
            throw new CaaersSystemException(e);
        }
    }
	
	private String getCurrentPageContextRelative(WebContext webContext) {
        String contextPath = webContext.getHttpServletRequest().getContextPath();
        String page = webContext.getCurrentPage();
        if (contextPath == null) {
            log.debug("context path not set");
            return page;
        } else if (!page.startsWith(contextPath)) {
            log.debug(page + " does not start with context path " + contextPath);
            return page;
        } else {
            return page.substring(contextPath.length());
        }
    }
	
	protected String createQueryString(Map<String, String> params) {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, String> entry : params.entrySet()) {
            sb.append(entry.getKey()).append('=').append(entry.getValue())
                    .append('&');
        }
        return sb.toString().substring(0, sb.length() - 1);
    }
	
	protected String getUserId(){
		WebContext webContext = getWebContext();
		SecurityContext context = (SecurityContext)webContext.getHttpServletRequest().getSession().getAttribute("ACEGI_SECURITY_CONTEXT");
		return SecurityUtils.getUserLoginName(context.getAuthentication());
	}
	
	protected Object extractCommand() {
        WebContext webContext = getWebContext();
        Object command = null;
        for (Class<?> controllerClass : controllers()) {
            String formSessionAttributeName = controllerClass.getName() + ".FORM.command";
            command = webContext.getSession().getAttribute(formSessionAttributeName);
            if (command == null) {
                log.debug("Command not found using name " + formSessionAttributeName);
            } else {
                log.debug("Command found using name " + formSessionAttributeName);
                break;
            }
        }
        if (command == null) {
            throw new CaaersSystemException("Could not find command in session");
        } else {
            return command;
        }
    }
	
	protected WebContext getWebContext(){
    	return WebContextFactory.get();
    }
	
	public void setSearchDao(SearchDao searchDao){
		this.searchDao = searchDao;
	}
	
	@Required
    public void setStudySearchableAjaxableDomainObjectRepository(StudySearchableAjaxableDomainObjectRepository studyAjaxableDomainObjectRepository) {
        this.studySearchableAjaxableDomainObjectRepository = studyAjaxableDomainObjectRepository;
    }

    @Required
    public void setParticipantAjaxableDomainObjectRepository(ParticipantAjaxableDomainObjectRepository participantAjaxableDomainObjectRepository) {
        this.participantAjaxableDomainObjectRepository = participantAjaxableDomainObjectRepository;
    }

	/**
	 * @param ctcTermDao the ctcTermDao to set
	 */
	public void setCtcTermDao(CtcTermDao ctcTermDao) {
		this.ctcTermDao = ctcTermDao;
	}
}