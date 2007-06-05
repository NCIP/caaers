<?xml version="1.0"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
	
	<xsl:output method="xml" indent="yes"/> 
	<xsl:template match="/"> 
 		<ae_report> 
  			<xsl:apply-templates/>
  		</ae_report>
	</xsl:template>
	
	<xsl:template match="AdverseEventReport">
		<report_created_date>
			<xsl:apply-templates select="detectionDate"/> 
		</report_created_date>	
		<xsl:apply-templates select="Physician"/> 	
		<xsl:apply-templates select="Reporter"/>		
		<concomitant_medications>
			<xsl:apply-templates select="ConcomitantMedication"/> 	
		</concomitant_medications>
		<xsl:apply-templates select="StudyParticipantAssignment"/>
		<prior_therapies>
			<xsl:apply-templates select="AdverseEventPriorTherapy"/>
		</prior_therapies>
		<adverse_events_ctc>
			<xsl:apply-templates select="AdverseEvent"/>
		</adverse_events_ctc>
		<lab_results>
			<xsl:apply-templates select="Lab"/>
		</lab_results>
	</xsl:template>

	<xsl:template match="Physician">
		<physician_information>
			<first_name>
				<xsl:apply-templates select="firstName"/> 
			</first_name>
			<last_name>
				<xsl:apply-templates select="lastName"/> 
			</last_name>
		</physician_information>		
	</xsl:template>

	<xsl:template match="Reporter">
		<reporter_information>
			<first_name>
				<xsl:apply-templates select="firstName"/> 
			</first_name>
			<last_name>
				<xsl:apply-templates select="lastName"/> 
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
	<xsl:template match="AdverseEvent"> 
		<adverse_event_ctc>
			<category>
				sample
			</category>
			<adverse_event>
				sample
			</adverse_event>
			<meddra_code>
				sample
			</meddra_code>
			<grade>
				<xsl:apply-templates select="grade"/> 
			</grade>
			<ae_term>
				<xsl:apply-templates select="CtcTerm/term"/> <!--verify-->
			</ae_term> 
		
			<select_ae>
				sample
			</select_ae>
		
			<latest_ctcae_version_category>
				<xsl:apply-templates select="CtcTerm/CtcCategory/name"/> <!--verify-->
			</latest_ctcae_version_category>
		
			<latest_ctcae_version_ae>
				sample
			</latest_ctcae_version_ae>
			<latest_ctcae_version_meddra_code>
				sample
			</latest_ctcae_version_meddra_code>
			<latest_ctcae_version_grade>
				sample
			</latest_ctcae_version_grade>
			<other_adverse_event>
				sample
			</other_adverse_event>

			<hospitalization>
				<xsl:apply-templates select="hospitalization"/> 
			</hospitalization>
			<ae_comments>
				<xsl:apply-templates select="comments"/>
			</ae_comments>
			
			<ae_reported_by>
				sample 
			</ae_reported_by>

			<attributions_for_ae>
				<xsl:apply-templates select="ConcomitantMedicationAttribution"/> 
				<xsl:apply-templates select="CourseAgentAttribution"/>
				<xsl:apply-templates select="OtherCauseAttribution"/>
			</attributions_for_ae>

		</adverse_event_ctc>		
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
			<lab_category>Sample</lab_category>
			<lab>
				<xsl:apply-templates select="name"/> 
			</lab>
			<microbiology_site>Sample</microbiology_site>
			<microbiology_date>Sample</microbiology_date>
			<infectious_agent>Sample</infectious_agent>
			<other_lab>Sample</other_lab>
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