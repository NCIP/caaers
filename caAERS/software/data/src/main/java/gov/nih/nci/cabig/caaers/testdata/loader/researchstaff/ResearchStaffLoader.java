package gov.nih.nci.cabig.caaers.testdata.loader.researchstaff;

import gov.nih.nci.cabig.caaers.api.impl.DefaultResearchStaffMigratorService;
import gov.nih.nci.cabig.caaers.domain.ResearchStaff;
import gov.nih.nci.cabig.caaers.integration.schema.researchstaff.ResearchStaffType;
import gov.nih.nci.cabig.caaers.integration.schema.researchstaff.Staff;
import gov.nih.nci.cabig.caaers.service.DomainObjectImportOutcome;
import gov.nih.nci.cabig.caaers.testdata.TestDataFileUtils;
import gov.nih.nci.cabig.caaers.testdata.loader.DataLoader;
import gov.nih.nci.cabig.caaers.utils.XmlValidator;
import org.springframework.context.ApplicationContext;

import java.io.File;
import java.io.FileInputStream;

/**
 * This class will load research staff.
 * Used to load test data.
 * @author: Biju Joseph
 */
public class ResearchStaffLoader  extends DataLoader{
    DefaultResearchStaffMigratorService service;
    public ResearchStaffLoader(ApplicationContext appContext) throws Exception {
        this(appContext, TestDataFileUtils.getResearchStaffTestDataFolder().getPath());
    }
    public ResearchStaffLoader(ApplicationContext appContext, String loc) throws Exception {
        super(appContext, loc, "gov.nih.nci.cabig.caaers.integration.schema.researchstaff");
         service = (DefaultResearchStaffMigratorService) appContext.getBean("researchStaffMigratorService");
    }

    @Override
    public boolean loadFile(File f, StringBuffer detailsBuffer) throws Exception {

        //validate file.
//       boolean valid = XmlValidator.validateAgainstSchema(TestDataFileUtils.getContent(f), "classpath:schema/integration/ResearchStaff.xsd", detailsBuffer);
//       if(!valid) return false;
       Staff staff = getResearchStaff(f);
       boolean loadStatus = true;
       for(ResearchStaffType rs : staff.getResearchStaff()){
           DomainObjectImportOutcome<ResearchStaff> outcome =  service.processResearchStaff(rs);
           loadStatus &= outcome.hasErrors();
           if(outcome.hasErrors()) detailsBuffer.append(outcome.toString()).append("\n");
       }

       return loadStatus;
    }

    /**
     * Will load the ResearchStaff from the file.
     * @param f
     * @return
     * @throws Exception
     */
    public Staff getResearchStaff(File f) throws Exception{
          return (Staff) unmarshaller.unmarshal(new FileInputStream(f));
    }
}
