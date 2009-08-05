package gov.nih.nci.cabig.caaers.web.ae;

import gov.nih.nci.cabig.caaers.api.BlankFormGenerator;
import gov.nih.nci.cabig.caaers.api.AdverseEventReportSerializer;
import gov.nih.nci.cabig.caaers.dao.StudyDao;
import gov.nih.nci.cabig.caaers.dao.EpochDao;
import gov.nih.nci.cabig.caaers.domain.Study;
import gov.nih.nci.cabig.caaers.domain.Epoch;

import java.io.*;
import java.rmi.RemoteException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractCommandController;

public class GenerateBlankFormPdfController extends AbstractCommandController {

	private StudyDao studyDao;
	private EpochDao epochDao;
    private BlankFormGenerator g;

	public GenerateBlankFormPdfController() {
		setCommandClass(GenerateExpeditedPdfCommand.class);
        g = new BlankFormGenerator();
    }

	private void generateOutput(String PDFAbsolutePath, String PDFName, HttpServletResponse response) throws Exception{
		File file = new File(PDFAbsolutePath);
		FileInputStream fileIn = new FileInputStream(file);

		response.setContentType("application/x-download");
		response.setHeader("Content-Disposition", "attachment; filename=" + PDFName);
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
        String PDFName = "AE-Blank.pdf";  
        String PDFAbsolutePath = tempDir + File.separator + System.currentTimeMillis()+ "_" + PDFName;

        String studyID = request.getParameter("st");
        String subjectID = request.getParameter("sb");
        String courseID = request.getParameter("cs");
        String epochID = request.getParameter("ep");

        try {
            int studyIDint = Integer.parseInt(studyID);
            Study study = studyDao.getById(studyIDint);

            int epochIDint = Integer.parseInt(epochID);
            Epoch epoch = epochDao.getById(epochIDint);
            String xmlData = g.serialize(study, epoch);

            // Save the XML in temp
            FileOutputStream out = new FileOutputStream(tempDir + File.separator + "AE-Blank.xml");
            out.write(xmlData.getBytes());
            out.close();

            g.generatePdf(xmlData, PDFAbsolutePath);
            generateOutput(PDFAbsolutePath, PDFName, response);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            throw e;
        }
        catch (Exception e) {
            e.printStackTrace();
            throw new RemoteException("Error generating PDF ", e);
        }
        return null;
	}

    public StudyDao getStudyDao() {
        return studyDao;
    }

    public void setStudyDao(StudyDao studyDao) {
        this.studyDao = studyDao;
    }

    public EpochDao getEpochDao() {
        return epochDao;
    }

    public void setEpochDao(EpochDao epochDao) {
        this.epochDao = epochDao;
    }
}