package gov.nih.nci.cabig.caaers.web.ae;


import gov.nih.nci.cabig.caaers.service.adverseevent.AdditionalInformationDocumentService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;
import org.springframework.web.servlet.view.json.MappingJacksonJsonView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Controller for deleting files for Additional information
 *
 * @author Saurabh Agrawal
 */

public class AdditionalInformationDocumentDeleteController extends AbstractController {

    private AdditionalInformationDocumentService additionalInformationDocumentService;


    @Override
    protected ModelAndView handleRequestInternal(HttpServletRequest request, HttpServletResponse response) throws ServletRequestBindingException, IOException {


        String fileId = request.getParameter("fileId");

        additionalInformationDocumentService.delete(fileId);
        MappingJacksonJsonView jsonView = new MappingJacksonJsonView();

        jsonView.setPrefixJson(false);

        ModelAndView modelAndView = new ModelAndView(jsonView);


        modelAndView.addObject("success", true);

        return modelAndView;
    }


    @Required
    public void setAdditionalInformationDocumentService(AdditionalInformationDocumentService additionalInformationDocumentService) {
        this.additionalInformationDocumentService = additionalInformationDocumentService;
    }


}

