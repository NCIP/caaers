package gov.nih.nci.cabig.caaers.web.ae;

import gov.nih.nci.cabig.caaers.api.AdeersReportGenerator;
import gov.nih.nci.cabig.caaers.api.AdverseEventReportSerializer;
import gov.nih.nci.cabig.caaers.dao.ExpeditedAdverseEventReportDao;
import gov.nih.nci.cabig.caaers.domain.ExpeditedAdverseEventReport;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.OutputStream;
import java.rmi.RemoteException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractCommandController;

public class GenerateExpeditedPdfController extends AbstractCommandController {
	
	public GenerateExpeditedPdfController() {
		setCommandClass(GenerateExpeditedPdfCommand.class);
    }
	
	private void generateOutput(String outFile,HttpServletResponse response,String reportId) throws Exception{
		String tempDir = System.getProperty("java.io.tmpdir");
		File file = new File(tempDir+File.separator+outFile);
		FileInputStream fileIn = new FileInputStream(file);
		OutputStream out = response.getOutputStream();
		response.setContentType( "application/x-download" );
		response.setHeader( "Content-Disposition", "attachment; filename="+outFile );
		 
		byte[] buffer = new byte[2048];
		int bytesRead = fileIn.read(buffer);
		while (bytesRead >= 0) {
		  if (bytesRead > 0)
		    out.write(buffer, 0, bytesRead);
		    bytesRead = fileIn.read(buffer);
		}
		out.flush();
		out.close();
		fileIn.close();
	}

	@Override
	protected ModelAndView handle(HttpServletRequest request, HttpServletResponse response, Object arg2, BindException arg3) throws Exception {

		
		String tempDir = System.getProperty("java.io.tmpdir");
		String reportId = request.getParameter("aeReport");
		String format = request.getParameter("format");
   		try {
    			ExpeditedAdverseEventReportDao expeditedAdverseEventReportDao = (ExpeditedAdverseEventReportDao)getApplicationContext().getBean("expeditedAdverseEventReportDao");
    			ExpeditedAdverseEventReport aeReport = expeditedAdverseEventReportDao.getById(Integer.parseInt(reportId));
    			AdverseEventReportSerializer ser = new AdverseEventReportSerializer();
    			String xml = ser.serialize(aeReport);
    			//System.out.print(xml);
    			
    			if (format.equals("pdf")) {
    			
	    			String pdfOutFile = "expeditedAdverseEventReport-"+reportId+".pdf";
	    			
	    			AdeersReportGenerator gen = new AdeersReportGenerator();
	    			gen.generatePdf(xml,tempDir+File.separator+pdfOutFile);
	    			
	    			generateOutput(pdfOutFile,response,reportId);
    			} else if (format.equals("medwatchpdf")) {
 
	    			String pdfOutFile = "MedWatchReport-"+reportId+".pdf";
	    			
	    			AdeersReportGenerator gen = new AdeersReportGenerator();
	    			gen.generateMedwatchPdf(xml,tempDir+File.separator+pdfOutFile);
	    			
	    			generateOutput(pdfOutFile,response,reportId);
    			} else if (format.equals("dcp")) {
    				String pdfOutFile = "dcp-"+reportId+".pdf";
    				AdeersReportGenerator gen = new AdeersReportGenerator();
    				gen.generateDcpSaeForm(xml, tempDir+File.separator+pdfOutFile);
    				
    				generateOutput(pdfOutFile,response,reportId);
    			} else if (format.equals("cioms")) {
    				String pdfOutFile = "cioms-"+reportId+".pdf";
    				AdeersReportGenerator gen = new AdeersReportGenerator();
    				gen.generateCIOMS(xml, tempDir+File.separator+pdfOutFile);
    				
    				generateOutput(pdfOutFile,response,reportId);
    			} else if (format.equals("ciomssae")) {
    				String pdfOutFile = "ciomssae-"+reportId+".pdf";
    				AdeersReportGenerator gen = new AdeersReportGenerator();
    				gen.generateCIOMSTypeForm(xml, tempDir+File.separator+pdfOutFile);
    				
    				generateOutput(pdfOutFile,response,reportId);
    			} else  {
	    			String xmlOutFile = "expeditedAdverseEventReport-"+reportId+".xml";
	    			
	    			BufferedWriter outw = new BufferedWriter(new FileWriter(tempDir+File.separator+xmlOutFile));
	    			outw.write(xml);
	    			outw.close();
	    			
	    			generateOutput(xmlOutFile,response,reportId);  				
    			}
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				throw new RemoteException ("Error generating PDF ",e);
			}

		
		return null;
	}
   
}