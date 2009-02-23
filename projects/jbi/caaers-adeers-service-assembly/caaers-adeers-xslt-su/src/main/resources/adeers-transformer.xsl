<?xml version="1.0"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
    <xsl:output method="xml" indent="yes"/>
    <xsl:template match="/">
        <AE_REPORT>
            <xsl:apply-templates/>
        </AE_REPORT>
    </xsl:template>

    <xsl:template match="AdverseEventReport">

        <CAEERS_AEREPORT_ID>
            <xsl:value-of select="id"/>
        </CAEERS_AEREPORT_ID>
        <EXTERNAL_SYSTEMS>
            <xsl:value-of select="EXTERNAL_SYSTEMS"/>
        </EXTERNAL_SYSTEMS>
        <REPORT_ID>
            <xsl:value-of select="REPORT_ID"/>
        </REPORT_ID>
        <SUBMITTER_EMAIL>
            <xsl:value-of select="SUBMITTER_EMAIL"/>
        </SUBMITTER_EMAIL>
        <xsl:if test="Report/assignedIdentifer != '' and Report/ReportVersion/reportVersionId != ''" >
			<TICKET_NUMBER><xsl:value-of select="Report/assignedIdentifer"/></TICKET_NUMBER>
			<AMENDMENT_NUMBER><xsl:value-of select="Report/ReportVersion/reportVersionId"/></AMENDMENT_NUMBER>
		</xsl:if>

		
        <REPORTER_INFORMATION>
            <xsl:attribute name="FIRST_NAME">
                <xsl:value-of select="Reporter/firstName"/>
            </xsl:attribute>
            <xsl:attribute name="LAST_NAME">
                <xsl:value-of select="Reporter/lastName"/>
            </xsl:attribute>
            <xsl:attribute name="PHONE">
                <xsl:for-each select="Reporter/ContactMechanism">
                    <xsl:if test="key = 'phone'">
                        <xsl:value-of select="value"/>
                    </xsl:if>
                </xsl:for-each>
            </xsl:attribute>
            <xsl:attribute name="EMAIL">
                <xsl:for-each select="Reporter/ContactMechanism">
                    <xsl:if test="key = 'e-mail'">
                        <xsl:value-of select="value"/>
                    </xsl:if>
                </xsl:for-each>
            </xsl:attribute>
            <xsl:attribute name="FAX">
                <xsl:for-each select="Reporter/ContactMechanism">
                    <xsl:if test="key = 'fax'">
                        <xsl:value-of select="value"/>
                    </xsl:if>
                </xsl:for-each>
            </xsl:attribute>
        </REPORTER_INFORMATION>
        <PHYSICIAN_INFORMATION>
            <xsl:attribute name="FIRST_NAME">
                <xsl:value-of select="Physician/firstName"/>
            </xsl:attribute>
            <xsl:attribute name="LAST_NAME">
                <xsl:value-of select="Physician/lastName"/>
            </xsl:attribute>
            <xsl:attribute name="PHONE">
                <xsl:for-each select="Physician/ContactMechanism">
                    <xsl:if test="key = 'phone'">
                        <xsl:value-of select="value"/>
                    </xsl:if>
                </xsl:for-each>
            </xsl:attribute>
            <xsl:attribute name="EMAIL">
                <xsl:for-each select="Physician/ContactMechanism">
                    <xsl:if test="key = 'e-mail'">
                        <xsl:value-of select="value"/>
                    </xsl:if>
                </xsl:for-each>
            </xsl:attribute>
        </PHYSICIAN_INFORMATION>
        <SUBMITTER_INFORMATION>
            <xsl:attribute name="FIRST_NAME">
                <xsl:value-of select="Reporter/firstName"/>
            </xsl:attribute>
            <xsl:attribute name="LAST_NAME">
                <xsl:value-of select="Reporter/lastName"/>
            </xsl:attribute>
            <xsl:attribute name="PHONE">
                <xsl:for-each select="Reporter/ContactMechanism">
                    <xsl:if test="key = 'phone'">
                        <xsl:value-of select="value"/>
                    </xsl:if>
                </xsl:for-each>
            </xsl:attribute>
            <xsl:attribute name="EMAIL">
                <xsl:for-each select="Reporter/ContactMechanism">
                    <xsl:if test="key = 'e-mail'">
                        <xsl:value-of select="value"/>
                    </xsl:if>
                </xsl:for-each>
            </xsl:attribute>
            <xsl:attribute name="FAX">
                <xsl:for-each select="Reporter/ContactMechanism">
                    <xsl:if test="key = 'fax'">
                        <xsl:value-of select="value"/>
                    </xsl:if>
                </xsl:for-each>
            </xsl:attribute>
        </SUBMITTER_INFORMATION>
        <INSTITUTION_INFORMATION>
            <xsl:attribute name="CTEP_ID">
                <xsl:value-of
                    select="StudyParticipantAssignment/StudySite/Organization/nciInstituteCode"/>
            </xsl:attribute>
            <xsl:if test="StudyParticipantAssignment/StudySite/Organization/name">
                <INSTITUTION_NAME>
                    <xsl:value-of select="StudyParticipantAssignment/StudySite/Organization/name"/>
                </INSTITUTION_NAME>
            </xsl:if>

        </INSTITUTION_INFORMATION>
        <PROTOCOL_INFORMATION>
            <xsl:attribute name="NCI_PROTOCOL_NUMBER">
                <xsl:for-each select="StudyParticipantAssignment/StudySite/Study/Identifier">
                    <xsl:if test="primaryIndicator='true'">
                        <xsl:value-of select="value"/>
                    </xsl:if>
                </xsl:for-each>
            </xsl:attribute>
        </PROTOCOL_INFORMATION>
        <TREATMENT_ASSIGNMENT_INFORMATION>
            <xsl:if test="TreatmentInformation/TreatmentAssignment/code != '' ">
                <TREATMENT_ASSIGNMENT_CODE>
                    <xsl:value-of select="TreatmentInformation/TreatmentAssignment/code"/>
                </TREATMENT_ASSIGNMENT_CODE>
            </xsl:if>
            <!--
            <OTHER_TREATMENT_ASSIGNMENT></OTHER_TREATMENT_ASSIGNMENT>
            -->
        </TREATMENT_ASSIGNMENT_INFORMATION>
        <COURSE_INFORMATION>
            <xsl:if test="TreatmentInformation/firstCourseDate != ''">
                <START_DATE_OF_FIRST_COURSE>
                    <xsl:call-template name="standard_date">
                        <xsl:with-param name="date" select="TreatmentInformation/firstCourseDate"/>
                    </xsl:call-template>
                    <!--2002-09-24-->
                </START_DATE_OF_FIRST_COURSE>
            </xsl:if>
            <xsl:if test="TreatmentInformation/AdverseEventCourse/date != ''">
                <START_DATE_OF_AE_COURSE>
                    <xsl:call-template name="standard_date">
                        <xsl:with-param name="date"
                            select="TreatmentInformation/AdverseEventCourse/date"/>
                    </xsl:call-template>
                </START_DATE_OF_AE_COURSE>
            </xsl:if>
            <xsl:if test="TreatmentInformation/AdverseEventCourse/number != ''">
                <COURSE_NUMBER_OF_AE>
                    <xsl:value-of select="TreatmentInformation/AdverseEventCourse/number"/>
                </COURSE_NUMBER_OF_AE>
            </xsl:if>
            <xsl:if test="TreatmentInformation/totalCourses != ''">
                <TOTAL_NUMBER_OF_COURSES>
                    <xsl:value-of select="TreatmentInformation/totalCourses"/>
                </TOTAL_NUMBER_OF_COURSES>
            </xsl:if>
            
			<xsl:variable name="flg">
	  			<xsl:for-each select="TreatmentInformation/CourseAgent">
	  				<xsl:if test="StudyAgent/INDType = 'DCP_IND'">Yes</xsl:if>
	  				<xsl:if test="StudyAgent/INDType = 'CTEP_IND'">Yes</xsl:if>
	  				<xsl:if test="StudyAgent/INDType = 'OTHER'">Yes</xsl:if>
	  			</xsl:for-each>
	  		</xsl:variable>
	  		
	  		<xsl:choose>
	  			<xsl:when test="string-length($flg)=0">
	  				<INV_AGENT_ADMIN>No</INV_AGENT_ADMIN>
	  			</xsl:when>
	  			<xsl:otherwise>
	  				<INV_AGENT_ADMIN><xsl:value-of select="substring($flg,1,3)"/></INV_AGENT_ADMIN>
	  			</xsl:otherwise>
	  		</xsl:choose>
 		
            
            <!-- no info -->
        </COURSE_INFORMATION>

        <xsl:if test="RadiationIntervention/administration != '' ">
            <RADIATION_INTERVENTION>
			  <xsl:choose>
                    <xsl:when test="RadiationIntervention/administration = 'BT_HDR' ">
                        <TYPE_OF_RADIATION_ADMINISTRATION>Brachytherapy HDR</TYPE_OF_RADIATION_ADMINISTRATION>
                    </xsl:when>
                    <xsl:when test="RadiationIntervention/administration = 'BT_LDR' ">
                        <TYPE_OF_RADIATION_ADMINISTRATION>Brachytherapy LDR</TYPE_OF_RADIATION_ADMINISTRATION>
                    </xsl:when>
                    <xsl:when test="RadiationIntervention/administration = 'BT_NOS' ">
                        <TYPE_OF_RADIATION_ADMINISTRATION>Brachytherapy NOS</TYPE_OF_RADIATION_ADMINISTRATION>
                    </xsl:when>
                    <xsl:when test="RadiationIntervention/administration = 'EB_NOS' ">
                        <TYPE_OF_RADIATION_ADMINISTRATION>External Beam NOS</TYPE_OF_RADIATION_ADMINISTRATION>
                    </xsl:when>
                    <xsl:when test="RadiationIntervention/administration = 'EB_2D' ">
                        <TYPE_OF_RADIATION_ADMINISTRATION>External Beam, 2D</TYPE_OF_RADIATION_ADMINISTRATION>
                    </xsl:when>
                    <xsl:when test="RadiationIntervention/administration = 'EB_3D' ">
                        <TYPE_OF_RADIATION_ADMINISTRATION>External Beam, 3D</TYPE_OF_RADIATION_ADMINISTRATION>
                    </xsl:when>
                    <xsl:when test="RadiationIntervention/administration = 'EB_IMRT' ">
                        <TYPE_OF_RADIATION_ADMINISTRATION>External Beam, IMRT</TYPE_OF_RADIATION_ADMINISTRATION>
                    </xsl:when>
                    <xsl:when test="RadiationIntervention/administration = 'EB_PROTON' ">
                        <TYPE_OF_RADIATION_ADMINISTRATION>External Beam, Proton</TYPE_OF_RADIATION_ADMINISTRATION>
                    </xsl:when>
                    <xsl:when test="RadiationIntervention/administration = 'SYSTEMIC_RADIOTHERAPY' ">
                        <TYPE_OF_RADIATION_ADMINISTRATION>Systemic radiotherapy</TYPE_OF_RADIATION_ADMINISTRATION>
                    </xsl:when>
                    <xsl:otherwise>
                        <TYPE_OF_RADIATION_ADMINISTRATION>
                            <xsl:value-of select="RadiationIntervention/administration"/>
                        </TYPE_OF_RADIATION_ADMINISTRATION>
                    </xsl:otherwise>
                </xsl:choose>

                <TOTAL_DOSE_TO_DATE>
                    <xsl:value-of select="RadiationIntervention/dosage"/>
                </TOTAL_DOSE_TO_DATE>
                <DATE_OF_LAST_TREATMENT>
                    <xsl:call-template name="standard_date">
                        <xsl:with-param name="date" select="RadiationIntervention/lastTreatmentDate"
                        />
                    </xsl:call-template>
                </DATE_OF_LAST_TREATMENT>

                <xsl:if test="RadiationIntervention/fractionNumber != '' ">
                    <SCHEDULE_NUMBER_OF_FRACTIONS>
                        <xsl:value-of select="RadiationIntervention/fractionNumber"/>
                    </SCHEDULE_NUMBER_OF_FRACTIONS>
                </xsl:if>

                <xsl:if test="RadiationIntervention/daysElapsed != '' ">
                    <SCHEDULE_NUMBER_OF_ELAPSED_DAYS>
                        <xsl:value-of select="RadiationIntervention/daysElapsed"/>
                    </SCHEDULE_NUMBER_OF_ELAPSED_DAYS>
                </xsl:if>

                <UNIT_OF_MEASURE>
                    <xsl:value-of select="RadiationIntervention/dosageUnit"/>
                </UNIT_OF_MEASURE>

                <xsl:if test="RadiationIntervention/adjustment != '' ">
                    <ADJUSTMENT>
                        <xsl:value-of select="RadiationIntervention/adjustment"/>
                    </ADJUSTMENT>
                </xsl:if>

            </RADIATION_INTERVENTION>
        </xsl:if>

        <xsl:if test="SurgeryIntervention/InterventionSite/name !=''">
            <SURGERY_INTERVENTION>
                <SITE_OF_INTERVENTION>
                    <xsl:value-of select="SurgeryIntervention/InterventionSite/name"/>
                </SITE_OF_INTERVENTION>
                <DATE_OF_INTERVENTION>
                    <xsl:call-template name="standard_date">
                        <xsl:with-param name="date" select="SurgeryIntervention/interventionDate"/>
                    </xsl:call-template>
                </DATE_OF_INTERVENTION>
            </SURGERY_INTERVENTION>
        </xsl:if>

        <xsl:if test="MedicalDevice/brandName !='' or MedicalDevice/commonName !=''">
            <SUSPECT_MEDICAL_DEVICE>

                <xsl:if test="MedicalDevice/brandName != ''">
                    <BRAND_NAME>
                        <xsl:value-of select="MedicalDevice/brandName"/>
                    </BRAND_NAME>
                </xsl:if>

                <xsl:if test="MedicalDevice/commonName != ''">
                    <COMMON_NAME>
                        <xsl:value-of select="MedicalDevice/commonName"/>
                    </COMMON_NAME>
                </xsl:if>

                <xsl:if test="MedicalDevice/deviceType != ''">
                    <TYPE_OF_DEVICE>
                        <xsl:value-of select="MedicalDevice/deviceType"/>
                    </TYPE_OF_DEVICE>
                </xsl:if>

                <xsl:if test="MedicalDevice/manufacturerName != ''">
                    <MANUFACTURER_NAME>
                        <xsl:value-of select="MedicalDevice/manufacturerName"/>
                    </MANUFACTURER_NAME>
                </xsl:if>

                <xsl:if test="MedicalDevice/manufacturerCity != ''">
                    <MANUFACTURER_CITY>
                        <xsl:value-of select="MedicalDevice/manufacturerCity"/>
                    </MANUFACTURER_CITY>
                </xsl:if>

                <xsl:if test="MedicalDevice/manufacturerState != ''">
                    <MANUFACTURER_STATE>
                        <xsl:value-of select="MedicalDevice/manufacturerState"/>
                    </MANUFACTURER_STATE>
                </xsl:if>

                <xsl:if test="MedicalDevice/modelNumber != ''">
                    <MODEL_NUMBER>
                        <xsl:value-of select="MedicalDevice/modelNumber"/>
                    </MODEL_NUMBER>
                </xsl:if>

                <xsl:if test="MedicalDevice/lotNumber != ''">
                    <LOT_NUMBER>
                        <xsl:value-of select="MedicalDevice/lotNumber"/>
                    </LOT_NUMBER>
                </xsl:if>

                <xsl:if test="MedicalDevice/catalogNumber != ''">
                    <CATALOG_NUMBER>
                        <xsl:value-of select="MedicalDevice/catalogNumber"/>
                    </CATALOG_NUMBER>
                </xsl:if>

                <xsl:if test="MedicalDevice/expirationDate != ''">
                    <EXPIRATION_DATE>
                        <xsl:call-template name="standard_date">
                            <xsl:with-param name="date" select="MedicalDevice/expirationDate"/>
                        </xsl:call-template>
                    </EXPIRATION_DATE>
                </xsl:if>

                <xsl:if test="MedicalDevice/serialNumber != ''">
                    <SERIAL_NUMBER>
                        <xsl:value-of select="MedicalDevice/serialNumber"/>
                    </SERIAL_NUMBER>
                </xsl:if>

                <xsl:if test="MedicalDevice/otherNumber != ''">
                    <OTHER_NUMBER>
                        <xsl:value-of select="MedicalDevice/otherNumber"/>
                    </OTHER_NUMBER>
                </xsl:if>


                <AE_DEVICE_OPERATOR>
                    <xsl:choose>
                        <xsl:when test="MedicalDevice/DeviceOperator = 'HEALTH_PROFESSIONAL'">
                            <DEVICE_OPERATOR>Health Professional</DEVICE_OPERATOR>
                        </xsl:when>
                        <xsl:when test="MedicalDevice/DeviceOperator = 'PATIENT'">
                            <DEVICE_OPERATOR>Lay User/Patient</DEVICE_OPERATOR>
                        </xsl:when>
                        <xsl:when test="MedicalDevice/DeviceOperator = 'OTHER'">
                            <DEVICE_OPERATOR>Other</DEVICE_OPERATOR>
                        </xsl:when>
                        <xsl:otherwise>
                            <DEVICE_OPERATOR>
                                <xsl:value-of select="MedicalDevice/DeviceOperator"/>
                            </DEVICE_OPERATOR>
                        </xsl:otherwise>
                    </xsl:choose>
                    <!--
                <DEVICE_OPERATOR_OTHER>
                    <xsl:value-of select="MedicalDevice/otherDeviceOperator"/>
                </DEVICE_OPERATOR_OTHER>
                -->
                </AE_DEVICE_OPERATOR>

                <xsl:if test="MedicalDevice/implantedDate != ''">
                    <IMPLANTED_DATE>
                        <xsl:call-template name="standard_date">
                            <xsl:with-param name="date" select="MedicalDevice/implantedDate"/>
                        </xsl:call-template>
                    </IMPLANTED_DATE>
                </xsl:if>

                <xsl:if test="MedicalDevice/explantedDate != ''">
                    <EXPLANTED_DATE>
                        <xsl:call-template name="standard_date">
                            <xsl:with-param name="date" select="MedicalDevice/explantedDate"/>
                        </xsl:call-template>
                    </EXPLANTED_DATE>
                </xsl:if>


                <xsl:choose>
                    <xsl:when test="MedicalDevice/DeviceReprocessed = 'YES'">
                        <IS_SINGLE_USE_DEVICE>Yes</IS_SINGLE_USE_DEVICE>
                    </xsl:when>
                    <xsl:otherwise>
                        <IS_SINGLE_USE_DEVICE>No</IS_SINGLE_USE_DEVICE>
                    </xsl:otherwise>
                </xsl:choose>

                <xsl:if test="MedicalDevice/reprocessorName != ''">
                    <REPROCESSOR_NAME>
                        <xsl:value-of select="MedicalDevice/reprocessorName"/>
                    </REPROCESSOR_NAME>
                </xsl:if>

                <xsl:if test="MedicalDevice/reprocessorAddress != ''">
                    <REPROCESSOR_ADDRESS>
                        <xsl:value-of select="MedicalDevice/reprocessorAddress"/>
                    </REPROCESSOR_ADDRESS>
                </xsl:if>


                <xsl:choose>
                    <xsl:when test="MedicalDevice/EvaluationAvailability = 'YES'">
                        <EVAL_DEVICE>Yes</EVAL_DEVICE>
                    </xsl:when>
                    <xsl:when test="MedicalDevice/EvaluationAvailability = 'NO'">
                        <EVAL_DEVICE>No</EVAL_DEVICE>
                    </xsl:when>
                    <xsl:when test="MedicalDevice/EvaluationAvailability = 'RETURNED'">
                        <EVAL_DEVICE>Returned</EVAL_DEVICE>
                    </xsl:when>
                    <xsl:when test="MedicalDevice/EvaluationAvailability = 'UNKNOWN'">
                        <EVAL_DEVICE>Unknown</EVAL_DEVICE>
                    </xsl:when>
                    <xsl:otherwise>
                        <EVAL_DEVICE>
                            <xsl:value-of select="MedicalDevice/EvaluationAvailability"/>
                        </EVAL_DEVICE>
                    </xsl:otherwise>
                </xsl:choose>

                <xsl:if test="MedicalDevice/returnedDate != ''">
                    <RETURNED_DATE>
                        <xsl:call-template name="standard_date">
                            <xsl:with-param name="date" select="MedicalDevice/returnedDate"/>
                        </xsl:call-template>
                    </RETURNED_DATE>
                </xsl:if>

            </SUSPECT_MEDICAL_DEVICE>
        </xsl:if>


        <DESCRIPTION_OF_EVENT>
            <xsl:if test="AdverseEventResponseDescription/eventDescription != ''">
                <EVENT_DESCRIPTION>
                    <xsl:value-of select="AdverseEventResponseDescription/eventDescription"/>
                </EVENT_DESCRIPTION>
            </xsl:if>
            <!-- TODO NEED TO FIX IT , THIS IS JUST A HACK-->
            <PRESENT_STATUS>
                <xsl:if
                    test="AdverseEventResponseDescription/presentStatus = 'INTERVENTION_CONTINUES'">Intervention for AE Continues</xsl:if>
                <xsl:if test="AdverseEventResponseDescription/presentStatus = 'RECOVERING'">Recovering/Resolving</xsl:if>
                <xsl:if
                    test="AdverseEventResponseDescription/presentStatus = 'RECOVERED_WITH_SEQUELAE'">Recovered/Resolved with Sequelae</xsl:if>
                <xsl:if
                    test="AdverseEventResponseDescription/presentStatus = 'RECOVERED_WITHOUT_SEQUELAE'">Recovered/Resolved without Sequelae</xsl:if>
                <xsl:if test="AdverseEventResponseDescription/presentStatus = 'NOT_RECOVERED'">Not recovered/Not resolved</xsl:if>
                <xsl:if test="AdverseEventResponseDescription/presentStatus = 'DEAD'">Fatal/Died</xsl:if>
            </PRESENT_STATUS>



            <xsl:if test="AdverseEventResponseDescription/recoveryDate != ''">
                <DATE_OF_RECOVERY_OR_DEATH>
                    <xsl:call-template name="standard_date">
                        <xsl:with-param name="date"
                            select="AdverseEventResponseDescription/recoveryDate"/>
                    </xsl:call-template>
                </DATE_OF_RECOVERY_OR_DEATH>
            </xsl:if>
            <xsl:if test="AdverseEventResponseDescription/retreated != ''">
                <RETREATED>
                    <xsl:if test="AdverseEventResponseDescription/retreated = 'true'">Yes</xsl:if>
                    <xsl:if test="AdverseEventResponseDescription/retreated = 'false'">No</xsl:if>
                </RETREATED>
            </xsl:if>

            <REMOVED_FROM_PROTOCOL_TRT>
                <xsl:choose>
                    <xsl:when test="AdverseEventResponseDescription/dateRemovedFromProtocol">Yes</xsl:when>
                    <xsl:otherwise>No</xsl:otherwise>
                </xsl:choose>
            </REMOVED_FROM_PROTOCOL_TRT>

            <xsl:if test="AdverseEventResponseDescription/dateRemovedFromProtocol != ''">
                <REMOVED_FROM_PROTOCOL_TRT_DATE>
                    <xsl:call-template name="standard_date">
                        <xsl:with-param name="date"
                            select="AdverseEventResponseDescription/dateRemovedFromProtocol"/>
                    </xsl:call-template>
                </REMOVED_FROM_PROTOCOL_TRT_DATE>
            </xsl:if>


            <xsl:if test="AdverseEventResponseDescription/recoveryDate != ''">
                <DEATH_DATE>
                    <xsl:call-template name="standard_date">
                        <xsl:with-param name="date"
                            select="AdverseEventResponseDescription/recoveryDate"/>
                    </xsl:call-template>
                </DEATH_DATE>
            </xsl:if>
            <xsl:if test="AdverseEventResponseDescription/presentStatus = 'DEAD'">
                <AUTOPSY_PERFORMED>No</AUTOPSY_PERFORMED>
            </xsl:if>

        </DESCRIPTION_OF_EVENT>
        <PATIENT_INFORMATION>
            <xsl:attribute name="PATIENT_ID">
                <xsl:value-of select="StudyParticipantAssignment/studySubjectIdentifier"/>
            </xsl:attribute>

            <!--
                <xsl:for-each select="StudyParticipantAssignment/Participant/Identifier">
                    <xsl:if test="primaryIndicator='true'">
                        <xsl:value-of select="value"/>
                    </xsl:if>
                </xsl:for-each>
                -->
            <BIRTH_DATE>
                <xsl:value-of select="StudyParticipantAssignment/Participant/dateOfBirth/yearString"/>-<xsl:value-of select="StudyParticipantAssignment/Participant/dateOfBirth/monthString"/>
                <!--2002-09-->
            </BIRTH_DATE>
            <RACE>
                <xsl:value-of select="StudyParticipantAssignment/Participant/race"/>
            </RACE>
            <ETHNICITY>
                <xsl:value-of select="StudyParticipantAssignment/Participant/ethnicity"/>
            </ETHNICITY>
            <GENDER>
                <xsl:value-of select="StudyParticipantAssignment/Participant/gender"/>
            </GENDER>
            <xsl:if test="ParticipantHistory/height/quantity != ''">
                <HEIGHT>
                    <xsl:value-of select="ParticipantHistory/height/quantity"/>
                </HEIGHT>
            </xsl:if>
            <xsl:if test="ParticipantHistory/weight/quantity != ''">
                <WEIGHT>
                    <xsl:value-of select="ParticipantHistory/weight/quantity"/>
                </WEIGHT>
            </xsl:if>

            <xsl:if test="ParticipantHistory/baselinePerformanceStatus != ''">
                <BASELINE_PERFORMANCE_STATUS>
                    <xsl:variable name="bps" select="ParticipantHistory/baselinePerformanceStatus"/>
                    <xsl:value-of select="substring($bps, 1, 1)"/>
                </BASELINE_PERFORMANCE_STATUS>
            </xsl:if>

            <xsl:if test="DiseaseHistory/CtepStudyDisease/DiseaseTerm/ctepTerm != ''">
                <DISEASE_NAME>
                    <xsl:value-of select="DiseaseHistory/CtepStudyDisease/DiseaseTerm/ctepTerm"/>
                </DISEASE_NAME>
            </xsl:if>
            <xsl:if test="DiseaseHistory/AnatomicSite/name != ''">
                <PRIMARY_SITE_OF_DISEASE>
                    <xsl:value-of select="DiseaseHistory/AnatomicSite/name"/>
                </PRIMARY_SITE_OF_DISEASE>
            </xsl:if>
            
            <xsl:if test="DiseaseHistory/AnatomicSite/category != ''">
                <PRIMARY_ANATOMIC_SITE>
                    <xsl:value-of select="DiseaseHistory/AnatomicSite/category"/>
                </PRIMARY_ANATOMIC_SITE>
            </xsl:if>

            <xsl:if test="DiseaseHistory/otherPrimaryDisease != ''">
                <DISEASE_NAME_NOT_LISTED>
                    <xsl:value-of select="DiseaseHistory/otherPrimaryDisease"/>
                </DISEASE_NAME_NOT_LISTED>
            </xsl:if>

            <xsl:if test="DiseaseHistory/diagnosisDate != ''">
                <DATE_OF_INITIAL_DIAGNOSIS>
                    <!--
                    <xsl:call-template name="standard_date_yymm">
                        <xsl:with-param name="date" select="DiseaseHistory/diagnosisDate"/>
                    </xsl:call-template>
                    -->
                    <xsl:value-of select="DiseaseHistory/diagnosisDate/yearString"/>-<xsl:value-of select="DiseaseHistory/diagnosisDate/monthString"/>
                    <!--2002-09-->
                </DATE_OF_INITIAL_DIAGNOSIS>
            </xsl:if>

            <xsl:if test="DiseaseHistory/otherPrimaryDiseaseSite != ''">
                <OTHER_PRIMARY_SITE_OF_DISEASE>
                    <xsl:value-of select="DiseaseHistory/otherPrimaryDiseaseSite"/>
                </OTHER_PRIMARY_SITE_OF_DISEASE>
            </xsl:if>

        </PATIENT_INFORMATION>
        <xsl:for-each select="SAEReportPriorTherapy">
            <PRIOR_THERAPY>
                <xsl:if test="PriorTherapy/text != ''">
                    <THERAPY_NAME>
                        <xsl:value-of select="PriorTherapy/text"/>
                    </THERAPY_NAME>
                </xsl:if>
                <xsl:if test="startDate != ''">
                    <THERAPY_START_DATE>
                        <!--
                        <xsl:call-template name="standard_date_yymm">
                            <xsl:with-param name="date" select="startDate"/>
                        </xsl:call-template>
                        -->
                        <xsl:value-of select="startDate/yearString"/>-<xsl:value-of select="startDate/monthString"/>
                        
                        <!--2007-09-->
                    </THERAPY_START_DATE>
                </xsl:if>
                <xsl:if test="endDate != ''">
                    <THERAPY_END_DATE>
                        <!--
                        <xsl:call-template name="standard_date_yymm">
                            <xsl:with-param name="date" select="endDate"/>
                        </xsl:call-template>
                        -->
                        <xsl:value-of select="endDate/yearString"/>-<xsl:value-of select="endDate/monthString"/>
                        
                        <!--2002-09-->
                    </THERAPY_END_DATE>
                </xsl:if>
                <xsl:if test="other != ''">
                    <THERAPY_COMMENTS>
                        <xsl:value-of select="other"/>
                    </THERAPY_COMMENTS>
                </xsl:if>

                <xsl:for-each select="PriorTherapyAgent">
                    <CHEMO_AGENT_NAME>
                        <xsl:value-of select="ChemoAgent/name"/>
                    </CHEMO_AGENT_NAME>
                </xsl:for-each>
            </PRIOR_THERAPY>
        </xsl:for-each>
        <xsl:for-each select="SAEReportPreExistingCondition">
            <PRE_EXISTING_CONDITION>
                <xsl:if test="PreExistingCondition/text != ''">
                    <CONDITION_NAME>
                        <xsl:value-of select="PreExistingCondition/text"/>
                    </CONDITION_NAME>
                </xsl:if>
                <xsl:if test="other != ''">
                    <OTHER_CONDITION_NAME>
                        <xsl:value-of select="other"/>
                    </OTHER_CONDITION_NAME>
                </xsl:if>

            </PRE_EXISTING_CONDITION>
        </xsl:for-each>
        <xsl:for-each select="DiseaseHistory/MetastaticDiseaseSite">
            <SITE_OF_METASTATIC_DISEASE>
                <xsl:if test="AnatomicSite/name != ''">
                    <SITE_NAME>
                        <xsl:value-of select="AnatomicSite/name"/>
                    </SITE_NAME>
                </xsl:if>
                <xsl:if test="otherSite != ''">
                    <OTHER_SITE_NAME>
                        <xsl:value-of select="otherSite"/>
                    </OTHER_SITE_NAME>
                </xsl:if>

            </SITE_OF_METASTATIC_DISEASE>
        </xsl:for-each>
        <xsl:for-each select="TreatmentInformation/CourseAgent">
            <PROTOCOL_AGENT>
                <xsl:attribute name="AGENT_NAME">
                    <xsl:value-of select="StudyAgent/Agent/name"/>
                </xsl:attribute>
                <xsl:attribute name="NSC_NUMBER">
                    <xsl:value-of select="StudyAgent/Agent/nscNumber"/>
                </xsl:attribute>
                <xsl:variable name="totalDose" select="Dose/amount"/>

                <xsl:if test="Dose/amount != ''">
                    <TOTAL_DOSE_ADMINISTERED>
                        <xsl:value-of select="Dose/amount"/>
                    </TOTAL_DOSE_ADMINISTERED>
                </xsl:if>
                <xsl:if test="lastAdministeredDate != ''">
                    <LAST_ADMINISTERED_DATE>
                        <xsl:call-template name="standard_date">
                            <xsl:with-param name="date" select="lastAdministeredDate"/>
                        </xsl:call-template>
                    </LAST_ADMINISTERED_DATE>
                </xsl:if>
                <xsl:if test="Dose/units != ''">
                    <DOSE_UOM>
                        <xsl:value-of select="Dose/units"/>
                    </DOSE_UOM>
                </xsl:if>


                <xsl:if test="AgentAdjustment='DOSE_INCREASED'">
                	<AGENT_ADJUSTMENT>Dose increased</AGENT_ADJUSTMENT>
                </xsl:if>
                <xsl:if test="AgentAdjustment='DOSE_NOTCHANGED'">
                	<AGENT_ADJUSTMENT>Dose not changed</AGENT_ADJUSTMENT>
                </xsl:if>
                <xsl:if test="AgentAdjustment='DOSE_REDUCED'">
                	<AGENT_ADJUSTMENT>Dose reduced</AGENT_ADJUSTMENT>
                </xsl:if>
                <xsl:if test="AgentAdjustment='DRUG_WITHDRAWN'">
                	<AGENT_ADJUSTMENT>Drug withdrawn</AGENT_ADJUSTMENT>
                </xsl:if>
                <xsl:if test="AgentAdjustment='NA'">
                	<AGENT_ADJUSTMENT>Not applicable</AGENT_ADJUSTMENT>
                </xsl:if>
                
                <xsl:choose>
                    <xsl:when test="administrationDelayAmount">
                        <AGENT_DELAYED>Yes</AGENT_DELAYED>
                    </xsl:when>
                    <xsl:otherwise>
                        <AGENT_DELAYED>No</AGENT_DELAYED>
                    </xsl:otherwise>
                </xsl:choose>
                <xsl:if test="administrationDelayAmount">
                    <DELAY>
                        <xsl:value-of select="administrationDelayAmount"/>
                    </DELAY>
                </xsl:if>
                <xsl:if test="administrationDelayUnits">
                    <!-- modify case. Eg: HOURS to Hours -->
                    <DELAY_UOM>
                        <xsl:value-of
                            select="concat(translate(substring(administrationDelayUnits,1,1),'abcdefghijklmnopqrstuvwxyz','ABCDEFGHIJKLMNOPQRSTUVWXYZ'),translate(substring(administrationDelayUnits,2),'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz'))"
                        />
                    </DELAY_UOM>
                </xsl:if>

            </PROTOCOL_AGENT>
        </xsl:for-each>

        <xsl:for-each select="ConcomitantMedication">
            <CONCOMITANT_MEDICATION>
                <xsl:attribute name="CONCOMITANT_MEDICATION_NAME">
                    <xsl:value-of select="name"/>
                </xsl:attribute>
            </CONCOMITANT_MEDICATION>
        </xsl:for-each>
        <xsl:for-each select="OtherCause">
            <OTHER_CONTRIBUTING_CAUSE>
                <xsl:attribute name="OTHER_CAUSE">
                    <xsl:value-of select="text"/>
                </xsl:attribute>
            </OTHER_CONTRIBUTING_CAUSE>
        </xsl:for-each>

        <xsl:for-each select="AdverseEvent">
            <ADVERSE_EVENT_CTC>
                <xsl:attribute name="GRADE">
                    <xsl:value-of select="substring(grade, 1, 1)"/>
                </xsl:attribute>

                <xsl:choose>
                    <xsl:when test="substring(gridId,1,3) = 'PRY'">
                        <IS_PRIMARY_AE>Yes</IS_PRIMARY_AE>
                    </xsl:when>
                    <xsl:otherwise>
                        <IS_PRIMARY_AE>No</IS_PRIMARY_AE>
                    </xsl:otherwise>
                </xsl:choose>

                <xsl:if test="AdverseEventCtcTerm/ctc-term/CtcCategory/name != ''">
                    <CATEGORY>
                        <xsl:value-of select="AdverseEventCtcTerm/ctc-term/CtcCategory/name"/>
                    </CATEGORY>
                </xsl:if>

                <xsl:if test="AdverseEventCtcTerm/ctc-term/term != ''">
                    <AE_TERM>
                        <xsl:value-of select="AdverseEventCtcTerm/ctc-term/term"/>
                    </AE_TERM>
                </xsl:if>

                <xsl:if test="AdverseEventCtcTerm/ctc-term/select != ''">
                    <SELECT_AE>
                        <xsl:value-of select="AdverseEventCtcTerm/ctc-term/select"/>
                    </SELECT_AE>
                </xsl:if>
 				<xsl:if test="LowLevelTerm/fullName != ''">
                    <OTHER_ADVERSE_EVENT>
                        <xsl:value-of select="LowLevelTerm/fullName"/>
                    </OTHER_ADVERSE_EVENT>
                </xsl:if>               

           <xsl:choose>     
         		<xsl:when test="LowLevelTerm/fullName">  
                </xsl:when>
                
                <xsl:otherwise>
                	<xsl:if test="detailsForOther != ''">
                        <OTHER_ADVERSE_EVENT>
                        	<xsl:value-of select="detailsForOther"/>
                    	</OTHER_ADVERSE_EVENT>
                    </xsl:if>
                </xsl:otherwise>
		 	</xsl:choose>
		 
                <xsl:if test="startDate">
                    <AE_START_DATE>
                        <xsl:call-template name="standard_date">
                            <xsl:with-param name="date" select="startDate"/>
                        </xsl:call-template>
                    </AE_START_DATE>
                </xsl:if>

                <xsl:if test="endDate">
                    <AE_END_DATE>
                        <xsl:call-template name="standard_date">
                            <xsl:with-param name="date" select="endDate"/>
                        </xsl:call-template>
                    </AE_END_DATE>
                </xsl:if>
                <HOSPITALIZATION>
                    <xsl:choose>
                        <xsl:when test="substring(hospitalization, 1, 1) = 2">No</xsl:when>
                        <xsl:otherwise>Yes</xsl:otherwise>
                    </xsl:choose>
                </HOSPITALIZATION>
                <AE_COMMENTS>
                        <xsl:value-of select="detailsForOther"/>
                </AE_COMMENTS>

                <xsl:for-each select="ConcomitantMedicationAttribution">
                    <ATTRIBUTION_FOR_AE>
                        <CAUSE_NAME>
                            <xsl:value-of select="ConcomitantMedication/name"/>
                        </CAUSE_NAME>
                        <TYPE_OF_CAUSE>CONCOMITANT MEDICATION</TYPE_OF_CAUSE>
                        <ATTRIBUTION>
                            <xsl:value-of select="substring(attribution, 4, 20)"/>
                        </ATTRIBUTION>
                    </ATTRIBUTION_FOR_AE>
                </xsl:for-each>
                <xsl:for-each select="OtherCauseAttribution">
                    <ATTRIBUTION_FOR_AE>
                        <CAUSE_NAME>
                            <xsl:value-of select="OtherCause/text"/>
                        </CAUSE_NAME>
                        <TYPE_OF_CAUSE>OTHER CAUSE</TYPE_OF_CAUSE>
                        <ATTRIBUTION>
                            <xsl:value-of select="substring(attribution, 4, 20)"/>
                        </ATTRIBUTION>
                    </ATTRIBUTION_FOR_AE>
                </xsl:for-each>
                <xsl:for-each select="CourseAgentAttribution">
                    <ATTRIBUTION_FOR_AE>
                        <CAUSE_NAME>
                            <xsl:value-of select="CourseAgent/StudyAgent/Agent/name"/>
                        </CAUSE_NAME>
                        <TYPE_OF_CAUSE>AGENT</TYPE_OF_CAUSE>
                        <ATTRIBUTION>
                            <xsl:value-of select="substring(attribution, 4, 20)"/>
                        </ATTRIBUTION>
                    </ATTRIBUTION_FOR_AE>
                </xsl:for-each>

                <xsl:for-each select="DiseaseAttribution">
                    <ATTRIBUTION_FOR_AE>
                        <CAUSE_NAME>
                            <xsl:value-of
                                select="DiseaseHistory/CtepStudyDisease/DiseaseTerm/ctepTerm"/>
                        </CAUSE_NAME>
                        <TYPE_OF_CAUSE>DISEASE</TYPE_OF_CAUSE>
                        <ATTRIBUTION>
                            <xsl:value-of select="substring(attribution, 4, 20)"/>
                        </ATTRIBUTION>
                    </ATTRIBUTION_FOR_AE>
                </xsl:for-each>

                <xsl:for-each select="SurgeryAttribution">
                    <ATTRIBUTION_FOR_AE>
                        <CAUSE_NAME>
                            <xsl:value-of select="SurgeryIntervention/InterventionSite/name"/>
                        </CAUSE_NAME>
                        <TYPE_OF_CAUSE>SURGERY</TYPE_OF_CAUSE>
                        <ATTRIBUTION>
                            <xsl:value-of select="substring(attribution, 4, 20)"/>
                        </ATTRIBUTION>
                    </ATTRIBUTION_FOR_AE>
                </xsl:for-each>
                <xsl:for-each select="RadiationAttribution">
                    <ATTRIBUTION_FOR_AE>
                        <xsl:choose>
                            <xsl:when test="RadiationIntervention/administration = 'BT_HDR' ">
                                <CAUSE_NAME>Brachytherapy HDR</CAUSE_NAME>
                            </xsl:when>
                            <xsl:when test="RadiationIntervention/administration = 'BT_LDR' ">
                                <CAUSE_NAME>Brachytherapy LDR</CAUSE_NAME>
                            </xsl:when>
                            <xsl:when test="RadiationIntervention/administration = 'BT_NOS' ">
                                <CAUSE_NAME>Brachytherapy NOS</CAUSE_NAME>
                            </xsl:when>
                            <xsl:when test="RadiationIntervention/administration = 'EB_NOS' ">
                                <CAUSE_NAME>External Beam NOS</CAUSE_NAME>
                            </xsl:when>
                            <xsl:when test="RadiationIntervention/administration = 'EB_2D' ">
                                <CAUSE_NAME>External Beam, 2D</CAUSE_NAME>
                            </xsl:when>
                            <xsl:when test="RadiationIntervention/administration = 'EB_3D' ">
                                <CAUSE_NAME>External Beam, 3D</CAUSE_NAME>
                            </xsl:when>
                            <xsl:when test="RadiationIntervention/administration = 'EB_IMRT' ">
                                <CAUSE_NAME>External Beam, IMRT</CAUSE_NAME>
                            </xsl:when>
                            <xsl:when test="RadiationIntervention/administration = 'EB_PROTON' ">
                                <CAUSE_NAME>External Beam, Proton</CAUSE_NAME>
                            </xsl:when>
                            <xsl:when
                                test="RadiationIntervention/administration = 'SYSTEMIC_RADIOTHERAPY' ">
                                <CAUSE_NAME>Systemic radiotherapy</CAUSE_NAME>
                            </xsl:when>
                            <xsl:otherwise>
                                <TYPE_OF_RADIATION_ADMINISTRATION>
                                    <xsl:value-of select="RadiationIntervention/administration"/>
                                </TYPE_OF_RADIATION_ADMINISTRATION>
                            </xsl:otherwise>
                        </xsl:choose>
                        <TYPE_OF_CAUSE>RADIATION</TYPE_OF_CAUSE>
                        <ATTRIBUTION>
                            <xsl:value-of select="substring(attribution, 4, 20)"/>
                        </ATTRIBUTION>
                    </ATTRIBUTION_FOR_AE>
                </xsl:for-each>

                <xsl:for-each select="DeviceAttribution">
                    <ATTRIBUTION_FOR_AE>
                        <CAUSE_NAME>
                            <xsl:value-of select="MedicalDevice/brandName"/>
                        </CAUSE_NAME>
                        <TYPE_OF_CAUSE>DEVICE</TYPE_OF_CAUSE>
                        <ATTRIBUTION>
                            <xsl:value-of select="substring(attribution, 4, 20)"/>
                        </ATTRIBUTION>
                    </ATTRIBUTION_FOR_AE>
                </xsl:for-each>

            </ADVERSE_EVENT_CTC>
        </xsl:for-each>
        <xsl:for-each select="Lab">
            <LAB_RESULT>
                <xsl:if test="labTerm/term != ''">
                    <xsl:attribute name="LAB_NAME">
                        <xsl:value-of select="labTerm/term"/>
                    </xsl:attribute>
                </xsl:if>

                <xsl:if test="labTerm/category/name != ''">
                    <LAB_CATEGORY>
                        <xsl:value-of select="labTerm/category/name"/>
                    </LAB_CATEGORY>
                </xsl:if>


                <xsl:if test="other != ''">
                    <OTHER_LAB>
                        <xsl:value-of select="other"/>
                    </OTHER_LAB>
                </xsl:if>
                <xsl:if test="baseline/date != ''">
                    <BASELINE_DATE>
                        <xsl:call-template name="standard_date">
                            <xsl:with-param name="date" select="baseline/date"/>
                        </xsl:call-template>
                    </BASELINE_DATE>
                </xsl:if>
                <xsl:if test="baseline/value != ''">
                    <BASELINE_VALUE>
                        <xsl:value-of select="baseline/value"/>
                    </BASELINE_VALUE>
                </xsl:if>
                <xsl:if test="units != ''">
                    <BASELINE_UOM>
                        <xsl:value-of select="units"/>
                    </BASELINE_UOM>
                </xsl:if>
                <xsl:if test="recovery/date != ''">
                    <RECOVERY_LATEST_DATE>
                        <xsl:call-template name="standard_date">
                            <xsl:with-param name="date" select="recovery/date"/>
                        </xsl:call-template>
                    </RECOVERY_LATEST_DATE>
                </xsl:if>
                <xsl:if test="recovery/value != ''">
                    <RECOVERY_LATEST_VALUE>
                        <xsl:value-of select="recovery/value"/>
                    </RECOVERY_LATEST_VALUE>
                </xsl:if>
                <xsl:if test="units != ''">
                    <RECOVERY_LATEST_UOM>
                        <xsl:value-of select="units"/>
                    </RECOVERY_LATEST_UOM>
                </xsl:if>
                <xsl:if test="nadir/date != ''">
                    <WORST_DATE>
                        <xsl:call-template name="standard_date">
                            <xsl:with-param name="date" select="nadir/date"/>
                        </xsl:call-template>
                    </WORST_DATE>
                </xsl:if>
                <xsl:if test="nadir/value != ''">
                    <WORST_VALUE>
                        <xsl:value-of select="nadir/value"/>
                    </WORST_VALUE>
                </xsl:if>
                <xsl:if test="units != ''">
                    <WORST_UOM>
                        <xsl:value-of select="units"/>
                    </WORST_UOM>
                </xsl:if>
                <xsl:if test="site != ''">
                    <MICROBIOLOGY_SITE>
                        <xsl:value-of select="site"/>
                    </MICROBIOLOGY_SITE>
                </xsl:if>
                <xsl:if test="labDate != ''">
                    <MICROBIOLOGY_DATE>
                        <xsl:call-template name="standard_date">
                            <xsl:with-param name="date" select="labDate"/>
                        </xsl:call-template>                    
                    </MICROBIOLOGY_DATE>
                </xsl:if>
                <xsl:if test="infectiousAgent != ''">
                    <INFECTIOUS_AGENT>
                        <xsl:value-of select="infectiousAgent"/>
                    </INFECTIOUS_AGENT>
                </xsl:if>	
            </LAB_RESULT>
        </xsl:for-each>

        <xsl:if test="AdditionalInformation/autopsyReport = 'true'">
            <ADDITIONAL_INFORMATION>
                <ADDITIONAL_INFO_NAME>Autopsy Report</ADDITIONAL_INFO_NAME>
            </ADDITIONAL_INFORMATION>
        </xsl:if>
        <xsl:if test="AdditionalInformation/consults = 'true'">
            <ADDITIONAL_INFORMATION>
                <ADDITIONAL_INFO_NAME>Consults</ADDITIONAL_INFO_NAME>
            </ADDITIONAL_INFORMATION>
        </xsl:if>
        <xsl:if test="AdditionalInformation/dischargeSummary = 'true'">
            <ADDITIONAL_INFORMATION>
                <ADDITIONAL_INFO_NAME>Discharge Summary</ADDITIONAL_INFO_NAME>
            </ADDITIONAL_INFORMATION>
        </xsl:if>
        <xsl:if test="AdditionalInformation/flowCharts = 'true'">
            <ADDITIONAL_INFORMATION>
                <ADDITIONAL_INFO_NAME>Flow Sheets/Case Report Forms</ADDITIONAL_INFO_NAME>
            </ADDITIONAL_INFORMATION>
        </xsl:if>
        <xsl:if test="AdditionalInformation/labReports = 'true'">
            <ADDITIONAL_INFORMATION>
                <ADDITIONAL_INFO_NAME>Laboratory Reports</ADDITIONAL_INFO_NAME>
            </ADDITIONAL_INFORMATION>
        </xsl:if>
        <xsl:if test="AdditionalInformation/obaForm = 'true'">
            <ADDITIONAL_INFORMATION>
                <ADDITIONAL_INFO_NAME>OBA Form</ADDITIONAL_INFO_NAME>
            </ADDITIONAL_INFORMATION>
        </xsl:if>
        <!--
            <xsl:if test="AdditionalInformation/other = 'true'">
                <ADDITIONAL_INFO_NAME>Other</ADDITIONAL_INFO_NAME>
            </xsl:if>
            -->
        <xsl:if test="AdditionalInformation/pathologyReport = 'true'">
            <ADDITIONAL_INFORMATION>
                <ADDITIONAL_INFO_NAME>Pathology Report</ADDITIONAL_INFO_NAME>
            </ADDITIONAL_INFORMATION>
        </xsl:if>
        <xsl:if test="AdditionalInformation/progressNotes = 'true'">
            <ADDITIONAL_INFORMATION>
                <ADDITIONAL_INFO_NAME>Progress Notes</ADDITIONAL_INFO_NAME>
            </ADDITIONAL_INFORMATION>
        </xsl:if>
        <xsl:if test="AdditionalInformation/radiologyReports = 'true'">
            <ADDITIONAL_INFORMATION>
                <ADDITIONAL_INFO_NAME>Radiology Reports</ADDITIONAL_INFO_NAME>
            </ADDITIONAL_INFORMATION>
        </xsl:if>
        <xsl:if test="AdditionalInformation/referralLetters = 'true'">
            <ADDITIONAL_INFORMATION>
                <ADDITIONAL_INFO_NAME>Referral Letters</ADDITIONAL_INFO_NAME>
            </ADDITIONAL_INFORMATION>
        </xsl:if>
        <xsl:if test="AdditionalInformation/irbReport = 'true'">
            <ADDITIONAL_INFORMATION>
                <ADDITIONAL_INFO_NAME>Summary Report Sent to IRB</ADDITIONAL_INFO_NAME>
            </ADDITIONAL_INFORMATION>
        </xsl:if>

        <xsl:if test="AdditionalInformation/otherInformation != ''">
            <ADDITIONAL_INFORMATION>
                <ADDITIONAL_INFO_NAME>Other</ADDITIONAL_INFO_NAME>
                <ADDITIONAL_INFO_OTHER>
                    <xsl:value-of select="AdditionalInformation/otherInformation"/>
                </ADDITIONAL_INFO_OTHER>
            </ADDITIONAL_INFORMATION>
        </xsl:if>

    </xsl:template>

    <xsl:template name="standard_date">
        <xsl:param name="date"/>
        <xsl:if test="$date">
            <!-- Year -->
            <xsl:value-of select="substring($date, 1, 4)"/>
            <xsl:text>-</xsl:text>
            <!-- Month -->
            <xsl:value-of select="substring($date, 6, 2)"/>
            <xsl:text>-</xsl:text>
            <!-- Day -->
            <xsl:value-of select="substring($date, 9, 2)"/>
        </xsl:if>
    </xsl:template>

    <xsl:template name="standard_date_yymm">
        <xsl:param name="date"/>
        <xsl:if test="$date">
            <!-- Year -->
            <xsl:value-of select="substring($date, 1, 4)"/>
            <xsl:text>-</xsl:text>
            <!-- Month -->
            <xsl:value-of select="substring($date, 6, 2)"/>
        </xsl:if>
    </xsl:template>

    <xsl:template name="date_padder">
        <xsl:param name="num"/>
        <xsl:if test="string-length($num) = 1">
            <xsl:text>0</xsl:text>
            <xsl:value-of select="$num"/>
        </xsl:if>
        <xsl:if test="string-length($num) > 1">
            <xsl:value-of select="$num"/>
        </xsl:if>
    </xsl:template>
</xsl:stylesheet>
