package gov.nih.nci.cabig.caaers.testdata.loader;

import gov.nih.nci.cabig.caaers.dao.CaaersDao;
import gov.nih.nci.cabig.caaers.dao.StudyDao;
import gov.nih.nci.cabig.caaers.testdata.TestDataFileUtils;
import gov.nih.nci.cabig.caaers.webservice.ObjectFactory;
import org.springframework.context.ApplicationContext;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import java.io.File;

/**
 * TemplateMethod for all loaders
 * @author: Biju Joseph
 */
public abstract class DataLoader {
    protected String dataFileLocation;
    protected JAXBContext jaxbContext = null;
    protected Unmarshaller unmarshaller = null;
    protected ObjectFactory objectFactory;
    protected ApplicationContext appContext;

    public DataLoader(ApplicationContext appContext, String loc, String jaxbPackageUrl) throws Exception {
        this.appContext = appContext;
        this.dataFileLocation = loc;
        objectFactory = new ObjectFactory();
        jaxbContext = JAXBContext.newInstance(jaxbPackageUrl);
        unmarshaller = jaxbContext.createUnmarshaller();
    }

    /**
     * Will load each file and prints the status.
     * The actual saving of the entity in the file is done by the subclasses. 
     * @throws Exception
     */
    public final void load() throws Exception{
        File filesToLoad[] = new File(dataFileLocation).listFiles();
        double beginTime = System.currentTimeMillis();
        int i = 1;
        System.out.println(" Files to load :" + filesToLoad.length );
        for(File f : filesToLoad){
            double startTime = System.currentTimeMillis();
            StringBuilder sb = new StringBuilder("(" + i++ + ") Loading " + f.getName()).append("-->");
            StringBuffer detailsBuffer = new StringBuffer();
            if(loadFile(f, detailsBuffer)) {
                sb.append(" SUCCESS");
            } else {
                sb.append(" FAILED");
            }
            sb.append(", took : ").append((System.currentTimeMillis() - startTime)/1000).append(" seconds") ;

            if(detailsBuffer.length() > 0)
                sb.append("\n Details").append(detailsBuffer);
            
            System.out.println(sb.toString());
            flushAndClearSession();
        }
        double endTime = System.currentTimeMillis();

        double d = (endTime - beginTime) /60000;
        System.out.println("Time for loading : " + d + " minutes");

    }

    public void flushAndClearSession(){
        StudyDao dao = (StudyDao) appContext.getBean("studyDao");
        dao.flush();
        dao.clearSession();
    }
    public abstract boolean loadFile(File f, StringBuffer detailsBuffer) throws Exception;

   
}
