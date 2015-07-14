/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.web.admin;

import gov.nih.nci.cabig.caaers.tools.configuration.Configuration;
import gov.nih.nci.cabig.caaers.utils.DateUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import gov.nih.nci.cabig.caaers.utils.ZipUtils;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

/**
 * @author: Biju Joseph
 */
public class CTEPDataIntegrationLogDownloadController extends AbstractController {
    private final Log log = LogFactory.getLog(getClass());
    private Configuration configuration;

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

        String dateStr = request.getParameter("dstr");
        String correlationId = request.getParameter("cstr");
        String entity = request.getParameter("entity");

        if (StringUtils.isEmpty(dateStr)) {
            log.warn("dstr in request is empty");
            return null;
        }
        if (StringUtils.isEmpty(correlationId)) {
            log.warn("cstr in request is empty");
            return null;
        }
        
        if (StringUtils.isEmpty(entity)) {
            log.warn("entity in request is empty");
            return null;
        }

        
        File tempFile = ZipUtils.createZipFile(baseFolder, entity, dateStr, correlationId);
        if(tempFile != null){


            FileInputStream fis = null;
            OutputStream out = null;
            try{
                fis = new FileInputStream(tempFile);
                out = response.getOutputStream();

                response.setContentType("application/x-download");
                response.setHeader("Content-Disposition", "attachment; filename=" + correlationId + ".zip");
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
}
