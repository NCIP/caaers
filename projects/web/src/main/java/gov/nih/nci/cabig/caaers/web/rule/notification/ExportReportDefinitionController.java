package gov.nih.nci.cabig.caaers.web.rule.notification;

import gov.nih.nci.cabig.caaers.dao.report.ReportDefinitionDao;
import gov.nih.nci.cabig.caaers.domain.report.ReportDefinition;
import gov.nih.nci.cabig.caaers.reportdefinition.ReportDefinitions;
import gov.nih.nci.cabig.caaers.rules.common.RuleUtil;
import gov.nih.nci.cabig.caaers.service.migrator.ReportDefinitionConverter;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.OutputStream;
import java.io.StringWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;

import org.apache.commons.io.FileUtils;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractCommandController;


public class ExportReportDefinitionController extends AbstractCommandController{
	
	protected ReportDefinitionDao reportDefinitionDao;
	protected ReportDefinitionConverter reportDefinitionConverter;
	
	public ExportReportDefinitionController(){
		setCommandClass(ReportDefinitionCommand.class);
	}
	
	@Override
	protected ModelAndView handle(HttpServletRequest request,
			HttpServletResponse response, Object arg2, BindException arg3)
			throws Exception {
		
		// fetch report definition Id
        Integer rpDefId = Integer.valueOf(request.getParameter("repDefId"));
        // feth the ReportDefinition by Id
        ReportDefinition rpDef = reportDefinitionDao.getById(rpDefId);
        // initialize all the lazy collections in rpDef
        reportDefinitionDao.initialize(rpDef);
		
        //Convert the Domain Object to Data Transfer Object.
        ReportDefinitions reportDefinitions = reportDefinitionConverter.domainToDto(rpDef);
        
        //Marshall the Data Transfer Object according to ReportDefinition.xsd schema,
        //and download it to the client machine.
		try {
			String tempDir = System.getProperty("java.io.tmpdir");
			String fileName = reportDefinitions.getReportDefinition().get(0).getName();
			fileName = RuleUtil.getStringWithoutSpaces(fileName);
			StringWriter sw = new StringWriter();
			JAXBContext jaxbContext = JAXBContext.newInstance("gov.nih.nci.cabig.caaers.reportdefinition");
	    	Marshaller marshaller = jaxbContext.createMarshaller();
	    	marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true) ;
	    	marshaller.marshal(reportDefinitions, sw);
	        BufferedWriter out = new BufferedWriter(new FileWriter(tempDir+fileName+".xml"));
	        out.write(sw.toString());
	        out.close();
	        
	        response.setContentType("application/xml");
	        response.setHeader("Content-Disposition", "attachment; filename="+fileName+".xml");
	        response.setHeader("Content-length", String.valueOf(sw.toString().length()));
	        response.setHeader("Pragma", "private");
	        response.setHeader("Cache-control", "private, must-revalidate");
	
	        OutputStream outputStream = response.getOutputStream();
	        File file = new File(tempDir+fileName+".xml");
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
            FileUtils.deleteQuietly(file);
        
		}catch (Exception e) {
		
		}
		return null;
	}

	public void setReportDefinitionDao(ReportDefinitionDao reportDefinitionDao) {
		this.reportDefinitionDao = reportDefinitionDao;
	}

	public void setReportDefinitionConverter(
			ReportDefinitionConverter reportDefinitionConverter) {
		this.reportDefinitionConverter = reportDefinitionConverter;
	}
	
	
}
