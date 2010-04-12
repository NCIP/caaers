package gov.nih.nci.cabig.caaers.testdata.loader.investigator;

import gov.nih.nci.cabig.caaers.api.impl.DefaultInvestigatorMigratorService;
import gov.nih.nci.cabig.caaers.integration.schema.common.CaaersServiceResponse;
import gov.nih.nci.cabig.caaers.integration.schema.common.WsError;
import gov.nih.nci.cabig.caaers.integration.schema.investigator.Staff;
import gov.nih.nci.cabig.caaers.testdata.TestDataFileUtils;
import gov.nih.nci.cabig.caaers.testdata.loader.DataLoader;
import org.springframework.context.ApplicationContext;

import java.io.File;
import java.io.FileInputStream;

/**
 * This class will load investigators.
 * Used to load test data.
 * @author: Biju Joseph
 */
public class InvestigatorLoader extends DataLoader{
     DefaultInvestigatorMigratorService service;
    public InvestigatorLoader(ApplicationContext appContext) throws Exception{
        this(appContext, TestDataFileUtils.getInvestigatorTestDataFolder().getPath());
    }
    public InvestigatorLoader(ApplicationContext appContext, String loc) throws Exception {
        super(appContext, loc, "gov.nih.nci.cabig.caaers.integration.schema.investigator");
        service =  (DefaultInvestigatorMigratorService) appContext.getBean("investigatorMigratorService");
    }

    @Override
    public boolean loadFile(File f, StringBuffer detailsBuffer) throws Exception {
     
       Staff staff = getInvestigator(f);
       boolean loadStatus = true;

       CaaersServiceResponse response = service.saveInvestigator(staff);
       for(WsError wsError : response.getServiceResponse().getWsError()){
            loadStatus=false;
            detailsBuffer.append(wsError.getErrorDesc()).append("\n");
        }
      
       return loadStatus;
    }


    /**
     * Will load the Investigator from the file.
     * @param f
     * @return
     * @throws Exception
     */
    public Staff getInvestigator(File f) throws Exception{
          return (Staff) unmarshaller.unmarshal(new FileInputStream(f));
    }
}
