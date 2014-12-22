package gov.nih.nci.cabig.caaers.utils;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang.math.RandomUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * Utility methods to add multiple folders to a zip file.
 */
public class ZipUtils {

    private static final Log log = LogFactory.getLog(ZipUtils.class);

    public static File createZipFile(String baseFolder, String entity, String dateStr, String correlationId) {
        Map<String, String> params = new LinkedHashMap<String, String>();
        params.put(correlationId, dateStr);
       return createZipFile(baseFolder, entity, params);
    }


    public static File createZipFile(String baseFolder, String entity, Map<String, String> correlationIdDateMap){
        if(log.isDebugEnabled()) {
            log.debug(String.format("Creating Zip for %s and coorelationIds :%s" , entity, correlationIdDateMap.toString() ));
        }

        File zipFile = null;
        File tempFolder =  null;
        Date d = null;

        ZipOutputStream zos = null;
        FileOutputStream fos = null;

        try{

            tempFolder = File.createTempFile(RandomStringUtils.randomAlphabetic(5), "");
            FileUtils.deleteQuietly(tempFolder);
            tempFolder.mkdirs();

            zipFile = File.createTempFile(RandomStringUtils.randomAlphabetic(5), ".zip");

            //copy individual folders
            for(Map.Entry<String, String> entry : correlationIdDateMap.entrySet()) {
                String correlationId = entry.getKey();
                String dateStr = entry.getValue();
                SimpleDateFormat sf = new SimpleDateFormat(DateUtils.DATE_WITH_HYPHENS);
                d = sf.parse(dateStr);
                String subFolder = DateUtils.formatDate(d, "yyyy") +
                        File.separator +
                        DateUtils.formatDate(d,"MM") +
                        File.separator +
                        DateUtils.formatDate(d, "dd");

                String strFolderToZip = baseFolder + subFolder + File.separator + entity + File.separator + correlationId;
                File srcFolder = new File(strFolderToZip);
                FileUtils.copyDirectory(srcFolder, tempFolder, true);
            }

            fos = new FileOutputStream(zipFile);
            zos = new ZipOutputStream(fos);

            int len = tempFolder.getAbsolutePath().lastIndexOf(File.separator);
            String baseName = tempFolder.getAbsolutePath().substring(0, len + 1);
            addFolderToZip(tempFolder, zos, baseName);

        }catch (Exception e){
            log.error("Unable to create temp file", e);
            return null;
        }finally {
            if(zos != null) IOUtils.closeQuietly(zos);
            if(fos != null)IOUtils.closeQuietly(fos);
        }

        FileUtils.deleteQuietly(tempFolder);

        return zipFile;
    }

    public static void addFolderToZip(File folder, ZipOutputStream zip, String baseName) throws IOException {
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
}
