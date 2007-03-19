package gov.nih.nci.cabig.caaers.web.rule;


import gov.nih.nci.cabig.caaers.CaaersSystemException;
import gov.nih.nci.cabig.caaers.rules.ui.RuleUi;

import java.io.InputStream;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

public class ContextListener implements ServletContextListener {

	public void contextDestroyed(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	public void contextInitialized(ServletContextEvent servletContextEvent) {
		InputStream inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("rules-ui.xml");
		try {
			Unmarshaller unmarshaller = JAXBContext.newInstance("gov.nih.nci.cabig.caaers.rules.ui").createUnmarshaller();
			RuleUi ruleUi = (RuleUi)unmarshaller.unmarshal(inputStream);
			servletContextEvent.getServletContext().setAttribute("ruleUi", ruleUi);
		} catch (JAXBException e) {
			throw new CaaersSystemException(e.getMessage(), e);
		}		
	}
}