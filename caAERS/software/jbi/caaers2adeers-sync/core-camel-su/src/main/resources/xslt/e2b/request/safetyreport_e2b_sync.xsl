<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="2.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/"
	xmlns:cct="http://schema.integration.caaers.cabig.nci.nih.gov/common"
	xmlns:ae="http://schema.integration.caaers.cabig.nci.nih.gov/aereport"
	xmlns:func="http://exslt.org/functions"
	extension-element-prefixes="func">
	<xsl:output method="xml" encoding="UTF-8" indent="yes"/>
	
	<xsl:template match="/">
		<xsl:call-template name="safetyreport"/>
	</xsl:template>

	
	<xsl:template match="safetyreport" name="safetyreport">
		<soapenv:Envelope>
			<soapenv:Body>
			<ae:submitSafetyReport>
		<ae:AdverseEventReport >
			<xsl:call-template name="responseDescription"/>
            <ae:adverseEventReportingPeriod>
               <ae:externalId>
						<xsl:value-of select = "/ichicsr/safetyreport/safetyreportid" /> 
				</ae:externalId>
            </ae:adverseEventReportingPeriod>
            <xsl:call-template name="physician"/>
			<xsl:call-template name="reporter"/>
			<xsl:call-template name="submitter"/>
            <!--Optional:-->
            <ae:diseaseHistory>
               <!--Optional:-->
			   <xsl:if test="/ichicsr/safetyreport/patient/medicalhistoryepisode[patientmedicalcomment = 'Study Disease']/patientmedicalstartdate != ''">
				 <ae:diagnosisDate>
					<xsl:call-template name="splitDateYYYYMMDD">
					         <xsl:with-param name="date" select="/ichicsr/safetyreport/patient/medicalhistoryepisode/patientmedicalstartdate" /> 
					</xsl:call-template>
				 </ae:diagnosisDate>
			    </xsl:if>
              			   
               <!--Zero or more repetitions:-->
               <xsl:for-each select="//medicalhistoryepisode">
					<xsl:call-template name="metastaticDiseaseSite"/>
					<xsl:call-template name="disease_diseaseSite"/>
			   </xsl:for-each>
            </ae:diseaseHistory>
           <xsl:call-template name="participantHistory" select="/ichicsr/safetyreport/patient"/>
           <!--Zero or more repetitions:-->
            <xsl:for-each select="//drug">
				<xsl:if test="radiationadjustment">
					<xsl:call-template name="radiationIntervention"/>
				</xsl:if>
			</xsl:for-each>
            <!--Zero or more repetitions:-->
            <xsl:for-each select="//drug">
				<xsl:if test="surgerystartdate">
					<xsl:call-template name="surgeryIntervention"/>
				</xsl:if>
			</xsl:for-each>
            <!--Zero or more repetitions:-->
             <xsl:for-each select="//drug">
				<xsl:if test="devicenamecommon">
					<xsl:call-template name="medicalDevice"/>
				</xsl:if>
			</xsl:for-each>
            <!--Zero or more repetitions:-->
            <xsl:for-each select="//drug[drugcharacterization = '2']">
					<xsl:call-template name="concomitantMedication"/>
			</xsl:for-each>
            <!--1 or more repetitions:-->
			<xsl:for-each select="//reaction">
				<xsl:if test="/ichicsr/safetyreport/patient/reaction/aeexternalid">
					<xsl:call-template name="adverseEvent"/>
				</xsl:if>
			</xsl:for-each>
						
            <ae:adverseEvent>
				<externalId><xsl:value-of select="aeexternalid"/></externalId>
				<isPrimary><xsl:value-of select="primaryaeflag"/></isPrimary>
			
				<endDate><xsl:value-of select="reactionenddate"/></endDate>
				 
			   <!--Optional:-->
               <!--Zero or more repetitions:-->
               <ae:concomitantMedicationAttribution>
                  <ae:attribution>?</ae:attribution>
                  <ae:cause>
                     <ae:name>?</ae:name>
                     <!--Optional:-->
                     <ae:startDate>
                        <!--Optional:-->
                        <ae:day>?</ae:day>
                        <!--Optional:-->
                        <ae:month>?</ae:month>
                        <!--Optional:-->
                        <ae:year>?</ae:year>
                        <!--Optional:-->
                        <ae:zone>?</ae:zone>
                     </ae:startDate>
                     <!--Optional:-->
                     <ae:endDate>
                        <!--Optional:-->
                        <ae:day>?</ae:day>
                        <!--Optional:-->
                        <ae:month>?</ae:month>
                        <!--Optional:-->
                        <ae:year>?</ae:year>
                        <!--Optional:-->
                        <ae:zone>?</ae:zone>
                     </ae:endDate>
                     <!--Optional:-->
                     <ae:stillTakingMedications>?</ae:stillTakingMedications>
                  </ae:cause>
               </ae:concomitantMedicationAttribution>
               <!--Zero or more repetitions:-->
               <ae:otherCauseAttribution>
                  <ae:attribution>?</ae:attribution>
                  <ae:cause>
                     <ae:text>?</ae:text>
                  </ae:cause>
               </ae:otherCauseAttribution>
               <!--Zero or more repetitions:-->
               <ae:courseAgentAttribution>
                  <ae:attribution>?</ae:attribution>
                  <ae:cause>
                     <!--Optional:-->
                     <ae:lastAdministeredDate>?</ae:lastAdministeredDate>
                     <!--Optional:-->
                     <ae:totalDoseAdministeredThisCourse>?</ae:totalDoseAdministeredThisCourse>
                     <!--Optional:-->
                     <ae:administrationDelayAmount>?</ae:administrationDelayAmount>
                     <!--Optional:-->
                     <ae:administrationDelayUnits>?</ae:administrationDelayUnits>
                     <!--Optional:-->
                     <ae:durationAndSchedule>?</ae:durationAndSchedule>
                     <!--Optional:-->
                     <ae:dose>
                        <ae:amount>?</ae:amount>
                        <ae:units>?</ae:units>
                        <ae:route>?</ae:route>
                     </ae:dose>
                     <!--Optional:-->
                     <ae:modifiedDose>
                        <ae:amount>?</ae:amount>
                        <ae:units>?</ae:units>
                        <ae:route>?</ae:route>
                     </ae:modifiedDose>
                     <!--Optional:-->
                     <ae:studyAgent>
                        <!--You have a CHOICE of the next 2 items at this level-->
                        <ae:Agent>
                           <!--Optional:-->
                           <ae:name>?</ae:name>
                           <!--Optional:-->
                           <ae:description>?</ae:description>
                           <ae:nscNumber>?</ae:nscNumber>
                        </ae:Agent>
                        <ae:otherAgent>?</ae:otherAgent>
                     </ae:studyAgent>
                  </ae:cause>
               </ae:courseAgentAttribution>
               <!--Zero or more repetitions:-->
               <ae:surgeryAttribution>
                  <ae:attribution>?</ae:attribution>
                  <ae:cause>
                     <ae:interventionDate>?</ae:interventionDate>
                     <ae:InterventionSite>
                        <ae:name>?</ae:name>
                     </ae:InterventionSite>
                     <ae:OtherIntervention>
                        <ae:name>?</ae:name>
                        <!--Optional:-->
                        <ae:description>?</ae:description>
                     </ae:OtherIntervention>
                  </ae:cause>
               </ae:surgeryAttribution>
               <!--Zero or more repetitions:-->
               <ae:radiationAttribution>
                  <ae:attribution>?</ae:attribution>
                  <ae:cause>
                     <ae:dosage>?</ae:dosage>
                     <ae:dosageUnit>?</ae:dosageUnit>
                     <ae:lastTreatmentDate>?</ae:lastTreatmentDate>
                     <ae:fractionNumber>?</ae:fractionNumber>
                     <ae:daysElapsed>?</ae:daysElapsed>
                     <ae:adjustment>?</ae:adjustment>
                     <ae:administration>?</ae:administration>
                     <ae:OtherIntervention>
                        <ae:name>?</ae:name>
                        <!--Optional:-->
                        <ae:description>?</ae:description>
                     </ae:OtherIntervention>
                  </ae:cause>
               </ae:radiationAttribution>
               <!--Zero or more repetitions:-->
               <ae:deviceAttribution>
                  <ae:attribution>?</ae:attribution>
                  <ae:cause>
                     <!--Optional:-->
                     <ae:brandName>?</ae:brandName>
                     <!--Optional:-->
                     <ae:commonName>?</ae:commonName>
                     <!--Optional:-->
                     <ae:deviceType>?</ae:deviceType>
                     <!--Optional:-->
                     <ae:manufacturerName>?</ae:manufacturerName>
                     <!--Optional:-->
                     <ae:manufacturerCity>?</ae:manufacturerCity>
                     <!--Optional:-->
                     <ae:manufacturerState>?</ae:manufacturerState>
                     <!--Optional:-->
                     <ae:modelNumber>?</ae:modelNumber>
                     <!--Optional:-->
                     <ae:catalogNumber>?</ae:catalogNumber>
                     <!--Optional:-->
                     <ae:serialNumber>?</ae:serialNumber>
                     <!--Optional:-->
                     <ae:otherNumber>?</ae:otherNumber>
                     <!--Optional:-->
                     <ae:explantedDate>?</ae:explantedDate>
                     <!--Optional:-->
                     <ae:DeviceOperator>?</ae:DeviceOperator>
                     <!--Optional:-->
                     <ae:DeviceReprocessed>?</ae:DeviceReprocessed>
                     <!--Optional:-->
                     <ae:EvaluationAvailability>?</ae:EvaluationAvailability>
                     <!--Optional:-->
                     <ae:StudyDevice>
                        <!--You have a CHOICE of the next 2 items at this level-->
                        <!--Optional:-->
                        <ae:otherBrandName>?</ae:otherBrandName>
                        <!--Optional:-->
                        <ae:otherCommonName>?</ae:otherCommonName>
                        <!--Optional:-->
                        <ae:otherDeviceType>?</ae:otherDeviceType>
                        <!--Optional:-->
                        <ae:device>
                           <ae:brandName>?</ae:brandName>
                           <ae:commonName>?</ae:commonName>
                           <!--Optional:-->
                           <ae:type>?</ae:type>
                        </ae:device>
                        <!--Optional:-->
                        <ae:catalogNumber>?</ae:catalogNumber>
                        <!--Optional:-->
                        <ae:manufacturerName>?</ae:manufacturerName>
                        <!--Optional:-->
                        <ae:manufacturerCity>?</ae:manufacturerCity>
                        <!--Optional:-->
                        <ae:manufacturerState>?</ae:manufacturerState>
                        <!--Optional:-->
                        <ae:modelNumber>?</ae:modelNumber>
                     </ae:StudyDevice>
                  </ae:cause>
               </ae:deviceAttribution>
               <!--Zero or more repetitions:-->
               <ae:biologicalInterventionAttribution>
                  <ae:attribution>?</ae:attribution>
                  <ae:cause>
                     <ae:description>?</ae:description>
                     <ae:OtherIntervention>
                        <ae:name>?</ae:name>
                        <!--Optional:-->
                        <ae:description>?</ae:description>
                     </ae:OtherIntervention>
                  </ae:cause>
               </ae:biologicalInterventionAttribution>
               <!--Zero or more repetitions:-->
               <ae:dietarySupplementInterventionAttribution>
                  <ae:attribution>?</ae:attribution>
                  <ae:cause>
                     <ae:description>?</ae:description>
                     <ae:otherIntervention>
                        <ae:name>?</ae:name>
                        <!--Optional:-->
                        <ae:description>?</ae:description>
                     </ae:otherIntervention>
                  </ae:cause>
               </ae:dietarySupplementInterventionAttribution>
               <!--Zero or more repetitions:-->
               <ae:diseaseAttribution>
                  <ae:attribution>?</ae:attribution>
                  <ae:cause>
                     <!--Optional:-->
                     <ae:diagnosisDate>
                        <!--Optional:-->
                        <ae:day>?</ae:day>
                        <!--Optional:-->
                        <ae:month>?</ae:month>
                        <!--Optional:-->
                        <ae:year>?</ae:year>
                        <!--Optional:-->
                        <ae:zone>?</ae:zone>
                     </ae:diagnosisDate>
                     <!--Zero or more repetitions:-->
                     <ae:metastaticDiseaseSite>
                        <ae:otherMetastaticDiseaseSite>?</ae:otherMetastaticDiseaseSite>
                        <ae:AnatomicSite>
                           <ae:name>?</ae:name>
                           <ae:category>?</ae:category>
                        </ae:AnatomicSite>
                     </ae:metastaticDiseaseSite>
                  </ae:cause>
               </ae:diseaseAttribution>
               <!--Zero or more repetitions:-->
               <ae:otherInterventionAttribution>
                  <ae:attribution>?</ae:attribution>
                  <ae:cause>
                     <!--Optional:-->
                     <ae:description>?</ae:description>
                     <!--Optional:-->
                     <ae:studyIntervention>
                        <ae:name>?</ae:name>
                        <!--Optional:-->
                        <ae:description>?</ae:description>
                     </ae:studyIntervention>
                  </ae:cause>
               </ae:otherInterventionAttribution>
               <!--Zero or more repetitions:-->
               <ae:behavioralInterventionAttribution>
                  <ae:attribution>?</ae:attribution>
                  <ae:cause>
                     <!--Optional:-->
                     <ae:description>?</ae:description>
                     <!--Optional:-->
                     <ae:studyIntervention>
                        <ae:name>?</ae:name>
                        <!--Optional:-->
                        <ae:description>?</ae:description>
                     </ae:studyIntervention>
                  </ae:cause>
               </ae:behavioralInterventionAttribution>
               <!--Zero or more repetitions:-->
               <ae:geneticInterventionAttribution>
                  <ae:attribution>?</ae:attribution>
                  <ae:cause>
                     <!--Optional:-->
                     <ae:description>?</ae:description>
                     <!--Optional:-->
                     <ae:studyIntervention>
                        <ae:name>?</ae:name>
                        <!--Optional:-->
                        <ae:description>?</ae:description>
                     </ae:studyIntervention>
                  </ae:cause>
               </ae:geneticInterventionAttribution>
            </ae:adverseEvent>
            <!--Zero or more repetitions:-->
			
			<xsl:for-each select="//medicalhistoryepisode[patientmedicalcomment = 'Prior Therapy' or patientmedicalcomment = 'Other Prior Therapy']">
					<xsl:call-template name="priorTherapy"/>
			</xsl:for-each>
            
            <!--Optional:-->
            <ae:treatmentInformation>
               <!--Optional:-->
               <ae:firstCourseDate>?</ae:firstCourseDate>
               <!--Optional:-->
               <ae:treatmentAssignment>
                  <ae:code>?</ae:code>
                  <!--Optional:-->
                  <ae:doseLevelOrder>?</ae:doseLevelOrder>
                  <ae:description>?</ae:description>
                  <!--Optional:-->
                  <ae:comments>?</ae:comments>
               </ae:treatmentAssignment>
               <!--Optional:-->
               <ae:totalCourses>?</ae:totalCourses>
               <!--Optional:-->
               <ae:adverseEventCourse>
                  <!--Optional:-->
                  <ae:number>?</ae:number>
                  <!--Optional:-->
                  <ae:date>?</ae:date>
               </ae:adverseEventCourse>
               <!--Zero or more repetitions:-->
                <xsl:for-each select="//drug[drugcharacterization = '1']">
					<xsl:call-template name="courseAgent"/>
				</xsl:for-each>
            </ae:treatmentInformation>
            <!--Zero or more repetitions:-->
            <xsl:for-each select="//medicalhistoryepisode[patientmedicalcomment = 'Pre-existing Condition' or patientmedicalcomment = 'Other Pre-existing Condition']">
					<xsl:call-template name="preexistingCondition"/>
			</xsl:for-each>
            <!--Zero or more repetitions:-->
			<xsl:for-each select="//medicalhistoryepisode[patientmedicalcomment = 'Other Cause']">
					<xsl:call-template name="otherCause"/>
			</xsl:for-each>
            <!--Optional:-->
            <ae:additionalInformation>
               <!--Optional:-->
               <ae:autopsyReport>?</ae:autopsyReport>
               <!--Optional:-->
               <ae:consults>?</ae:consults>
               <!--Optional:-->
               <ae:dischargeSummary>?</ae:dischargeSummary>
               <!--Optional:-->
               <ae:flowCharts>?</ae:flowCharts>
               <!--Optional:-->
               <ae:labReports>?</ae:labReports>
               <!--Optional:-->
               <ae:obaForm>?</ae:obaForm>
               <!--Optional:-->
               <ae:other>?</ae:other>
               <!--Optional:-->
               <ae:pathologyReport>?</ae:pathologyReport>
               <!--Optional:-->
               <ae:progressNotes>?</ae:progressNotes>
               <!--Optional:-->
               <ae:radiologyReports>?</ae:radiologyReports>
               <!--Optional:-->
               <ae:referralLetters>?</ae:referralLetters>
               <!--Optional:-->
               <ae:irbReport>?</ae:irbReport>
               <!--Optional:-->
               <ae:otherInformation>?</ae:otherInformation>
               <!--Zero or more repetitions:-->
               <ae:additionalInformationDocuments>
                  <!--Optional:-->
                  <ae:fileId>?</ae:fileId>
                  <!--Optional:-->
                  <ae:fileName>?</ae:fileName>
                  <!--Optional:-->
                  <ae:originalFileName>?</ae:originalFileName>
                  <!--Optional:-->
                  <ae:filePath>?</ae:filePath>
                  <!--Optional:-->
                  <ae:fileSize>?</ae:fileSize>
                  <!--Optional:-->
                  <ae:relativePath>?</ae:relativePath>
                  <!--Optional:-->
                  <ae:additionalInformationDocumentType>?</ae:additionalInformationDocumentType>
               </ae:additionalInformationDocuments>
            </ae:additionalInformation>
            <!--1 or more repetitions:-->
            <ae:report>
               <ae:required>?</ae:required>
               <!--Optional:-->
               <ae:manuallySelected>?</ae:manuallySelected>
               <ae:aeReportDefinition>
                  <ae:name>?</ae:name>
                  <ae:duration>?</ae:duration>
                  <!--Optional:-->
                  <ae:label>?</ae:label>
                  <!--Optional:-->
                  <ae:Group>
                     <ae:code>?</ae:code>
                     <ae:name>?</ae:name>
                  </ae:Group>
                  <ae:timeScaleUnitType>?</ae:timeScaleUnitType>
                  <!--Zero or more repetitions:-->
                  <ae:reportDeliveryDefinition>
                     <ae:entityName>?</ae:entityName>
                     <!--Optional:-->
                     <ae:entityDescription>?</ae:entityDescription>
                     <ae:entityType>?</ae:entityType>
                     <ae:endPoint>?</ae:endPoint>
                     <ae:endPointType>?</ae:endPointType>
                     <!--Optional:-->
                     <ae:userName>?</ae:userName>
                     <!--Optional:-->
                     <ae:password>?</ae:password>
                     <ae:format>?</ae:format>
                  </ae:reportDeliveryDefinition>
               </ae:aeReportDefinition>
               <!--Zero or more repetitions:-->
               <ae:aeReportVersion>
                  <ae:reportVersionId>?</ae:reportVersionId>
                  <!--Optional:-->
                  <ae:createdOn>?</ae:createdOn>
                  <!--Optional:-->
                  <ae:dueOn>?</ae:dueOn>
                  <!--Optional:-->
                  <ae:submittedOn>?</ae:submittedOn>
                  <!--Optional:-->
                  <ae:withdrawnOn>?</ae:withdrawnOn>
                  <!--Optional:-->
                  <ae:amendedOn>?</ae:amendedOn>
                  <!--Optional:-->
                  <ae:physicianSignoff>?</ae:physicianSignoff>
                  <!--Optional:-->
                  <ae:submissionUrl>?</ae:submissionUrl>
                  <!--Optional:-->
                  <ae:email>?</ae:email>
                  <!--Optional:-->
                  <ae:submissionMessage>?</ae:submissionMessage>
                  <!--Optional:-->
                  <ae:ReportStatus>?</ae:ReportStatus>
               </ae:aeReportVersion>
               <!--Optional:-->
               <ae:reviewStatus>?</ae:reviewStatus>
            </ae:report>
        </ae:AdverseEventReport>
		<ae:submitSafetyReport>
		</soapenv:Body>
		</soapenv:Envelope>
	</xsl:template>
	
	<xsl:template name="disease_diseaseSite">
		<xsl:if test="./patientmedicalcomment = 'Study Disease' ">
			<ae:primaryDisease>
				<xsl:value-of select="patientepisodename"/>
			</ae:primaryDisease	>
		</xsl:if>
		<xsl:if test="./patientmedicalcomment = 'Disease Site' ">
			<ae:codedPrimaryDiseaseSite>
				<xsl:value-of select="patientepisodename"/>
			</ae:codedPrimaryDiseaseSite>
		</xsl:if>
		<xsl:if test="./patientmedicalcomment = 'Other Disease Site' ">
			<ae:otherPrimaryDiseaseSite>
				<xsl:value-of select="patientepisodename"/>
			</ae:otherPrimaryDiseaseSite>
		</xsl:if>
		<xsl:if test="./patientmedicalcomment = 'Other Study Disease' ">
			<ae:otherPrimaryDisease>
				<xsl:value-of select="patientepisodename"/>
			</ae:otherPrimaryDisease>
		</xsl:if>
	</xsl:template>
	
	<xsl:template name="metastaticDiseaseSite">
		<xsl:if test="./patientmedicalcomment = 'Metastatic Site' ">
			<ae:metastaticDiseaseSite>
				<ae:codedSite>
						 <ae:name>
							<xsl:value-of select="patientepisodename"/>
						 </ae:name>
					   <!--  <aer:category>?</aer:category> -->
				</ae:codedSite>
			</ae:metastaticDiseaseSite>
		</xsl:if>
		<xsl:if test="./patientmedicalcomment = 'Other Metastatic Site' ">
			<ae:metastaticDiseaseSite>
				<ae:otherSite>
					<xsl:value-of select="patientepisodename"/>
				</ae:otherSite>
			</ae:metastaticDiseaseSite>
		</xsl:if>
	</xsl:template>
	
	<xsl:template name="priorTherapy">
		<ae:sAEReportPriorTherapy>
               <ae:priorTherapy>
					<xsl:if test="./patientmedicalcomment = 'Prior Therapy' ">
						<ae:text>
							<xsl:value-of select="patientepisodename"/>
						</ae:text>
					</xsl:if>
					
					<xsl:if test="./patientmedicalcomment = 'Other Prior Therapy' ">
						<ae:other><xsl:value-of select="patientepisodename"/></ae:other>
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
			</ae:priorTherapy>
		</ae:sAEReportPriorTherapy>
	</xsl:template>
	
	<xsl:template name="preexistingCondition">
		<ae:sAEReportPreExistingCondition>
			<xsl:if test="./patientmedicalcomment = 'Pre-existing Condition' ">
			   <ae:preExistingCondition>
				  <text><xsl:value-of select="patientepisodename"/></text>
			   </ae:preExistingCondition>
			</xsl:if>
			<xsl:if test="./patientmedicalcomment = 'Other Pre-existing Condition' ">
			    <ae:other><xsl:value-of select="patientepisodename"/></ae:other>
			</xsl:if>
		</ae:sAEReportPreExistingCondition>
	</xsl:template>
	
	<xsl:template name="splitDateYYYYMMDD">
		<xsl:param name="date" />
		<xsl:variable name="valueLength" select="string-length($date)-1"/>
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
	

	<xsl:template name="concomitantMedication">
		<ae:concomitantMedication>
            <ae:name>
				<xsl:value-of select="medicinalproduct"/>
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
                  <!--Optional:-->
				 <xsl:if test="./drugenddate != '' ">
				 <ae:lastAdministeredDate>
					 <xsl:call-template name="splitDateYYYYMMDD">
							 <xsl:with-param name="date" select="./drugenddate" /> 
					</xsl:call-template>
				   </ae:lastAdministeredDate>
				</xsl:if>
                 
                <ae:totalDoseAdministeredThisCourse>
					<xsl:value-of select="drugcumulativedosagenumb"/>
				</ae:totalDoseAdministeredThisCourse>
                  <!--Optional:-->
                 <ae:administrationDelayAmount>
					<xsl:value-of select="drugdelayvalue"/>
				</ae:administrationDelayAmount>
                  <!--Optional:-->
                <ae:administrationDelayUnits>
					<xsl:value-of select="drugdelayunit"/>
				</ae:administrationDelayUnits>
                 <!--Optional:-->
                  <ae:dose>
                     <ae:units>
						<xsl:value-of select="drugcumulativedosageunit"/>
					 </ae:units>
                   </ae:dose>
                   <ae:studyAgent>
                     <!--You have a CHOICE of the next 2 items at this level-->
                     <ae:Agent>
                       <ae:nscNumber>
							<xsl:value-of select="medicinalproduct"/>
						</ae:nscNumber>
                     </ae:Agent>
                     </ae:studyAgent>
				  <ae:lotNumber>
					<xsl:value-of select="drugbatchnumb"/>
				  </ae:lotNumber>
				  <ae:comments>
					<xsl:value-of select="drugadditional"/>
				  </ae:comments>
               </ae:courseAgent>
	</xsl:template>
	<xsl:template name="medicalDevice">
		<ae:medicalDevice>
               <!--Optional:-->
               <ae:brandName><xsl:value-of select="devicenamebrand"/></ae:brandName>
               <!--Optional:-->
               <ae:commonName><xsl:value-of select="devicenamecommon"/></ae:commonName>
               <!--Optional:-->
               <ae:deviceType><xsl:value-of select="devicetype"/></ae:deviceType>
               <!--Optional:-->
               <ae:manufacturerName><xsl:value-of select="devicemanufacturername"/></ae:manufacturerName>
               <!--Optional:-->
               <ae:manufacturerCity><xsl:value-of select="devicemanufacturercity"/></ae:manufacturerCity>
               <!--Optional:-->
               <ae:manufacturerState><xsl:value-of select="devicemanufacturerstate"/></ae:manufacturerState>
               <!--Optional:-->
               <ae:modelNumber><xsl:value-of select="devicenumbermodel"/></ae:modelNumber>
               <!--Optional:-->
               <ae:catalogNumber><xsl:value-of select="devicenumbercatalog"/></ae:catalogNumber>
               <!--Optional:-->
               <ae:serialNumber><xsl:value-of select="devicenumberserial"/></ae:serialNumber>
               <!--Optional:-->
               <ae:otherNumber><xsl:value-of select="devicenumberother"/></ae:otherNumber>
               <!--Optional:-->
               <ae:explantedDate><xsl:value-of select="devicedateexplanted"/></ae:explantedDate>
               <!--Optional:-->
               <ae:DeviceOperator><xsl:value-of select="deviceoperator"/></ae:DeviceOperator>
               <!--Optional:-->
			
			 <xsl:if test="devicereprocessedflag = '1'">
				<ae:DeviceReprocessed>YES</ae:DeviceReprocessed>
			 </xsl:if>
			  <xsl:if test="devicereprocessedflag = '2'">
				<ae:DeviceReprocessed>NO</ae:DeviceReprocessed>
			 </xsl:if>
			 <xsl:if test="devicereprocessedflag = 1">
				   <ae:reprocessedName><xsl:value-of select="devicereprocessorname"/></ae:reprocessedName>
				   <ae:reprocessedAddress><xsl:value-of select="devicereprocessoraddress"/></ae:reprocessedAddress>
				</xsl:if>
				<ae:EvaluationAvailability><xsl:value-of select="deviceavailableflag"/></ae:EvaluationAvailability>
				<ae:StudyDevice>
				 <ae:device>
					 <ae:brandName><xsl:value-of select="devicenamebrand"/></ae:brandName>
					 <ae:commonName><xsl:value-of select="devicenamecommon"/></ae:commonName>
					 <!--Optional:-->
					 <ae:type><xsl:value-of select="devicetype"/></ae:type>
				  </ae:device>
				 </ae:StudyDevice>
			   <ae:otherDeviceOperator><xsl:value-of select="deviceoperatorother"/></ae:otherDeviceOperator>
			   <ae:lotNumber><xsl:value-of select="devicenumberlot"/></ae:lotNumber>
            </ae:medicalDevice>
	</xsl:template>
	<xsl:template name="radiationIntervention">
		<ae:radiationIntervention>
               <ae:dosage><xsl:value-of select="radiationcumulativedosevalue"/></ae:dosage>
               <ae:dosageUnit><xsl:value-of select="radiationcumulativedoseunit"/></ae:dosageUnit>
               <xsl:if test="./radiationlasttreatmentdate != '' ">
				<ae:lastTreatmentDate>
					 <xsl:call-template name="splitDateYYYYMMDD">
							 <xsl:with-param name="date" select="./radiationlasttreatmentdate" /> 
					</xsl:call-template>
				</ae:lastTreatmentDate>
			   </xsl:if>
               <ae:fractionNumber><xsl:value-of select="radiationnumberfractions"/></ae:fractionNumber>
               <ae:daysElapsed><xsl:value-of select="radiationnumberelapseddays"/></ae:daysElapsed>
               <ae:adjustment><xsl:value-of select="radiationadjustment"/></ae:adjustment>
               <ae:administration><xsl:value-of select="radiationtype"/></ae:administration>
         </ae:radiationIntervention>
	</xsl:template>
	<xsl:template name="surgeryIntervention">
		<ae:surgeryIntervention>
				<xsl:if test="./surgerystartdate != '' ">
				 <ae:interventionDate>
					 <xsl:call-template name="splitDateYYYYMMDD">
							 <xsl:with-param name="date" select="./surgerystartdate" /> 
					</xsl:call-template>
				   </ae:interventionDate>
				</xsl:if>
                <ae:InterventionSite>
                  <ae:name><xsl:value-of select="surgerysite"/></ae:name>
               </ae:InterventionSite>
        </ae:surgeryIntervention>>
	</xsl:template>
	<xsl:template name="lab">
		<ae:lab>
               <!--Optional:-->
               <ae:labTerm>
                  <ae:term><xsl:value-of select="testname"/></ae:term>
               </ae:labTerm>
               <!--Optional:-->
               <ae:units><xsl:value-of select="testunit"/></ae:units>
               <!--Optional:-->
               <ae:baseline>
                  <ae:value><xsl:value-of select="testresult"/></ae:value>
				  <xsl:if test="./testdate != '' ">
					 <ae:date>
						 <xsl:call-template name="splitDateYYYYMMDD">
								 <xsl:with-param name="date" select="./testdate" /> 
						</xsl:call-template>
					   </ae:date>
					</xsl:if>
               </ae:baseline>
               <!--Optional:-->
               <ae:nadir>
                   <ae:value><xsl:value-of select="testresult"/></ae:value>
				  <xsl:if test="./testdate != '' ">
					 <ae:date>
						 <xsl:call-template name="splitDateYYYYMMDD">
								 <xsl:with-param name="date" select="./testdate" /> 
						</xsl:call-template>
					   </ae:date>
					</xsl:if>
               </ae:nadir>
               <!--Optional:-->
               <ae:recovery>
                   <ae:value><xsl:value-of select="testresult"/></ae:value>
				  <xsl:if test="./testdate != '' ">
					 <ae:date>
						 <xsl:call-template name="splitDateYYYYMMDD">
								 <xsl:with-param name="date" select="./testdate" /> 
						</xsl:call-template>
					   </ae:date>
					</xsl:if>
               </ae:recovery>
			   <ae:infectiousAgent><xsl:value-of select="testresult"/></ae:infectiousAgent>
			   <ae:site><xsl:value-of select="infectionsite"/></ae:site>
        </ae:lab>
	</xsl:template>
	<xsl:template name="responseDescription">
		<ae:adverseEventResponseDescription>
               <ae:eventDescription><xsl:value-of select="/ichicsr/safetyreport/patient/summary/narrativeincludeclinical"/></ae:eventDescription>
               <ae:presentStatus><xsl:value-of select="/ichicsr/safetyreport/patient/summary/presentstatus"/></ae:presentStatus>
               <xsl:if test="/ichicsr/safetyreport/patient/patientdeath/patientautopsyyesno = '1'">
				 <ae:autopsyPerformed>true</ae:autopsyPerformed>
				</xsl:if>
				<xsl:if test="/ichicsr/safetyreport/patient/patientdeath/patientautopsyyesno = '2'">
				 <ae:autopsyPerformed>false</ae:autopsyPerformed>
				</xsl:if>
                <xsl:if test="/ichicsr/safetyreport/patient/patientdeath/patientdeathdate != '' ">
					 <ae:recoveryDate>
						 <xsl:call-template name="splitDateYYYYMMDD">
								 <xsl:with-param name="date" select="/ichicsr/safetyreport/patient/patientdeath/patientdeathdate" /> 
						</xsl:call-template>
					   </ae:recoveryDate>
				</xsl:if>
        </ae:adverseEventResponseDescription>
	</xsl:template>
	<xsl:template name="otherCause">
		<ae:otherCause>
			 <ae:text><xsl:value-of select="patientepisodename"/></ae:text>
		</ae:otherCause>
	</xsl:template>
	
	<xsl:template name="physician">
		<ae:physician>
               <ae:firstName><xsl:value-of select="reportergivenname"/></ae:firstName>
               <ae:lastName><xsl:value-of select="reporterfamilyname"/></ae:lastName>
			   <ae:middleName><xsl:value-of select="reportermiddlename"/></ae:middleName>
			   <xsl:if test="reporteremail">
					<ae:ContactMechanism>
						  <ae:type>e-mail</ae:type>
						  <ae:value><xsl:value-of select="reporteremail"/></ae:value>
					</ae:ContactMechanism>
				</xsl:if>
				<xsl:if test="reporterphone">
					<ae:ContactMechanism>
						  <ae:type>phone</ae:type>
						  <ae:value><xsl:value-of select="reporterphone"/></ae:value>
					</ae:ContactMechanism>
				</xsl:if> 
        </ae:physician>
	</xsl:template>
	
	<xsl:template name="reporter">
		<ae:reporter>
               <ae:firstName><xsl:value-of select="reportergivenname"/></ae:firstName>
               <ae:lastName><xsl:value-of select="reporterfamilyname"/></ae:lastName>
			   <ae:middleName><xsl:value-of select="reportermiddlename"/></ae:middleName>
			   <xsl:if test="reporteremail">
					<ae:ContactMechanism>
						  <ae:type>e-mail</ae:type>
						  <ae:value><xsl:value-of select="reporteremail"/></ae:value>
					</ae:ContactMechanism>
				</xsl:if>
				<xsl:if test="reporterphone">
					<ae:ContactMechanism>
						  <ae:type>phone</ae:type>
						  <ae:value><xsl:value-of select="reporterphone"/></ae:value>
					</ae:ContactMechanism>
				</xsl:if>
				 <xsl:if test="reporterfax">
					<ae:ContactMechanism>
						  <ae:type>e-mail</ae:type>
						  <ae:value><xsl:value-of select="reporterfax"/></ae:value>
					</ae:ContactMechanism>
				</xsl:if>
        </ae:reporter>
	</xsl:template>
	
	<xsl:template name="submitter">
		<ae:reporter>
               <ae:firstName><xsl:value-of select="sendergivename"/></ae:firstName>
               <ae:lastName><xsl:value-of select="senderfamilyname"/></ae:lastName>
			   <ae:middleName><xsl:value-of select="sendermiddlename"/></ae:middleName>
			   <xsl:if test="senderemailaddress">
					<ae:ContactMechanism>
						  <ae:type>e-mail</ae:type>
						  <ae:value><xsl:value-of select="senderemailaddress"/></ae:value>
					</ae:ContactMechanism>
				</xsl:if>
				<xsl:if test="senderphone">
					<ae:ContactMechanism>
						  <ae:type>phone</ae:type>
						  <ae:value><xsl:value-of select="senderphone"/></ae:value>
					</ae:ContactMechanism>
				</xsl:if>
				 <xsl:if test="senderfax">
					<ae:ContactMechanism>
						  <ae:type>e-mail</ae:type>
						  <ae:value><xsl:value-of select="senderfax"/></ae:value>
					</ae:ContactMechanism>
				</xsl:if>
        </ae:reporter>
	</xsl:template>
	
	<xsl:template name="participantHistory">
		<ae:participantHistory>
               <!--Optional:-->
               <ae:weight>
                  <ae:quantity><xsl:value-of select="/ichicsr/safetyreport/patient/patientweight"/></ae:quantity>
                  <ae:unit>kg</ae:unit>
               </ae:weight>
               <!--Optional:-->
               <ae:height>
                  <ae:quantity><xsl:value-of select="/ichicsr/safetyreport/patient/patientheight"/></ae:quantity>
                  <ae:unit>cm</ae:unit>
               </ae:height>
               <!--Optional:-->
               <ae:baselinePerformanceStatus><xsl:value-of select="/ichicsr/safetyreport/patient/baselinestatus"/></ae:baselinePerformanceStatus>
        </ae:participantHistory>
	</xsl:template>
	
	<xsl:template name="adverseEvent">
		<ae:adverseEvent>
				<externalId><xsl:value-of select="aeexternalid"/></externalId>
				<isPrimary><xsl:value-of select="primaryaeflag"/></isPrimary>
			   <startDate>
					<xsl:call-template name="dateConverterYYYYMMDDtoYY-MM-DD">
						<xsl:with-param name="date" select="reactionstartdate"/>
					</xsl:call-template>
				</startDate>
				<endDate>
					<xsl:call-template name="dateConverterYYYYMMDDtoYY-MM-DD">
						<xsl:with-param name="date" select="reactionenddate"/>
					</xsl:call-template>
				</endDate>
		</ae:adverseEvent>
	</xsl:template>
	
	<xsl:template name="dateConverterYYYYMMDDtoYY-MM-DD">
		<xsl:param name="date" />
		<xsl:variable name="valueLength" select="string-length($date)-1"/>
		<xsl:variable name="vMonth" select="substring($date,5,2)"/>
		<xsl:variable name="vYear" select="substring($date,1,4)"/>
		<xsl:variable name="outputDate" select="concat($vYear,'-',$vMonth,'-',substring($date,7,2))"/>
		<xsl:value-of select="$outputDate"/>		
	</xsl:template>
	
	</xsl:stylesheet>