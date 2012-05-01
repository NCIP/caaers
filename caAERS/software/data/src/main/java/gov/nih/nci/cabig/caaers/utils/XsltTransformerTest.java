package gov.nih.nci.cabig.caaers.utils;

import junit.framework.TestCase;

/**
 * @author Biju Joseph
 */
public class XsltTransformerTest extends TestCase {

    public void testToText() throws Exception {
        String xml  = new XsltTransformer().toText(xmlSearchResult(), "/xslt/c2a_generic_response.xslt");
        assertTrue(xml.endsWith("</stud:studies>"));
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
}
