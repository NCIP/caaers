package gov.nih.nci.cabig.caaers.service.adverseevent;

import gov.nih.nci.cabig.caaers.CaaersDbNoSecurityTestCase;
import gov.nih.nci.cabig.caaers.dao.ExpeditedAdverseEventReportDao;
import gov.nih.nci.cabig.caaers.domain.AdditionalInformation;
import gov.nih.nci.cabig.caaers.domain.AdditionalInformationDocument;
import gov.nih.nci.cabig.caaers.domain.AdditionalInformationDocumentType;
import gov.nih.nci.cabig.caaers.domain.ExpeditedAdverseEventReport;
import org.apache.commons.io.FileUtils;
import org.springframework.core.io.ClassPathResource;

import java.io.File;
import java.io.IOException;

/**
 */
public class AdditionalInformationDocumentServiceIntegrationTest extends CaaersDbNoSecurityTestCase {

    private AdditionalInformationDocumentService additionalInformationDocumentService;

    private static final String FILE_NAME = "test.txt";

    private ClassPathResource classPathResource = null;

    private final AdditionalInformationDocumentType ADDITIONAL_INFORMATION_DOCUMENT_TYPE = AdditionalInformationDocumentType.OTHER_INFORMATION;
    private AdditionalInformation additionalInformation;


    private ExpeditedAdverseEventReportDao expeditedAdverseEventReportDao;

    @Override
    protected void setUp() throws Exception {
        super.setUp();


        classPathResource = new ClassPathResource("/gov/nih/nci/cabig/caaers/service/adverseevent/test.txt");

        additionalInformationDocumentService = (AdditionalInformationDocumentService) getDeployedApplicationContext().getBean("additionalInformationDocumentService");
        expeditedAdverseEventReportDao = (ExpeditedAdverseEventReportDao) getDeployedApplicationContext().getBean("expeditedAdverseEventReportDao");
        ExpeditedAdverseEventReport expeditedAdverseEventReport = expeditedAdverseEventReportDao.getById(-1);
        additionalInformation = expeditedAdverseEventReport.getAdditionalInformation();


    }

    public void testFileUploadSameFileMultipleTimes() throws IOException {

        for (int i = 0; i < 3; i++) {
            AdditionalInformationDocument additionalInformationDocument = additionalInformationDocumentService.uploadFile(FILE_NAME, additionalInformation,
                    classPathResource.getInputStream(), ADDITIONAL_INFORMATION_DOCUMENT_TYPE);


            AdditionalInformationDocument additionalInformationDocumentServiceByFileId = additionalInformationDocumentService.findByFileId(additionalInformationDocument.getFileId());

            assertNotNull(additionalInformationDocument);
            assertNotNull(additionalInformationDocument.getId());

            assertEquals(ADDITIONAL_INFORMATION_DOCUMENT_TYPE, additionalInformationDocument.getAdditionalInformationDocumentType());

            assertTrue(FileUtils.contentEquals(classPathResource.getFile(), new File(additionalInformationDocument.getFilePath())));


            assertNotNull(additionalInformationDocumentServiceByFileId);
            assertNotNull(additionalInformationDocumentServiceByFileId.getFile());
            assertTrue(additionalInformationDocumentServiceByFileId.getFile().exists());
            assertTrue(FileUtils.contentEquals(classPathResource.getFile(), additionalInformationDocumentServiceByFileId.getFile()));

        }
    }
}
