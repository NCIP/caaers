/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.web.ae;


import gov.nih.nci.cabig.caaers.domain.AdditionalInformationDocument;
import gov.nih.nci.cabig.caaers.service.adverseevent.AdditionalInformationDocumentService;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang.math.RandomUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * Controller for downloading zip of all files for Additional information
 *
 * @author Saurabh Agrawal
 */

public class AdditionalInformationDocumentZipDownloadController extends AbstractController {
    private Logger log = Logger.getLogger(AdditionalInformationDocumentZipDownloadController.class);

    private AdditionalInformationDocumentService additionalInformationDocumentService;

    @Override
    protected ModelAndView handleRequestInternal(HttpServletRequest request, HttpServletResponse response) throws ServletRequestBindingException {


        Integer additionalInformationId = ServletRequestUtils.getRequiredIntParameter(request, "additionalInformationId");

        List<AdditionalInformationDocument> additionalInformationDocuments =
                additionalInformationDocumentService.findByAdditionalInformationId(additionalInformationId);

        File tempFile = null;
        ZipOutputStream zos = null;
        FileOutputStream fos = null;

        List<String> zipEntriesName = new ArrayList<String>();
        try {
            tempFile = File.createTempFile("additionalInformationFile" + System.currentTimeMillis() + RandomUtils.nextInt(1000), ".zip");

            fos = new FileOutputStream(tempFile);
            zos = new ZipOutputStream(fos);

            for (AdditionalInformationDocument additionalInformationDocument : additionalInformationDocuments) {
                String name = getUniqueZipEntryName(additionalInformationDocument, zipEntriesName);
                zipEntriesName.add(name);
                ZipEntry zipEntry = new ZipEntry(name);
                zos.putNextEntry(zipEntry);
                IOUtils.copy(new FileInputStream(additionalInformationDocument.getFile()), zos);
                zos.closeEntry();
            }

            zos.flush();

        } catch (Exception e) {

            log.error("Unable to create temp file", e);
            return null;
        } finally {
            if (zos != null) IOUtils.closeQuietly(zos);
            if (fos != null) IOUtils.closeQuietly(fos);
        }


        if (tempFile != null) {


            FileInputStream fis = null;
            OutputStream out = null;
            try {
                fis = new FileInputStream(tempFile);
                out = response.getOutputStream();

                response.setContentType("application/x-download");
                response.setHeader("Content-Disposition", "attachment; filename=" + additionalInformationId + ".zip");
                response.setHeader("Content-length", String.valueOf(tempFile.length()));
                response.setHeader("Pragma", "private");
                response.setHeader("Cache-control", "private, must-revalidate");

                IOUtils.copy(fis, out);
                out.flush();
            } catch (Exception e) {
                log.error("Error while reading zip file ", e);
            } finally {
                IOUtils.closeQuietly(fis);
                IOUtils.closeQuietly(out);
            }

            FileUtils.deleteQuietly(tempFile);
        }
        return null;
    }

    private String getUniqueZipEntryName(AdditionalInformationDocument additionalInformationDocument,
                                         List<String> zipEntriesName) {
        String originalFileName = additionalInformationDocument.getOriginalFileName();

        if (!zipEntriesName.contains(originalFileName)) {
            return originalFileName;
        } else {
            String fileNameWithFileType = additionalInformationDocument.getAdditionalInformationDocumentType().getName().toLowerCase() + "-" + additionalInformationDocument.getId()
                    + "-" + originalFileName;
            if (!zipEntriesName.contains(fileNameWithFileType)) {
                return fileNameWithFileType;
            } else {
                return fileNameWithFileType + System.currentTimeMillis() + RandomUtils.nextInt(1000);
            }
        }
    }


    @Required
    public void setAdditionalInformationDocumentService(AdditionalInformationDocumentService additionalInformationDocumentService) {
        this.additionalInformationDocumentService = additionalInformationDocumentService;
    }
}

