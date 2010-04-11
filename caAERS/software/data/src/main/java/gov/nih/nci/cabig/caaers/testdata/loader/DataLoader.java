package gov.nih.nci.cabig.caaers.testdata.loader;

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
        unmarshaller = jaxbContext.createUnmarshaller();
        objectFactory = new ObjectFactory();
        jaxbContext = JAXBContext.newInstance(jaxbPackageUrl);
    }

    /**
     * Will load each file and prints the status.
     * The actual saving of the entity in the file is done by the subclasses. 
     * @throws Exception
     */
    public final void load() throws Exception{
        File filesToLoad[] = new File(dataFileLocation).listFiles();
        for(File f : filesToLoad){
            StringBuilder sb = new StringBuilder("Loading " + f.getName()).append("-->");
            StringBuffer detailsBuffer = new StringBuffer();
            if(loadFile(f, detailsBuffer)) {
                sb.append(" SUCCESS");
            } else {
                sb.append(" FAILED");
            }
            sb.append("\n Details").append(detailsBuffer);
            
            System.out.println(sb.toString());
        }

    }
    public abstract boolean loadFile(File f, StringBuffer detailsBuffer) throws Exception;

   
}
