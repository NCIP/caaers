/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.service.adverseevent;

import gov.nih.nci.cabig.caaers.dao.AdditionalInformationDocumentDao;
import gov.nih.nci.cabig.caaers.domain.AdditionalInformation;
import gov.nih.nci.cabig.caaers.domain.AdditionalInformationDocument;
import gov.nih.nci.cabig.caaers.domain.AdditionalInformationDocumentType;
import gov.nih.nci.cabig.caaers.tools.configuration.Configuration;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.RandomUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.Calendar;
import java.util.List;

/**
 * This class implements the Data access related operations for the {@link AdditionalInformationDocument } domain object.
 *
 * @author Saurabh Agrawal
 * @since 10/25/2012
 */
@Transactional(propagation = Propagation.REQUIRED)
public class AdditionalInformationDocumentService {

    private AdditionalInformationDocumentDao additionalInformationDocumentDao;

    private Configuration configuration;

    private static Log logger = LogFactory.getLog(AdditionalInformationDocumentService.class);

    public AdditionalInformationDocument uploadFile(String fileName, AdditionalInformation additionalInformation, InputStream inputStream,
                                                    AdditionalInformationDocumentType additionalInformationDocumentType) {


        try {

            String aeAttachmentsLocation = configuration.get(Configuration.AE_ATTACHMENTS_LOCATION);

            String directory = FilenameUtils.normalize(aeAttachmentsLocation + "/" + additionalInformation.getId());

            String extension = StringUtils.isNotBlank(FilenameUtils.getExtension(fileName)) ? "." + FilenameUtils.getExtension(fileName) : "";


            String filePath = FilenameUtils.normalize(directory + "/" + FilenameUtils.getBaseName(fileName) + Calendar.getInstance().getTimeInMillis() + RandomUtils.nextInt(100) + extension);

            if (logger.isDebugEnabled()) {
                logger.debug(String.format("creating file  %s of type %s for additional information %s at %s ", fileName, additionalInformationDocumentType,
                        additionalInformation.getId(), filePath));
            }

            FileUtils.forceMkdir(new File(directory));

            File file = new File(filePath);
            if (file.createNewFile()) {
                long bytesCopied = IOUtils.copyLarge(inputStream, new FileOutputStream(file));

                AdditionalInformationDocument additionalInformationDocument = new AdditionalInformationDocument();
                additionalInformationDocument.setAdditionalInformationDocumentType(additionalInformationDocumentType);
                additionalInformationDocument.setAdditionalInformation(additionalInformation);
                additionalInformationDocument.setFileId(DigestUtils.md5Hex(file.getAbsolutePath()));
                additionalInformationDocument.setOriginalFileName(fileName);
                additionalInformationDocument.setFileName(file.getName());

                additionalInformationDocument.setFilePath(file.getCanonicalPath());
                additionalInformationDocument.setRelativePath(file.getAbsolutePath());
                additionalInformationDocument.setFileSize(bytesCopied);
                additionalInformationDocumentDao.save(additionalInformationDocument);
                if (logger.isDebugEnabled()) {
                    logger.debug(String.format("successfully created file  %s of type %s for additional information %s at %s. File information is - %s ",
                            fileName, additionalInformationDocumentType,
                            additionalInformation.getId(), filePath, additionalInformationDocument));
                }

                return additionalInformationDocument;
            } else {
                String errorMessage = String.format("error while creating  file  %s of type %s for additional information %s ",
                        fileName, additionalInformationDocumentType,
                        additionalInformation.getId());
                throw new RuntimeException(errorMessage);
            }
        } catch (Exception e) {
            String errorMessage = String.format("error while creating  file  %s of type %s for additional information %s ",
                    fileName, additionalInformationDocumentType,
                    additionalInformation.getId());

            logger.error(errorMessage, e);
            return null;
        }

    }


    public AdditionalInformationDocument findByFileId(String fileId) {
        return additionalInformationDocumentDao.findByFileId(fileId);

    }

    public void delete(String fileId) {
        AdditionalInformationDocument additionalInformationDocument = findByFileId(fileId);
        File file = additionalInformationDocument.getFile();
        if (file.exists()) {
            if (file.delete()) {
                additionalInformationDocumentDao.delete(additionalInformationDocument);
            } else {
                String errorMessage = "cannot delete document - " + additionalInformationDocument;
                logger.error(errorMessage);
                throw new RuntimeException(errorMessage);
            }

        } else {
            additionalInformationDocumentDao.delete(additionalInformationDocument);
        }
    }


    public List<AdditionalInformationDocument> findByAdditionalInformationId(Integer additionalInformationId) {
        return additionalInformationDocumentDao.findByAdditionalInformationId(additionalInformationId);
    }

    @Required
    public void setAdditionalInformationDocumentDao(AdditionalInformationDocumentDao additionalInformationDocumentDao) {
        this.additionalInformationDocumentDao = additionalInformationDocumentDao;
    }

    @Required
    public void setConfiguration(Configuration configuration) {
        this.configuration = configuration;
    }
}
