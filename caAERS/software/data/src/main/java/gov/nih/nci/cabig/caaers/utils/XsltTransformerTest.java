package gov.nih.nci.cabig.caaers.utils;

import junit.framework.TestCase;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;

/**
 * @author Biju Joseph
 */
public class XsltTransformerTest extends TestCase {

    public void testToText() throws Exception {
        String xml  = new XsltTransformer().toText(xmlSearchResult(), "/xslt/c2a_generic_response.xslt");
        assertTrue(xml.endsWith("</stud:studies>"));

        String xml2  = new XsltTransformer().toText(xmlStudyDetailsResult(), "/xslt/c2a_generic_response.xslt");
        String s = StringUtils.trim(xml2);
        int i = NumberUtils.toInt(s, -99);
        assertEquals(1, i);

    }
    
    public String xmlSearchResult(){
           return "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                   "<payload xmlns:stud=\"http://schema.integration.caaers.cabig.nci.nih.gov/study\">\n" +
                   "    <system>caaers</system>\n" +
                   "    <response>\n" +
                   "        <operation name=\"searchStudy\">\n" +
                   "            <data>\n" +
                   "                <stud:studies>\n" +
                   "                    <stud:study>\n" +
                   "                        <shortTitle>Phase II Trial of Flavopiridol and Cisplatin in Advanced Epithelial Ovarian and\n" +
                   "                            Primary Peritoneal Carcinomas\n" +
                   "                        </shortTitle>\n" +
                   "                        <fundingSponsor>\n" +
                   "                            <organizationAssignedIdentifier>\n" +
                   "                                <value>5876</value>\n" +
                   "                            </organizationAssignedIdentifier>\n" +
                   "                            <stud:studyFundingSponsor>\n" +
                   "                                <stud:organization>\n" +
                   "                                    <name>Cancer Therapy Evaluation Program</name>\n" +
                   "                                    <nciInstituteCode>CTEP</nciInstituteCode>\n" +
                   "                                </stud:organization>\n" +
                   "                            </stud:studyFundingSponsor>\n" +
                   "                        </fundingSponsor>\n" +
                   "                    </stud:study>\n" +
                   "                </stud:studies>\n" +
                   "            </data>\n" +
                   "        </operation>\n" +
                   "    </response>\n" +
                   "</payload>" ;
    }
    
    public String xmlStudyDetailsResult(){
       return "<soap:Envelope xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\">\n" +
               "   <soap:Body>\n" +
               "      <payload correlationId=\"1336138727601\" xmlns:com=\"http://schema.integration.caaers.cabig.nci.nih.gov/common\" xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\">\n" +
               "         <system>caaers</system>\n" +
               "         <response>\n" +
               "            <entity xmlns:stud=\"http://schema.integration.caaers.cabig.nci.nih.gov/study\">study</entity>\n" +
               "            <operation name=\"updateStudyResponse\" xmlns:stud=\"http://schema.integration.caaers.cabig.nci.nih.gov/study\">\n" +
               "               <status>Processed</status>\n" +
               "               <data>\n" +
               "                  <ns3:entityProcessingOutcome xmlns:ns1=\"http://schema.integration.caaers.cabig.nci.nih.gov/study\" xmlns:ns3=\"http://schema.integration.caaers.cabig.nci.nih.gov/common\" xmlns:ns4=\"http://schema.integration.caaers.cabig.nci.nih.gov/investigator\" xmlns:ns5=\"http://schema.integration.caaers.cabig.nci.nih.gov/researchstaff\" xmlns:ns6=\"http://schema.integration.caaers.cabig.nci.nih.gov/participant\">\n" +
               "                     <klassName>gov.nih.nci.cabig.caaers.domain.Study</klassName>\n" +
               "                     <businessIdentifier>N027D</businessIdentifier>\n" +
               "                     <message>Study with Short Title  \"A Phase I Study of CCI-779 and Temozolomide in Combination with Radiation Therapy in Glioblastoma Multiforme\" updated in caAERS</message>\n" +
               "                     <dataBaseId>1</dataBaseId>\n" +
               "                     <failed>false</failed>\n" +
               "                  </ns3:entityProcessingOutcome>\n" +
               "               </data>\n" +
               "            </operation>\n" +
               "         </response>\n" +
               "      </payload>\n" +
               "   </soap:Body>\n" +
               "</soap:Envelope>"   ;
    }
}
