package gov.nih.nci.cabig.caaers.web.study;

import gov.nih.nci.cabig.caaers.dao.report.ReportDefinitionDao;
import gov.nih.nci.cabig.caaers.dao.StudyDao;
import gov.nih.nci.cabig.caaers.domain.report.ReportDefinition;
import gov.nih.nci.cabig.caaers.domain.*;
import gov.nih.nci.cabig.caaers.reportdefinition.ReportDefinitions;
import gov.nih.nci.cabig.caaers.service.migrator.ReportDefinitionConverter;
import gov.nih.nci.cabig.caaers.service.migrator.StudyConverter;
import gov.nih.nci.cabig.caaers.web.rule.notification.ReportDefinitionCommand;
import gov.nih.nci.cabig.caaers.webservice.ObjectFactory;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.OutputStream;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;

import org.apache.commons.io.FileUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractCommandController;

import com.semanticbits.rules.utils.RuleUtil;


public class ExportStudyController extends AbstractCommandController{

    protected final Log log = LogFactory.getLog(getClass());
    
	protected StudyDao studyDao;
	protected StudyConverter converter;

	public ExportStudyController(){
		setCommandClass(ReportDefinitionCommand.class);
	}

    

	@Override
	protected ModelAndView handle(HttpServletRequest request, HttpServletResponse response, Object o, BindException e) throws Exception {

        Integer studyID = Integer.valueOf(request.getParameter("id"));
        Study study = studyDao.getById(studyID);
        studyDao.initialize(study);

        // START study export pre-population
        study.setCoordinatingCenter(new CoordinatingCenter());
        study.getCoordinatingCenter().setStudyCoordinatingCenter(study.getStudyCoordinatingCenter());

        study.setFundingSponsor(new FundingSponsor());
        study.getFundingSponsor().setStudyFundingSponsor(study.getStudyFundingSponsors().get(0));

        for (OrganizationAssignedIdentifier id : study.getOrganizationAssignedIdentifiers()) {
            if (id.getOrganization().equals(study.getFundingSponsor().getStudyFundingSponsor().getOrganization())) {
                study.getFundingSponsor().setOrganizationAssignedIdentifier(id);
                study.getFundingSponsor().getOrganizationAssignedIdentifier().setPrimaryIndicator(true);
                break;
            }
        }
        for (OrganizationAssignedIdentifier id : study.getOrganizationAssignedIdentifiers()) {
            if (id.getOrganization().equals(study.getCoordinatingCenter().getStudyCoordinatingCenter().getOrganization())) {
                study.getCoordinatingCenter().setOrganizationAssignedIdentifier(id);
                study.getCoordinatingCenter().getOrganizationAssignedIdentifier().setPrimaryIndicator(false);
                break;
            }
        }
        // END study export pre-population
        
        gov.nih.nci.cabig.caaers.webservice.Studies studies = converter.convertStudyDomainToStudyDto(study);

        //Marshall the Data Transfer Object according to Study.xsd schema,
        //and download it to the client machine.
		try {
			String tempDir = System.getProperty("java.io.tmpdir");
			String fileName = "ExportedStudy_" + study.getPrimaryIdentifierValue();
			fileName = RuleUtil.getStringWithoutSpaces(fileName);
            
			StringWriter sw = new StringWriter();
			JAXBContext jaxbContext = JAXBContext.newInstance("gov.nih.nci.cabig.caaers.webservice");
            
	    	Marshaller marshaller = jaxbContext.createMarshaller();
	    	marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true) ;
	    	marshaller.marshal(studies, sw);
	        BufferedWriter out = new BufferedWriter(new FileWriter(tempDir + fileName + ".xml"));
	        out.write(sw.toString());
            out.flush();
	        out.close();

	        response.setContentType("application/xml");
	        response.setHeader("Content-Disposition", "attachment; filename=" + fileName + ".xml");
	        response.setHeader("Content-length", String.valueOf(sw.toString().length()));
	        response.setHeader("Pragma", "private");
	        response.setHeader("Cache-control", "private, must-revalidate");

	        OutputStream outputStream = response.getOutputStream();
	        File file = new File(tempDir + fileName + ".xml");
	        FileInputStream fileIn = new FileInputStream(file);
	        byte[] buffer = new byte[2048];
            int bytesRead = fileIn.read(buffer);
            while (bytesRead >= 0) {
                if (bytesRead > 0) outputStream.write(buffer, 0, bytesRead);
                bytesRead = fileIn.read(buffer);
            }
            outputStream.flush();
            outputStream.close();
            fileIn.close();

		} catch (Exception ex) {
            log.error(ex);
            ex.printStackTrace();
		}
		return null;
	}

    public StudyDao getStudyDao() {
        return studyDao;
    }

    public void setStudyDao(StudyDao studyDao) {
        this.studyDao = studyDao;
    }

    public StudyConverter getConverter() {
        return converter;
    }

    public void setConverter(StudyConverter converter) {
        this.converter = converter;
    }
}