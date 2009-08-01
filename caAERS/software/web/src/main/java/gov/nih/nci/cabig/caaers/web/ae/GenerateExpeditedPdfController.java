package gov.nih.nci.cabig.caaers.web.ae;

import gov.nih.nci.cabig.caaers.api.AdeersReportGenerator;
import gov.nih.nci.cabig.caaers.api.AdverseEventReportSerializer;
import gov.nih.nci.cabig.caaers.dao.ExpeditedAdverseEventReportDao;
import gov.nih.nci.cabig.caaers.dao.report.ReportDao;
import gov.nih.nci.cabig.caaers.domain.ExpeditedAdverseEventReport;
import gov.nih.nci.cabig.caaers.domain.ReportStatus;
import gov.nih.nci.cabig.caaers.domain.report.Report;
import gov.nih.nci.cabig.caaers.domain.report.ReportContent;

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
	
	private ReportDao reportDao;
	private ExpeditedAdverseEventReportDao aeReportDao;
	private AdverseEventReportSerializer adverseEventReportSerializer;
	private AdeersReportGenerator adeersReportGenerator;
	
	public GenerateExpeditedPdfController() {
		setCommandClass(GenerateExpeditedPdfCommand.class);
    }
	
	private void generateOutput(String outFile,HttpServletResponse response,Integer reportId) throws Exception{
		String tempDir = System.getProperty("java.io.tmpdir");
		File file = new File(tempDir+File.separator+outFile);
		FileInputStream fileIn = new FileInputStream(file);
		
		response.setContentType( "application/x-download" );
		response.setHeader( "Content-Disposition", "attachment; filename="+outFile );
		response.setHeader("Content-length", String.valueOf(file.length()));
		response.setHeader("Pragma", "private");
		response.setHeader("Cache-control","private, must-revalidate");
		
		
		OutputStream out = response.getOutputStream();
		
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
		String strAeReportId = request.getParameter("aeReport");
		String strReportId = request.getParameter("reportId");
		String format = request.getParameter("format");
		
   		try {
   				Integer aeReportId = Integer.parseInt(strAeReportId);
   				Integer reportId = Integer.parseInt(strReportId);
    			ExpeditedAdverseEventReport aeReport = aeReportDao.getById(aeReportId);
    			Report report = reportDao.getById(reportId);
    			
    			//if report is completed xml should be obtained from saved data.
    			String xml = null;
    			if(report.getLastVersion().getReportStatus().equals(ReportStatus.COMPLETED) || report.getLastVersion().getReportStatus().equals(ReportStatus.AMENDED)){
    				//obtain the saved xml report
    				ReportContent reportContent = reportDao.getReportContent(report);
    				if(reportContent == null){
    					xml =  adverseEventReportSerializer.serialize(aeReport,report);
    				}else{
    					xml = new String(reportContent.getContent());
    				}
    			}else{
    				//obtain newly generated caaers xml
    				xml =  adverseEventReportSerializer.serialize(aeReport,report);
    			}
    			
                
    			
    			if (format.equals("pdf")) {
    			
	    			String pdfOutFile = "expeditedAdverseEventReport-"+aeReportId+".pdf";
	    	        // generate report and send ...
	    			//AdeersReportGenerator gen = new AdeersReportGenerator();
	    			adeersReportGenerator.generatePdf(xml,tempDir+File.separator+pdfOutFile);
	    			
	    			generateOutput(pdfOutFile,response,aeReportId);
    			} else if (format.equals("medwatchpdf")) {
 
	    			String pdfOutFile = "MedWatchReport-"+aeReportId+".pdf";
	    			
	    			adeersReportGenerator.generateMedwatchPdf(xml,tempDir+File.separator+pdfOutFile);
	    			
	    			generateOutput(pdfOutFile,response,aeReportId);
    			} else if (format.equals("dcp")) {
    				String pdfOutFile = "dcp-"+aeReportId+".pdf";
    				adeersReportGenerator.generateDcpSaeForm(xml, tempDir+File.separator+pdfOutFile);
    				
    				generateOutput(pdfOutFile,response,aeReportId);
    			} else if (format.equals("cioms")) {
    				String pdfOutFile = "cioms-"+aeReportId+".pdf";
    				adeersReportGenerator.generateCIOMS(xml, tempDir+File.separator+pdfOutFile);
    				
    				generateOutput(pdfOutFile,response,aeReportId);
    			} else if (format.equals("ciomssae")) {
    				String pdfOutFile = "ciomssae-"+aeReportId+".pdf";
    				adeersReportGenerator.generateCIOMSTypeForm(xml, tempDir+File.separator+pdfOutFile);
    				
    				generateOutput(pdfOutFile,response,aeReportId);
    			} else  {
	    			String xmlOutFile = "expeditedAdverseEventReport-"+aeReportId+".xml";
	    			
	    			BufferedWriter outw = new BufferedWriter(new FileWriter(tempDir+File.separator+xmlOutFile));
	    			outw.write(xml);
	    			outw.close();
	    			
	    			generateOutput(xmlOutFile,response,aeReportId);  				
    			}
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				throw new RemoteException ("Error generating PDF ",e);
			}

		
		return null;
	}

	public ReportDao getReportDao() {
		return reportDao;
	}

	public void setReportDao(ReportDao reportDao) {
		this.reportDao = reportDao;
	}

	public ExpeditedAdverseEventReportDao getAeReportDao() {
		return aeReportDao;
	}

	public void setAeReportDao(ExpeditedAdverseEventReportDao aeReportDao) {
		this.aeReportDao = aeReportDao;
	}

	public AdverseEventReportSerializer getAdverseEventReportSerializer() {
		return adverseEventReportSerializer;
	}

	public void setAdverseEventReportSerializer(
			AdverseEventReportSerializer adverseEventReportSerializer) {
		this.adverseEventReportSerializer = adverseEventReportSerializer;
	}

	public AdeersReportGenerator getAdeersReportGenerator() {
		return adeersReportGenerator;
	}

	public void setAdeersReportGenerator(AdeersReportGenerator adeersReportGenerator) {
		this.adeersReportGenerator = adeersReportGenerator;
	}
	
	
   
}
