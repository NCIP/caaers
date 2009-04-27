package gov.nih.nci.cabig.caaers.rules.deploy;

import gov.nih.nci.cabig.caaers.domain.AdverseEvent;
import gov.nih.nci.cabig.caaers.domain.Attribution;
import gov.nih.nci.cabig.caaers.domain.ConcomitantMedication;
import gov.nih.nci.cabig.caaers.domain.ExpeditedAdverseEventReport;
import gov.nih.nci.cabig.caaers.domain.Grade;
import gov.nih.nci.cabig.caaers.domain.ReportStatus;
import gov.nih.nci.cabig.caaers.domain.attribution.ConcomitantMedicationAttribution;
import gov.nih.nci.cabig.caaers.domain.attribution.CourseAgentAttribution;
import gov.nih.nci.cabig.caaers.validation.ValidationErrors;

import java.util.ArrayList;
import java.util.List;

import com.semanticbits.rules.objectgraph.NullSafeFieldExtractor;

public class AttributionBusinessRulesTest extends AbstractBusinessRulesExecutionTestCase {
    @Override
    public String getBindUri() {
        // TODO Auto-generated method stub
        return "gov.nih.nci.cabig.caaers.rules.reporting_attribution_section";
    }

    @Override
    public String getRuleFile() {
        // TODO Auto-generated method stub
        return "rules_reporting_attribution.xml";
    }


    /**
     * RuleName : AER_BR7_CHK Logic : "At least one attribution with one of the following values
     * should present for every AE entered on the report i.e. ‘Possible’, ‘Probable’ & ‘Definite’.
     * AEs reported on the ‘DEATH’ Category are considered as an exception and are not required to
     * meet this specification. Error Code : AER_BR7_ERR Error Message : At least one ATTRIBUTION
     * with one of the following values should present for every AE (except AEs reported on "DEATH"
     * category) provided i.e. ‘Possible’, ‘Probable’ & ‘Definite’
     */
    public void testCorrectAttribution() throws Exception {
        ExpeditedAdverseEventReport aeReport = createAEReport();
        ValidationErrors errors = fireRules(aeReport);
        System.out.println(errors);
        assertNoErrorsHavingCode(errors, "AER_BR7_ERR");
    }

    /**
     * RuleName : AER_BR7_CHK Logic : "At least one attribution with one of the following values
     * should present for every AE entered on the report i.e. ‘Possible’, ‘Probable’ & ‘Definite’.
     * AEs reported on the ‘DEATH’ Category are considered as an exception and are not required to
     * meet this specification. Error Code : AER_BR7_ERR Error Message : At least one ATTRIBUTION
     * with one of the following values should present for every AE (except AEs reported on "DEATH"
     * category) provided i.e. ‘Possible’, ‘Probable’ & ‘Definite’
     */
    public void testInCorrectAttribution() throws Exception {
        ExpeditedAdverseEventReport aeReport = createAEReport();
        aeReport.getTreatmentInformation().getCourseAgents().clear();

        for (AdverseEvent ae : aeReport.getAdverseEvents()) {
            ae.getDiseaseAttributions().get(0).setAttribution(Attribution.UNLIKELY);
        }
        ValidationErrors errors = fireRules(aeReport);
        System.out.println(errors);
        assertCorrectErrorCode(errors, "AER_BR7_ERR");
        assertSameErrorCount(errors, 2);
        assertEquals("Replacment variable should be same", 1, errors.getErrorAt(1).getReplacementVariables()[0]);
    }

    /**
     * RuleName : AER_BR7_CHK Logic : "At least one attribution with one of the following values
     * should present for every AE entered on the report i.e. ‘Possible’, ‘Probable’ & ‘Definite’.
     * AEs reported on the ‘DEATH’ Category are considered as an exception and are not required to
     * meet this specification. Error Code : AER_BR7_ERR Error Message : At least one ATTRIBUTION
     * with one of the following values should present for every AE (except AEs reported on "DEATH"
     * category) provided i.e. ‘Possible’, ‘Probable’ & ‘Definite’
     */
    public void testNoAttributionAtAll() throws Exception {
        ExpeditedAdverseEventReport aeReport = createAEReport();
        aeReport.getTreatmentInformation().getCourseAgents().clear();
        for (AdverseEvent ae : aeReport.getAdverseEvents()) {
            ae.getDiseaseAttributions().clear();
        }
        ValidationErrors errors = fireRules(aeReport);
        System.out.println(errors);
        assertHasErrorsHavingCode(errors, "AER_BR7_ERR");
        //assertSameErrorCount(errors, 2);
        assertEquals("Replacment variable should be same", 1, errors.getErrorAt(1).getReplacementVariables()[0]);
    }

    /**
     * RuleName : AER_BR7_CHK Logic : "At least one attribution with one of the following values
     * should present for every AE entered on the report i.e. ‘Possible’, ‘Probable’ & ‘Definite’.
     * AEs reported on the ‘DEATH’ Category are considered as an exception and are not required to
     * meet this specification. Error Code : AER_BR7_ERR Error Message : At least one ATTRIBUTION
     * with one of the following values should present for every AE (except AEs reported on "DEATH"
     * category) provided i.e. ‘Possible’, ‘Probable’ & ‘Definite’
     */
    public void testNoAttributionAtAll_AllAEHasGradeDEATH() throws Exception {
        ExpeditedAdverseEventReport aeReport = createAEReport();
        aeReport.getTreatmentInformation().getCourseAgents().clear();
        for (AdverseEvent ae : aeReport.getAdverseEvents()) {
            ae.setGrade(Grade.DEATH);
            ae.getDiseaseAttributions().clear();
        }
        ValidationErrors errors = fireRules(aeReport);
        System.out.println(errors);
        assertNoErrorsHavingCode(errors, "AER_BR7_ERR");
    }

    /**
     * RuleName : AER_BR7_CHK Logic : "At least one attribution with one of the following values
     * should present for every AE entered on the report i.e. ‘Possible’, ‘Probable’ & ‘Definite’.
     * AEs reported on the ‘DEATH’ Category are considered as an exception and are not required to
     * meet this specification. Error Code : AER_BR7_ERR Error Message : At least one ATTRIBUTION
     * with one of the following values should present for every AE (except AEs reported on "DEATH"
     * category) provided i.e. ‘Possible’, ‘Probable’ & ‘Definite’
     */
    public void testNoAttributionAtAll_OneOutOfTwoAEHasGradeDEATH() throws Exception {
        ExpeditedAdverseEventReport aeReport = createAEReport();
        aeReport.getTreatmentInformation().getCourseAgents().clear();
        int i = 0;
        for (AdverseEvent ae : aeReport.getAdverseEvents()) {
            if (i == 0) ae.setGrade(Grade.DEATH);
            ae.getDiseaseAttributions().clear();
            i++;
        }
        ValidationErrors errors = fireRules(aeReport);
        System.out.println(errors);
        assertHasErrorsHavingCode(errors, "AER_BR7_ERR");
       // assertSameErrorCount(errors, 1);
        assertEquals("Replacment variable should be same", 0, errors.getErrorAt(0).getReplacementVariables()[0]);

    }

    /**
     * RuleName : AER_BR7_CHK Logic : "At least one attribution with one of the following values
     * should present for every AE entered on the report i.e. ‘Possible’, ‘Probable’ & ‘Definite’.
     * AEs reported on the ‘DEATH’ Category are considered as an exception and are not required to
     * meet this specification. Error Code : AER_BR7_ERR Error Message : At least one ATTRIBUTION
     * with one of the following values should present for every AE (except AEs reported on "DEATH"
     * category) provided i.e. ‘Possible’, ‘Probable’ & ‘Definite’
     */
    public void testAttribution_AllAEHasGradeDEATH() throws Exception {
        ExpeditedAdverseEventReport aeReport = createAEReport();
        aeReport.getTreatmentInformation().getCourseAgents().clear();
        for (AdverseEvent ae : aeReport.getAdverseEvents()) {
            ae.setGrade(Grade.DEATH);
        }
        ValidationErrors errors = fireRules(aeReport);
        System.out.println(errors);
        assertNoErrorsHavingCode(errors, "AER_BR7_ERR");
    }
    
    
    /**
     * 	RuleName : ATT_BR1_CHK
	Logic : ATTRIBUTION_FOR_AE  to all possible causes not provided
	Error Code : ATT_BR1_ERR
	Error Message : AE must be attributed to all CAUSAL factors on report

     */
    public void testAllAttributable_Attributed() throws Exception{
    	ExpeditedAdverseEventReport aeReport = createAEReport();
    	
    	List<CourseAgentAttribution> courseAgentAttributions = new ArrayList<CourseAgentAttribution>();
    	
    	CourseAgentAttribution ca = new CourseAgentAttribution();
    	ca.setCause(aeReport.getTreatmentInformation().getCourseAgents().get(0));
    	ca.setAttribution(Attribution.DEFINITE);
    	courseAgentAttributions.add(ca);

    	
    	CourseAgentAttribution ca2 = new CourseAgentAttribution();
    	ca2.setCause(aeReport.getTreatmentInformation().getCourseAgents().get(1));
    	ca2.setAttribution(Attribution.DEFINITE);
    	courseAgentAttributions.add(ca2);
    	
    	for (AdverseEvent ae : aeReport.getAdverseEvents()) {
    		ca.setAdverseEvent(ae);
    		ca2.setAdverseEvent(ae);
            ae.setCourseAgentAttributions(courseAgentAttributions);
        }
    	
    	ValidationErrors errors = fireRules(aeReport);
    	assertNoErrors(errors, "When attributed no errors should be there");
    }

    /**
     * 	RuleName : ATT_BR1_CHK
	Logic : ATTRIBUTION_FOR_AE  to all possible causes not provided
	Error Code : ATT_BR1_ERR
	Error Message : AE must be attributed to all CAUSAL factors on report

     */
    public void testDiseaseNotAttributed() throws Exception{
    	ExpeditedAdverseEventReport aeReport = createAEReport();
    	
    	
    	for (AdverseEvent ae : aeReport.getAdverseEvents()) {
    		ae.getDiseaseAttributions().clear();
        }
    	
    	ValidationErrors errors = fireRules(aeReport);
    	System.out.println("*********** : " + "testDiseaseNotAttributed");
    	printErrors(errors);
    	assertHasErrorsHavingCode(errors, "ATT_BR1_ERR");
    }
    

    /**
     * 	RuleName : ATT_BR1_CHK
	Logic : ATTRIBUTION_FOR_AE  to all possible causes not provided
	Error Code : ATT_BR1_ERR
	Error Message : AE must be attributed to all CAUSAL factors on report

     */
    public void testOneConMedNotAttributed() throws Exception{
    	ExpeditedAdverseEventReport aeReport = createAEReport();
    	
    	ConcomitantMedication conMed1 = new ConcomitantMedication();
    	conMed1.setReport(aeReport);
    	aeReport.addConcomitantMedication(conMed1);

    	ConcomitantMedication conMed2= new ConcomitantMedication();
    	conMed2.setReport(aeReport);
    	aeReport.addConcomitantMedication(conMed2);

    	ConcomitantMedicationAttribution ma = new ConcomitantMedicationAttribution();
    	ma.setCause(conMed1);
    	ma.setAttribution(Attribution.DEFINITE);
    	
    	List<ConcomitantMedicationAttribution> list = new ArrayList<ConcomitantMedicationAttribution>();
    	list.add(ma);
    	
    	for (AdverseEvent ae : aeReport.getAdverseEvents()) {
    		ae.setConcomitantMedicationAttributions(list);
        }
    	
    	ValidationErrors errors = fireRules(aeReport);
    	assertHasErrorsHavingCode(errors, "ATT_BR1_ERR");
    	assertSameErrorCount(errors, 2);
    }
    
    /**
     * 	RuleName : ATT_BR1_CHK
	Logic : ATTRIBUTION_FOR_AE  to all possible causes not provided
	Error Code : ATT_BR1_ERR
	Error Message : AE must be attributed to all CAUSAL factors on report

     */
    public void testAllAttributable_NotAttributed_NoActiveReports() throws Exception{
    	ExpeditedAdverseEventReport aeReport = createAEReport();
    	aeReport.getReports().get(0).setStatus(ReportStatus.REPLACED);
    	
    	List<CourseAgentAttribution> courseAgentAttributions = new ArrayList<CourseAgentAttribution>();
    	
    	CourseAgentAttribution ca = new CourseAgentAttribution();
    	ca.setCause(aeReport.getTreatmentInformation().getCourseAgents().get(0));
    	ca.setAttribution(null);
    	courseAgentAttributions.add(ca);

    	
    	CourseAgentAttribution ca2 = new CourseAgentAttribution();
    	ca2.setCause(aeReport.getTreatmentInformation().getCourseAgents().get(1));
    	ca2.setAttribution(null);
    	courseAgentAttributions.add(ca2);
    	
    	for (AdverseEvent ae : aeReport.getAdverseEvents()) {
    		ca.setAdverseEvent(ae);
    		ca2.setAdverseEvent(ae);
            ae.setCourseAgentAttributions(courseAgentAttributions);
        }
    	
    	ValidationErrors errors = fireRules(aeReport);
    	assertNoErrors(errors, "No active reports and not attributed no errors should be there");
    }
    
    
    
    /*
    public void printLogs(ExpeditedAdverseEventReport aeReport){
    	
    	System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
    	int i = 0;
		for(AdverseEvent ae : aeReport.getAdverseEvents()){
			i++;
			if( (NullSafeFieldExtractor.extractFieldLength(aeReport,"concomitantMedications") != NullSafeFieldExtractor.extractFieldLength(ae,"concomitantMedicationAttributions"))){
				System.out.println("1");
			}
			if((NullSafeFieldExtractor.extractFieldLength(aeReport,"treatmentInformation.courseAgents") != NullSafeFieldExtractor.extractFieldLength(ae,"courseAgentAttributions"))){
				System.out.println(2);
			}
			   if((NullSafeFieldExtractor.extractFieldLength(aeReport,"medicalDevices") != NullSafeFieldExtractor.extractFieldLength(ae,"deviceAttributions"))){
				   System.out.println(3);
			   }
			  if((NullSafeFieldExtractor.extractFieldLength(aeReport,"radiationInterventions") != NullSafeFieldExtractor.extractFieldLength(ae,"radiationAttributions"))){
				  System.out.println(4);
			  }
			  if((NullSafeFieldExtractor.extractFieldLength(aeReport,"surgeryInterventions") != NullSafeFieldExtractor.extractFieldLength(ae,"surgeryAttributions"))){
				  System.out.println(5);
			  }
			  if((1 != NullSafeFieldExtractor.extractFieldLength(ae,"diseaseAttributions"))){
				  System.out.println(6);
			  }
			  if((NullSafeFieldExtractor.extractFieldLength(aeReport,"otherCauses") != NullSafeFieldExtractor.extractFieldLength(ae,"otherCauseAttributions"))){
			   
			  System.out.println(7);
			}
			  
		}
    	System.out.println("-----------------");
    	System.out.println("conmeds :" +NullSafeFieldExtractor.extractFieldLength(aeReport,"concomitantMedications"));
    	System.out.println("courseAgents :" +NullSafeFieldExtractor.extractFieldLength(aeReport,"treatmentInformation.courseAgents"));
    	System.out.println("Medical Device :"+NullSafeFieldExtractor.extractFieldLength(aeReport,"medicalDevices"));
    	System.out.println("radiation :"+  NullSafeFieldExtractor.extractFieldLength(aeReport,"radiationInterventions"));
    	System.out.println("surgery :"  + NullSafeFieldExtractor.extractFieldLength(aeReport,"surgeryInterventions"));
    	System.out.println("otherCause :" + NullSafeFieldExtractor.extractFieldLength(aeReport,"otherCauses"));
    	System.out.println("*************************");
    	for(AdverseEvent ae : aeReport.getAdverseEvents()){
    		System.out.println("conmeds : " + NullSafeFieldExtractor.extractFieldLength(ae,"concomitantMedicationAttributions"));
    		System.out.println(" courseAgentAttributions : " + NullSafeFieldExtractor.extractFieldLength(ae,"courseAgentAttributions"));
    		System.out.println("deviceAttributions  :" + NullSafeFieldExtractor.extractFieldLength(ae,"deviceAttributions"));
    		System.out.println("radiationAttributions :" + NullSafeFieldExtractor.extractFieldLength(ae,"radiationAttributions"));
    		System.out.println("surgeryAttributions :" + NullSafeFieldExtractor.extractFieldLength(ae,"surgeryAttributions"));
    		System.out.println("disease Attr :" + NullSafeFieldExtractor.extractFieldLength(ae,"diseaseAttributions"));
    		System.out.println("otherCauseAttributions :" + NullSafeFieldExtractor.extractFieldLength(ae,"otherCauseAttributions"));
    		
    	}
    	
    	System.out.println("-----------------");
    		
    }*/
}
