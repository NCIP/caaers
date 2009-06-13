package gov.nih.nci.cabig.caaers.web.search;

import java.io.InputStream;
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
import javax.xml.bind.Unmarshaller;

import gov.nih.nci.cabig.caaers.dao.CaaersDao;
import gov.nih.nci.cabig.caaers.dao.ParticipantDao;
import gov.nih.nci.cabig.caaers.dao.query.HQLQuery;
import gov.nih.nci.cabig.caaers.domain.Participant;
import gov.nih.nci.cabig.caaers.web.ControllerTools;
import gov.nih.nci.cabig.caaers.web.ae.AbstractAdverseEventInputController;
import gov.nih.nci.cabig.caaers.web.ae.ListAdverseEventsCommand;
import gov.nih.nci.cabig.caaers.web.rule.author.CreateRuleCommand;
//import gov.nih.nci.cabig.caaers.web.search.ui.CriteriaObject;
//import gov.nih.nci.cabig.caaers.web.search.ui.SearchTargetObject;
import gov.nih.nci.cabig.caaers.web.search.ui.AdvancedSearchUi;
import gov.nih.nci.cabig.caaers.web.search.ui.DependentObject;
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
	
	private ParticipantDao participantDao;
	
	public AdvancedSearchController() {
        setCommandClass(AdvancedSearchCommand.class);
        setSessionForm(true);
        setFormView("search/advancedSearch");
        setSuccessView("search/advancedSearchResults");
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
		request.getSession(true).setAttribute(AdvancedSearchController.class.getName() + ".FORM.command", command);
        return new AdvancedSearchCommand();
    }
	
	@Override
    @SuppressWarnings("unchecked")
    protected boolean isFormSubmission(HttpServletRequest request) {
		String finishAttribute = (String) findInRequest(request, "_finish");
		if(finishAttribute != null && !finishAttribute.equals(""))
			return true;
		else
			return false;
	}
	@SuppressWarnings("unused")
	protected ModelAndView onSubmit(HttpServletRequest request, HttpServletResponse response, Object cmd, BindException errors) throws Exception {
		
		AdvancedSearchCommand command = (AdvancedSearchCommand) cmd;
		Map map = new LinkedHashMap();
		
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
		
		//for(ViewColumn viewColumn: command.getSearchTargetObject().getViewColumn()){
		//	ViewColumn
		//}
		
		map.put("rowList", rowList);
		map.put("hql", query);
		//map.put("results", participantList);
		
        map.putAll(errors.getModel());
        ModelAndView modelAndView = new ModelAndView("search/advancedSearchResults", map);

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
			if(ajaxAction == null)
				return super.handleRequestInternal(request, response);
			else{
				Object command = getCommand(request);
				ServletRequestDataBinder binder = bindAndValidate(request, command);
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
        InputStream inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("advancedSearch-ui.xml");
        AdvancedSearchUi advancedSearchUi = null;
		Unmarshaller unmarshaller;
		try {
			unmarshaller = JAXBContext.newInstance("gov.nih.nci.cabig.caaers.web.search.ui").createUnmarshaller();
			advancedSearchUi = (AdvancedSearchUi) unmarshaller.unmarshal(inputStream);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
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
			refdata.put("index", Integer.parseInt(indexString));
		}
        
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
}