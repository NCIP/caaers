/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.web.admin;

import gov.nih.nci.cabig.caaers.dao.report.ReportDao;
import gov.nih.nci.cabig.caaers.domain.report.Report;
import gov.nih.nci.cabig.caaers.tools.configuration.Configuration;
import gov.nih.nci.cabig.caaers.utils.ZipUtils;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author: Biju Joseph
 */
public class ReportTrackingLogsDownloadController extends AbstractController {
    private final Log log = LogFactory.getLog(getClass());
    private Configuration configuration;
    private ReportDao reportDao;

    @Override
    protected ModelAndView handleRequestInternal(HttpServletRequest request, HttpServletResponse response) {
        String baseFolder = configuration.get(Configuration.ESB_LOG_LOCATION);
        if (StringUtils.isEmpty(baseFolder)) {
            log.warn("ESB_LOG_LOCATION in configuration page is empty");
            return null;
        }

        if (!baseFolder.endsWith(File.separator)) {
            baseFolder = baseFolder + File.separator;
        }

        String reportIdStr = request.getParameter("r");
        String reportVersionIdStr = request.getParameter("rv");

        Report report = reportDao.getById(Integer.parseInt(reportIdStr));
        Map<String, String>  correlationMap = new LinkedHashMap<String, String>();
        Map<String, String> metadataMap = report.getMetaDataAsMap();
        for(String correlationId : report.getCorrelationIds()) {
            String dateStr = metadataMap.get(correlationId);
            correlationMap.put(correlationId, dateStr);
        }
        report.getCorrelationIds();


        
        File tempFile = ZipUtils.createZipFile(baseFolder, "SafetyReport", correlationMap);
        if(tempFile != null){


            FileInputStream fis = null;
            OutputStream out = null;
            try{
                fis = new FileInputStream(tempFile);
                out = response.getOutputStream();

                response.setContentType("application/x-download");
                response.setHeader("Content-Disposition",
                        String.format("attachment; filename=reportlogs_%s_%s.zip", reportIdStr, reportVersionIdStr));
                response.setHeader("Content-length", String.valueOf(tempFile.length()));
                response.setHeader("Pragma", "private");
                response.setHeader("Cache-control", "private, must-revalidate");

                IOUtils.copy(fis, out);
                out.flush();
            }catch (Exception e){
               log.error("Error while reading zip file ", e);
            }finally {
                IOUtils.closeQuietly(fis);
                IOUtils.closeQuietly(out);
            }

            FileUtils.deleteQuietly(tempFile);
        }


        return null;
    }

    public Configuration getConfiguration() {
        return configuration;
    }

    public void setConfiguration(Configuration configuration) {
        this.configuration = configuration;
    }

    public ReportDao getReportDao() {
        return reportDao;
    }

    public void setReportDao(ReportDao reportDao) {
        this.reportDao = reportDao;
    }
}
