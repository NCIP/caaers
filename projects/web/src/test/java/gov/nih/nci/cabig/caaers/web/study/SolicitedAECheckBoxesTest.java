package gov.nih.nci.cabig.caaers.web.study;

import static gov.nih.nci.cabig.caaers.CaaersUseCase.CREATE_STUDY;

import java.util.LinkedList;
import java.util.List;

import gov.nih.nci.cabig.caaers.CaaersUseCases;
import gov.nih.nci.cabig.caaers.domain.Arm;
import gov.nih.nci.cabig.caaers.domain.CtcTerm;
import gov.nih.nci.cabig.caaers.domain.DiseaseCodeTerm;
import gov.nih.nci.cabig.caaers.domain.Epoch;
import gov.nih.nci.cabig.caaers.domain.Fixtures;
import gov.nih.nci.cabig.caaers.domain.LoadStatus;
import gov.nih.nci.cabig.caaers.domain.SolicitedAdverseEvent;
import gov.nih.nci.cabig.caaers.domain.Study;
import gov.nih.nci.cabig.caaers.domain.meddra.LowLevelTerm;
import junit.framework.TestCase;

@CaaersUseCases( { CREATE_STUDY })
public class SolicitedAECheckBoxesTest extends TestCase {
	
	public void testCheckBoxesDataStructure()
	{
		Study newStudy = Fixtures.createStudy("Arun Study 1");
		newStudy.setShortTitle("Short Title Inserted");
		newStudy.setLongTitle("Long Title Inserted");
		newStudy.setAeTerminology(Fixtures.createCtcV3Terminology(newStudy));
		newStudy.getDiseaseTerminology().setDiseaseCodeTerm(DiseaseCodeTerm.CTEP);
		newStudy.setMultiInstitutionIndicator(Boolean.FALSE);
		newStudy.setLoadStatus(LoadStatus.INPROGRESS.getCode());
		newStudy.setAdeersReporting(Boolean.TRUE);

  	    Epoch epoch1 = new Epoch(Epoch.NAME_PRETREATMENT, 1);
	    epoch1.setDescriptionText("Pre-Treatment description");
	    
	    Epoch epoch2 = new Epoch(Epoch.NAME_TREATMENT, 2);
  	    epoch2.setDescriptionText("Treatment description");
  	    
	    Epoch epoch3 = new Epoch(Epoch.NAME_POSTTREATMENT, 3);
  	    epoch3.setDescriptionText("Post-Treatment description");
  	    
  	    SolicitedAdverseEvent sae11 = new SolicitedAdverseEvent();
  	    CtcTerm ctcterm = new CtcTerm();
  	    ctcterm.setId(-5);
  	    ctcterm.setTerm("Headache");
  	    sae11.setCtcterm(ctcterm);
  	  
  	    SolicitedAdverseEvent sae22 = new SolicitedAdverseEvent();
	    
  	    LowLevelTerm llt = new LowLevelTerm();
  	    llt.setId(-7);
  	    llt.setMeddraTerm("Medra Term 1");
  	    sae22.setLowLevelTerm(llt);
  	    
 	    SolicitedAdverseEvent sae13 = new SolicitedAdverseEvent();
  	    CtcTerm ctcterm2 = new CtcTerm();
  	    ctcterm2.setId(-5);
  	    ctcterm2.setTerm("Head Ache");
	    sae13.setCtcterm(ctcterm2);
  	  
 	    SolicitedAdverseEvent sae31 = new SolicitedAdverseEvent();
  	    CtcTerm ctcterm3 = new CtcTerm();
  	    ctcterm3.setId(-8);
  	    ctcterm3.setTerm("Dizziness");
	    sae31.setCtcterm(ctcterm3);
  	  
 	    SolicitedAdverseEvent sae32 = new SolicitedAdverseEvent();
  	    CtcTerm ctcterm4 = new CtcTerm();
  	    ctcterm4.setId(-8);
  	    ctcterm4.setTerm("Dizziness");
	    sae32.setCtcterm(ctcterm4);
  	  
  	    
  	    List<SolicitedAdverseEvent> listOfSAE1 = new LinkedList<SolicitedAdverseEvent>();
	    listOfSAE1.add(sae11);
	    listOfSAE1.add(sae31);
	    
  	    List<SolicitedAdverseEvent> listOfSAE2 = new LinkedList<SolicitedAdverseEvent>();
	    listOfSAE2.add(sae22);
	    listOfSAE2.add(sae32);
	    
  	    List<SolicitedAdverseEvent> listOfSAE3 = new LinkedList<SolicitedAdverseEvent>();
	    listOfSAE3.add(sae13);
	    
	    
	    
	      	    
  	    List<Arm> armlist1 = epoch1.getArms();
  	    List<Arm> armlist2 = epoch2.getArms();
  	    List<Arm> armlist3 = epoch3.getArms();
	    armlist1.get(0).setSolicitedAdverseEvents(listOfSAE1);
	    armlist2.get(0).setSolicitedAdverseEvents(listOfSAE2);
	    armlist3.get(0).setSolicitedAdverseEvents(listOfSAE3);
	    newStudy.addEpoch(epoch1);
	    newStudy.addEpoch(epoch2);
	    newStudy.addEpoch(epoch3);
	    
////////////////////////////////////////////////////////////////////////////
	    SolicitedEventTabTable table = new SolicitedEventTabTable( newStudy );
	    LinkedList<LinkedList<Object>> entireListOfRows = table.getListOfSolicitedAERows();
	    
	    for(LinkedList<Object> eachRow : entireListOfRows)
	    {
	    	
	    	for( Object obj : eachRow)
	    	{
	    		System.out.println( obj + " --- " );
	    	}
	    	  System.out.println();
	    }
	}

}
