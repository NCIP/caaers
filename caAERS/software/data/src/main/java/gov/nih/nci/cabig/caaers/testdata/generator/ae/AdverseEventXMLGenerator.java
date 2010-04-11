package gov.nih.nci.cabig.caaers.testdata.generator.ae;

import gov.nih.nci.cabig.caaers.integration.schema.investigator.ObjectFactory;
import gov.nih.nci.cabig.caaers.testdata.NCICode;
import gov.nih.nci.cabig.caaers.testdata.TestDataFileUtils;
import gov.nih.nci.cabig.caaers.testdata.generator.XMLGenerator;
import gov.nih.nci.cabig.caaers.webservice.adverseevent.AdverseEvents;
import gov.nih.nci.cabig.caaers.webservice.adverseevent.AdverseEventsInputMessage;

import javax.xml.bind.JAXBContext;
import java.io.File;
import java.io.FileInputStream;
import java.util.List;

/**
 * This generator will create data load XML files, containing Adverse events.
 *
 * @author: Biju Joseph
 */
public class AdverseEventXMLGenerator extends XMLGenerator{


    private ObjectFactory objectFactory;
    private static  final String[] COURSE_FILE_TEMPLATES = {"adverse_event_course_1.xml",
            "adverse_event_course_2.xml",
            "adverse_event_course_3.xml",
            "adverse_event_course_4.xml",
            "adverse_event_course_5.xml",
            "adverse_event_course_6.xml"};

    public AdverseEventXMLGenerator() throws Exception{
        jaxbContext = JAXBContext.newInstance("gov.nih.nci.cabig.caaers.webservice.adverseevent");
		unmarshaller = jaxbContext.createUnmarshaller();
		marshaller = jaxbContext.createMarshaller();
		objectFactory = new ObjectFactory();
    }

    /**
     * Will read the adverse event message from the file. 
     * @param fileName
     * @return
     */
    public AdverseEventsInputMessage getAdverseEventInput(String fileName) throws Exception{
         return (AdverseEventsInputMessage)unmarshaller.unmarshal(createInputStream(AdverseEventXMLGenerator.class.getPackage(), fileName));
    }

    /**
     * Will modify the study and subject identifiers
     * @param msg
     */
    public void modifyAdverseEvenInput(AdverseEventsInputMessage msg, String studyPrimaryId, String subjectPrimaryId){
        msg.getCriteria().setParticipantIdentifier(subjectPrimaryId);
        msg.getCriteria().setStudyIdentifier(studyPrimaryId);  
    }

    /**
     * Will generate a file for the course , on the study and subject, based on the template.
     * @param studyPrimaryId
     * @param subjectPrimaryId
     * @param templateFileName
     * @param destinationFolder
     * @throws Exception
     */
    public void generateAdverseEventFile(String studyPrimaryId, String subjectPrimaryId, String templateFileName, File destinationFolder) throws Exception {
        AdverseEventsInputMessage aeMsg = getAdverseEventInput(templateFileName);
        modifyAdverseEvenInput(aeMsg, studyPrimaryId, subjectPrimaryId);

        marshal(aeMsg, destinationFolder,subjectPrimaryId + "_" + templateFileName);

    }


    public static void main(String[] args){

        int particpantIndexStart = 1;
        int participantIndexEnd = 80;

        int studyIndexStart = 1;
        int studyIndexEnd = 100;

        List<String> siteNCICodes = NCICode.ORGANIZATION_LIST;
        String studyPrimaryIdPattern ="C5876";
        try{
            AdverseEventXMLGenerator generator = new AdverseEventXMLGenerator();
            File dataFolder = TestDataFileUtils.getAdverseEventTestDataFolder();
            for(int i = studyIndexStart ; i <= studyIndexEnd; i++){
                String studyPrimaryId = studyPrimaryIdPattern + "." + i;

                for(String siteNCICode : siteNCICodes){
                    for(int j = particpantIndexStart ; j<=participantIndexEnd; j++){
                        String subjectPrimaryId = siteNCICode + "_" + studyPrimaryId +"_SI" + j ;

                        for(String courseTemplate : COURSE_FILE_TEMPLATES){
                            System.out.println("Generating ... [" + subjectPrimaryId +  " : "  + courseTemplate + "]....");
                            generator.generateAdverseEventFile(studyPrimaryId, subjectPrimaryId, courseTemplate, dataFolder);

                        }
                    }
                }
            }

            System.out.println("Done..........");
            

        }catch(Exception e){
            e.printStackTrace();
        }
    }

}
