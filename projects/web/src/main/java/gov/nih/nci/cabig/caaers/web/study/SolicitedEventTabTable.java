package gov.nih.nci.cabig.caaers.web.study;

import gov.nih.nci.cabig.caaers.domain.Arm;
import gov.nih.nci.cabig.caaers.domain.Epoch;
import gov.nih.nci.cabig.caaers.domain.SolicitedAdverseEvent;
import gov.nih.nci.cabig.caaers.domain.Study;

import java.util.ArrayList;
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
	
	private Set<SolicitedAdverseEvent> consolidatedListOfSolicitedAEsForAllEpochs = new LinkedHashSet<SolicitedAdverseEvent>();
	private LinkedList<LinkedList<Object>> listOfSolicitedAERows = new LinkedList<LinkedList<Object>>();
	private List<Epoch> listOfEpochs;
	private int numOfnewlyAddedRows;
	public SolicitedEventTabTable( Study command, String[] termIDs, String[] terms )
	{
		LinkedList<Object> eachRowOfSolicitedAE = null;
		for(int i = 0 ; i < termIDs.length ; i++ )
		{
			eachRowOfSolicitedAE = new LinkedList<Object>();
			eachRowOfSolicitedAE.add(termIDs[i]);
			eachRowOfSolicitedAE.add(terms[i]);
			
			int numberOfEpochs = command.getEpochs().size(); 
			for( int e = 0 ; e < numberOfEpochs ; e++ )
			  eachRowOfSolicitedAE.add(false);
			numOfnewlyAddedRows++;
			listOfSolicitedAERows.add(eachRowOfSolicitedAE);
		}
		
		
	}
	public SolicitedEventTabTable( Study command )
	{
		listOfEpochs = command.getEpochs();
		for( Epoch epoch : listOfEpochs )
			consolidatedListOfSolicitedAEsForAllEpochs.addAll( getSolicitedAEsForEpoch( epoch ));
		
		for( SolicitedAdverseEvent solicitedAE : consolidatedListOfSolicitedAEsForAllEpochs )
		{
			LinkedList<Object> eachRowOfSolicitedAE = new LinkedList<Object>();
			//add ctc or medra id as 1st element
			eachRowOfSolicitedAE.add( ( solicitedAE.getCtcterm() != null )? solicitedAE.getCtcterm().getId() : solicitedAE.getLowLevelTerm().getId() );
			// add ctdc or medra term as 2nd element
			eachRowOfSolicitedAE.add( ( solicitedAE.getCtcterm() != null )? solicitedAE.getCtcterm().getTerm() : solicitedAE.getLowLevelTerm().getMeddraTerm() );
			
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
	
	
	
		
