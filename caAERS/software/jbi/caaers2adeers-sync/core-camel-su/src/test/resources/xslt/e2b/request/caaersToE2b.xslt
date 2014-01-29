<?xml version="1.0" encoding="UTF-8"?>
<!-- Copyright SemanticBits, Northwestern University and Akaza Research Distributed 
	under the OSI-approved BSD 3-Clause License. See http://ncip.github.com/caaers/LICENSE.txt 
	for details. -->
<xsl:stylesheet version="2.0"
				xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
	<xsl:output method="xml" encoding="UTF-8" indent="yes" />
	
	<xsl:template match="/">
		<xsl:call-template name="e2b" />
	</xsl:template>
	
	<xsl:variable name="map" select="document('lookup.xml')" />

	<xsl:template name="lookup">
		<xsl:param name="_map" />
		<xsl:param name="_code" />
		<xsl:value-of select="$_map//code[text() = $_code]/parent::node()/value" />
	</xsl:template>
	
	<xsl:template name="ConvertOutcomeToCode">
		<xsl:param name="code" />	
		<reactionoutcome>
			<xsl:call-template name="lookup">
						<xsl:with-param name="_map" select="$map//aeoutcomes" />
						<xsl:with-param name="_code" select="$code" />
			</xsl:call-template>
		</reactionoutcome>
	</xsl:template>

	<xsl:template name="ConvertPresentStatusToCode">
		<xsl:param name="code" />
		<presentstatus>
			<xsl:call-template name="lookup">
						<xsl:with-param name="_map" select="$map//aee2bpresentstatuses" />
						<xsl:with-param name="_code" select="$code" />
			</xsl:call-template>
		</presentstatus>
	</xsl:template>
	
	<xsl:template name="ConvertRetreatedToCode">
		<xsl:param name="code" />
		 <xsl:choose>
			<xsl:when test="$code='true'">
				<retreatedflag>1</retreatedflag>
			</xsl:when>
			<xsl:otherwise>
            	<retreatedflag>2</retreatedflag>
			</xsl:otherwise>
		</xsl:choose>
	</xsl:template>
	
	<xsl:template name="ConvertYYYY-MM-DD_to_MMslashDDslashYYYY">
		<xsl:param name="date" />
		<xsl:choose>
			<xsl:when test="$date != null or $date != ''" >
				<xsl:variable name="month" select = "substring($date,6,2)"/>
				<xsl:variable name="day" select = "substring($date,9,2)"/>
				<xsl:variable name="year" select = "substring($date,1,4)"/>
				<xsl:value-of select="concat($month,'/',$day,'/',$year)"/>
			</xsl:when>
			<xsl:otherwise>
				<xsl:value-of select="''"/>
			</xsl:otherwise>
		</xsl:choose>
	</xsl:template>
	
	<xsl:template name="getAttributionCode">
		<xsl:param name="attrValue" />
		<attribution><xsl:value-of select="substring-before( $attrValue, ':')"/></attribution>
	</xsl:template>
	
	<xsl:template name="populateAttributions">
		<xsl:for-each select="/AdverseEventReport/AdverseEvent">
				<xsl:variable name="externalId" select="externalId"/>
			 
			 <!-- Disease Attribution -->
			<xsl:for-each select="DiseaseAttribution">
			  <reaction>
				<aeexternalid><xsl:value-of select="$externalId"/></aeexternalid>
				
				<factor><xsl:value-of select="DiseaseHistory/CtepStudyDisease/DiseaseTerm/ctepTerm"/></factor>        
				<factortype>primary disease</factortype>
				<xsl:call-template name="getAttributionCode"> 
						<xsl:with-param name="attrValue" select="attribution"/>
				</xsl:call-template>
			  </reaction>
			</xsl:for-each>	
			 
			<!-- Course Agent attribution -->  
			<xsl:for-each select="CourseAgentAttribution">
			  <reaction>
			  <aeexternalid><xsl:value-of  select="$externalId"/></aeexternalid>
				<!-- drug NSC number -->		
				<factor><xsl:value-of select="CourseAgent/StudyAgent/Agent/nscNumber"/></factor> 
				<factortype>course agent</factortype>
				<xsl:call-template name="getAttributionCode"> 
						<xsl:with-param name="attrValue" select="attribution"/>
					</xsl:call-template>
			  </reaction>
		    </xsl:for-each>
			
			<!-- Concomitant attribution -->  
			<xsl:for-each select="ConcomitantMedicationAttribution">
			  <reaction>
			  <aeexternalid><xsl:value-of  select="$externalId"/></aeexternalid>
				<!-- drug NSC number -->		
				<factor><xsl:value-of select="ConcomitantMedication/name"/></factor> 
				<factortype>concomitant medication</factortype>
				<xsl:call-template name="getAttributionCode"> 
						<xsl:with-param name="attrValue" select="attribution"/>
					</xsl:call-template>
			  </reaction>
		    </xsl:for-each>
			
			<!-- Other cause attribution -->  
			<xsl:for-each select="OtherCauseAttribution">
			  <reaction>
			  <aeexternalid><xsl:value-of  select="$externalId"/></aeexternalid>
				<!-- drug NSC number -->		
				<factor><xsl:value-of select="OtherCause/text"/></factor> 
				<factortype>other cause</factortype>
				<xsl:call-template name="getAttributionCode"> 
						<xsl:with-param name="attrValue" select="attribution"/>
					</xsl:call-template>
			  </reaction>
		    </xsl:for-each>
			
			<!-- Surgery Attribution-->
			<xsl:for-each select="SurgeryAttribution">
			  <reaction>
			  <aeexternalid><xsl:value-of  select="$externalId"/></aeexternalid>
				<!-- drug NSC number -->
				<xsl:variable name="date">
					 <xsl:call-template name="ConvertYYYY-MM-DD_to_MMslashDDslashYYYY">
								<xsl:with-param name="date"	select="SurgeryIntervention/interventionDate" />
					 </xsl:call-template>
				</xsl:variable>
				<factor><xsl:value-of select="concat('SITE=',SurgeryIntervention/InterventionSite/name,'^^','DATE=',
				$date,'^^')"/></factor> 
				<factortype>surgery</factortype>
				<xsl:call-template name="getAttributionCode"> 
						<xsl:with-param name="attrValue" select="attribution"/>
					</xsl:call-template>
			  </reaction>
		    </xsl:for-each>
			
			<!-- Radiation Attribution-->
			<xsl:for-each select="RadiationAttribution">
			  <reaction>
			  <aeexternalid><xsl:value-of  select="$externalId"/></aeexternalid>	
			   <xsl:variable name="date">
					 <xsl:call-template name="ConvertYYYY-MM-DD_to_MMslashDDslashYYYY">
								<xsl:with-param name="date"	select="RadiationIntervention/lastTreatmentDate" />
							</xsl:call-template>
				</xsl:variable>
				<factor><xsl:value-of select="concat('TYPE=',RadiationIntervention/administration,'^^','DATE=',$date,'^^')"/></factor> 
				
			<!--  	<factor><xsl:value-of select="RadiationIntervention/administration"/></factor> -->
				<factortype>radiation</factortype>
				<xsl:call-template name="getAttributionCode"> 
						<xsl:with-param name="attrValue" select="attribution"/>
					</xsl:call-template>
			  </reaction>
		    </xsl:for-each>			
			
			<!-- Device Attribution-->
			<xsl:for-each select="DeviceAttribution">
			  <reaction>
			  <aeexternalid><xsl:value-of  select="$externalId"/></aeexternalid>
				<!-- drug NSC number -->	
				<factor><xsl:value-of select="concat('CN=',MedicalDevice/StudyDevice/Device/commonName,'^^','BN=',
				MedicalDevice/StudyDevice/Device/brandName,'^^','TYPE=',MedicalDevice/StudyDevice/Device/type,'^^')"/></factor> 	
				<factortype>device</factortype>
				<xsl:call-template name="getAttributionCode"> 
						<xsl:with-param name="attrValue" select="attribution"/>
					</xsl:call-template>
			  </reaction>
		    </xsl:for-each>

		</xsl:for-each>
	</xsl:template>
	
	<xsl:template name="populateContactMechanisms">	
		<xsl:param name="isPhysician" />	
		<xsl:choose>
		<xsl:when test="$isPhysician='true'">
			<xsl:for-each select="ContactMechanism"> 
				<xsl:if test="key = 'phone'">
					<physicianphone><xsl:value-of select="value" /></physicianphone>	
				</xsl:if>
				<xsl:if test="key = 'e-mail'">
					<physicianemail><xsl:value-of select="value" /></physicianemail>	
				</xsl:if>
				<xsl:if test="key = 'fax'">
					<physicianfax><xsl:value-of select="value" /></physicianfax>	
				</xsl:if>
			</xsl:for-each>	  
		</xsl:when>
		<xsl:otherwise>
				<xsl:for-each select="ContactMechanism"> 
				<xsl:if test="key = 'phone'">
					<reporterphone><xsl:value-of select="value" /></reporterphone>	
				</xsl:if>
				<xsl:if test="key = 'e-mail'">
					<reporteremail><xsl:value-of select="value" /></reporteremail>	
				</xsl:if>
				<xsl:if test="key = 'fax'">
					<reporterfax><xsl:value-of select="value" /></reporterfax>	
				</xsl:if>
			</xsl:for-each>	  
		</xsl:otherwise>
		</xsl:choose>
	</xsl:template>
	
	<xsl:template name="formatCreationDate">
		<xsl:param name="createDate" />	
		 <messagedate> <xsl:value-of select="concat(
					  substring($createDate, 1, 4),
					  substring($createDate, 6, 2),
                      substring($createDate, 9, 2),
                      substring($createDate, 12, 2),
                      substring($createDate, 15, 2),
					  substring($createDate, 18, 2)
                      )"/></messagedate> 
	</xsl:template>
	
	<xsl:template name="getDate">
		<xsl:param name="givenDate" />	
		 <xsl:value-of select="concat(
					  substring($givenDate, 1, 4),
					  substring($givenDate, 6, 2),
                      substring($givenDate, 9, 2)
                      )"/>
	</xsl:template>
	
	<xsl:template name="convertBooleantoOneTwo">
		<xsl:param name="boolType" />
		<xsl:if test="$boolType = 'true'">1</xsl:if>
		<xsl:if test="$boolType = 'false'">2</xsl:if>
	</xsl:template>
	
	<xsl:template name="isPrimaryAE">
		<xsl:param name="verbatim" />	
		<xsl:variable name="primaryAEVerbatim" select="/AdverseEventReport/Summary[@id='Primary AE']/value"/>
		
		<xsl:choose>        
			<xsl:when test="contains($primaryAEVerbatim, $verbatim)">
				<primaryaeflag>1</primaryaeflag>
			</xsl:when>
			<xsl:otherwise>
				<primaryaeflag>2</primaryaeflag>
			</xsl:otherwise>
		</xsl:choose>
	</xsl:template>
	
	<xsl:template match="AdverseEventReport" name="e2b">
		<ichicsr lang="en">
		  <ichicsrmessageheader>
			<!-- report creation time -->
			
			 <xsl:call-template name="formatCreationDate"> 
					<xsl:with-param name="createDate" select="/AdverseEventReport/createdAt"/>
				</xsl:call-template>
		  </ichicsrmessageheader>
			
		<safetyreport>
			<reportname><xsl:value-of select="/AdverseEventReport/Report/ReportDefinition/label" /></reportname>
			<safetyreportid><xsl:value-of select="/AdverseEventReport/Report/caseNumber"/></safetyreportid> 
			<xsl:if test="/AdverseEventReport/Report/Submitter/ContactMechanism[key = 'phone']/value">
				<senderphone><xsl:value-of select="/AdverseEventReport/Report/Submitter/ContactMechanism[key = 'phone']/value" /></senderphone>
			</xsl:if>
			<xsl:for-each select="AdverseEventReport/Physician">				 
					 <physiciangivename><xsl:value-of select="/AdverseEventReport/Physician/firstName" /></physiciangivename>
					 <xsl:if test="/AdverseEventReport/Physician/middleName">
						<physicianmiddlename><xsl:value-of select="/AdverseEventReport/Physician/middleName" /></physicianmiddlename>
					 </xsl:if>
					 <physicianfamilyname><xsl:value-of select="/AdverseEventReport/Physician/lastName" /></physicianfamilyname>
					
					 <xsl:if test="ContactMechanism">
						<xsl:call-template name="populateContactMechanisms"> 	
							<xsl:with-param name="isPhysician" select="'true'"/>	
						</xsl:call-template>
					 </xsl:if>
			</xsl:for-each>
			<xsl:for-each select="AdverseEventReport/Reporter">
				<primarysource>
					 <sponsorstudynumb><xsl:value-of select="/AdverseEventReport/StudyParticipantAssignment/StudySite/Study/Identifier[type='Protocol Authority Identifier']/value"/></sponsorstudynumb>
				     <reportergivename><xsl:value-of select="/AdverseEventReport/Reporter/firstName" /></reportergivename>
					 <reportermiddlename><xsl:value-of select="/AdverseEventReport/Reporter/middleName" /></reportermiddlename>
					 <reporterfamilyname><xsl:value-of select="/AdverseEventReport/Reporter/lastName" /></reporterfamilyname>
					
					 <xsl:if test="ContactMechanism">
						<xsl:call-template name="populateContactMechanisms"> 
							<xsl:with-param name="isPhysician" select="'false'"/>	
						</xsl:call-template>
					 </xsl:if>
				</primarysource>
			</xsl:for-each>
			
			<xsl:for-each select="AdverseEventReport/Report/Submitter">
				<sender>
				     <sendergivename><xsl:value-of select="firstName" /></sendergivename>
				     <xsl:if test="middleName"> 
				     	 <sendermiddlename><xsl:value-of select="middleName" /></sendermiddlename>
				     </xsl:if>
					 <senderfamilyname><xsl:value-of select="lastName" /></senderfamilyname>
					 <xsl:if test="ContactMechanism">
						<xsl:for-each select="ContactMechanism">
							<xsl:if test="key = 'fax'">
								<senderfax><xsl:value-of select="value" /></senderfax>	
							</xsl:if>
							<xsl:if test="key = 'e-mail'">
								<senderemailaddress><xsl:value-of select="value" /></senderemailaddress>	
							</xsl:if>
						</xsl:for-each>	  
					 </xsl:if>
				</sender>
			</xsl:for-each>
		
		<!-- Submitter Information ( Note: CaaersXML doesnot have this section) -->
			
		<patient>
			<subjectstudysiteid><xsl:value-of select="/AdverseEventReport/StudyParticipantAssignment/StudySite/Organization/nciInstituteCode"/></subjectstudysiteid> <!-- study site NCI id-->	  
			<patientinvestigationnumb><xsl:value-of select="/AdverseEventReport/StudyParticipantAssignment/studySubjectIdentifier" /></patientinvestigationnumb> <!-- study subject id-->	  
			<xsl:if test="/AdverseEventReport/ParticipantHistory/weight">
				<patientweight><xsl:value-of select="/AdverseEventReport/ParticipantHistory/weight/quantity"/></patientweight>
			</xsl:if>
			<xsl:if test="/AdverseEventReport/ParticipantHistory/height">
				<patientheight><xsl:value-of select="/AdverseEventReport/ParticipantHistory/height/quantity"/></patientheight>
			</xsl:if>
			<xsl:if test="/AdverseEventReport/ParticipantHistory/baselinePerformanceStatus">
				<baselineperformancescale>ECOG</baselineperformancescale>
				<baselineperformancenumber>
					<xsl:call-template name="lookup">
							<xsl:with-param name="_map" select="$map//baselinestatustoe2b" />
							<xsl:with-param name="_code" select="/AdverseEventReport/ParticipantHistory/baselinePerformanceStatus" />
					</xsl:call-template>
				</baselineperformancenumber>	
			</xsl:if>
			<drug>		
				<drugcharacterization>3</drugcharacterization>	<!-- drugcharacterization = 3 identifies the "drug" as the reporting period -->		
				<medicinalproduct><xsl:value-of select="/AdverseEventReport/TreatmentInformation/TreatmentAssignment/code"/></medicinalproduct> <!-- Treatment Assignment Code -->		
				<drugstartdateformat>102</drugstartdateformat> 
				<!-- Start Date of first course -->
				<drugstartdate>
					<xsl:call-template name="getDate"> 
							<xsl:with-param name="givenDate" select="/AdverseEventReport/TreatmentInformation/firstCourseDate"/>	
					</xsl:call-template>
				</drugstartdate>
					<!-- Start Date of course associated with this Expedited Report -->	
				<drugenddate>
					<xsl:call-template name="getDate"> 
							<xsl:with-param name="givenDate" select="/AdverseEventReport/TreatmentInformation/AdverseEventCourse/date"/>	
					</xsl:call-template>
				</drugenddate> 			
				<drugstartperiod><xsl:value-of select="/AdverseEventReport/TreatmentInformation/AdverseEventCourse/number"/></drugstartperiod> <!-- Optional, but will likely be provided. This is the course number on which event occurred -->
			</drug>
			
			<!-- Populate Attributions -->
			<xsl:call-template name="populateAttributions"> 	
			</xsl:call-template>
						
			<!-- Pre existing conditions -->
			<xsl:for-each select="/AdverseEventReport/SAEReportPreExistingCondition">
				<xsl:if test="PreExistingCondition/text != ''">
				<medicalhistoryepisode>
					<patientepisodename><xsl:value-of select="PreExistingCondition/text"/></patientepisodename>
					<patientmedicalcomment>Pre-existing Condition</patientmedicalcomment>
                </medicalhistoryepisode>
				</xsl:if>
			</xsl:for-each>
			
					<!-- Other pre existing conditions -->
			<xsl:if test="/AdverseEventReport/SAEReportPreExistingCondition/other">
					<medicalhistoryepisode>
						<patientepisodename><xsl:value-of select="/AdverseEventReport/SAEReportPreExistingCondition/other"/></patientepisodename>
						<patientmedicalcomment>Other Pre-existing Condition</patientmedicalcomment>
					</medicalhistoryepisode>
			</xsl:if>
		
			<!-- Prior Therapy -->
			<xsl:for-each select="/AdverseEventReport/SAEReportPriorTherapy">
				<medicalhistoryepisode>
					<patientepisodename><xsl:value-of select="PriorTherapy/text"/></patientepisodename>
					<patientmedicalstartdateformat>102</patientmedicalstartdateformat>
					<patientmedicalstartdate><xsl:value-of select="startDate/yearString"/><xsl:value-of select="startDate/monthString"/><xsl:value-of select="startDate/dayString"/></patientmedicalstartdate>
					<patientmedicalenddateformat>102</patientmedicalenddateformat>
					<patientmedicalenddate><xsl:value-of select="endDate/yearString"/><xsl:value-of select="endDate/monthString"/><xsl:value-of select="endDate/dayString"/></patientmedicalenddate>
					<xsl:for-each select="PriorTherapyAgent/Agent">
						<priortherapyagent><xsl:value-of select="nscNumber"/></priortherapyagent>
					</xsl:for-each>	
					<patientmedicalcomment>Prior Therapy</patientmedicalcomment>
                </medicalhistoryepisode>
			</xsl:for-each>
			
			<!-- Lab -->
			<xsl:for-each select="/AdverseEventReport/Lab/baseline">
				<test>
					<testdateformat>String</testdateformat>
					<testdate>
						<xsl:call-template name="getDate"> 
							<xsl:with-param name="givenDate" select="date"/>	
						</xsl:call-template>
					</testdate>
					<testname><xsl:value-of select="/AdverseEventReport/Lab/labTerm/term"/></testname>
					<testresult><xsl:value-of select="value"/></testresult>
					<testunit>029</testunit>
					<testtype>Baseline</testtype>
				</test>	
			</xsl:for-each>
		
			<xsl:for-each select="/AdverseEventReport/Lab/nadir">
				<test>
					<testdateformat>String</testdateformat>
					<testdate>
						<xsl:call-template name="getDate"> 
							<xsl:with-param name="givenDate" select="date"/>	
						</xsl:call-template>
					</testdate>
					<testname><xsl:value-of select="/AdverseEventReport/Lab/labTerm/term"/></testname>
					<testresult><xsl:value-of select="value"/></testresult>
					<testunit>029</testunit>
					<testtype>Worst</testtype>
				</test>	
			</xsl:for-each>
		
			<xsl:for-each select="/AdverseEventReport/Lab/recovery">
				<test>
					<testdateformat>String</testdateformat>
					<testdate>
						<xsl:call-template name="getDate"> 
							<xsl:with-param name="givenDate" select="date"/>	
						</xsl:call-template>
					</testdate>
					<testname><xsl:value-of select="/AdverseEventReport/Lab/labTerm/term"/></testname>
					<testresult><xsl:value-of select="value"/></testresult>
					<testunit>029</testunit>
					<testtype>Recovery</testtype>
				</test>	
			</xsl:for-each>
					
			<!-- Adverse Events -->
			<xsl:for-each select="/AdverseEventReport/AdverseEvent">	
			  <reaction>
				<aeexternalid><xsl:value-of select="externalId"/></aeexternalid>
				
					<xsl:call-template name="isPrimaryAE"> 
						<xsl:with-param name="verbatim" select="detailsForOther"/>
					</xsl:call-template>
				
				<primarysourcereaction><xsl:value-of select="detailsForOther"/></primarysourcereaction>
				<reactionmeddrallt><xsl:value-of select="AdverseEventCtcTerm/ctc-term/ctepCode"/></reactionmeddrallt>
				<reactionstartdateformat>102</reactionstartdateformat>
				<reactionstartdate>
					<xsl:call-template name="getDate"> 
							<xsl:with-param name="givenDate" select="startDate"/>	
					</xsl:call-template>
				</reactionstartdate>
				<xsl:if test="endDate"> 
					<reactionenddateformat>102</reactionenddateformat>
					<reactionenddate>
						<xsl:call-template name="getDate"> 
								<xsl:with-param name="givenDate" select="endDate"/>	
						</xsl:call-template>
					</reactionenddate>
				</xsl:if>
				<xsl:for-each select="Outcome">
					<xsl:call-template name="ConvertOutcomeToCode"> 
						<xsl:with-param name="code" select="OutcomeType"/>
					</xsl:call-template>
				</xsl:for-each>
			  </reaction>

			</xsl:for-each>
			
			<!-- Medical Device -->
			<xsl:for-each select="/AdverseEventReport/MedicalDevice">
				<drug>
					<devicetype><xsl:value-of select="deviceType"/></devicetype>
					<devicenamecommon><xsl:value-of select="brandName"/></devicenamecommon>
					<devicenamebrand><xsl:value-of select="commonName"/></devicenamebrand>
					<devicenumbercatalog><xsl:value-of select="catalogNumber"/></devicenumbercatalog>
					<deviceavailableflag><xsl:value-of select="EvaluationAvailability"/></deviceavailableflag>
					<devicedateexpiration><xsl:value-of select="expiredDate"/></devicedateexpiration>
					<devicedateexplanted><xsl:value-of select="explantedDate"/></devicedateexplanted>
					<devicedateimplanted><xsl:value-of select="implantedDate"/></devicedateimplanted>
					<devicereprocessedflag><xsl:value-of select="DeviceReprocessed"/></devicereprocessedflag>
					<devicereprocessorname><xsl:value-of select="DeviceReprocessedName"/></devicereprocessorname>
					<devicereprocessoraddress><xsl:value-of select="DeviceReprocessorAddress"/></devicereprocessoraddress>
					<devicemanufacturername><xsl:value-of select="manufacturerName"/></devicemanufacturername>
					<devicemanufacturercity><xsl:value-of select="manufacturerCity"/></devicemanufacturercity>
					<devicemanufacturerstate><xsl:value-of select="manufacturerName"/></devicemanufacturerstate>
					<devicenumberlot><xsl:value-of select="lotNumber"/></devicenumberlot>
					<devicenumbermodel><xsl:value-of select="modelNumber"/></devicenumbermodel>
					<devicenumberserial><xsl:value-of select="serialNumber"/></devicenumberserial>
					<devicenumberother><xsl:value-of select="otherNumber"/></devicenumberother>
					<deviceoperator><xsl:value-of select="DeviceOperator"/></deviceoperator>
					<deviceoperatorother><xsl:value-of select="DeviceOperatorOther"/></deviceoperatorother>
					<devicereturndate><xsl:value-of select="DeviceReturnDate"/></devicereturndate>
					<drugadditional>Device</drugadditional>
				 </drug> 
			</xsl:for-each>	
			
			<!-- Radiation -->
			<xsl:for-each select="/AdverseEventReport/RadiationIntervention">
				 <drug>
					<medicinalproduct><xsl:value-of select="administration"/></medicinalproduct>
					<drugcumulativedosagenumb><xsl:value-of select="dosage"/></drugcumulativedosagenumb>
					<radiationdoseunit><xsl:value-of select="dosageUnit"/></radiationdoseunit>
					<drugintervaldosageunitnumb><xsl:value-of select="daysElapsed"/></drugintervaldosageunitnumb>
					<drugenddateformat>102</drugenddateformat>
						<!-- Date last administered -->
					<drugenddate>
						<xsl:call-template name="getDate"> 
								<xsl:with-param name="givenDate" select="lastTreatmentDate"/>	
						</xsl:call-template>
					</drugenddate> 
					<drugseparatedosagenumb><xsl:value-of select="fractionNumber"/></drugseparatedosagenumb>
					<actiondrug><xsl:value-of select="adjustment"/></actiondrug>
					<drugadditional>Radiation</drugadditional>
			      </drug>
			</xsl:for-each>	 
			
			<!-- Surgery -->
			<xsl:for-each select="/AdverseEventReport/SurgeryIntervention">
				  <drug>
					<medicinalproduct><xsl:value-of select="InterventionSite/name"/></medicinalproduct>
					<drugstartdateformat>102</drugstartdateformat>
						<!-- Date last administered -->
					<drugstartdate>
						<xsl:call-template name="getDate"> 
								<xsl:with-param name="givenDate" select="interventionDate"/>	
						</xsl:call-template>
					</drugstartdate> 
					<drugadditional>Surgery</drugadditional>
				  </drug>
			</xsl:for-each>	   
			
		
			<!-- Concomitant Medication -->
			<xsl:for-each select="/AdverseEventReport/ConcomitantMedication">
			  <drug>
			    <drugcharacterization>2</drugcharacterization>
				<medicinalproduct><xsl:value-of select="name"/></medicinalproduct>
				<drugstartdateformat>102</drugstartdateformat>
				<drugstartdate><xsl:value-of select="startDate/yearString"/><xsl:value-of select="startDate/monthString"/><xsl:value-of select="startDate/dayString"/></drugstartdate>
				<drugenddate><xsl:value-of select="endDate/yearString"/><xsl:value-of select="endDate/monthString"/><xsl:value-of select="endDate/dayString"/></drugenddate>
			   </drug>
			 </xsl:for-each>
			 
			 <!-- Other Cause -->
			<xsl:for-each select="/AdverseEventReport/OtherCause"> 
			   <medicalhistoryepisode>        
				<patientepisodename><xsl:value-of select="text"/></patientepisodename>
				<patientmedicalcomment>Other Cause</patientmedicalcomment>
			   </medicalhistoryepisode>
			</xsl:for-each>
			
			<!-- Study Disease -->
			<xsl:for-each select="/AdverseEventReport/StudyParticipantAssignment/StudySite/Study/CtepStudyDisease[leadDisease='true']"> 
				<medicalhistoryepisode>	
					<patientepisodename><xsl:value-of select="DiseaseTerm/ctepTerm"/></patientepisodename>            
						<!-- patientmedicalcomment = "Study Disease" identifies this as the study disease information -->
					<patientmedicalcomment>Study Disease</patientmedicalcomment> 
				</medicalhistoryepisode>	
			</xsl:for-each>
			
			<!-- Other study primary disease site -->
			<xsl:if test="/AdverseEventReport/DiseaseHistory/otherPrimaryDiseaseSite">
					<medicalhistoryepisode>	
						<patientepisodename><xsl:value-of select="/AdverseEventReport/DiseaseHistory/otherPrimaryDiseaseSite"/></patientepisodename>            
							<!-- patientmedicalcomment = "Study Disease" identifies this as the study disease information -->
						<patientmedicalcomment>Other Disease Site</patientmedicalcomment> 
					</medicalhistoryepisode>	
			</xsl:if>
			
			<!-- Metastatic Site -->
			<xsl:for-each select="/AdverseEventReport/DiseaseHistory/MetastaticDiseaseSite/AnatomicSite">
				<xsl:if test="name!='Other, specify'">
				<medicalhistoryepisode>
					<patientepisodename><xsl:value-of select="name"/></patientepisodename>
						<!-- patientmedicalcomment = "Disease Site" identifies this as the study disease site information -->
					<patientmedicalcomment>Metastatic Site</patientmedicalcomment> 
				</medicalhistoryepisode>   
				</xsl:if>
			</xsl:for-each>
			
			<!-- Other Metatstatic Site -->
			<xsl:if test="/AdverseEventReport/DiseaseHistory/MetastaticDiseaseSite/otherSite">
					<medicalhistoryepisode>	
						<patientepisodename><xsl:value-of select="/AdverseEventReport/DiseaseHistory/MetastaticDiseaseSite/otherSite"/></patientepisodename>            
							<!-- patientmedicalcomment = "Study Disease" identifies this as the study disease information -->
						<patientmedicalcomment>Other Metastatic Site</patientmedicalcomment> 
					</medicalhistoryepisode>	
			</xsl:if>		
			
			<!--  Course Agent -->
			<xsl:for-each select="/AdverseEventReport/TreatmentInformation/CourseAgent">
				<drug>
		            <drugcharacterization>1</drugcharacterization> 
						<!-- Agent NSC Number.  This is the identifier of the Agent. -->
		            <medicinalproduct><xsl:value-of select="StudyAgent/Agent/nscNumber"/></medicinalproduct> 
						<!-- Total Dose Administered this Course -->
					<drugcumulativedosagenumb><xsl:value-of select="Dose/amount"/></drugcumulativedosagenumb>  
						<!-- Dosage unit per E2B code -->   
					<drugcumulativedosageunit>
						<xsl:call-template name="lookup">
							<xsl:with-param name="_map" select="$map//uomse2b" />
							<xsl:with-param name="_code" select="Dose/units" />
						</xsl:call-template>
					</drugcumulativedosageunit>       
					<drugenddateformat>102</drugenddateformat>
						<!-- Date last administered -->
					<drugenddate>
						<xsl:call-template name="getDate"> 
								<xsl:with-param name="givenDate" select="lastAdministeredDate"/>	
						</xsl:call-template>
					</drugenddate>  
		           <drugadditional>Agent</drugadditional> 
	           </drug> 	
	         </xsl:for-each>
			
		 <summary>
		 
			 <xsl:if test="/AdverseEventReport/AdverseEventResponseDescription">
				<xsl:if test="/AdverseEventReport/TreatmentInformation/investigationalAgentAdministered">
					<indadminflag><xsl:call-template name="convertBooleantoOneTwo"> 
							<xsl:with-param name="boolType" select="/AdverseEventReport/TreatmentInformation/investigationalAgentAdministered"/>
							</xsl:call-template>
					</indadminflag>
				</xsl:if>
				<xsl:if test="/AdverseEventReport/TreatmentInformation/investigationalDeviceAdministered">	
					<ideadminflag><xsl:value-of select="/AdverseEventReport/TreatmentInformation/investigationalDeviceAdministered"/></ideadminflag>
				</xsl:if>
				<narrativeincludeclinical><xsl:value-of select="/AdverseEventReport/AdverseEventResponseDescription/eventDescription"/></narrativeincludeclinical>
				<xsl:call-template name="ConvertPresentStatusToCode"> 
					<xsl:with-param name="code" select="/AdverseEventReport/AdverseEventResponseDescription/presentStatus"/>
				</xsl:call-template>
				<xsl:if test="/AdverseEventReport/AdverseEventResponseDescription/deathDate">
					<patientdeathdate><xsl:value-of select="/AdverseEventReport/AdverseEventResponseDescription/deathDate"/></patientdeathdate>
				</xsl:if>
				<xsl:call-template name="ConvertRetreatedToCode"> 
					<xsl:with-param name="code" select="/AdverseEventReport/AdverseEventResponseDescription/retreated"/>
				</xsl:call-template>
			 </xsl:if>
		 
		 </summary>
		
		 </patient>
		</safetyreport>
		</ichicsr>	
	</xsl:template>
</xsl:stylesheet>