package testapp.web;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;
import org.springframework.web.servlet.view.RedirectView;

import testapp.bean.Person;
import testapp.dao.PersonDao;

public class PersonFormController extends SimpleFormController {

	private static final Log logger = LogFactory
			.getLog(PersonFormController.class);

	private PersonDao personDao;

	public PersonFormController() {
		setCommandName("person");
		setSessionForm(true);
		setBindOnNewForm(true);
	}

	protected Object formBackingObject(HttpServletRequest request)
			throws ServletException {
		// get the Owner referred to by id in the request
		Person p = null;
		String personId = request.getParameter("personId");
		if (personId != null) {
			logger.debug("Fetching person " + personId);
			p = getPersonDao().findById(Long.valueOf(personId));
		} else {
			logger.debug("No personId, creating new Person");
			p = new Person();
		}
		return p;
	}

	protected ModelAndView onSubmit(Object command) throws ServletException {
		Person p = (Person) command;
		if (p.getId() == null) {
			getPersonDao().save(p);
		} else {
			getPersonDao().update(p);
		}
		return new ModelAndView(new RedirectView(getSuccessView()));
	}

	public PersonDao getPersonDao() {
		return personDao;
	}

	public void setPersonDao(PersonDao personDao) {
		this.personDao = personDao;
	}

}
