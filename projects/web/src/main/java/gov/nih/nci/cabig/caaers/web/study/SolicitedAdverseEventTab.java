package gov.nih.nci.cabig.caaers.web.study;

import gov.nih.nci.cabig.caaers.dao.CtcTermDao;
import gov.nih.nci.cabig.caaers.dao.EpochDao;
import gov.nih.nci.cabig.caaers.dao.meddra.LowLevelTermDao;
import gov.nih.nci.cabig.caaers.domain.CtcTerm;
import gov.nih.nci.cabig.caaers.domain.Epoch;
import gov.nih.nci.cabig.caaers.domain.SolicitedAdverseEvent;
import gov.nih.nci.cabig.caaers.domain.Study;
import gov.nih.nci.cabig.caaers.domain.Term;
import gov.nih.nci.cabig.caaers.domain.meddra.LowLevelTerm;
import gov.nih.nci.cabig.caaers.web.fields.InputFieldGroup;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.BeanWrapper;
import org.springframework.validation.Errors;
import org.springframework.web.servlet.ModelAndView;

/**
 * This tab captures the solicited adverse event details in the study flow.
 * @author Biju Joseph
 * @author Arun Kumar
 * @author Ion C. Olaru
 *
 */
public class SolicitedAdverseEventTab extends StudyTab {
	
	public static final String AJAX_REQUEST_PARAMETER = "_isAjax";
	public static final String AJAX_SUBVIEW_PARAMETER = "_subview";
	public static final String AJAX_REQUEST_ADDEPOCH = "_addEpoch";
	public static final String AJAX_REQUEST_DELETEEPOCH = "_deleteEpoch";

	
    private CtcTermDao ctcTermDao;
    private LowLevelTermDao lowLevelTermDao;
    private EpochDao epochDao;

    private SolicitedEventTabTable table;
    
	public SolicitedAdverseEventTab() {
		 super("Evaluation Period Types & Solicited Adverse Events", "Evaluation Period Types", "study/solicited_ae");
	}

	
    @Override
    public Map<String, Object> referenceData(HttpServletRequest request, StudyCommand command) {

        Study study = command.getStudy();
        
        Map<String, Object> refdata = super.referenceData();
    	SolicitedEventTabTable table = null;
    	
    	if(request.getParameter(AJAX_REQUEST_ADDEPOCH ) != null || request.getParameter( AJAX_REQUEST_DELETEEPOCH ) != null ) {
    		String[] termIDs = request.getParameterValues("eachRowTermID");
    		table = new SolicitedEventTabTable(study , termIDs, ctcTermDao, lowLevelTermDao );
    	} else if (request.getParameter(AJAX_REQUEST_PARAMETER) == null && request.getAttribute(AJAX_REQUEST_PARAMETER) == null ) {
            table = new SolicitedEventTabTable(study);
    	} else {
    		// Executes when we try to add one or more rows
    		
    		String[] termIDs = (String[])request.getAttribute("listOfTermIDs");
    		String[] terms = (String[])request.getAttribute("listOfTerms");
    		table = new SolicitedEventTabTable(study, termIDs, terms, ctcTermDao );
    		refdata.put("numOfnewlyAddedRows", table.getNumOfnewlyAddedRows());
    	}	

        refdata.put("listOfSolicitedAERows", table.getListOfSolicitedAERows());

        return refdata;
    }
    
    @Override
    protected void validate(final StudyCommand command, final BeanWrapper commandBean, final Map<String, InputFieldGroup> fieldGroups, final Errors errors) {
       
    	List<Epoch> listOfEpochs = command.getStudy().getActiveEpochs();
    	List<String> listOfEpochNames = new ArrayList<String>();

        for (Epoch epoch : listOfEpochs) {
            if (epoch.getName() == null || epoch.getName().equalsIgnoreCase("Enter name here"))
                errors.reject("STU_015", "Each evaluation period type must have a valid title. Type the title or delete the evaluation period type.");
            listOfEpochNames.add(epoch.getName().toUpperCase());
        }
    	
    	java.util.Set<String> setOfEpochNames = new java.util.HashSet<String>();

        for (Epoch epoch : listOfEpochs) {
            setOfEpochNames.add(epoch.getName().toUpperCase());
        }

        if( setOfEpochNames.size() != listOfEpochNames.size())
    		errors.reject("STU_016","There is a duplicate evaluation period type. Modify or delete the evaluation period types so they are all unique.");
    	
    	HashSet<SolicitedAdverseEvent> solicitedAEsWithinEpochSet = new HashSet<SolicitedAdverseEvent>();

    	HashMap<String, Boolean> otherMeddraErrorMap = new HashMap<String, Boolean>(); // This is used to avoid repeating the error messages.
    	for(Epoch epoch: listOfEpochs){
    		solicitedAEsWithinEpochSet.clear();
    		
    		for(SolicitedAdverseEvent sae: epoch.getArms().get(0).getSolicitedAdverseEvents()){
    			//check within an arm if terms are duplicated
    			if(!solicitedAEsWithinEpochSet.add(sae)){
    				//this is a dup.
    				String termName = (sae.getCtcterm() != null) ? sae.getCtcterm().getTerm() : sae.getLowLevelTerm().getMeddraTerm();
    				errors.reject("STU_001", new Object[]{termName, epoch.getName()}, "Duplicate term added in evaluation period");
    			}
    			
    			// Validate otherMeddra for ctc terminology.
    			if(sae.getCtcterm() != null && sae.getCtcterm().isOtherRequired()){
    				if(sae.getOtherTerm() == null && !otherMeddraErrorMap.containsKey(sae.getCtcterm().getTerm())){
    					errors.reject("STU_017", new Object[]{sae.getCtcterm().getTerm()} , "Other medDRA term is required for the term " + sae.getCtcterm().getTerm());
    					otherMeddraErrorMap.put(sae.getCtcterm().getTerm(), Boolean.TRUE);
    				}
    			}
    		}
    	}
    }

    protected void retainOnlyTheseEpochsInStudy(HttpServletRequest request, Study study, String[] epoch_orders) {
        try {
            ArrayList<String> unDeletedEpochs = new ArrayList<String>();

            for (String epoch_order : epoch_orders) {
                unDeletedEpochs.add(epoch_order);
            }

            List<Epoch> all_epochs = study.getActiveEpochs();
            java.util.Iterator<Epoch> iterator = all_epochs.iterator();
            
            while (iterator.hasNext()) {
                Epoch epoch = iterator.next();
                if (!unDeletedEpochs.contains(String.valueOf(epoch.getEpochOrder()))) {
                	epoch.retire();
//                    int count = 0;
//                    if (epoch.getId() != null)
//                        count = epochDao.getCountReportingPeriodsByEpochId(epoch.getId());
//
//                    // System.out.println("This epoch is assigned to (" + count + ") Reporting Periods.");
//                    if (count == 0) iterator.remove(); else {
//                        request.setAttribute("statusMessage", "wrongEpochDelete");
//                    }
                }
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    @Override
    public void onBind(HttpServletRequest request, StudyCommand command, Errors errors) {
    	// TODO Auto-generated method stub
    	super.onBind(request, command, errors);
    
    	if( request.getParameter("_ajaxInPlaceEditParam") != null || request.getParameter(AJAX_REQUEST_PARAMETER) != null || request.getAttribute(AJAX_REQUEST_PARAMETER) != null ) return;

   	    String[] epoch_ids = request.getParameterValues("epoch_id");
    	
        int indexOfEpoch = 0;
    	
    	for (Epoch e : command.getStudy().getActiveEpochs()){
    		List<SolicitedAdverseEvent> listOfSolicitedAEs = new ArrayList<SolicitedAdverseEvent>();
    		String[] termIDs = request.getParameterValues( "epoch[" + indexOfEpoch + "]" );

            Term term = command.getStudy().getAeTerminology().getTerm();
            if (termIDs != null)
                for (String termID : termIDs) {
                    if (term.equals(Term.CTC)) {
                        CtcTerm ctcterm = ctcTermDao.getById(Integer.parseInt(termID));
                        SolicitedAdverseEvent solicitedAE = new SolicitedAdverseEvent();
                        solicitedAE.setCtcterm(ctcterm);
                        // Add otherMeddra term if exists
                        if (ctcterm.isOtherRequired()) {
                            String attributeName = "otherMeddra-" + ctcterm.getId();
                            String lowLevelTermIdString = (String) findInRequest(request, attributeName);
                            if (!lowLevelTermIdString.equals("")) {
                                LowLevelTerm lowLevelTerm = lowLevelTermDao.getById(Integer.parseInt(lowLevelTermIdString));
                                solicitedAE.setOtherTerm(lowLevelTerm);
                            }
                        }
                        listOfSolicitedAEs.add(solicitedAE);
                    } else {
                        LowLevelTerm medraterm = lowLevelTermDao.getById(Integer.parseInt(termID));
                        SolicitedAdverseEvent solicitedAE = new SolicitedAdverseEvent();
                        solicitedAE.setLowLevelTerm(medraterm);
                        listOfSolicitedAEs.add(solicitedAE);
                    }
                }

            List<SolicitedAdverseEvent> listOfOldSolicitedAEs = e.getArms().get(0).getSolicitedAdverseEvents();
    		listOfOldSolicitedAEs.clear();
    		listOfOldSolicitedAEs.addAll( listOfSolicitedAEs );
    		indexOfEpoch++;
    	}	
    	retainOnlyTheseEpochsInStudy(request, command.getStudy(), epoch_ids);
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

    private int generateNextEpochOrderNumber(Study study) {
       return study.getEpochs().size();
    }

    public ModelAndView addEpoch(HttpServletRequest request, Object command, Errors error) {
        Study study = ((StudyCommand)command).getStudy();
        int newOrderNumber = generateNextEpochOrderNumber(study);
        log.debug("newOrderNumber: " + newOrderNumber);
        Epoch newEpoch = new Epoch("Enter name here", newOrderNumber );
    	study.addEpoch(newEpoch);
    	return new ModelAndView(getAjaxViewName(request), new java.util.HashMap());
	}
    
    /**
     * Will be called, when you delete an epoch on the screen.
     * @param request
     * @param command
     * @param error
     * @return
     */
	public ModelAndView deleteEpoch(HttpServletRequest request, Object command, Errors error) {
		Study study = ((StudyCommand)command).getStudy();
        // the list of all epochs on the page (before deleting) except the # of the one to delete
        String[] epoch_ids = request.getParameterValues("epoch_id");
    	retainOnlyTheseEpochsInStudy(request, study, epoch_ids);
		return new ModelAndView(getAjaxViewName(request), new java.util.HashMap());
	}

    public EpochDao getEpochDao() {
        return epochDao;
    }

    public void setEpochDao(EpochDao epochDao) {
        this.epochDao = epochDao;
    }
}