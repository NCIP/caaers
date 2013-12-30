<?xml version="1.0" encoding="UTF-8"?>
<!-- Copyright SemanticBits, Northwestern University and Akaza Research Distributed 
	under the OSI-approved BSD 3-Clause License. See http://ncip.github.com/caaers/LICENSE.txt 
	for details. -->
<xsl:stylesheet version="2.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/"
	xmlns:cct="http://schema.integration.caaers.cabig.nci.nih.gov/common"
	xmlns:ae="http://schema.integration.caaers.cabig.nci.nih.gov/aereport">
	<xsl:output method="xml" encoding="UTF-8" indent="yes" />

	<xsl:variable name="map" select="document('lookup.xml')" />

	<xsl:template name="lookup">
		<xsl:param name="_map" />
		<xsl:param name="_code" />
		<xsl:value-of select="$_map//code[text() = $_code]/parent::node()/value" />
	</xsl:template>

	<xsl:template match="/">
		<xsl:call-template name="safetyreport" />
	</xsl:template>


	<xsl:template match="safetyreport" name="safetyreport">
		<soapenv:Envelope>
			<soapenv:Body>
				<ae:submitSafetyReport>
					<ae:AdverseEventReport>
						<ae:createdAt>
							<xsl:call-template name="dateTimeConverter">
								<xsl:with-param name="date"
									select="/ichicsr/ichicsrmessageheader/messagedate" />
							</xsl:call-template>
						</ae:createdAt>
						<xsl:call-template name="responseDescription" />
						<ae:adverseEventReportingPeriod>
							<xsl:if test="/ichicsr/safetyreport/patient/drug[drugcharacterization = '3']/ drugindication">
								<ae:treatmentAssignmentDescription>
									<xsl:value-of select="/ichicsr/safetyreport/patient/drug[drugcharacterization = '3']/drugindication" />
								</ae:treatmentAssignmentDescription>
							</xsl:if>
							<xsl:if test="/ichicsr/safetyreport/patient/drug[drugcharacterization = '3']/drugstartperiod">
								<ae:cycleNumber>
									<xsl:value-of select="/ichicsr/safetyreport/patient/drug[drugcharacterization = '3']/drugstartperiod" />
								</ae:cycleNumber>
							</xsl:if>
							<xsl:if test="/ichicsr/safetyreport/patient/drug[drugcharacterization = '3']/drugenddate">
								<ae:startDate>
									<xsl:call-template name="dateConverterYYYYMMDDtoYY-MM-DD">
										<xsl:with-param name="date" select="/ichicsr/safetyreport/patient/drug[drugcharacterization = '3']/drugenddate" />
									</xsl:call-template>
								</ae:startDate>								
							</xsl:if>
							<ae:studyParticipantAssignmentRef>
								<ae:studySiteRef>
									<ae:studyRef>
										<ae:identifiers>
											<ae:identifier>
												<ae:type>Protocol Authority Identifier</ae:type>
												<ae:value>
													<xsl:value-of
														select="/ichicsr/safetyreport/primarysource/sponsorstudynumb" />
												</ae:value>
											</ae:identifier>
										</ae:identifiers>
									</ae:studyRef>
									<ae:nciInstituteCode>
										<xsl:value-of select="/ichicsr/safetyreport/patient/subjectstudysiteid" />
									</ae:nciInstituteCode>
								</ae:studySiteRef>
								<ae:studySubjectIdentifier>
									<xsl:value-of
										select="/ichicsr/safetyreport/patient/patientinvestigationnumb" />
								</ae:studySubjectIdentifier>
							</ae:studyParticipantAssignmentRef>
							<ae:treatmentAssignment>
								<ae:code>
									<xsl:value-of select="/ichicsr/safetyreport/patient/drug[drugcharacterization = '3']/medicinalproduct" />
								</ae:code>
							</ae:treatmentAssignment>
						</ae:adverseEventReportingPeriod>	
						
						<xsl:if test="/ichicsr/safetyreport/physiciangivename">
							<ae:physician>
								<ae:firstName>
									<xsl:value-of select="/ichicsr/safetyreport/physiciangivename" />
								</ae:firstName>
								<ae:lastName>
									<xsl:value-of select="/ichicsr/safetyreport/physicianfamilyname" />
								</ae:lastName>
								<xsl:if test="/ichicsr/safetyreport/physicianmiddlename">
									<ae:middleName>
										<xsl:value-of select="/ichicsr/safetyreport/physicianmiddlename" />
									</ae:middleName>
								</xsl:if>
								<xsl:if test="/ichicsr/safetyreport/physicianemail">
									<ae:contactMechanism>
										<ae:type>e-mail</ae:type>
										<ae:value>
											<xsl:value-of select="/ichicsr/safetyreport/physicianemail" />
										</ae:value>
									</ae:contactMechanism>
								</xsl:if>
								<xsl:if test="/ichicsr/safetyreport/physicianphone">
									<ae:contactMechanism>
										<ae:type>phone</ae:type>
										<ae:value>
											<xsl:value-of select="/ichicsr/safetyreport/physicianphone" />
										</ae:value>
									</ae:contactMechanism>
								</xsl:if>
								<xsl:if test="/ichicsr/safetyreport/physicianfax">
									<ae:contactMechanism>
										<ae:type>fax</ae:type>
										<ae:value>
											<xsl:value-of select="/ichicsr/safetyreport/physicianfax" />
										</ae:value>
									</ae:contactMechanism>
								</xsl:if>
							</ae:physician>
						</xsl:if>

						
						<xsl:if test="/ichicsr/safetyreport/primarysource/reportergivename">
							<ae:reporter>
								<ae:firstName>
									<xsl:value-of select="/ichicsr/safetyreport/primarysource/reportergivename" />
								</ae:firstName>
								<ae:lastName>
									<xsl:value-of select="/ichicsr/safetyreport/primarysource/reporterfamilyname" />
								</ae:lastName>
								<xsl:if test="/ichicsr/safetyreport/primarysource/reportermiddlename">
									<ae:middleName>
										<xsl:value-of select="/ichicsr/safetyreport/primarysource/reportermiddlename" />
									</ae:middleName>
								</xsl:if>
								<xsl:if test="/ichicsr/safetyreport/reporteremail">
									<ae:contactMechanism>
										<ae:type>e-mail</ae:type>
										<ae:value>
											<xsl:value-of select="/ichicsr/safetyreport/reporteremail" />
										</ae:value>
									</ae:contactMechanism>
								</xsl:if>
								<xsl:if test="/ichicsr/safetyreport/reporterphone">
									<ae:contactMechanism>
										<ae:type>phone</ae:type>
										<ae:value>
											<xsl:value-of select="/ichicsr/safetyreport/reporterphone" />
										</ae:value>
									</ae:contactMechanism>
								</xsl:if>
								<xsl:if test="/ichicsr/safetyreport/reporterfax">
									<ae:contactMechanism>
										<ae:type>fax</ae:type>
										<ae:value>
											<xsl:value-of select="/ichicsr/safetyreport/reporterfax" />
										</ae:value>
									</ae:contactMechanism>
								</xsl:if>
							</ae:reporter>
						</xsl:if>	
					
						
						<xsl:if test="/ichicsr/safetyreport/sender/sendergivename">
							<xsl:call-template name="submitter" />
						</xsl:if>
						<!--Optional: -->
						<ae:diseaseHistory>
							<!--Optional: -->
							<xsl:if
								test="/ichicsr/safetyreport/patient/medicalhistoryepisode[patientmedicalcomment = 'Study Disease']/patientmedicalstartdate != ''">
								<ae:diagnosisDate>
									<xsl:call-template name="splitDateYYYYMMDD">
										<xsl:with-param name="date"
											select="/ichicsr/safetyreport/patient/medicalhistoryepisode[patientmedicalcomment = 'Study Disease']/patientmedicalstartdate" />
									</xsl:call-template>
								</ae:diagnosisDate>
							</xsl:if>
							<!--Zero or more repetitions: -->
							<xsl:for-each select="//medicalhistoryepisode[patientmedicalcomment = 'Study Disease']">
									<xsl:call-template name="studyDiseaseTemplate" />
							</xsl:for-each>
							<xsl:for-each select="//medicalhistoryepisode[patientmedicalcomment = 'Other Study Disease']">
								<xsl:call-template name="otherStudyDiseaseTemplate" />
							</xsl:for-each>
							<xsl:for-each select="//medicalhistoryepisode[patientmedicalcomment = 'Disease Site']">
								<xsl:call-template name="diseaseSiteTemplate" />
							</xsl:for-each>
							<xsl:for-each select="//medicalhistoryepisode[patientmedicalcomment = 'Other Disease Site']">
								<xsl:call-template name="otherDiseaseSiteTemplate" />
							</xsl:for-each>
							<xsl:for-each select="//medicalhistoryepisode[patientmedicalcomment = 'Other Metastatic Site']">
								<xsl:call-template name="otherMetastaticSiteTemplate" />
							</xsl:for-each>
							<xsl:for-each select="//medicalhistoryepisode[patientmedicalcomment = 'Metastatic Site']">
								<xsl:call-template name="metastaticSiteTemplate" />
							</xsl:for-each>
						</ae:diseaseHistory>
						<xsl:call-template name="participantHistory"
/>
						<!--Zero or more repetitions: -->
						<xsl:for-each select="//drug[drugadditional = 'Radiation']">
							<xsl:call-template name="radiationIntervention" />
						</xsl:for-each>
						<!--Zero or more repetitions: -->
						<xsl:for-each select="//drug[drugadditional = 'Surgery']">
							<xsl:call-template name="surgeryIntervention" />
						</xsl:for-each>
						<!--Zero or more repetitions: -->
						<xsl:for-each select="//drug[drugadditional = 'Device']">
							<xsl:call-template name="medicalDevice" />
						</xsl:for-each>
						<!--Zero or more repetitions: -->

						<xsl:for-each
							select="//drug[drugadditional != 'Radiation' and  drugadditional != 'Surgery' and drugadditional != 'Device' and drugcharacterization = '2']">
							<xsl:call-template name="concomitantMedication" />
						</xsl:for-each>
						<!--1 or more repetitions: -->
						<xsl:for-each select="//reaction[not(attribution)]">
							<xsl:if test="aeexternalid">
								<xsl:call-template name="adverseEvent" />
							</xsl:if>
						</xsl:for-each>

						<xsl:for-each select="//test">
							<xsl:call-template name="lab" />
						</xsl:for-each>

						<!--Zero or more repetitions: -->

						<xsl:for-each
							select="//medicalhistoryepisode[patientmedicalcomment = 'Prior Therapy']">
							<xsl:call-template name="priorTherapy" />
						</xsl:for-each>
						<ae:treatmentInformation>
							<xsl:if test="/ichicsr/safetyreport/patient/summary/indadminflag">
								<ae:investigationalAgentAdministered>
									<xsl:call-template name="convertOneTwotoBoolean">
										<xsl:with-param name="oneTwoType" select="/ichicsr/safetyreport/patient/summary/indadminflag" />
									</xsl:call-template>
								</ae:investigationalAgentAdministered>
							</xsl:if>
							<xsl:for-each
								select="//drug[drugadditional != 'Radiation' and  drugadditional != 'Surgery' and drugadditional != 'Device' and drugcharacterization = '1']">
								<xsl:call-template name="courseAgent" />
							</xsl:for-each>
						</ae:treatmentInformation>

						<!--Zero or more repetitions: -->
						<xsl:for-each
							select="//medicalhistoryepisode[patientmedicalcomment = 'Pre-existing Condition' or patientmedicalcomment = 'Other Pre-existing Condition']">
							<xsl:call-template name="preexistingCondition" />
						</xsl:for-each>
						<!--Zero or more repetitions: -->
						<xsl:for-each
							select="//medicalhistoryepisode[patientmedicalcomment = 'Other Cause']">
							<xsl:call-template name="otherCause" />
						</xsl:for-each>
						
						<ae:additionalInformation>
						   <xsl:if test="/ichicsr/safetyreport/autopsyreportflag">
							   <ae:autopsyReport>
									<xsl:call-template name="convertOneTwotoBoolean">
										<xsl:with-param name="oneTwoType" select="/ichicsr/safetyreport/autopsyreportflag" />
									</xsl:call-template>
							   </ae:autopsyReport>
							</xsl:if>
						  <xsl:if test="/ichicsr/safetyreport/consultsflag">
							   <ae:consults>
									<xsl:call-template name="convertOneTwotoBoolean">
										<xsl:with-param name="oneTwoType" select="/ichicsr/safetyreport/consultsflag" />
									</xsl:call-template>
							   </ae:consults>
							</xsl:if>
						   <xsl:if test="/ichicsr/safetyreport/dischargesummaryflag">
							   <ae:dischargeSummary>
									<xsl:call-template name="convertOneTwotoBoolean">
										<xsl:with-param name="oneTwoType" select="/ichicsr/safetyreport/dischargesummaryflag" />
									</xsl:call-template>
							   </ae:dischargeSummary>
							</xsl:if>
						   <xsl:if test="/ichicsr/safetyreport/flowsheetscrfsflag">
							   <ae:flowCharts>
									<xsl:call-template name="convertOneTwotoBoolean">
										<xsl:with-param name="oneTwoType" select="/ichicsr/safetyreport/flowsheetscrfsflag" />
									</xsl:call-template>
							   </ae:flowCharts>
							</xsl:if>
						   <xsl:if test="/ichicsr/safetyreport/labreportsflag">
							   <ae:labReports>
									<xsl:call-template name="convertOneTwotoBoolean">
										<xsl:with-param name="oneTwoType" select="/ichicsr/safetyreport/labreportsflag" />
									</xsl:call-template>
							   </ae:labReports>
							</xsl:if>
						   <xsl:if test="/ichicsr/safetyreport/obaformsflag">
							   <ae:obaForm>
									<xsl:call-template name="convertOneTwotoBoolean">
										<xsl:with-param name="oneTwoType" select="/ichicsr/safetyreport/obaformsflag" />
									</xsl:call-template>
							   </ae:obaForm>
							</xsl:if>
						    <xsl:if test="/ichicsr/safetyreport/otheradditionalflag">
							   <ae:other>
									<xsl:call-template name="convertOneTwotoBoolean">
										<xsl:with-param name="oneTwoType" select="/ichicsr/safetyreport/otheradditionalflag" />
									</xsl:call-template>
							   </ae:other>
							</xsl:if>
						    <xsl:if test="/ichicsr/safetyreport/pathologyreportflag">
							   <ae:pathologyReport>
									<xsl:call-template name="convertOneTwotoBoolean">
										<xsl:with-param name="oneTwoType" select="/ichicsr/safetyreport/pathologyreportflag" />
									</xsl:call-template>
							   </ae:pathologyReport>
							</xsl:if>
						    <xsl:if test="/ichicsr/safetyreport/progressnotesflag">
							   <ae:progressNotes>
									<xsl:call-template name="convertOneTwotoBoolean">
										<xsl:with-param name="oneTwoType" select="/ichicsr/safetyreport/progressnotesflag" />
									</xsl:call-template>
							   </ae:progressNotes>
							</xsl:if>
						    <xsl:if test="/ichicsr/safetyreport/radiologyreportflag">
							   <ae:radiologyReports>
									<xsl:call-template name="convertOneTwotoBoolean">
										<xsl:with-param name="oneTwoType" select="/ichicsr/safetyreport/radiologyreportflag" />
									</xsl:call-template>
							   </ae:radiologyReports>
							</xsl:if>
						    <xsl:if test="/ichicsr/safetyreport/referrallettersflag">
							   <ae:referralLetters>
									<xsl:call-template name="convertOneTwotoBoolean">
										<xsl:with-param name="oneTwoType" select="/ichicsr/safetyreport/referrallettersflag" />
									</xsl:call-template>
							   </ae:referralLetters>
							</xsl:if>
						    <xsl:if test="/ichicsr/safetyreport/irbsummaryreportflag">
							   <ae:irbReport>
									<xsl:call-template name="convertOneTwotoBoolean">
										<xsl:with-param name="oneTwoType" select="/ichicsr/safetyreport/irbsummaryreportflag" />
									</xsl:call-template>
							   </ae:irbReport>
							</xsl:if>						  
						    <xsl:if test="/ichicsr/safetyreport/documentlist">
							   <ae:otherInformation><xsl:value-of select="/ichicsr/safetyreport/documentlist" /></ae:otherInformation>
							</xsl:if>
						</ae:additionalInformation>

						<!--1 or more repetitions: -->
						<ae:report>
							<ae:caseNumber>
								<xsl:value-of select="/ichicsr/safetyreport/safetyreportid" />
							</ae:caseNumber>
							<ae:aeReportDefinition>
								<ae:name>
									<xsl:value-of select="/ichicsr/safetyreport/reportname" />
								</ae:name>
							</ae:aeReportDefinition>
							<!--Zero or more repetitions: -->
							<ae:aeReportVersion>
								<xsl:if test="/ichicsr/safetyreport/safetyreportversion">
									<ae:reportVersionId>
										<xsl:value-of select="/ichicsr/safetyreport/safetyreportversion" />
									</ae:reportVersionId>
								</xsl:if>
								 <xsl:if test="/ichicsr/safetyreport/sender/recipientemails">
									<ae:ccEmails>
										<xsl:value-of select="/ichicsr/safetyreport/sender/recipientemails" />
									</ae:ccEmails>
								</xsl:if>
							</ae:aeReportVersion>
						</ae:report>
					</ae:AdverseEventReport>
				</ae:submitSafetyReport>
			</soapenv:Body>
		</soapenv:Envelope>
	</xsl:template>


	<xsl:template name="studyDiseaseTemplate">
		<xsl:if test="./patientmedicalcomment = 'Study Disease' ">
			<ae:primaryDisease>
				<xsl:value-of select="patientepisodename" />
			</ae:primaryDisease>
		</xsl:if>
	</xsl:template>
	
	<xsl:template name="diseaseSiteTemplate">
		<xsl:if test="./patientmedicalcomment = 'Disease Site' ">
			<ae:codedPrimaryDiseaseSite>
				<xsl:value-of select="patientepisodename" />
			</ae:codedPrimaryDiseaseSite>
		</xsl:if>
	</xsl:template>
	
	<xsl:template name="otherDiseaseSiteTemplate">
		<xsl:if test="./patientmedicalcomment = 'Other Disease Site' ">
			<ae:otherPrimaryDiseaseSite>
				<xsl:value-of select="patientepisodename" />
			</ae:otherPrimaryDiseaseSite>
		</xsl:if>
	</xsl:template>
	
	<xsl:template name="otherStudyDiseaseTemplate">
		<xsl:if test="./patientmedicalcomment = 'Other Study Disease' ">
			<ae:otherPrimaryDisease>
				<xsl:value-of select="patientepisodename" />
			</ae:otherPrimaryDisease>
		</xsl:if>
	</xsl:template>
	
	<xsl:template name="metastaticSiteTemplate">
		<xsl:if test="./patientmedicalcomment = 'Metastatic Site' ">
			<ae:metastaticDiseaseSite>
				<ae:codedSite>
					<ae:name>
						<xsl:value-of select="patientepisodename" />
					</ae:name>
				</ae:codedSite>
			</ae:metastaticDiseaseSite>
		</xsl:if>
	</xsl:template>
	
	<xsl:template name="otherMetastaticSiteTemplate">
		<xsl:if test="./patientmedicalcomment = 'Other Metastatic Site' ">
			<ae:metastaticDiseaseSite>
				<ae:otherSite>
					<xsl:value-of select="patientepisodename" />
				</ae:otherSite>
			</ae:metastaticDiseaseSite>
		</xsl:if>
	</xsl:template>

	<xsl:template name="priorTherapy">
		<ae:sAEReportPriorTherapy>
			<ae:priorTherapy>
				<ae:text>
					<xsl:value-of select="patientepisodename" />
				</ae:text>				
			</ae:priorTherapy>
			
			<ae:other>
				<xsl:value-of select="priortherapycomment" />
			</ae:other>
			
			<xsl:if test="./patientmedicalstartdate != '' ">
				<ae:startDate>
					<xsl:call-template name="splitDateYYYYMMDD">
						<xsl:with-param name="date" select="./patientmedicalstartdate" />
					</xsl:call-template>
				</ae:startDate>
			</xsl:if>
			<xsl:if test="./patientmedicalenddate != '' ">
				<ae:endDate>
					<xsl:call-template name="splitDateYYYYMMDD">
						<xsl:with-param name="date" select="./patientmedicalenddate" />
					</xsl:call-template>
				</ae:endDate>
			</xsl:if>
			
			<xsl:for-each select="priortherapyagent">
				<ae:priorTherapyAgent>
					<ae:agent>
						<ae:nscNumber>
							<xsl:value-of select="." />
						</ae:nscNumber>
					</ae:agent>
				</ae:priorTherapyAgent>
			</xsl:for-each>
		</ae:sAEReportPriorTherapy>
	</xsl:template>

	<xsl:template name="preexistingCondition">
		<ae:sAEReportPreExistingCondition>
			<xsl:if test="./patientmedicalcomment = 'Pre-existing Condition' ">
				<ae:preExistingCondition>
					<text>
						<xsl:value-of select="patientepisodename" />
					</text>
				</ae:preExistingCondition>
			</xsl:if>
			<xsl:if test="./patientmedicalcomment = 'Other Pre-existing Condition' ">
				<ae:other>
					<xsl:value-of select="patientepisodename" />
				</ae:other>
			</xsl:if>
		</ae:sAEReportPreExistingCondition>
	</xsl:template>

	<xsl:template name="splitDateYYYYMMDD">
		<xsl:param name="date" />
		<xsl:variable name="valueLength" select="string-length($date)-1" />
		<xsl:if test="$valueLength > 6">
			<ae:day>
				<xsl:value-of select='substring($date,7,2)' />
			</ae:day>
		</xsl:if>
		<ae:month>
			<xsl:value-of select='substring($date,5,2)' />
		</ae:month>
		<ae:year>
			<xsl:value-of select='substring($date,1,4)' />
		</ae:year>
	</xsl:template>

	<xsl:template name="dateTimeConverter">
		<xsl:param name="date" />
		<xsl:variable name="valueLength" select="string-length($date)-1" />
		<xsl:variable name="vDay" select='substring($date,7,2)' />
		<xsl:variable name="vMonth" select='substring($date,5,2)' />
		<xsl:variable name="vYear" select='substring($date,1,4)' />

		<xsl:variable name="vHour" select='substring($date,9,2)' />
		<xsl:variable name="vMin" select='substring($date,11,2)' />
		<xsl:variable name="vSec" select='substring($date,13,2)' />

		<xsl:variable name="dateTime"
			select="concat($vYear,'-',$vMonth,'-',$vDay,'T',$vHour,':',$vMin,':',$vSec)" />
		<xsl:value-of select="$dateTime" />
	</xsl:template>

	<xsl:template name="concomitantMedication">
		<ae:concomitantMedication>
			<ae:name>
				<xsl:value-of select="medicinalproduct" />
			</ae:name>
			<xsl:if test="./drugstartdate != '' ">
				<ae:startDate>
					<xsl:call-template name="splitDateYYYYMMDD">
						<xsl:with-param name="date" select="./drugstartdate" />
					</xsl:call-template>
				</ae:startDate>
			</xsl:if>
			<xsl:if test="./drugenddate != '' ">
				<ae:endDate>
					<xsl:call-template name="splitDateYYYYMMDD">
						<xsl:with-param name="date" select="./drugenddate" />
					</xsl:call-template>
				</ae:endDate>
			</xsl:if>
		</ae:concomitantMedication>
	</xsl:template>
	<xsl:template name="courseAgent">
		<ae:courseAgent>
			<!--Optional: -->
			<xsl:if test="drugenddate != '' ">
				<ae:lastAdministeredDate>
					<xsl:call-template name="dateConverterYYYYMMDDtoYY-MM-DD">
						<xsl:with-param name="date" select="drugenddate" />
					</xsl:call-template>
				</ae:lastAdministeredDate>
			</xsl:if>
			<xsl:if test="drugtreatmentduration">
				<ae:administrationDelayAmount>
						<xsl:value-of select="drugtreatmentduration" />
				</ae:administrationDelayAmount>
			</xsl:if>
			<xsl:if test="drugtreatmentdurationunit">
				<ae:administrationDelayUnits>
						<xsl:value-of select="drugtreatmentdurationunit" />
				</ae:administrationDelayUnits>
			</xsl:if>
			<ae:dose>
				<ae:amount>
					<xsl:value-of select="drugcumulativedosagenumb" />
				</ae:amount>
				<ae:units>
					<xsl:call-template name="lookup">
						<xsl:with-param name="_map" select="$map//uoms" />
						<xsl:with-param name="_code" select='drugcumulativedosageunit' />
					</xsl:call-template>
				</ae:units>
			</ae:dose>
			<ae:studyAgent>
				<!--You have a CHOICE of the next 2 items at this level -->
				<ae:Agent>
					<ae:nscNumber>
						<xsl:value-of select="medicinalproduct" />
					</ae:nscNumber>
				</ae:Agent>
			</ae:studyAgent>
			<ae:lotNumber>
				<xsl:value-of select="drugbatchnumb" />
			</ae:lotNumber>
			<ae:comments>
				<xsl:value-of select="drugadditional" />
			</ae:comments>
		</ae:courseAgent>
	</xsl:template>
	<xsl:template name="medicalDevice">
		<ae:medicalDevice>
			<!--Optional: -->
			<ae:brandName>
				<xsl:value-of select="devicenamebrand" />
			</ae:brandName>
			<!--Optional: -->
			<ae:commonName>
				<xsl:value-of select="devicenamecommon" />
			</ae:commonName>
			<!--Optional: -->
			<ae:deviceType>
				<xsl:value-of select="devicetype" />
			</ae:deviceType>
			<!--Optional: -->
			<ae:manufacturerName>
				<xsl:value-of select="devicemanufacturername" />
			</ae:manufacturerName>
			<!--Optional: -->
			<ae:manufacturerCity>
				<xsl:value-of select="devicemanufacturercity" />
			</ae:manufacturerCity>
			<!--Optional: -->
			<ae:manufacturerState>
				<xsl:value-of select="devicemanufacturerstate" />
			</ae:manufacturerState>
			<!--Optional: -->
			<ae:modelNumber>
				<xsl:value-of select="devicenumbermodel" />
			</ae:modelNumber>
			<!--Optional: -->
			<xsl:if test="devicedateexpiration">
				<ae:expirationDate>
					<xsl:value-of select="devicedateexpiration" />
				</ae:expirationDate>
			</xsl:if>
			<!--Optional: -->
			<ae:catalogNumber>
				<xsl:value-of select="devicedateexpiration" />
			</ae:catalogNumber>
			<!--Optional: -->
			<ae:serialNumber>
				<xsl:value-of select="devicenumberserial" />
			</ae:serialNumber>
			<!--Optional: -->
			<ae:otherNumber>
				<xsl:value-of select="devicenumberother" />
			</ae:otherNumber>
			<ae:explantedDate>
				<xsl:call-template name="dateConverterYYYYMMDDtoYY-MM-DD">
					<xsl:with-param name="date" select="devicedateexplanted" />
				</xsl:call-template>
			</ae:explantedDate>
			<!--Optional: -->
			<ae:deviceOperator>
				<xsl:value-of select="deviceoperator" />
			</ae:deviceOperator>
			<!--Optional: -->
			<xsl:if test="devicereturndate">
				<ae:returnedDate>
					<xsl:value-of select="devicereturndate" />
				</ae:returnedDate>
			</xsl:if>

			<xsl:if test="devicereprocessedflag = '1'">
				<ae:deviceReprocessed>YES</ae:deviceReprocessed>
				<ae:reprocessedName>
					<xsl:value-of select="devicereprocessorname" />
				</ae:reprocessedName>
				<ae:reprocessedAddress>
					<xsl:value-of select="devicereprocessoraddress" />
				</ae:reprocessedAddress>
			</xsl:if>
			<xsl:if test="devicereprocessedflag = '2'">
				<ae:deviceReprocessed>NO</ae:deviceReprocessed>
			</xsl:if>

			<ae:evaluationAvailability>
				<xsl:value-of select="deviceavailableflag" />
			</ae:evaluationAvailability>
			<ae:studyDevice>
				<ae:device>
					<ae:brandName>
						<xsl:value-of select="devicenamebrand" />
					</ae:brandName>
					<ae:commonName>
						<xsl:value-of select="devicenamecommon" />
					</ae:commonName>
					<!--Optional: -->
					<ae:type>
						<xsl:value-of select="devicetype" />
					</ae:type>
				</ae:device>
			</ae:studyDevice>
			<ae:otherDeviceOperator>
				<xsl:value-of select="deviceoperatorother" />
			</ae:otherDeviceOperator>
			<ae:lotNumber>
				<xsl:value-of select="devicenumberlot" />
			</ae:lotNumber>
		</ae:medicalDevice>
	</xsl:template>
	<xsl:template name="radiationIntervention">
		<ae:radiationIntervention>
			<ae:dosage>
				<xsl:value-of select="drugcumulativedosagenumb" />
			</ae:dosage>
			<ae:dosageUnit>
				<xsl:value-of select="radiationdoseunit" />
			</ae:dosageUnit>
			<xsl:if test="./drugenddate != '' ">
				<ae:lastTreatmentDate>
					<xsl:call-template name="dateConverterYYYYMMDDtoYY-MM-DD">
						<xsl:with-param name="date" select="drugenddate" />
					</xsl:call-template>
				</ae:lastTreatmentDate>
			</xsl:if>
			<ae:fractionNumber>
				<xsl:value-of select="drugseparatedosagenumb" />
			</ae:fractionNumber>
			<ae:daysElapsed>
				<xsl:value-of select="drugintervaldosageunitnumb" />
			</ae:daysElapsed>
			<ae:administrationDelayAmount>
				<xsl:value-of select="drugtreatmentduration" />
			</ae:administrationDelayAmount>
			<ae:administrationDelayUnits>
				<xsl:value-of select="drugtreatmentdurationunit" />
			</ae:administrationDelayUnits>
			<ae:adjustment>
				<xsl:value-of select="actiondrug" />
			</ae:adjustment>
			<ae:administration>
				<xsl:value-of select="medicinalproduct" />
			</ae:administration>
		</ae:radiationIntervention>
	</xsl:template>
	<xsl:template name="surgeryIntervention">
		<ae:surgeryIntervention>
			<xsl:if test="drugstartdate != '' ">
				<ae:interventionDate>
					<xsl:call-template name="dateConverterYYYYMMDDtoYY-MM-DD">
						<xsl:with-param name="date" select="drugstartdate" />
					</xsl:call-template>
				</ae:interventionDate>
			</xsl:if>
			<ae:InterventionSite>
				<ae:name>
					<xsl:value-of select="medicinalproduct" />
				</ae:name>
			</ae:InterventionSite>
		</ae:surgeryIntervention>
	</xsl:template>
	<xsl:template name="lab">
		<ae:lab>
			<!--Optional: -->
			<ae:labTerm>
				<ae:term>
					<xsl:value-of select="testname" />
				</ae:term>
			</ae:labTerm>
			<!--Optional: -->
			<ae:units>
				<xsl:call-template name="lookup">
					<xsl:with-param name="_map" select="$map//uoms" />
					<xsl:with-param name="_code" select='testunit' />
				</xsl:call-template>
			</ae:units>
			<!--Optional: -->
			<xsl:if test="testtype = 'Baseline'">
				<ae:baseline>
					<ae:value>
						<xsl:value-of select="testresult" />
					</ae:value>
					<xsl:if test="./testdate != '' ">
						<ae:date>
							<xsl:call-template name="dateConverterYYYYMMDDtoYY-MM-DD">
								<xsl:with-param name="date" select="./testdate" />
							</xsl:call-template>
						</ae:date>
					</xsl:if>
				</ae:baseline>
			</xsl:if>
			<!--Optional: -->
			<xsl:if test="testtype = 'Worst'">
				<ae:nadir>
					<ae:value>
						<xsl:value-of select="testresult" />
					</ae:value>
					<xsl:if test="./testdate != '' ">
						<ae:date>
							<xsl:call-template name="dateConverterYYYYMMDDtoYY-MM-DD">
								<xsl:with-param name="date" select="./testdate" />
							</xsl:call-template>
						</ae:date>
					</xsl:if>
				</ae:nadir>
			</xsl:if>
			<!--Optional: -->
			<xsl:if test="testtype = 'Recovery'">
				<ae:recovery>
					<ae:value>
						<xsl:value-of select="testresult" />
					</ae:value>
					<xsl:if test="./testdate != '' ">
						<ae:date>
							<xsl:call-template name="dateConverterYYYYMMDDtoYY-MM-DD">
								<xsl:with-param name="date" select="./testdate" />
							</xsl:call-template>
						</ae:date>
					</xsl:if>
				</ae:recovery>
			</xsl:if>
			<xsl:if
				test="testname = 'Bacterial infection NOS' or testname = 'Fungal infection NOS' or testname = 'Viral infection NOS'">
				<ae:infectiousAgent>
					<xsl:value-of select="testresult" />
				</ae:infectiousAgent>
				<ae:site>
					<xsl:value-of select="infectionsite" />
				</ae:site>
				<ae:labDate>
					<xsl:call-template name="dateConverterYYYYMMDDtoYY-MM-DD">
						<xsl:with-param name="date" select="./testdate" />
					</xsl:call-template>
				</ae:labDate>
			</xsl:if>
		</ae:lab>
	</xsl:template>
	<xsl:template name="responseDescription">
		<ae:adverseEventResponseDescription>
			<ae:eventDescription>
				<xsl:value-of
					select="/ichicsr/safetyreport/patient/summary/narrativeincludeclinical" />
			</ae:eventDescription>
			<xsl:if
				test='/ichicsr/safetyreport/patient/reaction[primaryaeflag=1]/reactionoutcome'>
				<ae:presentStatus>
					<xsl:call-template name="lookup">
						<xsl:with-param name="_map" select="$map//aepresentstatuses" />
						<xsl:with-param name="_code"
							select='/ichicsr/safetyreport/patient/reaction[primaryaeflag=1]/reactionoutcome' />
					</xsl:call-template>
				</ae:presentStatus>
			</xsl:if>
			<xsl:if
				test="/ichicsr/safetyreport/patient/summary/dateremoved != '' ">
				<ae:dateRemovedFromProtocol>
					<xsl:call-template name="dateConverterYYYYMMDDtoYY-MM-DD">
						<xsl:with-param name="date"
							select="/ichicsr/safetyreport/patient/summary/dateremoved" />
					</xsl:call-template>
				</ae:dateRemovedFromProtocol>
			</xsl:if>
			<xsl:if test='/ichicsr/safetyreport/patient/summary/retreatedflag'>
				<ae:retreated>
					<xsl:call-template name="lookup">
						<xsl:with-param name="_map" select="$map//e2byesnos" />
						<xsl:with-param name="_code"
							select='/ichicsr/safetyreport/patient/summary/retreatedflag' />
					</xsl:call-template>
				</ae:retreated>
			</xsl:if>
			<xsl:if
				test='/ichicsr/safetyreport/patient/patientdeath/patientautopsyyesno'>
				<ae:autopsyPerformed>
					<xsl:call-template name="lookup">
						<xsl:with-param name="_map" select="$map//e2byesnos" />
						<xsl:with-param name="_code"
							select='/ichicsr/safetyreport/patient/patientdeath/patientautopsyyesno' />
					</xsl:call-template>
				</ae:autopsyPerformed>
			</xsl:if>
			<xsl:if
				test="/ichicsr/safetyreport/patient/patientdeath/patientdeathdate != '' ">
				<ae:recoveryDate>
					<xsl:call-template name="dateConverterYYYYMMDDtoYY-MM-DD">
						<xsl:with-param name="date"
							select="/ichicsr/safetyreport/patient/patientdeath/patientdeathdate" />
					</xsl:call-template>
				</ae:recoveryDate>
			</xsl:if>
		</ae:adverseEventResponseDescription>
	</xsl:template>
	<xsl:template name="otherCause">
		<ae:otherCause>
			<ae:text>
				<xsl:value-of select="patientepisodename" />
			</ae:text>
		</ae:otherCause>
	</xsl:template>
	
	<xsl:template name="submitter">
		<ae:submitter>
			<ae:firstName>
				<xsl:value-of select="/ichicsr/safetyreport/sender/sendergivename" />
			</ae:firstName>
			<ae:lastName>
				<xsl:value-of select="/ichicsr/safetyreport/sender/senderfamilyname" />
			</ae:lastName>
			<ae:middleName>
				<xsl:value-of select="/ichicsr/safetyreport/sender/sendermiddlename" />
			</ae:middleName>
			<xsl:if test="/ichicsr/safetyreport/sender/senderemailaddress">
				<ae:contactMechanism>
					<ae:type>e-mail</ae:type>
					<ae:value>
						<xsl:value-of select="/ichicsr/safetyreport/sender/senderemailaddress" />
					</ae:value>
				</ae:contactMechanism>
			</xsl:if>
			<xsl:if test="/ichicsr/safetyreport/sender/senderphone">
				<ae:contactMechanism>
					<ae:type>phone</ae:type>
					<ae:value>
						<xsl:value-of select="/ichicsr/safetyreport/sender/senderphone" />
					</ae:value>
				</ae:contactMechanism>
			</xsl:if>
			<xsl:if test="/ichicsr/safetyreport/sender/senderfax">
				<ae:contactMechanism>
					<ae:type>fax</ae:type>
					<ae:value>
						<xsl:value-of select="/ichicsr/safetyreport/sender/senderfax" />
					</ae:value>
				</ae:contactMechanism>
			</xsl:if>
		</ae:submitter>
	</xsl:template>

	<xsl:template name="participantHistory">
		<ae:participantHistory>
			<!--Optional: -->
			<xsl:if test="/ichicsr/safetyreport/patient/patientweight">
				<ae:weight>
					<ae:quantity>
						<xsl:value-of select="/ichicsr/safetyreport/patient/patientweight" />
					</ae:quantity>
					<ae:unit>kilogram</ae:unit>
				</ae:weight>
			</xsl:if>
			<!--Optional: -->
			<xsl:if test="/ichicsr/safetyreport/patient/patientheight">
				<ae:height>
					<ae:quantity>
						<xsl:value-of select="/ichicsr/safetyreport/patient/patientheight" />
					</ae:quantity>
					<ae:unit>centimeter</ae:unit>
				</ae:height>
			</xsl:if>
			<!--Optional: -->
			<ae:baselinePerformanceStatus>
				<xsl:call-template name="lookup">
					<xsl:with-param name="_map" select="$map//baselinestatuses" />
					<xsl:with-param name="_code"
							select='/ichicsr/safetyreport/patient/baselinestatus' />
				</xsl:call-template>
			</ae:baselinePerformanceStatus>
		</ae:participantHistory>
	</xsl:template>

	<xsl:template name="adverseEvent">
		<xsl:variable name="adverseEventId" select="aeexternalid" />
		<ae:adverseEvent>
			<xsl:if test="primarysourcereaction">
				<verbatim>
					<xsl:value-of select="primarysourcereaction" />
				</verbatim>
			</xsl:if>
			<xsl:if test="reactionstartdate">
				<startDate>
					<xsl:call-template name="dateConverterYYYYMMDDtoYY-MM-DD">
						<xsl:with-param name="date" select="reactionstartdate" />
					</xsl:call-template>
				</startDate>
			</xsl:if>
			<xsl:if test="reactionenddate">
				<endDate>
					<xsl:call-template name="dateConverterYYYYMMDDtoYY-MM-DD">
						<xsl:with-param name="date" select="reactionenddate" />
					</xsl:call-template>
				</endDate>
			</xsl:if>
			<externalId>
				<xsl:value-of select="$adverseEventId" />
			</externalId>
			<xsl:if test="primaryaeflag">
				<ae:isPrimary>
					<xsl:call-template name="convertOneTwotoBoolean">
						<xsl:with-param name="oneTwoType" select="primaryaeflag" />
					</xsl:call-template>
				</ae:isPrimary>
			</xsl:if>
			
			<xsl:for-each
				select="//reaction[aeexternalid = $adverseEventId and attribution and factortype = 'concomitant medication']">
				<xsl:call-template name="concomitantMedicationAttributionTemplate">
					<xsl:with-param name="adverseEventId" select="$adverseEventId" />
				</xsl:call-template>
			</xsl:for-each>

			<xsl:for-each
				select="//reaction[aeexternalid = $adverseEventId and attribution and factortype = 'other cause']">
				<xsl:call-template name="otherCauseAttributionTemplate">
					<xsl:with-param name="adverseEventId" select="$adverseEventId" />
				</xsl:call-template>
			</xsl:for-each>

			<xsl:for-each
				select="//reaction[aeexternalid = $adverseEventId and attribution and factortype = 'course agent']">
				<xsl:call-template name="courseAgentAttributionTemplate">
					<xsl:with-param name="adverseEventId" select="$adverseEventId" />
				</xsl:call-template>
			</xsl:for-each>

		<!--	<xsl:for-each
				select="//reaction[aeexternalid = $adverseEventId and attribution and factortype = 'surgery']">
				<xsl:call-template name="surgeryAttributionTemplate">
					<xsl:with-param name="adverseEventId" select="$adverseEventId" />
				</xsl:call-template>
			</xsl:for-each> -->

		<!--	<xsl:for-each
				select="//reaction[aeexternalid = $adverseEventId and attribution and factortype = 'radiation']">
				<xsl:call-template name="radiationAttributionTemplate">
					<xsl:with-param name="adverseEventId" select="$adverseEventId" />
				</xsl:call-template>
			</xsl:for-each> -->

		<!--	<xsl:for-each
				select="//reaction[aeexternalid = $adverseEventId and attribution and factortype = 'device']">
				<xsl:call-template name="deviceAttributionTemplate">
					<xsl:with-param name="adverseEventId" select="$adverseEventId" />
				</xsl:call-template>
			</xsl:for-each> -->

			<xsl:for-each
				select="//reaction[aeexternalid = $adverseEventId and attribution and factortype = 'primary disease']">
				<xsl:call-template name="primaryDiseaseAttributionTemplate">
					<xsl:with-param name="adverseEventId" select="$adverseEventId" />
				</xsl:call-template>
			</xsl:for-each>

			<xsl:for-each
				select="//reaction[aeexternalid = $adverseEventId and attribution and factortype = 'other disease']">
				<xsl:call-template name="otherDiseaseAttributionTemplate">
					<xsl:with-param name="adverseEventId" select="$adverseEventId" />
				</xsl:call-template>
			</xsl:for-each>
		</ae:adverseEvent>
	</xsl:template>

	<xsl:template name="concomitantMedicationAttributionTemplate">
		<xsl:param name="adverseEventId" />
		<ae:concomitantMedicationAttribution>
			<ae:attribution>
				<xsl:value-of
					select="attribution" />
			</ae:attribution>
			<ae:cause>
				<ae:name>
					<xsl:value-of select="factor" />
				</ae:name>
			<!--	<xsl:if test="./drugstartdate != '' ">
					<ae:startDate>
						<xsl:call-template name="splitDateYYYYMMDD">
							<xsl:with-param name="date" select="./drugstartdate" />
						</xsl:call-template>
					</ae:startDate>
				</xsl:if>
				<xsl:if test="./drugenddate != '' ">
					<ae:endDate>
						<xsl:call-template name="splitDateYYYYMMDD">
							<xsl:with-param name="date" select="./drugenddate" />
						</xsl:call-template>
					</ae:endDate>
				</xsl:if> -->
			</ae:cause>
		</ae:concomitantMedicationAttribution>
	</xsl:template>

	<xsl:template name="otherCauseAttributionTemplate">
		<xsl:param name="adverseEventId" />
		<ae:otherCauseAttribution>
			<ae:attribution>
				<xsl:value-of
					select="attribution" />
			</ae:attribution>
			<ae:cause>
				<ae:text>
					<xsl:value-of select="factor" />
				</ae:text>
			</ae:cause>
		</ae:otherCauseAttribution>
	</xsl:template>

	<xsl:template name="courseAgentAttributionTemplate">
		<xsl:param name="adverseEventId" />
		<ae:courseAgentAttribution>
			<ae:attribution>
				<xsl:value-of
					select="attribution" />
			</ae:attribution>
			<ae:cause>
				<ae:studyAgentRef>
					<ae:Agent>
						<ae:nscNumber>
							<xsl:value-of select="factor" />
						</ae:nscNumber>
					</ae:Agent>
				</ae:studyAgentRef>
			</ae:cause>
		</ae:courseAgentAttribution>
	</xsl:template>

	<xsl:template name="surgeryAttributionTemplate">
		<xsl:param name="adverseEventId" />
		<ae:surgeryAttribution>
			<ae:attribution>
				<xsl:value-of
					select="drugreactionrelatedness/drugreactionasses[aeexternalid = $adverseEventId]/drugresult" />
			</ae:attribution>
			<ae:cause>
				<ae:interventionDate>
					<xsl:call-template name="dateConverterYYYYMMDDtoYY-MM-DD">
						<xsl:with-param name="date" select="drugstartdate" />
					</xsl:call-template>
				</ae:interventionDate>
				<ae:InterventionSite>
					<ae:name>
						<xsl:value-of select="medicinalproduct" />
					</ae:name>
				</ae:InterventionSite>
			</ae:cause>
		</ae:surgeryAttribution>
	</xsl:template>

	<xsl:template name="radiationAttributionTemplate">
		<xsl:param name="adverseEventId" />
		<ae:radiationAttribution>
			<ae:attribution>
				<xsl:value-of
					select="drugreactionrelatedness/drugreactionasses[aeexternalid = $adverseEventId]/drugresult" />
			</ae:attribution>
			<ae:cause>
				<xsl:if test="drugenddate != '' ">
					<ae:lastTreatmentDate>
						<xsl:call-template name="dateConverterYYYYMMDDtoYY-MM-DD">
							<xsl:with-param name="date" select="drugenddate" />
						</xsl:call-template>
					</ae:lastTreatmentDate>
				</xsl:if>
				<ae:administration>
					<xsl:value-of select="medicinalproduct" />
				</ae:administration>
			</ae:cause>
		</ae:radiationAttribution>
	</xsl:template>

	<xsl:template name="deviceAttributionTemplate">
		<xsl:param name="adverseEventId" />
		<ae:deviceAttribution>
			<ae:attribution>
				<xsl:value-of
					select="drugreactionrelatedness/drugreactionasses[aeexternalid = $adverseEventId]/drugresult" />
			</ae:attribution>
			<ae:cause>
				<ae:studyDeviceRef>
					<ae:device>
						<ae:brandName>
							<xsl:value-of select="devicenamebrand" />
						</ae:brandName>
						<ae:commonName>
							<xsl:value-of select="devicenamecommon" />
						</ae:commonName>
						<xsl:if test="devicetype">
							<ae:type>
								<xsl:value-of select="devicetype" />
							</ae:type>
						</xsl:if>
					</ae:device>
				</ae:studyDeviceRef>
			</ae:cause>
		</ae:deviceAttribution>
	</xsl:template>

	<xsl:template name="primaryDiseaseAttributionTemplate">
		<xsl:param name="adverseEventId" />
		<ae:diseaseAttribution>
			<ae:attribution>
				<xsl:value-of
					select="attribution" />
			</ae:attribution>
			<ae:cause>
				<ae:primaryDisease>
					<xsl:value-of select="factor" />
				</ae:primaryDisease>
			</ae:cause>
		</ae:diseaseAttribution>
	</xsl:template>

	<xsl:template name="otherDiseaseAttributionTemplate">
		<xsl:param name="adverseEventId" />
		<ae:diseaseAttribution>
			<ae:attribution>
				<xsl:value-of
					select="attribution" />
			</ae:attribution>
			<ae:cause>
				<ae:otherPrimaryDisease>
					<xsl:value-of select="factor" />
				</ae:otherPrimaryDisease>
			</ae:cause>
		</ae:diseaseAttribution>
	</xsl:template>

	<xsl:template name="convertYESNO2Boolean">
		<xsl:param name="yesNoValue" />
		<xsl:if test="$yesNoValue = 'YES'">
			true
		</xsl:if>
		<xsl:if test="$yesNoValue = 'NO'">
			false
		</xsl:if>
	</xsl:template>

	<xsl:template name="convertOneTwotoBoolean">
		<xsl:param name="oneTwoType" />
		<xsl:if test="$oneTwoType = '1'">
			true
		</xsl:if>
		<xsl:if test="$oneTwoType = '2'">
			false
		</xsl:if>
	</xsl:template>

	<xsl:template name="dateConverterYYYYMMDDtoYY-MM-DD">
		<xsl:param name="date" />
		<xsl:variable name="valueLength" select="string-length($date)-1" />
		<xsl:variable name="vMonth" select="substring($date,5,2)" />
		<xsl:variable name="vYear" select="substring($date,1,4)" />
		<xsl:variable name="outputDate"
			select="concat($vYear,'-',$vMonth,'-',substring($date,7,2))" />
		<xsl:value-of select="$outputDate" />
	</xsl:template>

</xsl:stylesheet>