package gov.nih.nci.cabig.caaers.web.admin;

import gov.nih.nci.cabig.caaers.tools.configuration.Configuration;
import gov.nih.nci.cabig.caaers.utils.DateUtils;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

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

        
        File tempFile = createZipFile(baseFolder, entity, dateStr, correlationId);
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

    private File createZipFile(String baseFolder, String entity, String dateStr, String correlationId){
        Date d = null;
        try{
        	SimpleDateFormat sf = new SimpleDateFormat(DateUtils.DATE_WITH_HYPHENS);
        	d = sf.parse(dateStr);
        }catch (ParseException pe){
            log.error("Unable to parse dstr request parameter", pe);
            return null;
        }
        
        Calendar c = Calendar.getInstance();
        c.setTime(d);

        String subFolder = c.get(Calendar.YEAR)
                + "/"
                + (c.get(Calendar.MONTH) + 1)
                + "/"
                + c.get(Calendar.DAY_OF_MONTH);


        File tempFile = null;
        try{
            tempFile = File.createTempFile("x" + System.currentTimeMillis(), ".zip");
        }catch (Exception e){
            log.error("Unable to create temp file", e);
            return null;
        }

        ZipOutputStream zos = null;
        FileOutputStream fos = null;
        try{
            fos = new FileOutputStream(tempFile);
            zos = new ZipOutputStream(fos);

            String strFolderToZip = baseFolder + subFolder + File.separator + entity + File.separator + correlationId;
            File zipFolder = new File(strFolderToZip);

            int len = zipFolder.getAbsolutePath().lastIndexOf(File.separator);
            String baseName = zipFolder.getAbsolutePath().substring(0, len + 1);
            addFolderToZip(zipFolder, zos, baseName);

            zos.flush();
            
        }catch (Exception e){
            log.error("Unable to zip folder", e);
        } finally {
            if(zos != null)IOUtils.closeQuietly(zos);
            if(fos != null)IOUtils.closeQuietly(fos);  
        }
        return tempFile;
        
    }
    private static void addFolderToZip(File folder, ZipOutputStream zip, String baseName) throws IOException {
        File[] files = folder.listFiles();
        for (File file : files) {
            if (file.isDirectory()) {
                addFolderToZip(file, zip, baseName);
            } else {
                String name = file.getAbsolutePath().substring(baseName.length());
                ZipEntry zipEntry = new ZipEntry(name);
                zip.putNextEntry(zipEntry);
                IOUtils.copy(new FileInputStream(file), zip);
                zip.closeEntry();
            }
        }
    }

    public Configuration getConfiguration() {
        return configuration;
    }

    public void setConfiguration(Configuration configuration) {
        this.configuration = configuration;
    }
}
