/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.service.migrator.report;

import gov.nih.nci.cabig.caaers.domain.AdditionalInformation;
import gov.nih.nci.cabig.caaers.domain.AdditionalInformationDocument;
import gov.nih.nci.cabig.caaers.domain.AdditionalInformationDocumentType;
import gov.nih.nci.cabig.caaers.domain.ExpeditedAdverseEventReport;
import gov.nih.nci.cabig.caaers.service.DomainObjectImportOutcome;

import java.util.ArrayList;
import java.util.List;

import junit.framework.TestCase;

/**
 * User: Ramakrishna Gundala
 * Date: 30/04/13
 */
public class AdditionalInformationMigratorTest extends TestCase {

    AdditionalInformationMigrator migrator;
    ExpeditedAdverseEventReport src;
    ExpeditedAdverseEventReport dest;
    DomainObjectImportOutcome<ExpeditedAdverseEventReport> outcome;
 
    public void setUp() throws Exception {
        migrator = new AdditionalInformationMigrator();
        src = new ExpeditedAdverseEventReport();
        src.setAdditionalInformation(createAdditionalInformation());
        dest = new ExpeditedAdverseEventReport();
        outcome = new DomainObjectImportOutcome<ExpeditedAdverseEventReport>();
    }
    

    public void testMigrate() throws Exception {
        migrator.migrate(src,dest, outcome);
        assertNotNull(src.getAdditionalInformation());
        assertNotNull(dest.getAdditionalInformation());
        assertTrue(dest.getAdditionalInformation().getAutopsyReport());
        assertTrue(dest.getAdditionalInformation().getDischargeSummary());
        assertTrue(dest.getAdditionalInformation().getFlowCharts());
        assertTrue(dest.getAdditionalInformation().getPathologyReport());
        assertTrue(dest.getAdditionalInformation().getRadiologyReports());
        
        assertFalse(dest.getAdditionalInformation().getConsults());
        assertFalse(dest.getAdditionalInformation().getIrbReport());
        
        assertFalse(dest.getAdditionalInformation().getLabReports());
        assertFalse(dest.getAdditionalInformation().getObaForm());
        assertFalse(dest.getAdditionalInformation().getOther());
        assertFalse(dest.getAdditionalInformation().getReferralLetters());
        
        assertEquals("some information",dest.getAdditionalInformation().getOtherInformation());
        
        assertEquals(0,dest.getAdditionalInformation().getAdditionalInformationDocuments().size());
    }

    public void testMigrateAdditionalInformationDocuments() throws Exception {

        assertNotNull(src.getAdditionalInformation());
        assertEquals(0, src.getAdditionalInformation().getAdditionalInformationDocuments().size());
        src.getAdditionalInformation().getAdditionalInformationDocuments().addAll(createAdditionalInformationDocuments());
        migrator.migrate(src,dest, outcome);
        assertNotNull(dest.getAdditionalInformation());
        assertEquals(1, dest.getAdditionalInformation().getAdditionalInformationDocuments().size());
        
        assertEquals(AdditionalInformationDocumentType.DISCHARGE_SUMMARY, dest.getAdditionalInformation().getAdditionalInformationDocuments().get(0).getAdditionalInformationDocumentType());
        assertEquals("field1", dest.getAdditionalInformation().getAdditionalInformationDocuments().get(0).getFileId());
        assertEquals("file1", dest.getAdditionalInformation().getAdditionalInformationDocuments().get(0).getFileName());
        assertEquals("/temp/path", dest.getAdditionalInformation().getAdditionalInformationDocuments().get(0).getFilePath());
        assertEquals("originalFileName1", dest.getAdditionalInformation().getAdditionalInformationDocuments().get(0).getOriginalFileName());
        assertEquals("/temp/relativePath", dest.getAdditionalInformation().getAdditionalInformationDocuments().get(0).getRelativePath());
    }
    
    
    private AdditionalInformation createAdditionalInformation(){
    	AdditionalInformation additionalInformation = new AdditionalInformation();
    	additionalInformation.setAutopsyReport(true);
    	additionalInformation.setConsults(false);
    	additionalInformation.setDischargeSummary(true);
    	additionalInformation.setFlowCharts(true);
    	additionalInformation.setIrbReport(false);
    	additionalInformation.setLabReports(false);
    	additionalInformation.setObaForm(false);
    	additionalInformation.setOther(false);
    	additionalInformation.setPathologyReport(true);
    	additionalInformation.setProgressNotes(false);
    	additionalInformation.setRadiologyReports(true);
    	additionalInformation.setReferralLetters(false);
    	additionalInformation.setOtherInformation("some information");
    	
    	return additionalInformation;
    }
    
    
    private List<AdditionalInformationDocument> createAdditionalInformationDocuments(){
    	
    	List<AdditionalInformationDocument> additionalDocuments = new ArrayList<AdditionalInformationDocument>();
    	
    	AdditionalInformationDocument aID1 = new AdditionalInformationDocument();
    	aID1.setAdditionalInformationDocumentType(AdditionalInformationDocumentType.DISCHARGE_SUMMARY);
    	aID1.setFileId("field1");
    	aID1.setFileName("file1");
    	aID1.setFilePath("/temp/path");
    	aID1.setOriginalFileName("originalFileName1");
    	aID1.setRelativePath("/temp/relativePath");
    	
    	additionalDocuments.add(aID1);
    	
    	return additionalDocuments;
    }

}
