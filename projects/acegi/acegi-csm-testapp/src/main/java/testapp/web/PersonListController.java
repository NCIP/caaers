/**
 * 
 */
package testapp.web;

import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import testapp.dao.PersonDao;

/**
 * @author joshua
 * 
 */
public class PersonListController implements Controller {
	
	private static final Log logger = LogFactory.getLog(PersonListController.class);

	private PersonDao personDao;
	
	public ModelAndView handleRequest(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		ModelAndView mav = null;
		PersonDao dao = getPersonDao();
		Collection persons = dao.getAll();
		logger.debug("Got " + persons.size() + " persons");
		Map model = new HashMap();
		model.put("persons", persons);
		mav = new ModelAndView("personList", "model", model);
		return mav;
	}
	
	

	public PersonDao getPersonDao() {
		return personDao;
	}

	public void setPersonDao(PersonDao personDao) {
		this.personDao = personDao;
	}

}
