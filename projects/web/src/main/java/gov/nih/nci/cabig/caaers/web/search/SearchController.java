package gov.nih.nci.cabig.caaers.web.search;

import gov.nih.nci.cabig.caaers.service.StudyService;
import gov.nih.nci.cabig.caaers.dao.AdverseEventDao;
import gov.nih.nci.cabig.caaers.dao.CtcCategoryDao;
import gov.nih.nci.cabig.caaers.dao.ExpeditedAdverseEventReportDao;
import gov.nih.nci.cabig.caaers.dao.ParticipantDao;
import gov.nih.nci.cabig.caaers.dao.RoutineAdverseEventReportDao;
import gov.nih.nci.cabig.caaers.dao.StudyDao;
import gov.nih.nci.cabig.caaers.web.ListValues;
import gov.nih.nci.cabig.caaers.web.search.SearchStudyAjaxFacade;
import gov.nih.nci.cabig.caaers.web.study.SearchStudyCommand;
import gov.nih.nci.cabig.caaers.utils.ConfigProperty;

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
		    	
	private StudyService studyService;
	private ConfigProperty configurationProperty;
	private ListValues listValues;
	private CtcCategoryDao ctcCategoryDao;
	private StudyDao studyDao;
	private ParticipantDao participantDao;
	private ExpeditedAdverseEventReportDao expeditedDao;
	private RoutineAdverseEventReportDao routineDao;
	private AdverseEventDao adverseEventDao;
	
	public SearchController() {		             
		setCommandClass(SearchStudyCommand.class);    
		setFormView("search/study_search");
		setSuccessView("search/study_search");
	}
	
	 protected Map<String, Object> referenceData(HttpServletRequest request) throws Exception {
	    	Map<String, Object> refdata = new HashMap<String, Object>();
	        refdata.put("genders", listValues.getParticipantGender());
	        refdata.put("ethnicity", listValues.getParticipantEthnicity());
	        refdata.put("ctcCategories", ctcCategoryDao.getAll());
		  	return refdata;
	    }

	// TODO: I really do not like the way I am implementing this I need to find a better way  
	protected void buildSearchResultTable(HttpServletRequest request,String prop, String value, int x)throws Exception{
		
			SearchStudyAjaxFacade searchFacade = new SearchStudyAjaxFacade(studyDao,participantDao,adverseEventDao,expeditedDao,routineDao);
			Context context = null;
			context = new HttpServletRequestContext(request);
        
			TableModel model = new TableModelImpl(context);
			Object viewData = null;
			try {
				 switch (x) {
				 	case 0:
				 		//viewData = searchFacade.build(model, new ArrayList());
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
		            	viewData = searchFacade.getRoutineReportTable(null, prop, value, request);
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
	
	public StudyService getStudyService() {
		return studyService;
	}

	public void setStudyService(StudyService studyService) {
		this.studyService = studyService;
	}

	public ConfigProperty getConfigurationProperty() {
		return configurationProperty;
	}

	public void setConfigurationProperty(ConfigProperty configurationProperty) {
		this.configurationProperty = configurationProperty;
	}
	
	public ListValues getListValues() {
		return listValues;
	}

	public void setListValues(ListValues listValues) {
		this.listValues = listValues;
	}

	public void setCtcCategoryDao(CtcCategoryDao ctcCategoryDao) {
		this.ctcCategoryDao = ctcCategoryDao;
	}

	public CtcCategoryDao getCtcCategoryDao() {
		return ctcCategoryDao;
	}

	public StudyDao getStudyDao() {
		return studyDao;
	}

	public void setStudyDao(StudyDao studyDao) {
		this.studyDao = studyDao;
	}

	public AdverseEventDao getAdverseEventDao() {
		return adverseEventDao;
	}

	public void setAdverseEventDao(AdverseEventDao adverseEventDao) {
		this.adverseEventDao = adverseEventDao;
	}

	public ExpeditedAdverseEventReportDao getExpeditedDao() {
		return expeditedDao;
	}

	public void setExpeditedDao(ExpeditedAdverseEventReportDao expeditedDao) {
		this.expeditedDao = expeditedDao;
	}

	public ParticipantDao getParticipantDao() {
		return participantDao;
	}

	public void setParticipantDao(ParticipantDao participantDao) {
		this.participantDao = participantDao;
	}

	public RoutineAdverseEventReportDao getRoutineDao() {
		return routineDao;
	}

	public void setRoutineDao(RoutineAdverseEventReportDao routineDao) {
		this.routineDao = routineDao;
	}
}