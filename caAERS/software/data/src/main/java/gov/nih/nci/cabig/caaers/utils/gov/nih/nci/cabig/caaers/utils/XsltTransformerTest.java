/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
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
    
    public void testParticiapntxmlToText() throws Exception {
        String xml  = new XsltTransformer().toText(openParticipantCreatexml(), "/xslt/participant_details_sync.xsl");
        assertTrue(xml.endsWith("</stud:studies>"));

        String xml2  = new XsltTransformer().toText(xmlStudyDetailsResult(), "/xslt/c2a_generic_response.xslt");
        String s = StringUtils.trim(xml2);
        int i = NumberUtils.toInt(s, -99);
        assertEquals(1, i);

    }
    
    public String openParticipantCreatexml(){
        return "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
					"<ODM>\n" +
					"	<ClinicalData MetaDataVersionOID=\"3629869\" StudyOID=\"E2211 (UAT)\">\n" +
					"		<SubjectData SubjectKey=\"22062\" TransactionType=\"Insert\">\n" +
					"			<SiteRef LocationOID=\"CTSUTST01\"/>\n" +
					"			<StudyEventData StudyEventOID=\"SUBJECT\" TransactionType=\"Update\">\n" +
					"				<FormData FormOID=\"SUBJECT_ENROLLMENT\" TransactionType=\"Update\">\n" +
					"					<ItemGroupData ItemGroupOID=\"SUBJECT_ENROLLMENT\" TransactionType=\"Update\">\n" +
					"						<ItemData ItemOID=\"PARTIC_ENROL_DT\" Value=\"19 Mar 2013\"/>\n" +
					"						<ItemData ItemOID=\"CURRENT_SITE_ID\" Value=\"MN008\"/>\n" +
					"						<ItemData ItemOID=\"PT_ID\" Value=\"22062\"/>\n" +
					"						<ItemData ItemOID=\"ENROLLING_SITE_ID\" Value=\"MN008\"/>\n" +
					"						<ItemData ItemOID=\"LEAD_INST_NAME\" Value=\"ECOG\"/>\n" +
					"						<ItemData ItemOID=\"ENROLL_TIME\" Value=\"09:19:28:AM\">\n" +
					"							<MeasurementUnitRef MeasurementUnitOID=\"EDT\"/>\n" +
					"						</ItemData>\n" +
					"						<ItemData ItemOID=\"GROUP_DATA\" Value=\"N/A\"/>\n" +
					"						<ItemData ItemOID=\"SRC_APP\" Value=\"OPEN\"/>\n" +
					"					</ItemGroupData>\n" +
					"				</FormData>\n" +
					"			</StudyEventData>\n" +
					"			<StudyEventData StudyEventOID=\"ENROLLMENT_FORMS\" TransactionType=\"Update\">\n" +
					"				<FormData FormOID=\"DEMOGRAPHY\" TransactionType=\"Update\">\n" +
					"					<ItemGroupData ItemGroupOID=\"DEMOGRAPHY\" TransactionType=\"Update\">\n" +
					"						<ItemData ItemOID=\"PT_INITIALS_NAME\" Value=\"HS-\"/>\n" +
					"						<ItemData ItemOID=\"PER_BIR_DT\" Value=\"14 Jul 1981\"/>\n" +
					"						<ItemData ItemOID=\"ETHN_GRP_CAT_TXT\" Value=\"Not Hispanic or Latino\"/>\n" +
					"						<ItemData ItemOID=\"PERSON_GENDER\" Value=\"Female Gender\"/>\n" +
					"						<ItemData ItemOID=\"COUNTRY_CD\" Value=\"US\"/>\n" +
					"						<ItemData ItemOID=\"ADDR_POSTAL_CD\" Value=\"32464\"/>\n" +
					"						<ItemData ItemOID=\"PAYMENT_METHOD\" Value=\"PRIVATE INSURANCE\"/>\n" +
					"					</ItemGroupData>\n" +
					"					<ItemGroupData ItemGroupOID=\"DEMOGRAPHY\" ItemGroupRepeatKey=\"1\" TransactionType=\"Update\">\n" +
					"						<ItemData ItemOID=\"RACE_CAT_TXT\" Value=\"Black or African American\"/>\n" +
					"					</ItemGroupData>\n" +
					"				</FormData>\n" +
					"			</StudyEventData>\n" +
					"			<StudyEventData StudyEventOID=\"ENROLLMENT_FORMS\" TransactionType=\"Update\">\n" +
					"				<FormData FormOID=\"STEP_INFORMATION\" TransactionType=\"Update\">\n" +
					"					<ItemGroupData ItemGroupOID=\"STEP_INFORMATION\" ItemGroupRepeatKey=\"1\" TransactionType=\"Update\">\n" +
					"						<ItemData ItemOID=\"EVENT_DATE\" Value=\"19 Mar 2013\"/>\n" +
					"					</ItemGroupData>\n" +
					"					<ItemGroupData ItemGroupOID=\"STEP_INFORMATION\" ItemGroupRepeatKey=\"1\" TransactionType=\"Update\">\n" +
					"						<ItemData ItemOID=\"REG_STEP_NUM\" Value=\"1\"/>\n" +
					"					</ItemGroupData>\n" +
					"					<ItemGroupData ItemGroupOID=\"STEP_INFORMATION\" ItemGroupRepeatKey=\"1\" TransactionType=\"Update\">\n" +
					"						<ItemData ItemOID=\"EVENT_DESC\" Value=\"Randomization\"/>\n" +
					"					</ItemGroupData>\n" +
					"					<ItemGroupData ItemGroupOID=\"STEP_INFORMATION\" ItemGroupRepeatKey=\"1\" TransactionType=\"Update\">\n" +
					"						<ItemData ItemOID=\"EVENT_TIME\" Value=\"09:19:28:AM\">\n" +
					"							<MeasurementUnitRef MeasurementUnitOID=\"EDT\"/>\n" +
					"						</ItemData>\n" +
					"					</ItemGroupData>\n" +
					"					<ItemGroupData ItemGroupOID=\"STEP_INFORMATION\" ItemGroupRepeatKey=\"1\" TransactionType=\"Update\">\n" +
					"						<ItemData ItemOID=\"TRACKING_NUM\" Value=\"109975\"/>\n" +
					"					</ItemGroupData>\n" +
					"					<ItemGroupData ItemGroupOID=\"STEP_INFORMATION\" ItemGroupRepeatKey=\"1\" TransactionType=\"Update\">\n" +
					"						<ItemData ItemOID=\"TX_MD_PART_INV_NM\" Value=\"Stuart Bloom\"/>\n" +
					"					</ItemGroupData>\n" +
					"					<ItemGroupData ItemGroupOID=\"STEP_INFORMATION\" ItemGroupRepeatKey=\"1\" TransactionType=\"Update\">\n" +
					"						<ItemData ItemOID=\"PROT_REG_NAME\" Value=\"Stuart Bloom\"/>\n" +
					"					</ItemGroupData>\n" +
					"					<ItemGroupData ItemGroupOID=\"STEP_INFORMATION\" ItemGroupRepeatKey=\"1\" TransactionType=\"Update\">\n" +
					"						<ItemData ItemOID=\"INVESTIGATOR_NAME\" Value=\"Stuart Bloom\"/>\n" +
					"					</ItemGroupData>\n" +
					"					<ItemGroupData ItemGroupOID=\"STEP_INFORMATION\" ItemGroupRepeatKey=\"1\" TransactionType=\"Update\">\n" +
					"						<ItemData ItemOID=\"ORG_NAME\" Value=\"ECOG\"/>\n" +
					"					</ItemGroupData>\n" +
					"					<ItemGroupData ItemGroupOID=\"STEP_INFORMATION\" ItemGroupRepeatKey=\"1\" TransactionType=\"Update\">\n" +
					"						<ItemData ItemOID=\"PROT_TX_ARM_ASS_TXT\" Value=\"B\"/>\n" +
					"					</ItemGroupData>\n" +
					"				</FormData>\n" +
					"			</StudyEventData>\n" +
					"		</SubjectData>\n" +
					"	</ClinicalData>\n" +
					"</ODM>" ;
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
