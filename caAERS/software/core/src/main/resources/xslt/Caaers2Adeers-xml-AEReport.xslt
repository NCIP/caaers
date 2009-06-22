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
            <xsl:attribute name="NCI_PROTOCOL_NUMBER"><!-- no info -->7028</xsl:attribute>
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
            
            <INV_AGENT_ADMIN>No</INV_AGENT_ADMIN>
            <!-- no info -->
        </COURSE_INFORMATION>
        <!--
        <RADIATION_INTERVENTION>
            <TYPE_OF_RADIATION_ADMINISTRATION>
                <xsl:value-of select="RadiationIntervention/administration"/>
            </TYPE_OF_RADIATION_ADMINISTRATION>
            <TOTAL_DOSE_TO_DATE>
                <xsl:value-of select="RadiationIntervention/dosage"/>
            </TOTAL_DOSE_TO_DATE>
            <DATE_OF_LAST_TREATMENT>
                <xsl:call-template name="standard_date">
                    <xsl:with-param name="date" select="RadiationIntervention/lastTreatmentDate"/>
                </xsl:call-template>
            </DATE_OF_LAST_TREATMENT>
            <SCHEDULE_NUMBER_OF_FRACTIONS>
                <xsl:value-of select="RadiationIntervention/fractionNumber"/>
            </SCHEDULE_NUMBER_OF_FRACTIONS>
            <SCHEDULE_NUMBER_OF_ELAPSED_DAYS>
                <xsl:value-of select="RadiationIntervention/daysElapsed"/>
            </SCHEDULE_NUMBER_OF_ELAPSED_DAYS>
            <UNIT_OF_MEASURE>
                <xsl:value-of select="RadiationIntervention/dosageUnit"/>
            </UNIT_OF_MEASURE>
            <ADJUSTMENT>
                <xsl:value-of select="RadiationIntervention/adjustment"/>
            </ADJUSTMENT>
        </RADIATION_INTERVENTION>
        <SURGERY_INTERVENTION>
            <SITE_OF_INTERVENTION>
                <xsl:value-of select="SurgeryIntervention/AnatomicSite/name"/>
            </SITE_OF_INTERVENTION>
            <DATE_OF_INTERVENTION>
                <xsl:call-template name="standard_date">
                    <xsl:with-param name="date" select="SurgeryIntervention/interventionDate"/>
                </xsl:call-template>
            </DATE_OF_INTERVENTION>
        </SURGERY_INTERVENTION>
        <SUSPECT_MEDICAL_DEVICE>
            <BRAND_NAME>
                <xsl:value-of select="MedicalDevice/brandName"/>
            </BRAND_NAME>
            <COMMON_NAME>
                <xsl:value-of select="MedicalDevice/commonName"/>
            </COMMON_NAME>
            <TYPE_OF_DEVICE>
                <xsl:value-of select="MedicalDevice/deviceType"/>
            </TYPE_OF_DEVICE>
            <MANUFACTURER_NAME>
                <xsl:value-of select="MedicalDevice/manufacturerName"/>
            </MANUFACTURER_NAME>
            <MANUFACTURER_CITY>
                <xsl:value-of select="MedicalDevice/manufacturerCity"/>
            </MANUFACTURER_CITY>
            <MANUFACTURER_STATE>
                <xsl:value-of select="MedicalDevice/manufacturerState"/>
            </MANUFACTURER_STATE>
            <MODEL_NUMBER>
                <xsl:value-of select="MedicalDevice/modelNumber"/>
            </MODEL_NUMBER>
            <LOT_NUMBER>
                <xsl:value-of select="MedicalDevice/lotNumber"/>
            </LOT_NUMBER>
            <CATALOG_NUMBER>
                <xsl:value-of select="MedicalDevice/catalogNumber"/>
            </CATALOG_NUMBER>
            <EXPIRATION_DATE>
                <xsl:call-template name="standard_date">
                    <xsl:with-param name="date" select="MedicalDevice/expirationDate"/>
                </xsl:call-template>
            </EXPIRATION_DATE>
            <SERIAL_NUMBER>
                <xsl:value-of select="MedicalDevice/serialNumber"/>
            </SERIAL_NUMBER>
            <OTHER_NUMBER>
                <xsl:value-of select="MedicalDevice/otherNumber"/>
            </OTHER_NUMBER>
            <AE_DEVICE_OPERATOR>
                <DEVICE_OPERATOR>
                    <xsl:value-of select="MedicalDevice/DeviceOperator"/>
                </DEVICE_OPERATOR>
                <DEVICE_OPERATOR_OTHER>
                    <xsl:value-of select="MedicalDevice/otherDeviceOperator"/>
                </DEVICE_OPERATOR_OTHER>
            </AE_DEVICE_OPERATOR>
            <IMPLANTED_DATE>
                <xsl:call-template name="standard_date">
                    <xsl:with-param name="date" select="MedicalDevice/implantedDate"/>
                </xsl:call-template>
            </IMPLANTED_DATE>
            <EXPLANTED_DATE>
                <xsl:call-template name="standard_date">
                    <xsl:with-param name="date" select="MedicalDevice/explantedDate"/>
                </xsl:call-template>
            </EXPLANTED_DATE>
            <IS_SINGLE_USE_DEVICE>
                <xsl:value-of select="MedicalDevice/brandName"/>
            </IS_SINGLE_USE_DEVICE>
           
            <REPROCESSOR_NAME>
                <xsl:value-of select="MedicalDevice/reprocessorName"/>
            </REPROCESSOR_NAME>
            <REPROCESSOR_ADDRESS>
                <xsl:value-of select="MedicalDevice/reprocessorAddress"/>
            </REPROCESSOR_ADDRESS>
            <EVAL_DEVICE>
                <xsl:value-of select="MedicalDevice/EvaluationAvailability"/>
            </EVAL_DEVICE>
            <RETURNED_DATE>
                <xsl:call-template name="standard_date">
                    <xsl:with-param name="date" select="MedicalDevice/returnedDate"/>
                </xsl:call-template>
            </RETURNED_DATE>
        </SUSPECT_MEDICAL_DEVICE>
        -->
        <DESCRIPTION_OF_EVENT>
            <xsl:if test="AdverseEventResponseDescription/eventDescription != ''">
            <EVENT_DESCRIPTION>
                <xsl:value-of select="AdverseEventResponseDescription/eventDescription"/>
            </EVENT_DESCRIPTION>
            </xsl:if>
            <!-- TODO -->
            <PRESENT_STATUS>Fatal/Died</PRESENT_STATUS>
            <!--<xsl:value-of select="AdverseEventResponseDescription/presentStatus"/>-->


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
            
            <!--TODO-->
            <xsl:if test="AdverseEventResponseDescription/recoveryDate != ''">
            <DEATH_DATE>
                <xsl:call-template name="standard_date_yymm">
                    <xsl:with-param name="date"
                        select="AdverseEventResponseDescription/recoveryDate"/>
                </xsl:call-template>
            </DEATH_DATE>
            </xsl:if>

        </DESCRIPTION_OF_EVENT>
        <PATIENT_INFORMATION>
            <xsl:attribute name="PATIENT_ID">001</xsl:attribute>
            <BIRTH_DATE>
                <xsl:call-template name="standard_date_yymm">
                    <xsl:with-param name="date"
                        select="StudyParticipantAssignment/Participant/dateOfBirth"/>
                </xsl:call-template>
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
            
            <xsl:if test="DiseaseHistory/CtepStudyDisease/DiseaseTerm/term != ''">
            <DISEASE_NAME>
                <xsl:value-of select="DiseaseHistory/CtepStudyDisease/DiseaseTerm/term"/>
            </DISEASE_NAME>
            </xsl:if>
            <xsl:if test="DiseaseHistory/AnatomicSite/name != ''">
            <PRIMARY_SITE_OF_DISEASE>
                <xsl:value-of select="DiseaseHistory/AnatomicSite/name"/>
            </PRIMARY_SITE_OF_DISEASE>
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
                <xsl:value-of select="AdverseEventReport/DiseaseHistory/diagnosisDate/year"/>-<xsl:value-of select="AdverseEventReport/DiseaseHistory/diagnosisDate/month"/>
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
                    <xsl:call-template name="standard_date_yymm">
                        <xsl:with-param name="date" select="startDate"/>
                    </xsl:call-template>
                    <!--2007-09-->
                </THERAPY_START_DATE>
                </xsl:if>
                <xsl:if test="endDate != ''">
                <THERAPY_END_DATE>
                    <xsl:call-template name="standard_date_yymm">
                        <xsl:with-param name="date" select="endDate"/>
                    </xsl:call-template>
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
                        <xsl:value-of select="Agent/name"/>
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
        <PROTOCOL_AGENT>
            <xsl:attribute name="AGENT_NAME">
                <xsl:value-of select="TreatmentInformation/CourseAgent/StudyAgent/Agent/name"/>
            </xsl:attribute>
            <xsl:attribute name="NSC_NUMBER">
                <xsl:value-of select="TreatmentInformation/CourseAgent/StudyAgent/Agent/nscNumber"/>
            </xsl:attribute>
            <xsl:variable name="totalDose" select="TreatmentInformation/CourseAgent/Dose/amount"/>
            
            <xsl:if test="TreatmentInformation/CourseAgent/Dose/amount != ''">
            <TOTAL_DOSE_ADMINISTERED>
                <xsl:value-of select="TreatmentInformation/CourseAgent/Dose/amount"/>
            </TOTAL_DOSE_ADMINISTERED>
            </xsl:if>
            <xsl:if test="TreatmentInformation/CourseAgent/lastAdministeredDate != ''">
            <LAST_ADMINISTERED_DATE>
                <xsl:call-template name="standard_date">
                    <xsl:with-param name="date"
                        select="TreatmentInformation/CourseAgent/lastAdministeredDate"/>
                </xsl:call-template>
            </LAST_ADMINISTERED_DATE>
            </xsl:if>
            <xsl:if test="TreatmentInformation/CourseAgent/Dose/units != ''">
            <DOSE_UOM>
                <xsl:value-of select="TreatmentInformation/CourseAgent/Dose/units"/>
            </DOSE_UOM>
            </xsl:if>
            

            <xsl:if test="TreatmentInformation/CourseAgent/ModifiedDose/amount">
                <xsl:variable name="adjusted"
                    select="TreatmentInformation/CourseAgent/ModifiedDose/amount"/>
                <xsl:if test="$adjusted &gt; $totalDose">
                    <AGENT_ADJUSTMENT>Dose increased</AGENT_ADJUSTMENT>
                </xsl:if>
                <xsl:if test="$adjusted = $totalDose">
                    <AGENT_ADJUSTMENT>Dose not changed</AGENT_ADJUSTMENT>
                </xsl:if>
                <xsl:if test="$adjusted &lt; $totalDose">
                    <AGENT_ADJUSTMENT>Dose reduced</AGENT_ADJUSTMENT>
                </xsl:if>
            </xsl:if>
            <xsl:choose>
                <xsl:when test="TreatmentInformation/administrationDelayAmount">
                    <AGENT_DELAYED>Yes</AGENT_DELAYED>
                </xsl:when>
                <xsl:otherwise>
                    <AGENT_DELAYED>No</AGENT_DELAYED>
                </xsl:otherwise>
            </xsl:choose>
            <xsl:if test="TreatmentInformation/administrationDelayAmount">
                <DELAY>
                    <xsl:value-of select="TreatmentInformation/administrationDelayAmount"/>
                </DELAY>
            </xsl:if>
            <xsl:if test="TreatmentInformation/administrationDelayUnits">
                <DELAY_UOM>
                    <xsl:value-of select="TreatmentInformation/administrationDelayUnits"/>
                </DELAY_UOM>
            </xsl:if>

        </PROTOCOL_AGENT>
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

                
                <CATEGORY>
                    <xsl:value-of select="AdverseEventCtcTerm/ctc-term/CtcCategory/name"/>
                </CATEGORY>
                <AE_TERM>
                    <xsl:value-of select="AdverseEventCtcTerm/ctc-term/term"/>
                </AE_TERM>
                <SELECT_AE>
                    <xsl:value-of select="AdverseEventCtcTerm/ctc-term/select"/>
                </SELECT_AE>

                <!--<OTHER_ADVERSE_EVENT> SAMPL </OTHER_ADVERSE_EVENT>-->
                <AE_START_DATE>
                    <xsl:call-template name="standard_date">
                        <xsl:with-param name="date" select="startDate"/>
                    </xsl:call-template>
                </AE_START_DATE>
                <AE_END_DATE>
                    <xsl:call-template name="standard_date">
                        <xsl:with-param name="date" select="endDate"/>
                    </xsl:call-template>
                </AE_END_DATE>
                <HOSPITALIZATION>
                    <xsl:choose>
                        <xsl:when test="substring(hospitalization, 1, 1) = 0">No</xsl:when>
                        <xsl:otherwise>Yes</xsl:otherwise>
                    </xsl:choose>
                </HOSPITALIZATION>
                <AE_COMMENTS>
                    <xsl:value-of select="comments"/>
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
                            <xsl:value-of select="DiseaseHistory/CtepStudyDisease/DiseaseTerm/term"
                            />
                        </CAUSE_NAME>
                        <TYPE_OF_CAUSE>DISEASE</TYPE_OF_CAUSE>
                        <ATTRIBUTION>
                            <xsl:value-of select="substring(attribution, 4, 20)"/>
                        </ATTRIBUTION>
                    </ATTRIBUTION_FOR_AE>
                </xsl:for-each>
                <xsl:for-each select="SurgeryAttribution">
                    <ATTRIBUTION_FOR_AE>
                        <CAUSE_NAME/>
                        <TYPE_OF_CAUSE>SURGERY</TYPE_OF_CAUSE>
                        <ATTRIBUTION>
                            <xsl:value-of select="substring(attribution, 4, 20)"/>
                        </ATTRIBUTION>
                    </ATTRIBUTION_FOR_AE>
                </xsl:for-each>
                <xsl:for-each select="RadiationAttribution">
                    <ATTRIBUTION_FOR_AE>
                        <CAUSE_NAME/>
                        <TYPE_OF_CAUSE>RADIATION</TYPE_OF_CAUSE>
                        <ATTRIBUTION>
                            <xsl:value-of select="substring(attribution, 4, 20)"/>
                        </ATTRIBUTION>
                    </ATTRIBUTION_FOR_AE>
                </xsl:for-each>
                <xsl:for-each select="DeviceAttribution">
                    <ATTRIBUTION_FOR_AE>
                        <CAUSE_NAME/>
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
                <xsl:attribute name="LAB_NAME">
                    <xsl:value-of select="name"/>
                </xsl:attribute>
                <!-- no
                <LAB_CATEGORY>
                    <xsl:value-of select="name"/>
                </LAB_CATEGORY>
                 -->
                <OTHER_LAB>
                    <xsl:value-of select="other"/>
                </OTHER_LAB>
                <BASELINE_DATE>
                    <xsl:call-template name="standard_date">
                        <xsl:with-param name="date" select="baseline/date"/>
                    </xsl:call-template>
                </BASELINE_DATE>
                <BASELINE_VALUE>
                    <xsl:value-of select="baseline/value"/>
                </BASELINE_VALUE>
                <BASELINE_UOM>
                    <xsl:value-of select="units"/>
                </BASELINE_UOM>
                <RECOVERY_LATEST_DATE>
                    <xsl:call-template name="standard_date">
                        <xsl:with-param name="date" select="recovery/date"/>
                    </xsl:call-template>
                </RECOVERY_LATEST_DATE>
                <RECOVERY_LATEST_VALUE>
                    <xsl:value-of select="recovery/value"/>
                </RECOVERY_LATEST_VALUE>
                <RECOVERY_LATEST_UOM>
                    <xsl:value-of select="units"/>
                </RECOVERY_LATEST_UOM>
                <WORST_DATE>
                    <xsl:call-template name="standard_date">
                        <xsl:with-param name="date" select="nadir/date"/>
                    </xsl:call-template>
                </WORST_DATE>
                <WORST_VALUE>
                    <xsl:value-of select="nadir/value"/>
                </WORST_VALUE>
                <WORST_UOM>
                    <xsl:value-of select="units"/>
                </WORST_UOM>

                <!-- 
                <MICROBIOLOGY_SITE>
                    <xsl:value-of select="name"/>
                </MICROBIOLOGY_SITE>
                <MICROBIOLOGY_DATE>
                    <xsl:value-of select="name"/>
                </MICROBIOLOGY_DATE>
                <INFECTIOUS_AGENT>
                    <xsl:value-of select="name"/>
                </INFECTIOUS_AGENT>
                no -->
            </LAB_RESULT>
        </xsl:for-each>
        <ADDITIONAL_INFORMATION>
            <xsl:if test="AdditionalInformation/autopsyReport = 'true'">
                <ADDITIONAL_INFO_NAME>Autopsy Report</ADDITIONAL_INFO_NAME>
            </xsl:if>
            <xsl:if test="AdditionalInformation/consults = 'true'">
                <ADDITIONAL_INFO_NAME>Consults</ADDITIONAL_INFO_NAME>
            </xsl:if>
            <xsl:if test="AdditionalInformation/dischargeSummary = 'true'">
                <ADDITIONAL_INFO_NAME>Discharge Summary</ADDITIONAL_INFO_NAME>
            </xsl:if>
            <xsl:if test="AdditionalInformation/flowCharts = 'true'">
                <ADDITIONAL_INFO_NAME>Flow Sheets/Case Report Forms</ADDITIONAL_INFO_NAME>
            </xsl:if>
            <xsl:if test="AdditionalInformation/labReports = 'true'">
                <ADDITIONAL_INFO_NAME>Laboratory Reports</ADDITIONAL_INFO_NAME>
            </xsl:if>
            <xsl:if test="AdditionalInformation/obaForm = 'true'">
                <ADDITIONAL_INFO_NAME>OBA Form</ADDITIONAL_INFO_NAME>
            </xsl:if>
            <xsl:if test="AdditionalInformation/other = 'true'">
                <ADDITIONAL_INFO_NAME>Other</ADDITIONAL_INFO_NAME>
            </xsl:if>
            <xsl:if test="AdditionalInformation/pathologyReport = 'true'">
                <ADDITIONAL_INFO_NAME>Pathology Report</ADDITIONAL_INFO_NAME>
            </xsl:if>
            <xsl:if test="AdditionalInformation/progressNotes = 'true'">
                <ADDITIONAL_INFO_NAME>Progress Notes</ADDITIONAL_INFO_NAME>
            </xsl:if>
            <xsl:if test="AdditionalInformation/radiologyReports = 'true'">
                <ADDITIONAL_INFO_NAME>Radiology Reports</ADDITIONAL_INFO_NAME>
            </xsl:if>
            <xsl:if test="AdditionalInformation/referralLetters = 'true'">
                <ADDITIONAL_INFO_NAME>Referral Letters</ADDITIONAL_INFO_NAME>
            </xsl:if>
            <xsl:if test="AdditionalInformation/irbReport = 'true'">
                <ADDITIONAL_INFO_NAME>Summary Report Sent to IRB</ADDITIONAL_INFO_NAME>
            </xsl:if>

            <xsl:if test="AdditionalInformation/otherInformation = 'true'">
                <ADDITIONAL_INFO_OTHER>
                    <xsl:value-of select="AdditionalInformation/otherInformation"/>
                </ADDITIONAL_INFO_OTHER>
            </xsl:if>
        </ADDITIONAL_INFORMATION>
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
</xsl:stylesheet>
