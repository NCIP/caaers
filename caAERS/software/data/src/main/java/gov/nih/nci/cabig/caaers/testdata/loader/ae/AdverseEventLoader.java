package gov.nih.nci.cabig.caaers.testdata.loader.ae;

import gov.nih.nci.cabig.caaers.api.impl.AdverseEventManagementServiceImpl;
import gov.nih.nci.cabig.caaers.testdata.TestDataFileUtils;
import gov.nih.nci.cabig.caaers.testdata.loader.DataLoader;
import gov.nih.nci.cabig.caaers.utils.XmlValidator;
import gov.nih.nci.cabig.caaers.webservice.adverseevent.AdverseEventsInputMessage;
import gov.nih.nci.cabig.caaers.webservice.adverseevent.CaaersServiceResponse;
import org.apache.commons.lang.StringUtils;
import org.springframework.context.ApplicationContext;

import java.io.File;
import java.io.FileInputStream;

/**
 * @author: Biju Joseph
 */
public class AdverseEventLoader extends DataLoader{

    AdverseEventManagementServiceImpl service;

    public AdverseEventLoader(ApplicationContext appContext, String loc ) throws Exception {
        super(appContext, loc, "gov.nih.nci.cabig.caaers.webservice.adverseevent");
        service = (AdverseEventManagementServiceImpl) appContext.getBean("adverseEventManagementServiceImpl") ;
    }

    @Override
    public boolean loadFile(File f, StringBuffer detailsBuffer) throws Exception {

        //validate
        boolean valid = XmlValidator.validateAgainstSchema(TestDataFileUtils.getContent(f), "classpath:gov/nih/nci/cabig/caaers/ManageAdverseEventsSchema.xsd", detailsBuffer);
        if(!valid) return false;

        boolean loadStatus = true;
        CaaersServiceResponse caaersResponse = service.createAdverseEvent(getAdverseEventInput(f));
        if(StringUtils.isNotEmpty(caaersResponse.getResponse().getResponsecode())) loadStatus = false;
        return loadStatus;

    }


    /**
     * Will read the adverse event message from the file.
     * @param f
     * @return
     */
    public AdverseEventsInputMessage getAdverseEventInput(File f) throws Exception{
         return (AdverseEventsInputMessage)unmarshaller.unmarshal(new FileInputStream(f));
    }

}
