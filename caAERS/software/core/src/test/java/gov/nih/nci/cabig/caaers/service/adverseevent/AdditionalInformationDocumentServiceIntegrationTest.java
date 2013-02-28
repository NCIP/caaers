/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.service.adverseevent;

import gov.nih.nci.cabig.caaers.CaaersDbNoSecurityTestCase;
import gov.nih.nci.cabig.caaers.dao.ExpeditedAdverseEventReportDao;
import gov.nih.nci.cabig.caaers.domain.AdditionalInformation;
import gov.nih.nci.cabig.caaers.domain.AdditionalInformationDocument;
import gov.nih.nci.cabig.caaers.domain.AdditionalInformationDocumentType;
import gov.nih.nci.cabig.caaers.domain.ExpeditedAdverseEventReport;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.RandomStringUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 */
public class AdditionalInformationDocumentServiceIntegrationTest extends CaaersDbNoSecurityTestCase {

    private AdditionalInformationDocumentService additionalInformationDocumentService;



    private final AdditionalInformationDocumentType ADDITIONAL_INFORMATION_DOCUMENT_TYPE = AdditionalInformationDocumentType.OTHER_INFORMATION;
    private AdditionalInformation additionalInformation;


    private ExpeditedAdverseEventReportDao expeditedAdverseEventReportDao;
    private File file;

    @Override
    protected void setUp() throws Exception {
        super.setUp();

        file = File.createTempFile(RandomStringUtils.randomAlphabetic(4),".txt");

        FileUtils.writeStringToFile(file,"sample text");

        additionalInformationDocumentService = (AdditionalInformationDocumentService) getDeployedApplicationContext().getBean("additionalInformationDocumentService");
        expeditedAdverseEventReportDao = (ExpeditedAdverseEventReportDao) getDeployedApplicationContext().getBean("expeditedAdverseEventReportDao");
        ExpeditedAdverseEventReport expeditedAdverseEventReport = expeditedAdverseEventReportDao.getById(-1);
        additionalInformation = expeditedAdverseEventReport.getAdditionalInformation();


    }

    public void testFileUploadSameFileMultipleTimes() throws IOException {

        for (int i = 0; i < 3; i++) {
            AdditionalInformationDocument additionalInformationDocument = additionalInformationDocumentService.uploadFile(file.getName(), additionalInformation,
                    new FileInputStream(file), ADDITIONAL_INFORMATION_DOCUMENT_TYPE);


            AdditionalInformationDocument additionalInformationDocumentServiceByFileId = additionalInformationDocumentService.findByFileId(additionalInformationDocument.getFileId());

            assertNotNull(additionalInformationDocument);
            assertNotNull(additionalInformationDocument.getId());

            assertEquals(ADDITIONAL_INFORMATION_DOCUMENT_TYPE, additionalInformationDocument.getAdditionalInformationDocumentType());

            assertTrue(FileUtils.contentEquals(file, new File(additionalInformationDocument.getFilePath())));


            assertNotNull(additionalInformationDocumentServiceByFileId);
            assertNotNull(additionalInformationDocumentServiceByFileId.getFile());
            assertTrue(additionalInformationDocumentServiceByFileId.getFile().exists());
            assertTrue(FileUtils.contentEquals(file, additionalInformationDocumentServiceByFileId.getFile()));

        }
    }
}
