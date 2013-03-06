/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.web.search;

import org.apache.commons.lang.StringUtils;
import org.springframework.validation.BindException;
import org.springframework.validation.Errors;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * 
 * @author Monish
 *
 */
public class SearchUserController extends SimpleFormController {
    
	public SearchUserController() {
        setCommandClass(SearchUserCommand.class);
        setBindOnNewForm(true);
    }

    @Override
    protected ModelAndView showForm(HttpServletRequest request, HttpServletResponse response, BindException errors) throws Exception {
        ModelAndView mv =  super.showForm(request, response, errors);
        SearchUserCommand command = (SearchUserCommand) getCommand(request);
        if(command.isPopupRequest()){
           return  new ModelAndView("search/user_search_popup", mv.getModel());      
        }
        return  new ModelAndView("search/user_search", mv.getModel());

    }

    @Override
    protected Object formBackingObject(HttpServletRequest request) throws Exception {

        SearchUserCommand command = new SearchUserCommand();

        String popupRequest = request.getParameter("popupRequest");
        if(StringUtils.isNotEmpty(popupRequest)){
            command.setPopupRequest(Boolean.valueOf(popupRequest));
        }

        String popupRequestType = request.getParameter("popupRequestType");
        if(StringUtils.isNotEmpty(popupRequestType)){
            command.setPopupRequestType(popupRequestType);
        }

        return command; 
    }

}
