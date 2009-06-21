package gov.nih.nci.cabig.caaers.web.search;

import java.io.InputStream;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import gov.nih.nci.cabig.caaers.dao.CaaersDao;
import gov.nih.nci.cabig.caaers.dao.ParticipantDao;
import gov.nih.nci.cabig.caaers.dao.SearchDao;
import gov.nih.nci.cabig.caaers.dao.query.HQLQuery;
import gov.nih.nci.cabig.caaers.domain.Participant;
import gov.nih.nci.cabig.caaers.domain.Search;
import gov.nih.nci.cabig.caaers.security.SecurityUtils;
import gov.nih.nci.cabig.caaers.web.ControllerTools;
import gov.nih.nci.cabig.caaers.web.ae.AbstractAdverseEventInputController;
import gov.nih.nci.cabig.caaers.web.ae.ListAdverseEventsCommand;
import gov.nih.nci.cabig.caaers.web.rule.author.CreateRuleCommand;
//import gov.nih.nci.cabig.caaers.web.search.ui.CriteriaObject;
//import gov.nih.nci.cabig.caaers.web.search.ui.SearchTargetObject;
import gov.nih.nci.cabig.caaers.web.search.ui.AdvancedSearchUi;
import gov.nih.nci.cabig.caaers.web.search.ui.CriteriaParameter;
import gov.nih.nci.cabig.caaers.web.search.ui.DependentObject;
import gov.nih.nci.cabig.caaers.web.search.ui.SaveSearch;
import gov.nih.nci.cabig.caaers.web.search.ui.SearchTargetObject;
import gov.nih.nci.cabig.caaers.web.search.ui.UiAttribute;
import gov.nih.nci.cabig.caaers.web.search.ui.ViewColumn;
import gov.nih.nci.cabig.caaers.web.study.SearchStudyCommand;
import gov.nih.nci.cabig.ctms.domain.DomainObject;
import gov.nih.nci.cagrid.cqlquery.CQLQuery;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.validation.BindException;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.validation.Errors;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;

import com.semanticbits.core.CQL2HQL;

public class AdvancedSearchController extends SimpleFormController{
	
	private static final Log log = LogFactory.getLog(AdvancedSearchController.class);
	public static final String AJAX_SUBVIEW_PARAMETER = "subview";
	public static final String AJAX_ACTION_PARAMETER = "ajax_action";
	private SearchDao searchDao;
	private AdvancedSearchUi advancedSearchUi;
	
	private ParticipantDao participantDao;
	
	public AdvancedSearchController() {
        setCommandClass(AdvancedSearchCommand.class);
        setSessionForm(true);
        setFormView("search/advancedSearch");
        setSuccessView("search/advancedSearchResults");
        
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
	
	@Override
    protected void initBinder(final HttpServletRequest request, final ServletRequestDataBinder binder) throws Exception {
        super.initBinder(request, binder);
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
        binder.registerCustomEditor(Date.class, ControllerTools.getDateEditor(false));
    }
	
	@Override
    protected Object formBackingObject(HttpServletRequest request) {
		AdvancedSearchCommand command = new AdvancedSearchCommand();
		
		String modifyCriteria = request.getParameter("modifyCriteria");
		String searchName = request.getParameter("searchName");
		if(modifyCriteria != null){
			// This is when the user clicks "modifyCriteria" link on the results page.
			SearchTargetObject stObject = (SearchTargetObject) request.getSession().getAttribute("searchTargetObject");
			List<AdvancedSearchCriteriaParameter> criteriaParameters = (List<AdvancedSearchCriteriaParameter>) request.getSession().getAttribute("criteriaParameters");
			command.setSearchTargetObject(stObject);
			command.setCriteriaParameters(criteriaParameters);
		}
		if(searchName != null){
			// This is when the user clicks on one of the saved searches.
			String loginId = SecurityUtils.getUserLoginName();
			List<Search> searchList = searchDao.getByLoginAndName(loginId, searchName);
			Unmarshaller unmarshaller;
			SaveSearch savedSearch = null;
			try {
				unmarshaller = JAXBContext.newInstance("gov.nih.nci.cabig.caaers.web.search.ui").createUnmarshaller();
				//advancedSearchUi = (AdvancedSearchUi) unmarshaller.unmarshal(inputStream);
				StringReader reader = new StringReader(searchList.get(0).getCriteriaXml()); 
				savedSearch = (SaveSearch) unmarshaller.unmarshal(reader);
				for(SearchTargetObject targetObject: advancedSearchUi.getSearchTargetObject()){
					if(targetObject.getClassName().equals(savedSearch.getTargetClassName()))
						command.setSearchTargetObject(targetObject);
				}
				for(CriteriaParameter criteriaParameter: savedSearch.getCriteriaParameter()){
					AdvancedSearchCriteriaParameter parameter = new AdvancedSearchCriteriaParameter();
					parameter.setObjectName(criteriaParameter.getObjectName());
					parameter.setAttributeName(criteriaParameter.getAttributeName());
					parameter.setPredicate(criteriaParameter.getPredicate());
					parameter.setValue(criteriaParameter.getValue());
					command.getCriteriaParameters().add(parameter);
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		request.getSession(true).setAttribute(AdvancedSearchController.class.getName() + ".FORM.command", command);
        return command;
    }
	
	@Override
    @SuppressWarnings("unchecked")
    protected boolean isFormSubmission(HttpServletRequest request) {
		String actionAttribute = (String) findInRequest(request, "_action");
		if(actionAttribute != null)
			return true;
		else
			return false;
	}
	
	protected boolean isSaveSearchSubmission(HttpServletRequest request){
		String actionAttribute = (String) findInRequest(request, "_action");
		if(actionAttribute != null && actionAttribute.equals("saveSearch"))
			return true;
		else
			return false;
	}
	
	@SuppressWarnings("unused")
	protected ModelAndView onSubmit(HttpServletRequest request, HttpServletResponse response, Object cmd, BindException errors) throws Exception {
		
		AdvancedSearchCommand command = (AdvancedSearchCommand) cmd;
		Map map = new LinkedHashMap();
		ModelAndView modelAndView = null;
		List<AdvancedSearchCriteriaParameter> parameters = new ArrayList<AdvancedSearchCriteriaParameter>();
		for(AdvancedSearchCriteriaParameter p: command.getCriteriaParameters()){
			if(p.getAttributeName()!= null && !p.getAttributeName().equals("") && !p.getAttributeName().equals("none") && !p.isDeleted())
					parameters.add(p);
			}
			CQLQuery cql = UICQL2CQL.transform(parameters, command.getSearchTargetObject());
			String query = CQL2HQL.translate(cql, false, true);
			System.out.println("query = " + query);
		
			//List<Participant> participantList = (List<Participant>)participantDao.search(new HQLQuery(query));
			List<DomainObject> objectList = (List<DomainObject>) participantDao.search(new HQLQuery(query)); 
			//System.out.println("Number of participants  = " + participantList.size());
			map.put("numberOfResults", objectList.size());
			//map.put("viewColumnDetails", command.getSearchTargetObject().getViewColumn());
			SearchResultRowListDTO rowList = new SearchResultRowListDTO();
			//for(Participant participant: participantList){
			for(Object object: objectList){
				SearchResultRowDTO row = new SearchResultRowDTO();
				SearchResultColumnDTO column = null;
				BeanWrapper wrapper = new BeanWrapperImpl(object);
				for(ViewColumn viewColumn: command.getSearchTargetObject().getViewColumn()){
					column = new SearchResultColumnDTO();
					column.setColumnHeader(viewColumn.getColumnTitle());
					column.setValue(wrapper.getPropertyValue(viewColumn.getColumnAttribute()));
					row.getColumnListDTO().getColumnDTOList().add(column);
				}
				rowList.getRowListDTO().add(row);
			}
		
			map.put("rowList", rowList);
			map.put("hql", query);
			map.putAll(errors.getModel());
			modelAndView = new ModelAndView("search/advancedSearchResults", map);
        
			// Put searchTargetObject and criteriaParameters in session to create the form when the user clicks "modify criteria" on the 
			// results page.
			request.getSession().setAttribute("searchTargetObject", command.getSearchTargetObject());
			request.getSession().setAttribute("criteriaParameters", command.getCriteriaParameters());
		if(isSaveSearchSubmission(request)){
				String loginId = SecurityUtils.getUserLoginName();
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
				search.setDescription((String) findInRequest(request,"searchDescription"));
				search.setLoginId(loginId);
				search.setName((String) findInRequest(request, "searchName"));
				
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
		}
		return modelAndView;
	}
		
	
	@Override
	@SuppressWarnings("unchecked")
	protected ModelAndView showForm(HttpServletRequest request, HttpServletResponse response, BindException errors) throws Exception{
		
		ModelAndView mv = super.showForm(request, response, errors);
		
		String ajaxAction = (String) findInRequest(request, AJAX_ACTION_PARAMETER);
		if(ajaxAction != null){
			String ajaxSubview = (String)findInRequest(request, AJAX_SUBVIEW_PARAMETER);
			mv.setViewName("search/ajax/" + ajaxSubview);
		}
		return mv;
	}
	
	protected ModelAndView handleRequestInternal(HttpServletRequest request, HttpServletResponse response)
		throws Exception {
		
		// Form submission or new form to show?
		if (isFormSubmission(request)) {
			return super.handleRequestInternal(request, response);
		}else{
			String ajaxAction = (String) findInRequest(request, AJAX_ACTION_PARAMETER);
			String action = request.getParameter("_action");
			if(ajaxAction == null)
				return super.handleRequestInternal(request, response);
			else{
				Object cmd = getCommand(request);
				ServletRequestDataBinder binder = bindAndValidate(request, cmd);
				BindException errors = new BindException(binder.getBindingResult());
				return showForm(request, response, errors);
			}
		}
	}
	
	@Override
    @SuppressWarnings("unchecked")
    protected Map referenceData(HttpServletRequest request, Object cmd, Errors errors) throws Exception {
		AdvancedSearchCommand command = (AdvancedSearchCommand) cmd;
        Map<String, Object> refdata = new HashMap<String, Object>();
        
		String ajaxAction = (String) findInRequest(request, AJAX_ACTION_PARAMETER);
		
		// This is for adding a new criteria
		if(ajaxAction != null && ajaxAction.equals("addNewCriteria")){
			String dependentObjectDisplayName = (String) findInRequest(request, "dependentObjectDisplayName");
			DependentObject dependentObject = AdvancedSearchUiUtil.getDependentObjectByDisplayName(command.getSearchTargetObject(), dependentObjectDisplayName);
			refdata.put("dependentObject", dependentObject);
		}
		
		// This is for updating the attribute select element
		if(ajaxAction != null && ajaxAction.equals("updateAttribute")){
			String indexString = (String) findInRequest(request, "index");
			String attributeName = (String) findInRequest(request, "attributeName");
			Integer index = Integer.parseInt(indexString);
			refdata.put("index", index);
			
			//Put the uiAttribute in refdata. Its needed to provide the metadata to the renderValueColumn.tag
			for(DependentObject dObject: command.getSearchTargetObject().getDependentObject()){
				if(dObject.getClassName().equals(command.getCriteriaParameters().get(index).getObjectName())){
					for(UiAttribute uiAttribute: dObject.getUiAttribute())
						if(uiAttribute.getName().equals(attributeName))
							refdata.put("uiAttribute", uiAttribute);
				}
			}
		}
		
		// Collect the list of saved searches for this user and put it in the refdata.
		String loginId = SecurityUtils.getUserLoginName();
		List<Search> searchList = new ArrayList<Search>();
		List<Search> shortSearchList = new ArrayList<Search>();
		searchList = searchDao.getByLogin(loginId);
		int i = 0;
		for(Search s: searchList){
			if(i++ > 4) break;
			shortSearchList.add(s);
		}
		refdata.put("savedSearchList", searchList);
		refdata.put("shortSearchList", shortSearchList);
        
		refdata.put("advancedSearchUi", advancedSearchUi);
		request.getSession(true).setAttribute(AdvancedSearchController.class.getName() + ".FORM.command", command);
        return refdata;
	}
	
	/**
     * Returns the value associated with the <code>attributeName</code>, if present in
     * HttpRequest parameter, if not available, will check in HttpRequest attribute map.
     */
    protected Object findInRequest(final ServletRequest request, final String attributName) {

        Object attr = request.getParameter(attributName);
        if (attr == null) {
            attr = request.getAttribute(attributName);
        }
        return attr;
    }
    
    public void setParticipantDao(ParticipantDao participantDao){
    	this.participantDao = participantDao;
    }
    
    public ParticipantDao getParticipantDao(){
    	return participantDao;
    }
    
    public void setSearchDao(SearchDao searchDao){
    	this.searchDao = searchDao;
    }
}