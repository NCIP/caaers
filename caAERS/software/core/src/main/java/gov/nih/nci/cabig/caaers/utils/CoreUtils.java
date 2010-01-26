package gov.nih.nci.cabig.caaers.utils;

import org.apache.log4j.Logger;
import org.springframework.core.io.ClassPathResource;

import java.io.File;
import java.io.IOException;

/*
* @author Ion C. Olaru
* 
* */

public class CoreUtils {

	private static Logger log = Logger.getLogger(CoreUtils.class);

    /*
    *
    * Finds the file in the classpath
    * 
    * */
    public static File findFile(String filePath) {
        File f = null;
        try {
            f = new ClassPathResource(filePath).getFile();
        } catch (IOException e) {
            log.error("File not found", e);
        } finally {
            return f;
        }
    }
    
}