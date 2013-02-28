/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.web.ae;


import gov.nih.nci.cabig.caaers.domain.AdditionalInformationDocument;
import gov.nih.nci.cabig.caaers.service.adverseevent.AdditionalInformationDocumentService;
import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;

/**
 * Controller for downloading files for Additional information
 *
 * @author Saurabh Agrawal
 */

public class AdditionalInformationDocumentDownloadController extends AbstractController {
    private Logger log = Logger.getLogger(AdditionalInformationDocumentDownloadController.class);

    private AdditionalInformationDocumentService additionalInformationDocumentService;

    @Override
    protected ModelAndView handleRequestInternal(HttpServletRequest request, HttpServletResponse response) throws ServletRequestBindingException {


        String fileId = request.getParameter("fileId");

        AdditionalInformationDocument additionalInformationDocument = additionalInformationDocumentService.findByFileId(fileId);
        if (additionalInformationDocument != null) {

            File file = additionalInformationDocument.getFile();

            if (file.exists()) {

                FileInputStream fis = null;
                OutputStream out = null;
                try {
                    fis = new FileInputStream(file);
                    out = response.getOutputStream();

                    response.setContentType("application/x-download");
                    response.setHeader("Content-Disposition", "attachment; filename=\"" + additionalInformationDocument.getOriginalFileName() + "\"");
                    response.setHeader("Content-length", String.valueOf(additionalInformationDocument.getFileSize()));
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
            } else {
                logger.error(String.format("not sure why file for %s does not exist. Looks like files have been moved form file path",
                        additionalInformationDocument));
            }
        }

        return null;
    }


    @Required
    public void setAdditionalInformationDocumentService(AdditionalInformationDocumentService additionalInformationDocumentService) {
        this.additionalInformationDocumentService = additionalInformationDocumentService;
    }
}

