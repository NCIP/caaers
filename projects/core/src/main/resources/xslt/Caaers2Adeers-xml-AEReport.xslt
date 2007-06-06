<?xml version="1.0"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
	
	<xsl:output method="xml" indent="yes"/> 
	<xsl:template match="/"> 
 		<ae_report> 
  			<xsl:apply-templates/>
  		</ae_report>
	</xsl:template>
	
	<xsl:template match="AdverseEventReport">
		<ticket_number>##</ticket_number>
		<amendment_number>##</amendment_number>
		<report_type>##</report_type>
		<report_status>
			<xsl:value-of select="status"/> 
		</report_status>
		<assessment_comments>##</assessment_comments>
		
		<is_death_unrelated_to_ae>##</is_death_unrelated_to_ae>
		<is_sent_to_fda>##</is_sent_to_fda>
		<is_24_hour_report>##</is_24_hour_report>		
		
		<report_created_date>
			<xsl:value-of select="detectionDate"/> 
		</report_created_date>	
		<date_submitted_to_group>##</date_submitted_to_group>
		<date_submitted_to_nci>##</date_submitted_to_nci>
		<date_submitted_to_fda>##</date_submitted_to_fda>
		
		<xsl:apply-templates select="Physician"/> 	
		<xsl:apply-templates select="Reporter"/>	
		
		<submitter_information>
			<first_name>##</first_name>
			<last_name>##</last_name>
			<phone>##</phone>
			<fax>##</fax>
			<email>##</email>
		</submitter_information>

		<institution_information>
			<institution_name>
				<xsl:value-of select="StudyParticipantAssignment/StudySite/Site/name"/>
			</institution_name>
			<ctep_id>##</ctep_id>
		</institution_information>

		<protocol_information>
			<nci_protocol_number>
				<xsl:value-of select="StudyParticipantAssignment/StudySite/Study/shortTitle"/><!-- verify--> 
			</nci_protocol_number>
			<protocol_title>
				<xsl:value-of select="StudyParticipantAssignment/StudySite/Study/longTitle"/> 
			</protocol_title>
			<protocol_phase>
				<xsl:value-of select="StudyParticipantAssignment/StudySite/Study/phaseCode"/> 
			</protocol_phase>
			<protocol_status>
				<xsl:value-of select="StudyParticipantAssignment/StudySite/Study/status"/> 
			</protocol_status>
			<type_of_study>
				<xsl:value-of select="StudyParticipantAssignment/StudySite/Study/shortTitle"/> <!-- verify-->
			</type_of_study>
			<lead_institution>
				<xsl:value-of select="StudyParticipantAssignment/StudySite/Study/primarySponsorCode"/> 
			</lead_institution>
			<principal_investigator>
				<xsl:value-of select="StudyParticipantAssignment/StudySite/Study/shortTitle"/> <!-- verify-->
			</principal_investigator>
			<lead_drug_monitor>
				<xsl:value-of select="StudyParticipantAssignment/StudySite/Study/monitorCode"/> 
			</lead_drug_monitor>
			<ctcae_version>
				<xsl:value-of select="StudyParticipantAssignment/StudySite/Study/shortTitle"/> <!-- verify-->
			</ctcae_version>			
		</protocol_information>	
		
		<course_information>
			<!-- choice -->
			<other_treatment_assignment>##</other_treatment_assignment>
			<treatment_assignment_code>##</treatment_assignment_code>
			<treatment_description>##</treatment_description>
			<!-- ..choice -->
			
			<start_date_of_first_course>
				<xsl:value-of select="AdverseEvent/CourseAgentAttribution/CourseAgent/TreatmentInformation/firstCourseDate"/>
			</start_date_of_first_course>
			<start_date_of_ae_course>
				<xsl:value-of select="AdverseEvent/CourseAgentAttribution/CourseAgent/TreatmentInformation/CourseDate/date"/>			
			</start_date_of_ae_course>
			<start_date_of_primary_ae>##</start_date_of_primary_ae>
			<end_date_of_primary_ae>##</end_date_of_primary_ae>
			<course_number_of_ae>
				<xsl:value-of select="AdverseEvent/CourseAgentAttribution/CourseAgent/TreatmentInformation/CourseDate/number"/>			
			</course_number_of_ae>
			<total_number_of_courses>##</total_number_of_courses>
		</course_information>

		<description_of_event>
			<event_description>			
				<xsl:value-of select="AdverseEventResponseDescription/eventDescription"/>			
			</event_description>
			<present_status>
				<xsl:value-of select="AdverseEventResponseDescription/presentStatus"/>
			</present_status>
			<date_of_recovery_or_death>
				<xsl:value-of select="AdverseEventResponseDescription/recoveryDate"/>
			</date_of_recovery_or_death>
			<retreated>
				<xsl:value-of select="AdverseEventResponseDescription/retreated"/>
			</retreated>
			<removed_from_protocol_trt>##</removed_from_protocol_trt>
			<removed_from_protocol_trt_date>
				<xsl:value-of select="AdverseEventResponseDescription/dateRemovedFromProtocol"/>
			</removed_from_protocol_trt_date>
			<death_date>##</death_date>
			<autopsy_performed>##</autopsy_performed>
		</description_of_event>

		<patient_information>
			<patient_id>
				<xsl:value-of select="StudyParticipantAssignment/Participant/institutionalPatientNumber"/>
			</patient_id>
			<birth_date>
				<xsl:value-of select="StudyParticipantAssignment/Participant/dateOfBirth"/>
			</birth_date>
			<race>
				<xsl:value-of select="StudyParticipantAssignment/Participant/race"/>
			</race>
			<gender>
				<xsl:value-of select="StudyParticipantAssignment/Participant/gender"/>
			</gender>
			<height>
				<xsl:value-of select="ParticipantHistory/height"/>
				<xsl:value-of select="ParticipantHistory/heightUnitOfMeasure"/>
			</height>
			<weight>
				<xsl:value-of select="ParticipantHistory/weight"/>
				<xsl:value-of select="ParticipantHistory/weightUnitOfMeasure"/>
			</weight>
			<baseline_performance_status>
				<xsl:value-of select="ParticipantHistory/baselinePerformanceStatus"/>
			</baseline_performance_status>
			<disease_name>
				<xsl:value-of select="DiseaseHistory/otherPrimaryDiseaseCode"/>
			</disease_name>
			<disease_category>
				<xsl:value-of select="DiseaseHistory/otherPrimaryDiseaseCode"/>
			</disease_category>
			<primary_site_of_disease>
				<xsl:value-of select="DiseaseHistory/otherPrimaryDiseaseSiteCode"/>
			</primary_site_of_disease>
			<date_of_initial_diagnosis>
				<xsl:value-of select="DiseaseHistory/dateOfInitialPathologicDiagnosis"/>
			</date_of_initial_diagnosis>
		</patient_information>
							
			<!-- 
				<xsl:apply-templates select="StudyParticipantAssignment"/>
			 -->	
		<prior_therapies>
			<xsl:apply-templates select="AdverseEventPriorTherapy"/>
		</prior_therapies>
		
		<pre_existing_conditions>
			<!-- loop -->
			<pre_existing_condition>
				<!-- choice -->
				<condition_name>##</condition_name>
				<other_condition_name>##</other_condition_name>
			</pre_existing_condition>		
		</pre_existing_conditions>		

		<sites_of_metastatic_disease>
			<xsl:for-each select="DiseaseHistory/MetastaticDiseaseSite">			
				<site_of_metastatic_disease>
					<!-- choice -->
					<other_site_name>
						<xsl:value-of select="otherMetastaticDiseaseSite"/>
					</other_site_name>
					<site_name>
						<xsl:value-of select="otherMetastaticDiseaseSite"/>
					</site_name>
				</site_of_metastatic_disease>
			</xsl:for-each>		
		</sites_of_metastatic_disease>	

		<protocol_agents>
			<xsl:for-each select="AdverseEvent">
				<xsl:for-each select="CourseAgentAttribution">
					<protocol_agent>
						<agent_name>##</agent_name>
						<nsc_number>##</nsc_number>
						<total_dose_administered>
							<xsl:value-of select = "CourseAgent/totalDoseAdministeredThisCourse"/>
						</total_dose_administered>
						<last_administered_date>
							<xsl:value-of select = "CourseAgent/lastAdministeredDate"/>
						</last_administered_date>
						<dose_uom>
							<xsl:value-of select = "CourseAgent/Dose/units"/>
						</dose_uom>
						<protocol_agent_comments>##</protocol_agent_comments>
						<agent_adjustment>##</agent_adjustment>
						<agent_delayed>##</agent_delayed>
						<delay>
							<xsl:value-of select = "CourseAgent/administrationDelayAmount"/>
						</delay>
						<delay_uom>
							<xsl:value-of select = "CourseAgent/administrationDelayUnits"/>
						</delay_uom>
					</protocol_agent>
				</xsl:for-each>	
			</xsl:for-each>	
		</protocol_agents>

		<other_contributing_causes>
			<xsl:for-each select="AdverseEvent">
				<other_contributing_cause>
					<other_cause>
						<xsl:value-of select = "OtherCauseAttribution/OtherCause/text"/>
					</other_cause>
					<other_cause_reported_by>##</other_cause_reported_by>	
				</other_contributing_cause>
			</xsl:for-each>
		</other_contributing_causes>
									
		<concomitant_medications>
			<xsl:apply-templates select="ConcomitantMedication"/> 	
		</concomitant_medications>

		

		<adverse_events_ctc>
			<xsl:for-each select="AdverseEvent">
			<adverse_event_ctc>
				<category>
					<xsl:value-of select="CtcTerm/CtcCategory/name"/> 
				</category>
				<adverse_event>##</adverse_event>
				<meddra_code>##</meddra_code>
				<grade>
					<xsl:value-of  select="grade"/> 
				</grade>
				<ae_term>
					<xsl:value-of select="CtcTerm/term"/> <!--verify-->
				</ae_term> 
			
				<select_ae>##</select_ae>
			
				<latest_ctcae_version_category>
					<xsl:value-of select="CtcTerm/CtcCategory/name"/> <!--verify-->
				</latest_ctcae_version_category>
			
				<latest_ctcae_version_ae>##</latest_ctcae_version_ae>
				<latest_ctcae_version_meddra_code>##</latest_ctcae_version_meddra_code>
				<latest_ctcae_version_grade>##</latest_ctcae_version_grade>
				<other_adverse_event>
					<xsl:value-of select="detailsForOther"/>
				</other_adverse_event>
	
				<hospitalization>
					<xsl:value-of select="hospitalization"/> 
				</hospitalization>
				<ae_comments>
					<xsl:value-of select="comments"/>
				</ae_comments>
				
				<ae_reported_by>##</ae_reported_by>
	
				<attributions_for_ae>
					<xsl:apply-templates select="ConcomitantMedicationAttribution"/> 
					<xsl:apply-templates select="CourseAgentAttribution"/>
					<xsl:apply-templates select="OtherCauseAttribution"/>
				</attributions_for_ae>
	
			</adverse_event_ctc>	
			</xsl:for-each>	
		</adverse_events_ctc>
		<lab_results>
			<xsl:apply-templates select="Lab"/>
		</lab_results>
		
		<additional_info>
			<!-- loop -->
			<additional_info_name>##</additional_info_name>		
		</additional_info>
				
	</xsl:template>

	<xsl:template match="Physician">
		<physician_information>
			<first_name>
				<xsl:value-of select="firstName"/> 
			</first_name>
			<last_name>
				<xsl:value-of select="lastName"/> 
			</last_name>
		</physician_information>		
	</xsl:template>

	<xsl:template match="Reporter">
		<reporter_information>
			<first_name>
				<xsl:value-of select="firstName"/> 
			</first_name>
			<last_name>
				<xsl:value-of select="lastName"/> 
			</last_name>
		</reporter_information>		
	</xsl:template>
	
	<xsl:template match="ConcomitantMedication"> 
		<concomitant_medication>
			<concomitant_medication_name>
				<xsl:apply-templates select="Agent/name"/>  <!-- verify--> 
			</concomitant_medication_name>
			<concomitant_medication_reported_by>
				<xsl:apply-templates select="Agent/description"/>  <!-- verify--> 
			</concomitant_medication_reported_by>
		</concomitant_medication>		
	</xsl:template>
	<xsl:template match="StudyParticipantAssignment"> 
		<protocol_information>
			<nci_protocol_number>
				<xsl:apply-templates select="StudySite/Study/shortTitle"/><!-- verify--> 
			</nci_protocol_number>
			<protocol_title>
				<xsl:apply-templates select="StudySite/Study/longTitle"/> 
			</protocol_title>
			<protocol_phase>
				<xsl:apply-templates select="StudySite/Study/phaseCode"/> 
			</protocol_phase>
			<protocol_status>
				<xsl:apply-templates select="StudySite/Study/status"/> 
			</protocol_status>
			<type_of_study>
				<xsl:apply-templates select="StudySite/Study/shortTitle"/> <!-- verify-->
			</type_of_study>
			<lead_institution>
				<xsl:apply-templates select="StudySite/Study/primarySponsorCode"/> 
			</lead_institution>
			<principal_investigator>
				<xsl:apply-templates select="StudySite/Study/shortTitle"/> <!-- verify-->
			</principal_investigator>
			<lead_drug_monitor>
				<xsl:apply-templates select="StudySite/Study/monitorCode"/> 
			</lead_drug_monitor>
			<ctcae_version>
				<xsl:apply-templates select="StudySite/Study/shortTitle"/> <!-- verify-->
			</ctcae_version>			
		</protocol_information>		
	</xsl:template>
	<xsl:template match="AdverseEventPriorTherapy"> 
		<prior_therapy>
			<therapy_name>
				<xsl:apply-templates select="PriorTherapy/meddraTerm"/><!-- verify--> 
			</therapy_name>
			<therapy_start_date>
				<xsl:apply-templates select="startDate"/> 
			</therapy_start_date>
			<therapy_end_date>
				<xsl:apply-templates select="endDate"/> 
			</therapy_end_date>
			<therapy_comments>
				<xsl:apply-templates select="other"/> <!--verify-->
			</therapy_comments>
		</prior_therapy>		
	</xsl:template>		

	
	<xsl:template match="ConcomitantMedicationAttribution"> 
		<attribution_for_ae>
			<type_of_cause>Concomitant Medication</type_of_cause>
			<cause_name>
				<xsl:apply-templates select="ConcomitantMedication/Agent/description"/> 
			</cause_name>
			<cause_added_by>
				<xsl:apply-templates select="ConcomitantMedication/Agent/name"/> 
			</cause_added_by>
			<attribution>
				<xsl:apply-templates select="attribution"/> 
			</attribution>
			<attribution_added_by>
				<xsl:apply-templates select="ConcomitantMedication/Agent/name"/>
			</attribution_added_by>
		</attribution_for_ae>		
	</xsl:template>		

	<xsl:template match="CourseAgentAttribution"> 
		<attribution_for_ae>
			<type_of_cause>Course Agent</type_of_cause>
			<cause_name>
				<xsl:apply-templates select="CourseAgent/durationAndSchedule"/> 
			</cause_name>
			<cause_added_by>
				<xsl:apply-templates select="CourseAgent/durationAndSchedule"/> 
			</cause_added_by>
			<attribution>
				<xsl:apply-templates select="attribution"/> 
			</attribution>
			<attribution_added_by>
				<xsl:apply-templates select="CourseAgent/durationAndSchedule"/>
			</attribution_added_by>
		</attribution_for_ae>		
	</xsl:template>

	<xsl:template match="OtherCauseAttribution"> 
		<attribution_for_ae>
			<type_of_cause>Other</type_of_cause>
			<cause_name>
				<xsl:apply-templates select="OtherCause/text"/> 
			</cause_name>
			<cause_added_by>
				<xsl:apply-templates select="OtherCause/text"/> 
			</cause_added_by>
			<attribution>
				<xsl:apply-templates select="attribution"/> 
			</attribution>
			<attribution_added_by>
				<xsl:apply-templates select="OtherCause/text"/>
			</attribution_added_by>
		</attribution_for_ae>		
	</xsl:template>

	<xsl:template match="Lab"> 
		<lab_result>
			<lab_category>##</lab_category>
			<lab>
				<xsl:apply-templates select="name"/> 
			</lab>
			<microbiology_site>##</microbiology_site>
			<microbiology_date>##</microbiology_date>
			<infectious_agent>##</infectious_agent>
			<other_lab>##</other_lab>
			<baseline_date>
				<xsl:apply-templates select="baseline/date"/> 
			</baseline_date>
			<baseline_value>
				<xsl:apply-templates select="baseline/value"/> 
			</baseline_value>
			<baseline_uom>
				<xsl:apply-templates select="units"/> 
			</baseline_uom>
			<recovery_latest_date>
				<xsl:apply-templates select="recovery/date"/> 
			</recovery_latest_date>
			<recovery_latest_value>
				<xsl:apply-templates select="recovery/value"/> 
			</recovery_latest_value>
			<recovery_latest_uom>
				<xsl:apply-templates select="units"/> 
			</recovery_latest_uom>
			<worst_date>
				<xsl:apply-templates select="nadir/date"/> 
			</worst_date>
			<worst_value>
				<xsl:apply-templates select="nadir/value"/> 
			</worst_value>
			<worst_uom>
				<xsl:apply-templates select="units"/> 
			</worst_uom>
		</lab_result>		
	</xsl:template>
					
</xsl:stylesheet>