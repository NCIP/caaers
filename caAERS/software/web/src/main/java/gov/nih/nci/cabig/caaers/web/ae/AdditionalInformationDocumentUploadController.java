package gov.nih.nci.cabig.caaers.web.ae;


import gov.nih.nci.cabig.caaers.dao.ExpeditedAdverseEventReportDao;
import gov.nih.nci.cabig.caaers.domain.AdditionalInformationDocument;
import gov.nih.nci.cabig.caaers.domain.AdditionalInformationDocumentType;
import gov.nih.nci.cabig.caaers.domain.ExpeditedAdverseEventReport;
import gov.nih.nci.cabig.caaers.service.adverseevent.AdditionalInformationDocumentService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;
import org.springframework.web.servlet.view.json.MappingJacksonJsonView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Controller for uploading files for Additional information
 *
 * @author Saurabh Agrawal
 */

public class AdditionalInformationDocumentUploadController extends AbstractController {

    private AdditionalInformationDocumentService additionalInformationDocumentService;

    protected ExpeditedAdverseEventReportDao expeditedAdverseEventReportDao;


    @Override
    protected ModelAndView handleRequestInternal(HttpServletRequest request, HttpServletResponse response) throws ServletRequestBindingException, IOException {

        MappingJacksonJsonView jsonView = new MappingJacksonJsonView();

        jsonView.setPrefixJson(false);

        ModelAndView modelAndView = new ModelAndView(jsonView);

        try {
            int aeReport = ServletRequestUtils.getIntParameter(request, "aeReport");

            ExpeditedAdverseEventReport expeditedAdverseEventReport = expeditedAdverseEventReportDao.getById(
                    aeReport);


            String additionalInformationDocumentType = request.getParameter("additionalInformationDocumentType");

            AdditionalInformationDocument additionalInformationDocument =
                    additionalInformationDocumentService.uploadFile(request.getParameter("qqfile"), expeditedAdverseEventReport.getAdditionalInformation(),
                            request.getInputStream(), AdditionalInformationDocumentType.getAdditionalInformationDocumentType(additionalInformationDocumentType));

            if (logger.isDebugEnabled()) {
                logger.debug("successfully saved following document" + additionalInformationDocument);
            }


            if (additionalInformationDocument != null) {

                modelAndView.addObject("success", true);
                modelAndView.addObject("fileId", additionalInformationDocument.getFileId());
                modelAndView.addObject("fileSize", additionalInformationDocument.getByteCountToDisplaySize());
            } else {
                modelAndView.addObject("error", "error while upload file");
            }
        } catch (Exception e) {
            modelAndView.addObject("error", "error while upload file");
            logger.error("error while upload file", e);
        }
        return modelAndView;
    }


    @Required
    public void setAdditionalInformationDocumentService(AdditionalInformationDocumentService additionalInformationDocumentService) {
        this.additionalInformationDocumentService = additionalInformationDocumentService;
    }


    @Required
    public void setExpeditedAdverseEventReportDao(ExpeditedAdverseEventReportDao expeditedAdverseEventReportDao) {
        this.expeditedAdverseEventReportDao = expeditedAdverseEventReportDao;
    }
}

