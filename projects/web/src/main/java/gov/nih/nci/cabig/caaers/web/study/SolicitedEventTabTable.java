package gov.nih.nci.cabig.caaers.web.study;

import gov.nih.nci.cabig.caaers.dao.CtcTermDao;
import gov.nih.nci.cabig.caaers.dao.meddra.LowLevelTermDao;
import gov.nih.nci.cabig.caaers.domain.Arm;
import gov.nih.nci.cabig.caaers.domain.CtcTerm;
import gov.nih.nci.cabig.caaers.domain.Epoch;
import gov.nih.nci.cabig.caaers.domain.SolicitedAdverseEvent;
import gov.nih.nci.cabig.caaers.domain.Study;
import gov.nih.nci.cabig.caaers.domain.Term;
import gov.nih.nci.cabig.caaers.domain.meddra.LowLevelTerm;
import gov.nih.nci.cabig.caaers.utils.CustomLinkedSet;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * This table is used for rendering check boxes in Solicited AE Tab in Create/Edit Study Flow.
 * @author Arun Kumar Kandregula
 *
 */
public class SolicitedEventTabTable{
	
	private Set<SolicitedAdverseEvent> consolidatedListOfSolicitedAEsForAllEpochs = new CustomLinkedSet<SolicitedAdverseEvent>();
	private LinkedList<LinkedList<Object>> listOfSolicitedAERows = new LinkedList<LinkedList<Object>>();
	private List<Epoch> listOfEpochs;
	private int numOfnewlyAddedRows;
	public SolicitedEventTabTable( Study command, String[] termIDs, String[] terms, CtcTermDao ctcTermDao)
	{
		LinkedList<Object> eachRowOfSolicitedAE = null;
		for(int i = 0 ; i < termIDs.length ; i++ )
		{
			eachRowOfSolicitedAE = new LinkedList<Object>();
			eachRowOfSolicitedAE.add(termIDs[i]);
			eachRowOfSolicitedAE.add(terms[i]);
			
			eachRowOfSolicitedAE.add(null);
			eachRowOfSolicitedAE.add(isOtherFieldRequired(command, termIDs[i], ctcTermDao));
			
			int numberOfEpochs = command.getActiveEpochs().size(); 
			for( int e = 0 ; e < numberOfEpochs ; e++ )
			  eachRowOfSolicitedAE.add(false);
			numOfnewlyAddedRows++;
			listOfSolicitedAERows.add(eachRowOfSolicitedAE);
		}
	}
	
	public Boolean isOtherFieldRequired(Study command, String termID, CtcTermDao ctcTermDao){
		if(command.getAeTerminology().getTerm().equals(Term.CTC)){
			CtcTerm ctcterm = ctcTermDao.getById(Integer.parseInt(termID));
			if(ctcterm.isOtherRequired())
				return true;
			else
				return false;
		}else
			return false;
	}

	public SolicitedEventTabTable( Study command, String[] termIDs, CtcTermDao ctcTermDao, LowLevelTermDao lowLevelTermDao )
	{
          listOfEpochs = command.getActiveEpochs();
		  
		  buildConsolidatedListOfSolicitedAEsWithExtraTerms( command, termIDs, ctcTermDao, lowLevelTermDao);
		  
		  constructListOfSolicitedAErows();
	}

	
	public SolicitedEventTabTable( Study command )
	{
		listOfEpochs = command.getActiveEpochs();
		
		buildConsolidatedListOfSolicitedAEs( listOfEpochs );
		
		constructListOfSolicitedAErows();
	}
	
	private void constructListOfSolicitedAErows()
	{
		for( SolicitedAdverseEvent solicitedAE : consolidatedListOfSolicitedAEsForAllEpochs )
		{
			LinkedList<Object> eachRowOfSolicitedAE = new LinkedList<Object>();
			//add ctc or medra id as 1st element
			eachRowOfSolicitedAE.add( ( solicitedAE.getCtcterm() != null )? solicitedAE.getCtcterm().getId() : solicitedAE.getLowLevelTerm().getId() );
			// add ctdc or medra term as 2nd element
			eachRowOfSolicitedAE.add( ( solicitedAE.getCtcterm() != null )? solicitedAE.getCtcterm().getFullName() : solicitedAE.getLowLevelTerm().getFullName());
			
			// add Other meddra field as the 3rd element
			eachRowOfSolicitedAE.add((solicitedAE.getOtherTerm() != null) ? solicitedAE.getOtherTerm() : null);
			// add a boolean which is true incase the otherMeddra is needed for ctc Term.
			if(solicitedAE.getCtcterm() != null && solicitedAE.getCtcterm().isOtherRequired())
				eachRowOfSolicitedAE.add(Boolean.TRUE);
			else
				eachRowOfSolicitedAE.add(Boolean.FALSE);
				
			
			for( Epoch epoch : listOfEpochs )
			{
				boolean mayExpectSolicitedAE = false;
				if( doEpochExpectSolicitedAE( epoch , solicitedAE) )
					mayExpectSolicitedAE = true;
				
				eachRowOfSolicitedAE.add( mayExpectSolicitedAE );
			}
			listOfSolicitedAERows.add(eachRowOfSolicitedAE);
		}
	}
   
	/*
	 * This will be used for Addition of Epochs
	 */
	private Set<SolicitedAdverseEvent> buildConsolidatedListOfSolicitedAEsWithExtraTerms( Study command, String[] termIDs , CtcTermDao ctcTermDao, LowLevelTermDao lowLevelTermDao )
	{
		Set<SolicitedAdverseEvent> listOfSolicitedAEs = new LinkedHashSet<SolicitedAdverseEvent>();
		
		//add all the terms to a set
		Set<String> termIDSet = new HashSet<String>();
		if(termIDs != null){
			for(String termId : termIDs){
				termIDSet.add(termId);
			}
		}
		
		for(Epoch e : listOfEpochs){
			listOfSolicitedAEs.addAll(getSolicitedAEsForEpoch(e));
		}
		
		Term term = command.getAeTerminology().getTerm();
		 
		if(term.equals(Term.CTC)){
			//now remove the terms that are already avaliable in solicited aes...
			for(SolicitedAdverseEvent sae : listOfSolicitedAEs){
				termIDSet.remove(sae.getCtcterm().getId().toString());
			}
			//add the terms, available in the set
			for(String termID : termIDSet){
				CtcTerm ctcterm = ctcTermDao.getById(Integer.parseInt(termID));
    			SolicitedAdverseEvent solicitedAE = new SolicitedAdverseEvent();
    			solicitedAE.setCtcterm( ctcterm );
                listOfSolicitedAEs.add( solicitedAE );
			}
			
		}else{
			//now remove the terms that are already avaliable in solicited aes...
			for(SolicitedAdverseEvent sae : listOfSolicitedAEs){
				termIDSet.remove(sae.getLowLevelTerm().getId().toString());
			}
			//add for the terms available in the set
			for(String termID : termIDSet){
				LowLevelTerm medraterm = lowLevelTermDao.getById(Integer.parseInt(termID));
    			SolicitedAdverseEvent solicitedAE = new SolicitedAdverseEvent();
    			solicitedAE.setLowLevelTerm( medraterm );
                listOfSolicitedAEs.add( solicitedAE );
			}
			
		}
		consolidatedListOfSolicitedAEsForAllEpochs.addAll( listOfSolicitedAEs );
		return consolidatedListOfSolicitedAEsForAllEpochs;
	}


	private Set<SolicitedAdverseEvent> buildConsolidatedListOfSolicitedAEs( List<Epoch> listOfEpochs )
	{
		for( Epoch epoch : listOfEpochs )
			consolidatedListOfSolicitedAEsForAllEpochs.addAll( getSolicitedAEsForEpoch( epoch ));
		return consolidatedListOfSolicitedAEsForAllEpochs;
	}

	private boolean doEpochExpectSolicitedAE( Epoch epoch, SolicitedAdverseEvent solicitedAE )
	{
		List<SolicitedAdverseEvent> listOFSolicitedAEs = epoch.getArms().get(0).getSolicitedAdverseEvents();
        
		if( listOFSolicitedAEs == null )
			return false;
		else
			return listOFSolicitedAEs.contains( solicitedAE );	
	}
	
	private List<SolicitedAdverseEvent> getSolicitedAEsForEpoch(Epoch epoch)
	{
		List<SolicitedAdverseEvent> seList = new ArrayList<SolicitedAdverseEvent>();
		for(Arm arm : epoch.getArms())
		{
		  	List<SolicitedAdverseEvent> listOfSolicitedAEsForArm = arm.getSolicitedAdverseEvents() ;
		  	if( listOfSolicitedAEsForArm != null) 
			  seList.addAll( listOfSolicitedAEsForArm );
		}
		return seList;
	}
	
	
	protected boolean isSolicitedAEAddedAgain( Study command, String termID )
	{
	   return command.containsSolicitedAE( new Integer(termID) );	
	}

	public Set<SolicitedAdverseEvent> getConsolidatedListOfSolicitedAEsForAllEpochs() {
		return consolidatedListOfSolicitedAEsForAllEpochs;
	}

	public void setConsolidatedListOfSolicitedAEsForAllEpochs(
			Set<SolicitedAdverseEvent> consolidatedListOfSolicitedAEsForAllEpochs) {
		this.consolidatedListOfSolicitedAEsForAllEpochs = consolidatedListOfSolicitedAEsForAllEpochs;
	}

	public LinkedList<LinkedList<Object>> getListOfSolicitedAERows() {
		return listOfSolicitedAERows;
	}

	public void setListOfSolicitedAERows(
			LinkedList<LinkedList<Object>> listOfSolicitedAERows) {
		this.listOfSolicitedAERows = listOfSolicitedAERows;
	}

	public List<Epoch> getListOfEpochs() {
		return listOfEpochs;
	}

	public void setListOfEpochs(List<Epoch> listOfEpochs) {
		this.listOfEpochs = listOfEpochs;
	}

	public int getNumOfnewlyAddedRows() {
		return numOfnewlyAddedRows;
	}

	public void setNumOfnewlyAddedRows(int numOfnewlyAddedRows) {
		this.numOfnewlyAddedRows = numOfnewlyAddedRows;
	}
	
}
	
	
	
		
