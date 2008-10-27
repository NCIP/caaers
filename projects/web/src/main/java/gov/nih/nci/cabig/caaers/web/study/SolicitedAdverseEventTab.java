package gov.nih.nci.cabig.caaers.web.study;

import gov.nih.nci.cabig.caaers.dao.CtcTermDao;
import gov.nih.nci.cabig.caaers.dao.meddra.LowLevelTermDao;
import gov.nih.nci.cabig.caaers.domain.CtcTerm;
import gov.nih.nci.cabig.caaers.domain.DiseaseCodeTerm;
import gov.nih.nci.cabig.caaers.domain.Epoch;
import gov.nih.nci.cabig.caaers.domain.SolicitedAdverseEvent;
import gov.nih.nci.cabig.caaers.domain.Study;
import gov.nih.nci.cabig.caaers.domain.Term;
import gov.nih.nci.cabig.caaers.domain.meddra.LowLevelTerm;
import gov.nih.nci.cabig.caaers.web.fields.InputField;
import gov.nih.nci.cabig.caaers.web.fields.InputFieldGroup;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;

import org.directwebremoting.WebContextFactory;
import org.springframework.beans.BeanWrapper;
import org.springframework.validation.Errors;
import org.springframework.web.servlet.ModelAndView;

/**
 * This tab captures the solicited adverse event details in the study flow.
 * @author Biju Joseph
 * @author Arun Kumar
 *
 */
public class SolicitedAdverseEventTab extends StudyTab {
	
	public static final String AJAX_REQUEST_PARAMETER = "_isAjax";
	public static final String AJAX_SUBVIEW_PARAMETER = "_subview";
	public static final String AJAX_REQUEST_ADDEPOCH = "_addEpoch";
	public static final String AJAX_REQUEST_DELETEEPOCH = "_deleteEpoch";

	
    private CtcTermDao ctcTermDao;

    private LowLevelTermDao lowLevelTermDao;
    
    private SolicitedEventTabTable table;
    
	public SolicitedAdverseEventTab() {
		 super("Evaluation Period Types & Solicited Adverse Events", "Evaluation Period Types", "study/solicited_ae");
	}

	
    @Override
    public Map<String, Object> referenceData(HttpServletRequest request, Study study) {
      
    	Map<String, Object> refdata = super.referenceData();
    	
    	SolicitedEventTabTable table = null;
    	
    	if(request.getParameter( AJAX_REQUEST_ADDEPOCH ) != null || request.getParameter( AJAX_REQUEST_DELETEEPOCH ) != null )
    	{
    		String[] termIDs = request.getParameterValues("eachRowTermID");
    		table = new SolicitedEventTabTable( study , termIDs, ctcTermDao, lowLevelTermDao );
    	}
    	else if( request.getParameter(AJAX_REQUEST_PARAMETER) == null && request.getAttribute(AJAX_REQUEST_PARAMETER) == null )
    	{
            table = new SolicitedEventTabTable(study);
    	}
        else
    	{
    		// Executes when we try to add one or more rows
    		
    		String[] termIDs = (String[])request.getAttribute("listOfTermIDs");
    		String[] terms = (String[])request.getAttribute("listOfTerms");
    		table = new SolicitedEventTabTable( study, termIDs, terms, ctcTermDao );
    		refdata.put("numOfnewlyAddedRows", table.getNumOfnewlyAddedRows());
    	}	
    	System.out.println(table.getListOfSolicitedAERows());
    	refdata.put("listOfSolicitedAERows",table.getListOfSolicitedAERows());  
        System.out.println("listOfSolicitedAERows:"+ table.getListOfSolicitedAERows());
        
        return refdata;
    }
    
    
    @Override
    protected void validate(final Study command, final BeanWrapper commandBean, final Map<String, InputFieldGroup> fieldGroups, final Errors errors) {
       
    	List<Epoch> listOfEpochs = command.getEpochs();
    	List<String> listOfEpochNames = new ArrayList<String>();
    	
    	for( Epoch epoch : listOfEpochs )
    	{
    		if( epoch.getName()== null || epoch.getName().equalsIgnoreCase("Enter name here") )
    			errors.reject("name","Each evaluation period type must have a valid title. Type the title or delete the evaluation period type.");
    		listOfEpochNames.add(epoch.getName().toUpperCase());
    	}
    	
    	java.util.Set<String> setOfEpochNames = new java.util.HashSet<String>();
    	
    	for( Epoch epoch : listOfEpochs )
    	{
    		setOfEpochNames.add(epoch.getName().toUpperCase());
    	}
    	
    	if( setOfEpochNames.size() != listOfEpochNames.size())
    		errors.reject("name","There is a duplicate evaluation period type. Modify or delete the evaluation period types so they are all unique.");
    	
    	// Validate otherMeddra for ctc terminology.
    	HashMap<String, Boolean> otherMeddraErrorMap = new HashMap<String, Boolean>(); // This is used to avoid repeating the error messages.
    	for(Epoch epoch: command.getEpochs()){
    		for(SolicitedAdverseEvent sae: epoch.getArms().get(0).getSolicitedAdverseEvents()){
    			if(sae.getCtcterm() != null && sae.getCtcterm().isOtherRequired()){
    				if(sae.getOtherTerm() == null && !otherMeddraErrorMap.containsKey(sae.getCtcterm().getTerm())){
    					errors.reject("otherMeddraRequired", "Other medDRA term is required for the term " + sae.getCtcterm().getTerm());
    					otherMeddraErrorMap.put(sae.getCtcterm().getTerm(), Boolean.TRUE);
    				}
    			}
    		}
    	}
    }

    protected void retainOnlyTheseEpochsInStudy( Study command, String[] epoch_orders )
    {
    	try{
    	ArrayList<String> unDeletedEpochs = new ArrayList<String>();
    	
    	for(String epoch_order : epoch_orders ) {
    		unDeletedEpochs.add( epoch_order );
    	}
    	
    	List<Epoch> all_epochs = command.getEpochs();
    	
    	java.util.Iterator<Epoch> iterator =  all_epochs.iterator();
    	while( iterator.hasNext() )
    	{
    		Epoch epoch = iterator.next();
    		System.out.println("1:"+unDeletedEpochs.get(0).equals(String.valueOf(epoch.getEpochOrder())));	
    		
    	    
    		if( !unDeletedEpochs.contains(String.valueOf(epoch.getEpochOrder())) )
    		{
    		   System.out.println("Deleting... : " + epoch.getId());    			
               iterator.remove();
    		}
    	}
    	
    	}catch(Exception ex)
    	{
    		 ex.printStackTrace();
    	}
    	
    }

    
    @Override
    public void onBind(HttpServletRequest request, Study command, Errors errors) {
    	// TODO Auto-generated method stub
    	super.onBind(request, command, errors);
    
    	if( request.getParameter("_ajaxInPlaceEditParam") != null || request.getParameter(AJAX_REQUEST_PARAMETER) != null || request.getAttribute(AJAX_REQUEST_PARAMETER) != null ) return;

        List<Epoch> listOfEpochs = command.getEpochs();
   	    String[] epoch_ids = request.getParameterValues("epoch_id");
    	
    	System.out.println( epoch_ids.length );
    	
        int indexOfEpoch = 0;
    	
    	for(Epoch e : command.getEpochs()){
    		List<SolicitedAdverseEvent> listOfSolicitedAEs = new ArrayList<SolicitedAdverseEvent>();
    		String[] termIDs = request.getParameterValues( "epoch[" + indexOfEpoch + "]" );
    		
    		Term term = command.getAeTerminology().getTerm();
    		if( termIDs != null )
	    		for( String termID : termIDs )
	    		{
	      		  if( term.equals( Term.CTC ) )
	    		  {
	    			  CtcTerm ctcterm = ctcTermDao.getById(Integer.parseInt(termID));
	      			  SolicitedAdverseEvent solicitedAE = new SolicitedAdverseEvent();
	      			  solicitedAE.setCtcterm( ctcterm );
	      			  // Add otherMeddra term if exists
	      			  if(ctcterm.isOtherRequired()){
	      				  String attributeName = "otherMeddra-" + ctcterm.getId();
	      				  String lowLevelTermIdString = (String)findInRequest(request, attributeName);
	      				  if(!lowLevelTermIdString.equals("")){
	      					  LowLevelTerm lowLevelTerm = lowLevelTermDao.getById(Integer.parseInt(lowLevelTermIdString));
	      					  solicitedAE.setOtherTerm(lowLevelTerm);
	      				  }
	      			  }
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
    		
    		List<SolicitedAdverseEvent> listOfOldSolicitedAEs = e.getArms().get(0).getSolicitedAdverseEvents();
    		listOfOldSolicitedAEs.clear();
    		listOfOldSolicitedAEs.addAll( listOfSolicitedAEs );
    		indexOfEpoch++;
    	}	
    	retainOnlyTheseEpochsInStudy( command, epoch_ids );

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
    
    /*
     * 
     * For future use...
     */
    public String[] getSortedTerms(String[] termIDs)
    {
    	ArrayList<Integer> listOfTermIDS = new ArrayList<Integer>();
    	
    	for( String termID : termIDs )
    		listOfTermIDS.add( Integer.valueOf(termID) ); 
    	
    	java.util.Collections.sort( listOfTermIDS );
    	
    	for( int i = 0 ; i < termIDs.length ; i++)
    		termIDs[i] = String.valueOf(listOfTermIDS.get(i)); 
    	
    	return termIDs;
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
    
	/*
	 *  For future use
	 */

	private int generateNextEpochOrderNumber( Study study)
    {
    	List<Epoch> listOfEpochs = study.getEpochs();
    	return listOfEpochs.get(listOfEpochs.size()-1).getEpochOrder() + 1;
    }
    
	public ModelAndView addEpoch(HttpServletRequest request, Object commandObj, Errors error) {        
		Study study= (Study) commandObj;
	 	int newOrderNumber = generateNextEpochOrderNumber(study);
        log.debug("newOrderNumber: " + newOrderNumber);
        Epoch newEpoch = new Epoch("Enter name here", newOrderNumber );
    	study.addEpoch( newEpoch );
    	return new ModelAndView(getAjaxViewName(request), new java.util.HashMap());
	}

/*
 *  For future use
 */
	public ModelAndView deleteEpoch(HttpServletRequest request, Object commandObj, Errors error) {        
		Study study= (Study) commandObj;
        String[] epoch_ids = request.getParameterValues("epoch_id");
        log.debug("epochs_ids.length: " + epoch_ids.length);
    	retainOnlyTheseEpochsInStudy( study, epoch_ids );
		return new ModelAndView(getAjaxViewName(request), new java.util.HashMap());
	}


}