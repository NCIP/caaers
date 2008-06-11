package gov.nih.nci.cabig.caaers.web.study;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.validation.Errors;

import gov.nih.nci.cabig.caaers.dao.CtcDao;
import gov.nih.nci.cabig.caaers.dao.CtcTermDao;
import gov.nih.nci.cabig.caaers.dao.MeddraVersionDao;
import gov.nih.nci.cabig.caaers.dao.meddra.LowLevelTermDao;
import gov.nih.nci.cabig.caaers.domain.CtcTerm;
import gov.nih.nci.cabig.caaers.domain.SolicitedAdverseEvent;
import gov.nih.nci.cabig.caaers.domain.Study;
import gov.nih.nci.cabig.caaers.domain.Epoch;
import gov.nih.nci.cabig.caaers.domain.Term;
import gov.nih.nci.cabig.caaers.domain.Arm;
import gov.nih.nci.cabig.caaers.domain.meddra.LowLevelTerm;

/**
 * This tab captures the solicited adverse event details in the study flow.
 * @author Biju Joseph
 * @author Arun Kumar
 *
 */
public class SolicitedAdverseEventTab extends StudyTab {
	
	public static final String AJAX_REQUEST_PARAMETER = "_isAjax";
    public static final String AJAX_SUBVIEW_PARAMETER = "_subview";

	
    private CtcTermDao ctcTermDao;

    private LowLevelTermDao lowLevelTermDao;
    
    private SolicitedEventTabTable table;
    
	public SolicitedAdverseEventTab() {
		 super("Solicited adverse events", "SolictedAEs", "study/solicited_ae");
	}

	
    @Override
    public Map<String, Object> referenceData(HttpServletRequest request, Study study) {
      System.out.println("In reference data");
    	Map<String, Object> refdata = super.referenceData();
    	
    	SolicitedEventTabTable table = null;
    	
    	if( request.getParameter(AJAX_REQUEST_PARAMETER) == null && request.getAttribute(AJAX_REQUEST_PARAMETER) == null )
    	     table = new SolicitedEventTabTable( study );
    	else
    	{
    		String[] termIDs = (String[])request.getAttribute("listOfTermIDs");
    		String[] terms = (String[])request.getAttribute("listOfTerms");
    		table = new SolicitedEventTabTable( study, termIDs, terms);
    		refdata.put("numOfnewlyAddedRows", table.getNumOfnewlyAddedRows());
    	}	
    	System.out.println(table.getListOfSolicitedAERows());
    	refdata.put("listOfSolicitedAERows",table.getListOfSolicitedAERows());  
        System.out.println("listOfSolicitedAERows:"+ table.getListOfSolicitedAERows());
    	return refdata;
    }
    @Override
    public void onBind(HttpServletRequest request, Study command, Errors errors) {
    	// TODO Auto-generated method stub
    	super.onBind(request, command, errors);
    	
    	if( request.getParameter(AJAX_REQUEST_PARAMETER) != null || request.getAttribute(AJAX_REQUEST_PARAMETER) != null )
    		return;
        
    	for(Epoch e : command.getEpochs()){
    		List<SolicitedAdverseEvent> listOfSolicitedAEs = new ArrayList<SolicitedAdverseEvent>();
    		String[] termIDs = request.getParameterValues("epoch["+e.getEpochOrder()+"]");
    		Term term = command.getAeTerminology().getTerm();
    		if( termIDs != null )
	    		for( String termID : termIDs )
	    		{
	      		  if( term.equals( Term.CTC ) )
	    		  {
	    			  CtcTerm ctcterm = ctcTermDao.getById(Integer.parseInt(termID));
	      			  SolicitedAdverseEvent solicitedAE = new SolicitedAdverseEvent();
	      			  solicitedAE.setCtcterm( ctcterm );
	                  listOfSolicitedAEs.add( solicitedAE );
	    		  }
	      		  else
	    		  {
	      			  LowLevelTerm medraterm = lowLevelTermDao.getById(Integer.parseInt(termID));
	      			  SolicitedAdverseEvent solicitedAE = new SolicitedAdverseEvent();
	      			  solicitedAE.setLowLevelTerm( medraterm );
	                  listOfSolicitedAEs.add( solicitedAE );
	    		  }
	    		}
    		e.getArms().get(0).setSolicitedAdverseEvents( listOfSolicitedAEs );
    		
    	}	

    }

	public CtcTermDao getCtcTermDao() {
		return ctcTermDao;
	}

	public void setCtcTermDao(CtcTermDao ctcTermDao) {
		this.ctcTermDao = ctcTermDao;
	}

	public LowLevelTermDao getLowLevelTermDao() {
		return lowLevelTermDao;
	}

	public void setLowLevelTermDao(LowLevelTermDao lowLevelTermDao) {
		this.lowLevelTermDao = lowLevelTermDao;
	}
    
}