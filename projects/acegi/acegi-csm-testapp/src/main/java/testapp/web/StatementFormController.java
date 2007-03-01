package testapp.web;

import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;
import org.springframework.web.servlet.view.RedirectView;

import testapp.bean.Person;
import testapp.bean.Statement;
import testapp.dao.PersonDao;
import testapp.dao.StatementDao;

public class StatementFormController extends SimpleFormController {

	private static final Log logger = LogFactory
			.getLog(StatementFormController.class);

	private StatementDao statementDao;

	private PersonDao personDao;

	public PersonDao getPersonDao() {
		return personDao;
	}

	public void setPersonDao(PersonDao personDao) {
		this.personDao = personDao;
	}

	public StatementFormController() {
		setCommandName("statement");
		setSessionForm(true);
		setBindOnNewForm(true);
	}

	protected Object formBackingObject(HttpServletRequest request)
			throws ServletException {
		Statement s = null;
		String statementId = request.getParameter("statementId");
		if (statementId == null) {
			logger.debug("creating new statement");
			s = new Statement();
			String personId = request.getParameter("personId");
			if (personId == null || personId.trim().length() == 0) {
				throw new RuntimeException(
						"no personId specified for new statement");
			}
			Person p = getPersonDao().findById(Long.valueOf(personId));
			s.setPerson(p);
		} else {
			logger.debug("fetching statement " + statementId);
			s = getStatementDao().findById(Long.valueOf(statementId));
		}
		return s;
	}

	protected ModelAndView onSubmit(Object command) throws ServletException {
		Statement s = (Statement) command;
		
		logger.debug("New Statement value: " + s.getValue());
		if (s.getId() == null) {
			logger.debug("Saving");
			s.setDate(new Date(System.currentTimeMillis()));
			getStatementDao().save(s);
		} else {
			logger.debug("Updating");
			getStatementDao().update(s);
		}
		Person p = s.getPerson();
		getPersonDao().update(p);
		p = getPersonDao().findById(p.getId());
		return new ModelAndView(new RedirectView(getSuccessView() + "?personId=" + p.getId()), "person", p);
	}

	public StatementDao getStatementDao() {
		return statementDao;
	}

	public void setStatementDao(StatementDao statementDao) {
		this.statementDao = statementDao;
	}

}
