<?xml version="1.0" encoding="UTF-8"?>
<!-- Copyright SemanticBits, Northwestern University and Akaza Research Distributed 
	under the OSI-approved BSD 3-Clause License. See http://ncip.github.com/caaers/LICENSE.txt 
	for details. -->
<xsl:stylesheet version="2.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/"
	xmlns:cct="http://schema.integration.caaers.cabig.nci.nih.gov/common"
	xmlns:ae="http://schema.integration.caaers.cabig.nci.nih.gov/aereport">
	<xsl:output method="xml" encoding="UTF-8" indent="yes" />
	
	<xsl:variable name="map" select="document('lookup.xml')"/>
	
	<xsl:template name="lookup">
        <xsl:param name="_map" />
        <xsl:param name="_code" />        
        <xsl:value-of select="$_map//code[text() = $_code]/parent::node()/value"/>
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
							<ae:externalId>
								<xsl:value-of select="/ichicsr/safetyreport/safetyreportid" />
							</ae:externalId>
						</ae:adverseEventReportingPeriod>

						<xsl:for-each select="//primarysource">
							<xsl:if test="qualification = 1">
								<xsl:call-template name="physician" />
							</xsl:if>
							<xsl:if test="qualification = 3">
								<xsl:call-template name="reporter" />
							</xsl:if>
						</xsl:for-each>
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
							<xsl:for-each select="//medicalhistoryepisode">
								<xsl:call-template name="metastaticDiseaseSite" />
								<xsl:call-template name="disease_diseaseSite" />
							</xsl:for-each>
						</ae:diseaseHistory>
						<xsl:call-template name="participantHistory"
							select="/ichicsr/safetyreport/patient" />
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
						<xsl:for-each select="//reaction">
							<xsl:if test="/ichicsr/safetyreport/patient/reaction/aeexternalid">
								<xsl:call-template name="adverseEvent" />
							</xsl:if>
						</xsl:for-each>
						
						<xsl:for-each select="//test">
							<xsl:call-template name="lab" />
						</xsl:for-each>

						<!--Zero or more repetitions: -->

						<xsl:for-each
							select="//medicalhistoryepisode[patientmedicalcomment = 'Prior Therapy' or patientmedicalcomment = 'Other Prior Therapy']">
							<xsl:call-template name="priorTherapy" />
						</xsl:for-each>
						<ae:treatmentInformation>
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

						<!--1 or more repetitions: -->
						<ae:report>
							<ae:caseNumber>
								<xsl:value-of select="/ichicsr/safetyreport/companynumb" />
							</ae:caseNumber>
							<ae:aeReportDefinition>
								<ae:name>
									<xsl:value-of select="/ichicsr/safetyreport/reportname" />
								</ae:name>
							</ae:aeReportDefinition>
							<!--Zero or more repetitions: -->
							<ae:aeReportVersion>
								<ae:reportVersionId>
									<xsl:value-of select="/ichicsr/safetyreport/safetyreportversion" />
								</ae:reportVersionId>
							</ae:aeReportVersion>
						</ae:report>
					</ae:AdverseEventReport>
				</ae:submitSafetyReport>
			</soapenv:Body>
		</soapenv:Envelope>
	</xsl:template>

	<xsl:template name="disease_diseaseSite">
		<xsl:if test="./patientmedicalcomment = 'Study Disease' ">
			<ae:primaryDisease>
				<xsl:value-of select="patientepisodename" />
			</ae:primaryDisease>
		</xsl:if>
		<xsl:if test="./patientmedicalcomment = 'Disease Site' ">
			<ae:codedPrimaryDiseaseSite>
				<xsl:value-of select="patientepisodename" />
			</ae:codedPrimaryDiseaseSite>
		</xsl:if>
		<xsl:if test="./patientmedicalcomment = 'Other Disease Site' ">
			<ae:otherPrimaryDiseaseSite>
				<xsl:value-of select="patientepisodename" />
			</ae:otherPrimaryDiseaseSite>
		</xsl:if>
		<xsl:if test="./patientmedicalcomment = 'Other Study Disease' ">
			<ae:otherPrimaryDisease>
				<xsl:value-of select="patientepisodename" />
			</ae:otherPrimaryDisease>
		</xsl:if>
	</xsl:template>

	<xsl:template name="metastaticDiseaseSite">
		<xsl:if test="./patientmedicalcomment = 'Metastatic Site' ">
			<ae:metastaticDiseaseSite>
				<ae:codedSite>
					<ae:name>
						<xsl:value-of select="patientepisodename" />
					</ae:name>
					<!-- <aer:category>?</aer:category> -->
				</ae:codedSite>
			</ae:metastaticDiseaseSite>
		</xsl:if>
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
				<xsl:if test="./patientmedicalcomment = 'Prior Therapy' ">
					<ae:text>
						<xsl:value-of select="patientepisodename" />
					</ae:text>
				</xsl:if>
			</ae:priorTherapy>
			<xsl:if test="./patientmedicalcomment = 'Other Prior Therapy' ">
				<ae:other>
					<xsl:value-of select="patientepisodename" />
				</ae:other>
			</xsl:if>

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
			<xsl:variable name="vSec" select='substring($date,3,2)' />
		
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
			<ae:catalogNumber>
				<xsl:value-of select="devicenumbercatalog" />
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
			<ae:DeviceOperator>
				<xsl:value-of select="deviceoperator" />
			</ae:DeviceOperator>
			<!--Optional: -->			
			
			<xsl:if test="devicereprocessedflag = '1'">
				<ae:DeviceReprocessed>YES</ae:DeviceReprocessed>
				<ae:reprocessedName>
					<xsl:value-of select="devicereprocessorname" />
				</ae:reprocessedName>
				<ae:reprocessedAddress>
					<xsl:value-of select="devicereprocessoraddress" />
				</ae:reprocessedAddress>
			</xsl:if>
			<xsl:if test="devicereprocessedflag = '2'">
				<ae:DeviceReprocessed>NO</ae:DeviceReprocessed>
			</xsl:if>
			
			<ae:EvaluationAvailability>
				<xsl:value-of select="deviceavailableflag" />
			</ae:EvaluationAvailability>
			<ae:StudyDevice>
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
			</ae:StudyDevice>
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
				<xsl:call-template name="lookup">
					<xsl:with-param name="_map" select="$map//uoms" />
					<xsl:with-param name="_code" select='drugcumulativedosageunit' />
				</xsl:call-template>
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
			<xsl:if test="surgerystartdate != '' ">
				<ae:interventionDate>
					<xsl:call-template name="dateConverterYYYYMMDDtoYY-MM-DD">
						<xsl:with-param name="date" select="surgerystartdate" />
					</xsl:call-template>
				</ae:interventionDate>
			</xsl:if>
			<ae:InterventionSite>
				<ae:name>
					<xsl:value-of select="surgerysite" />
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
			<xsl:if test="testname = 'Bacterial infection NOS' or testname = 'Fungal infection NOS' or testname = 'Viral infection NOS'">
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
			<ae:presentStatus>
				<xsl:call-template name="lookup">
					<xsl:with-param name="_map" select="$map//aepresentstatuses" />
					<xsl:with-param name="_code" select='/ichicsr/safetyreport/patient/summary/presentstatus' />
				</xsl:call-template>
			</ae:presentStatus>
			<ae:retreated>
				<xsl:call-template name="lookup">
					<xsl:with-param name="_map" select="$map//e2byesnos" />
					<xsl:with-param name="_code" select='/ichicsr/safetyreport/patient/summary/retreatedflag' />
				</xsl:call-template>
			</ae:retreated>
			<ae:autopsyPerformed>
				<xsl:call-template name="lookup">
					<xsl:with-param name="_map" select="$map//e2byesnos" />
					<xsl:with-param name="_code" select='/ichicsr/safetyreport/patient/patientdeath/patientautopsyyesno' />
				</xsl:call-template>
			</ae:autopsyPerformed>
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

	<xsl:template name="physician">
		<ae:physician>
			<ae:firstName>
				<xsl:value-of select="reportergivename" />
			</ae:firstName>
			<ae:lastName>
				<xsl:value-of select="reporterfamilyname" />
			</ae:lastName>
			<xsl:if test="reportermiddlename">
				<ae:middleName>
					<xsl:value-of select="reportermiddlename" />
				</ae:middleName>
			</xsl:if>
			<xsl:if test="reporteremail">
				<ae:ContactMechanism>
					<ae:type>e-mail</ae:type>
					<ae:value>
						<xsl:value-of select="reporteremail" />
					</ae:value>
				</ae:ContactMechanism>
			</xsl:if>
			<xsl:if test="reporterphone">
				<ae:ContactMechanism>
					<ae:type>phone</ae:type>
					<ae:value>
						<xsl:value-of select="reporterphone" />
					</ae:value>
				</ae:ContactMechanism>
			</xsl:if>
		</ae:physician>
	</xsl:template>

	<xsl:template name="reporter">
		<ae:reporter>
			<ae:firstName>
				<xsl:value-of select="reportergivename" />
			</ae:firstName>
			<ae:lastName>
				<xsl:value-of select="reporterfamilyname" />
			</ae:lastName>
			<xsl:if test="reportermiddlename">
				<ae:middleName>
					<xsl:value-of select="reportermiddlename" />
				</ae:middleName>
			</xsl:if>
			<xsl:if test="reporteremail">
				<ae:ContactMechanism>
					<ae:type>e-mail</ae:type>
					<ae:value>
						<xsl:value-of select="reporteremail" />
					</ae:value>
				</ae:ContactMechanism>
			</xsl:if>
			<xsl:if test="reporterphone">
				<ae:ContactMechanism>
					<ae:type>phone</ae:type>
					<ae:value>
						<xsl:value-of select="reporterphone" />
					</ae:value>
				</ae:ContactMechanism>
			</xsl:if>
			<xsl:if test="reporterfax">
				<ae:ContactMechanism>
					<ae:type>e-mail</ae:type>
					<ae:value>
						<xsl:value-of select="reporterfax" />
					</ae:value>
				</ae:ContactMechanism>
			</xsl:if>
		</ae:reporter>
	</xsl:template>

	<xsl:template name="submitter">
		<ae:submitter>
			<ae:firstName>
				<xsl:value-of select="sendergivename" />
			</ae:firstName>
			<ae:lastName>
				<xsl:value-of select="senderfamilyname" />
			</ae:lastName>
			<ae:middleName>
				<xsl:value-of select="sendermiddlename" />
			</ae:middleName>
			<xsl:if test="senderemailaddress">
				<ae:ContactMechanism>
					<ae:type>e-mail</ae:type>
					<ae:value>
						<xsl:value-of select="senderemailaddress" />
					</ae:value>
				</ae:ContactMechanism>
			</xsl:if>
			<xsl:if test="senderphone">
				<ae:ContactMechanism>
					<ae:type>phone</ae:type>
					<ae:value>
						<xsl:value-of select="senderphone" />
					</ae:value>
				</ae:ContactMechanism>
			</xsl:if>
			<xsl:if test="senderfax">
				<ae:ContactMechanism>
					<ae:type>e-mail</ae:type>
					<ae:value>
						<xsl:value-of select="senderfax" />
					</ae:value>
				</ae:ContactMechanism>
			</xsl:if>
		</ae:submitter>
	</xsl:template>

	<xsl:template name="participantHistory">
		<ae:participantHistory>
			<!--Optional: -->
			<ae:weight>
				<ae:quantity>
					<xsl:value-of select="/ichicsr/safetyreport/patient/patientweight" />
				</ae:quantity>
				<ae:unit>kg</ae:unit>
			</ae:weight>
			<!--Optional: -->
			<ae:height>
				<ae:quantity>
					<xsl:value-of select="/ichicsr/safetyreport/patient/patientheight" />
				</ae:quantity>
				<ae:unit>cm</ae:unit>
			</ae:height>
			<!--Optional: -->
			<ae:baselinePerformanceStatus>
				<xsl:value-of select="/ichicsr/safetyreport/patient/baselinestatus" />
			</ae:baselinePerformanceStatus>
		</ae:participantHistory>
	</xsl:template>

	<xsl:template name="adverseEvent">
		<xsl:variable name="adverseEventId" select="aeexternalid"/>
		<ae:adverseEvent>
			<startDate>
				<xsl:call-template name="dateConverterYYYYMMDDtoYY-MM-DD">
					<xsl:with-param name="date" select="reactionstartdate" />
				</xsl:call-template>
			</startDate>
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
					<xsl:call-template name="convertYESNO2Boolean">
						<xsl:with-param name="yesNoValue" select="primaryaeflag" />
					</xsl:call-template>
				</ae:isPrimary>
			</xsl:if>
			<xsl:if test="//drug[drugadditional != 'Radiation' and  drugadditional != 'Surgery' and drugadditional != 'Device' and drugcharacterization = '2']/drugreactionrelatedness/drugreactionasses[aeexternalid = $adverseEventId]">
				   <ae:concomitantMedicationAttribution>	
					  <ae:attribution>
						 <xsl:value-of select="//drug[drugadditional != 'Radiation' and  drugadditional != 'Surgery' and drugadditional != 'Device' and drugcharacterization = '2']/drugreactionrelatedness/drugreactionasses[aeexternalid = $adverseEventId]/drugresult" />
					</ae:attribution>
					  <ae:cause>
						   	<ae:name>
								<xsl:value-of select="//drug[drugadditional != 'Radiation' and  drugadditional != 'Surgery' and drugadditional != 'Device' and drugcharacterization = '2'and drugreactionrelatedness/drugreactionasses/aeexternalid = $adverseEventId]/medicinalproduct" />
							</ae:name>
					  </ae:cause>
				   </ae:concomitantMedicationAttribution>
			</xsl:if>
			<xsl:if test="//medicalhistoryepisode[patientmedicalcomment = 'Other Cause']/medicalepisodeassess[aeexternalid = $adverseEventId]">
				   <ae:otherCauseAttribution>
					  <ae:attribution>
						<xsl:value-of select="//medicalhistoryepisode[patientmedicalcomment = 'Other Cause']/medicalepisodeassess[aeexternalid = $adverseEventId]/othercauseresult" />
					 </ae:attribution>
					  <ae:cause>
					
							 <ae:text>
								<xsl:value-of select="//medicalhistoryepisode[patientmedicalcomment = 'Other Cause' and medicalepisodeassess/aeexternalid = $adverseEventId]/patientepisodename" />
							 </ae:text>
						
					  </ae:cause>
				   </ae:otherCauseAttribution>
			</xsl:if>
			<xsl:if test="//drug[drugadditional != 'Radiation' and  drugadditional != 'Surgery' and drugadditional != 'Device' and drugcharacterization = '1']/drugreactionrelatedness/drugreactionasses[aeexternalid = $adverseEventId]">
				   <ae:courseAgentAttribution>	
					  <ae:attribution>
					    <xsl:value-of select="//drug[drugadditional != 'Radiation' and  drugadditional != 'Surgery' and drugadditional != 'Device' and drugcharacterization = '1']/drugreactionrelatedness/drugreactionasses[aeexternalid = $adverseEventId]/drugresult" />
					 </ae:attribution>
					  <ae:cause>
							<ae:studyAgent>
								<ae:Agent>
									<ae:nscNumber>
										<xsl:value-of select="//drug[drugadditional != 'Radiation' and  drugadditional != 'Surgery' and drugadditional != 'Device' and drugcharacterization = '1'and drugreactionrelatedness/drugreactionasses/aeexternalid = $adverseEventId]/medicinalproduct" />
									</ae:nscNumber>
								</ae:Agent>						
							</ae:studyAgent>
					 </ae:cause>
				   </ae:courseAgentAttribution>
			</xsl:if> 
			<xsl:if test="//drug[drugadditional = 'Surgery']/drugreactionrelatedness/drugreactionasses[aeexternalid = $adverseEventId]">
				   <ae:surgeryAttribution>	
					  <ae:attribution>
					    <xsl:value-of select="//drug[drugadditional = 'Surgery']/drugreactionrelatedness/drugreactionasses[aeexternalid = $adverseEventId]/drugresult" />
					 </ae:attribution>
					  <ae:cause>
						  <ae:interventionDate>
							<xsl:call-template name="dateConverterYYYYMMDDtoYY-MM-DD">
								<xsl:with-param name="date" select="//drug[drugadditional = 'Surgery' and drugreactionrelatedness/drugreactionasses/aeexternalid = $adverseEventId]/surgerystartdate" />
							</xsl:call-template>
						  </ae:interventionDate>
						  <ae:InterventionSite>
								<ae:name>
									<xsl:value-of select="//drug[drugadditional = 'Surgery' and drugreactionrelatedness/drugreactionasses/aeexternalid = $adverseEventId]/surgerysite" />
								</ae:name>
							</ae:InterventionSite>
					  </ae:cause>
				   </ae:surgeryAttribution>
			</xsl:if>
			<xsl:if test="//drug[drugadditional = 'Radiation']/drugreactionrelatedness/drugreactionasses[aeexternalid = $adverseEventId]">
				   <ae:radiationAttribution>	
					  <ae:attribution>
					    <xsl:value-of select="//drug[drugadditional = 'Radiation']/drugreactionrelatedness/drugreactionasses[aeexternalid = $adverseEventId]/drugresult"  />
					 </ae:attribution>
					  <ae:cause>
						 <ae:OtherIntervention>
								 <ae:name>
									<xsl:value-of select="//drug[drugadditional = 'Radiation' and drugreactionrelatedness/drugreactionasses/aeexternalid = $adverseEventId]/medicinalproduct" />
								</ae:name>
							</ae:OtherIntervention>
					  </ae:cause>
				   </ae:radiationAttribution>
			</xsl:if>
		<xsl:if test="//drug[drugadditional = 'Device']/drugreactionrelatedness/drugreactionasses[aeexternalid = $adverseEventId]">
				   <ae:deviceAttribution>
					  <ae:attribution>
					  	<xsl:value-of select="//drug[drugadditional = 'Device']/drugreactionrelatedness/drugreactionasses[aeexternalid = $adverseEventId]/drugresult" />
					  </ae:attribution>
					  <ae:cause>
						 <ae:StudyDevice>
						 	<ae:device>
								<ae:brandName>
									<xsl:value-of select="//drug[drugadditional = 'Device' and drugreactionrelatedness/drugreactionasses/aeexternalid = $adverseEventId]/devicenamebrand" />
								</ae:brandName>
								<ae:commonName>
									<xsl:value-of select="//drug[drugadditional = 'Device' and drugreactionrelatedness/drugreactionasses/aeexternalid = $adverseEventId]/devicenamecommon" />
								</ae:commonName>
								<xsl:if test ="//drug[drugadditional = 'Device' and drugreactionrelatedness/drugreactionasses/aeexternalid = $adverseEventId]/devicetype">
									<ae:type>
										<xsl:value-of select="//drug[drugadditional = 'Device' and drugreactionrelatedness/drugreactionasses/aeexternalid = $adverseEventId]/devicetype" />
									</ae:type>
								</xsl:if>
							</ae:device>
						 </ae:StudyDevice>
					  </ae:cause>
				   </ae:deviceAttribution>
			</xsl:if> 
				
			<xsl:if test="//medicalhistoryepisode[patientmedicalcomment = 'Study Disease']/medicalepisodeassess[aeexternalid = $adverseEventId]">
				<ae:diseaseAttribution>
					  <ae:attribution>
						<xsl:value-of select="//medicalhistoryepisode[patientmedicalcomment = 'Study Disease']/medicalepisodeassess[aeexternalid = $adverseEventId]/diseaseresult" />
					 </ae:attribution>
					  <ae:cause>
						 <ae:primaryDisease>
								<xsl:value-of select="//medicalhistoryepisode[patientmedicalcomment = 'Study Disease' and medicalepisodeassess/aeexternalid = $adverseEventId]/patientepisodename" />
							</ae:primaryDisease>
					    </ae:cause>
				   </ae:diseaseAttribution>
			</xsl:if>
			<xsl:if test="//medicalhistoryepisode[patientmedicalcomment = 'Other Study Disease']/medicalepisodeassess[aeexternalid = $adverseEventId]">
				<ae:diseaseAttribution>
					  <ae:attribution>
							<xsl:value-of select="//medicalhistoryepisode[patientmedicalcomment = 'Other Study Disease']/medicalepisodeassess[aeexternalid = $adverseEventId]/diseaseresult" />
						 </ae:attribution>
					  <ae:cause>
							<ae:otherPrimaryDisease>
								<xsl:value-of select="//medicalhistoryepisode[patientmedicalcomment = 'Other Study Disease' and medicalepisodeassess/aeexternalid = $adverseEventId]/patientepisodename" />
							</ae:otherPrimaryDisease>
					   </ae:cause>
				   </ae:diseaseAttribution>
			</xsl:if>
		</ae:adverseEvent>
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