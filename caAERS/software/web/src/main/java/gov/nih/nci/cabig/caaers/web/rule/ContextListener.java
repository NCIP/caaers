/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.web.rule;

import gov.nih.nci.cabig.caaers.CaaersSystemException;

import java.io.InputStream;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import com.semanticbits.rules.ui.RuleUi;
import org.apache.commons.io.IOUtils;

public class ContextListener implements ServletContextListener {

    public void contextDestroyed(ServletContextEvent servletContextEvent) {
       servletContextEvent.getServletContext().removeAttribute("ruleUi-general");
       servletContextEvent.getServletContext().removeAttribute("ruleUi-safety");
    }

    public void contextInitialized(ServletContextEvent servletContextEvent) {

        RuleUi ruleUi = load("rules-ui.xml");
        servletContextEvent.getServletContext().setAttribute("ruleUi-general", ruleUi);

        ruleUi = load("rules-ui-safety-monitoring.xml");
        servletContextEvent.getServletContext().setAttribute("ruleUi-safety", ruleUi);

    }
    
    private RuleUi load(String fileName){

        InputStream inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream(fileName);
        try {
            Unmarshaller unmarshaller = JAXBContext.newInstance("com.semanticbits.rules.ui").createUnmarshaller();
            RuleUi ruleUi = (RuleUi) unmarshaller.unmarshal(inputStream);
            return ruleUi;
        } catch (JAXBException e) {
            throw new CaaersSystemException(e.getMessage(), e);
        }finally {
            IOUtils.closeQuietly(inputStream);
        }
    }
}
