package gov.nih.nci.cabig.caaers.rules.runtime;

import gov.nih.nci.cabig.caaers.domain.AnatomicSite;
import gov.nih.nci.cabig.caaers.domain.DiseaseTerm;
import gov.nih.nci.cabig.caaers.domain.ExpeditedAdverseEventReport;
import gov.nih.nci.cabig.caaers.validation.ValidationErrors;

public class PatientInformationBusinessRulesTest extends
		BusinessRulesExecutionServiceTest {

	String bindUri = "gov.nih.nci.cabig.caaers.rules.reporting_medical_info_section";
	
	public String getBindUri() {
		return bindUri;
	}

	@Override
	public String getRuleFile() {
		return "patient_information_rule.xml";
	}
	/**
	 * RuleName : PAT_BR2A_CHK
	Rule : Disease Name Not Listed must not be null if Disease Name is  'Solid tumor, NOS' or 'Hematopoietic malignancy, NOS'.
	Error Code : PAT_BR2A_ERR
	Error Message : DISEASE_NAME_NOT_LISTED must be provided if DISEASE_NAME is "Solid tumor, NOS" or "Hematopoietic malignancy, NOS".

	 * @throws Exception
	 */
	public void testOtherDiseaseName_WhenDiseaseTermIsSolidTumorNOS() throws Exception {
		ExpeditedAdverseEventReport aeReport = createAEReport();
		ValidationErrors errors = fireRules(aeReport);
		System.out.println(errors);
		assertEquals("No errors when OtherDiseaseName is present", 0, errors.getErrorCount());
	}
	/**
	 * RuleName : PAT_BR2A_CHK
	Rule : Disease Name Not Listed must not be null if Disease Name is  'Solid tumor, NOS' or 'Hematopoietic malignancy, NOS'.
	Error Code : PAT_BR2A_ERR
	Error Message : DISEASE_NAME_NOT_LISTED must be provided if DISEASE_NAME is "Solid tumor, NOS" or "Hematopoietic malignancy, NOS".

	 * @throws Exception
	 */
	public void testOtherDiseaseName_WhenDiseaseTermIsHematopoieticmalignancyNOS() throws Exception {
		ExpeditedAdverseEventReport aeReport = createAEReport();
		DiseaseTerm diseaseTerm = new DiseaseTerm();
		diseaseTerm.setTerm("Hematopoietic malignancy, NOS");
		aeReport.getDiseaseHistory().getAbstractStudyDisease().setTerm(diseaseTerm);
		ValidationErrors errors = fireRules(aeReport);
		System.out.println(errors);
		assertEquals("No errors when OtherDiseaseName is present", 0, errors.getErrorCount());
	}
	

	
	
	/**
	 * RuleName : PAT_BR2A_CHK
	Rule : Disease Name Not Listed must not be null if Disease Name is  'Solid tumor, NOS' or 'Hematopoietic malignancy, NOS'.
	Error Code : PAT_BR2A_ERR
	Error Message : DISEASE_NAME_NOT_LISTED must be provided if DISEASE_NAME is "Solid tumor, NOS" or "Hematopoietic malignancy, NOS".

	 * @throws Exception
	 */
	public void testNoOtherDiseaseName_WhenDiseaseTermIsHematopoieticmalignancyNOS() throws Exception {
		ExpeditedAdverseEventReport aeReport = createAEReport();
		aeReport.getDiseaseHistory().setOtherPrimaryDisease(null);
		DiseaseTerm diseaseTerm = new DiseaseTerm();
		diseaseTerm.setTerm("Hematopoietic malignancy, NOS");
		aeReport.getDiseaseHistory().getAbstractStudyDisease().setTerm(diseaseTerm);
		ValidationErrors errors = fireRules(aeReport);
		System.out.println(errors);
		assertEquals("Errors when OtherDiseaseName is not present", 1, errors.getErrorCount());
		assertEquals("Error code should be same", "PAT_BR2A_ERR" , errors.getErrorAt(0).getCode());
	}
	/**
	 * RuleName : PAT_BR2A_CHK
	Rule : Disease Name Not Listed must not be null if Disease Name is  'Solid tumor, NOS' or 'Hematopoietic malignancy, NOS'.
	Error Code : PAT_BR2A_ERR
	Error Message : DISEASE_NAME_NOT_LISTED must be provided if DISEASE_NAME is "Solid tumor, NOS" or "Hematopoietic malignancy, NOS".

	 * @throws Exception
	 */
	public void testNoOtherDiseaseName_WhenDiseaseTermIsNotHematopoieticmalignancyNOS() throws Exception {
		ExpeditedAdverseEventReport aeReport = createAEReport();
		aeReport.getDiseaseHistory().setOtherPrimaryDisease(null);
		DiseaseTerm diseaseTerm = new DiseaseTerm();
		diseaseTerm.setTerm("xxxHematopoietic malignancy, NOS");
		aeReport.getDiseaseHistory().getAbstractStudyDisease().setTerm(diseaseTerm);
		ValidationErrors errors = fireRules(aeReport);
		System.out.println(errors);
		assertEquals("No Errors when OtherDiseaseName is not present and diseaseTerm is not Hemtopoietc.....", 0, errors.getErrorCount());
	}
	
	/**
	 *
	 *	RuleName : PAT_BR2B_CHK
	Rule : Disease Name Not Listed must not be provided where Disease Name is not 'Solid tumor, NOS' or 'Hematopoietic malignancy, NOS'.
	Error Code : PAT_BR2B_ERR
	Error Message : DISEASE_NAME_NOT_LISTED must not be provided if DISEASE_NAME is not " Solid tumor, NOS" or " Hematopoietic malignancy, NOS".

	 * @throws Exception
	 */
	public void testOtherDiseaseName_WhenDiseaseTermIsNotHematopoieticmalignancyNOS() throws Exception {
		ExpeditedAdverseEventReport aeReport = createAEReport();
		aeReport.getDiseaseHistory().setOtherPrimaryDisease("Some diesase");
		DiseaseTerm diseaseTerm = new DiseaseTerm();
		diseaseTerm.setTerm("abc NOS");
		aeReport.getDiseaseHistory().getAbstractStudyDisease().setTerm(diseaseTerm);
		ValidationErrors errors = fireRules(aeReport);
		System.out.println(errors);
		assertEquals("Errors when OtherDiseaseName is present and disease is not Hematopoietic...", 1, errors.getErrorCount());
		assertEquals("Error code must be same", "PAT_BR2B_ERR" , errors.getErrorAt(0).getCode());
	}
	
	/**
	 * RuleName : PAT_BR3_CHK
	Rule : "'Other Primary Site of Disease'  must not be provided if 'Primary Site of Disease' is provided and vice-versa.
	Error Code : PAT_BR3B_ERR
	Error Message :  Either and only PRIMARY_SITE_OF_DISEASE or OTHER_PRIMARY_SITE_OF_DISEASE must be provided.
	 */
	public void testOtherPrimarySiteOfDiseaseOnly() throws Exception {
		ExpeditedAdverseEventReport aeReport = createAEReport();
		aeReport.getDiseaseHistory().setOtherPrimaryDiseaseSite("OtherSite");
		ValidationErrors errors = fireRules(aeReport);
		System.out.println(errors);
		assertEquals("No Errors when OtherPrimarySiteOfDisease is only present", 0, errors.getErrorCount());
	}

	/**
	 * RuleName : PAT_BR3_CHK
	Rule : "'Other Primary Site of Disease'  must not be provided if 'Primary Site of Disease' is provided and vice-versa.
	Error Code : PAT_BR3B_ERR
	Error Message :  Either and only PRIMARY_SITE_OF_DISEASE or OTHER_PRIMARY_SITE_OF_DISEASE must be provided.
	 */
	public void testPrimarySiteOfDiseaseOnly() throws Exception {
		ExpeditedAdverseEventReport aeReport = createAEReport();
		aeReport.getDiseaseHistory().setOtherPrimaryDiseaseSite(null);
		AnatomicSite diseaseSite = new AnatomicSite();
		diseaseSite.setName("orignal disease site");
		aeReport.getDiseaseHistory().setCodedPrimaryDiseaseSite(diseaseSite);
		ValidationErrors errors = fireRules(aeReport);
		System.out.println(errors);
		assertEquals("No Errors when CodedPrimaryDiseaseSite is only present", 0, errors.getErrorCount());
		
	}
	

	/**
	 * RuleName : PAT_BR3_CHK
	Rule : "'Other Primary Site of Disease'  must not be provided if 'Primary Site of Disease' is provided and vice-versa.
	Error Code : PAT_BR3B_ERR
	Error Message :  Either and only PRIMARY_SITE_OF_DISEASE or OTHER_PRIMARY_SITE_OF_DISEASE must be provided.
	 */
	public void testNoPrimaryOrOtherDiseaseSite() throws Exception {
		ExpeditedAdverseEventReport aeReport = createAEReport();
		aeReport.getDiseaseHistory().setOtherPrimaryDiseaseSite(null);
		aeReport.getDiseaseHistory().setCodedPrimaryDiseaseSite(null);
		ValidationErrors errors = fireRules(aeReport);
		System.out.println(errors);
		assertEquals("No Errors when both other and coded disease site is not present", 0, errors.getErrorCount());
	}
	

	/**
	 * RuleName : PAT_BR3_CHK
	Rule : "'Other Primary Site of Disease'  must not be provided if 'Primary Site of Disease' is provided and vice-versa.
	Error Code : PAT_BR3B_ERR
	Error Message :  Either and only PRIMARY_SITE_OF_DISEASE or OTHER_PRIMARY_SITE_OF_DISEASE must be provided.
	 */
	public void testOtherPrimarySiteOfDisease_AndCodedSiteOfDisease() throws Exception {
		ExpeditedAdverseEventReport aeReport = createAEReport();
		AnatomicSite diseaseSite = new AnatomicSite();
		diseaseSite.setName("orignal disease site");
		aeReport.getDiseaseHistory().setCodedPrimaryDiseaseSite(diseaseSite);
		aeReport.getDiseaseHistory().setOtherPrimaryDiseaseSite("some site");
		ValidationErrors errors = fireRules(aeReport);
		System.out.println(errors);
		assertEquals("Errors when OtherDiseaseSite and CodedDiseaseSite is present ", 1, errors.getErrorCount());
		assertEquals("Error code must be same", "PAT_BR3B_ERR" , errors.getErrorAt(0).getCode());

	}
}
