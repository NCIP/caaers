/*
	Copyright SemanticBits, Northwestern University and Akaza Research
  
	Distributed under the OSI-approved BSD 3-Clause License.
	See http://ncip.github.com/caaers/LICENSE.txt for details.
*/

/*
This sql script file will pull all the information related one specific SAEReport based on its external id multiple csv files, one for each section of the report

How to use:
1) 'cd' to PostgreSql bin folder
	Example:
	cd /usr/local/postgresql/bin
2) Create the output dir
	Example:
	mkdir /tmp/output/
3) Edit this sql script file and replace in all the paths, the value '/tmp/output' with your output dir path
4) Save and exit from this script file
5) Run the following command 
	Pattern:
	psql -h <<dn_server_name>> -p <<db_server_port>> -d <<db_name>> -U <<db_user_name>> -v rep_ext_id='<<SAEReport_external_id>>' -f "<<path_to_this_sql_script_file>>"
	Example:
	/usr/local/postgresql/bin>psql -h localhost -p 5432 -d caaers_duncan -U postgres -e -v rep_ext_id='0625-77' -f "/tmp/ExtractExpeditedReportInfo.sql"

*/

-- Additional Information.
COPY (select * from additional_information where report_id in  ( select id as report_id from ae_reports where external_id = :rep_ext_id )) TO '/tmp/output/additional_information.csv' (format csv, delimiter ',', header true, FORCE_QUOTE *);  

-- Retrieve all the Adverse Events for Report.
COPY (select * from adverse_events where retired_indicator = 'f' and report_id in  ( select id as report_id from ae_reports where external_id = :rep_ext_id )) TO '/tmp/output/adverse_events.csv' (format csv, delimiter ',', header true, FORCE_QUOTE *);  

-- Retrieve the agent details.
COPY (select agents.name, agents.description,course_agents.dose_amount, course_agents.dose_units, course_agents.dose_route, course_agents.total_dose_this_course, course_agents.duration_and_schedule, course_agents.last_administered_date, course_agents.modified_dose_amount, course_agents.modified_dose_units, course_agents.modified_dose_route, course_agents.comments, course_agents.lot_number, course_agents.formulation
from course_agents
inner join treatments on ( course_agents.treatment_id = treatments.id )
inner join study_agents on ( course_agents.study_agent_id = study_agents.id )
inner join agents on ( study_agents.agent_id = agents.id)
where treatments.report_id in  ( select id as report_id from ae_reports where external_id = :rep_ext_id )) TO '/tmp/output/course_agents.csv' (format csv, delimiter ',', header true, FORCE_QUOTE *); 
 
-- Retrieve all the fields for Behavioral Interventions.
COPY (select other_interventions.name,ae_behavioral_interventions.description  from ae_behavioral_interventions 
inner join other_interventions on ( ae_behavioral_interventions.study_intervention_id = other_interventions.id)
where report_id in  ( select id as report_id from ae_reports where external_id = :rep_ext_id )) TO '/tmp/output/behavioral_interventions.csv' (format csv, delimiter ',', header true, FORCE_QUOTE *); 

-- Retrieve all the fields for Biological Interventions.
COPY (select other_interventions.name,ae_biological_interventions.description  from ae_biological_interventions 
inner join other_interventions on ( ae_biological_interventions.study_intervention_id = other_interventions.id)
where report_id in  ( select id as report_id from ae_reports where external_id = :rep_ext_id )) TO '/tmp/output/biological_interventions.csv' (format csv, delimiter ',', header true, FORCE_QUOTE *); 

-- Retrieve all the fields for Behavioral Attributions.
COPY (select name, attribution_code  from adverse_events  inner join ae_attributions on (adverse_events.id = ae_attributions.adverse_event_id) 
inner join ae_cause_types on ( ae_attributions.cause_type = ae_cause_types.code)
inner join ae_behavioral_interventions on ( adverse_events.report_id =  ae_behavioral_interventions.report_id )
where ae_cause_types.code = 'HI' and adverse_events.report_id in  ( select id as report_id from ae_reports where external_id = :rep_ext_id )) TO '/tmp/output/BehavAttributions.csv' (format csv, delimiter ',', header true, FORCE_QUOTE *); 

-- Retrieve all the fields for Biological Attributions.
COPY (select name, attribution_code  from adverse_events  inner join ae_attributions on (adverse_events.id = ae_attributions.adverse_event_id) 
inner join ae_cause_types on ( ae_attributions.cause_type = ae_cause_types.code)
inner join ae_biological_interventions on ( adverse_events.report_id =  ae_biological_interventions.report_id )
where  ae_cause_types.code = 'BI' and adverse_events.report_id in  ( select id as report_id from ae_reports where external_id = :rep_ext_id )) TO '/tmp/output/BioAttributions.csv' (format csv, delimiter ',', header true, FORCE_QUOTE *); 
 
-- Retrieve all the fields for Concomittant Medication Attributions. 
 COPY (select distinct agent_name, attribution_code  from adverse_events  inner join ae_attributions on (adverse_events.id = ae_attributions.adverse_event_id) 
inner join ae_cause_types on ( ae_attributions.cause_type = ae_cause_types.code)
inner join concomitant_medications on ( adverse_events.report_id =  concomitant_medications.report_id )
where   ae_cause_types.code = 'CM' and adverse_events.report_id in  ( select id as report_id from ae_reports where external_id = :rep_ext_id )) TO '/tmp/output/CMAttributions.csv' (format csv, delimiter ',', header true, FORCE_QUOTE *); 

-- Dietary Attributions
COPY (select name, attribution_code  from adverse_events  
inner join ae_attributions on (adverse_events.id = ae_attributions.adverse_event_id) 
inner join ae_cause_types on ( ae_attributions.cause_type = ae_cause_types.code)
inner join ae_dietary_interventions on ( adverse_events.report_id =  ae_dietary_interventions.report_id )
where ae_cause_types.code = 'DI' and adverse_events.report_id in  ( select id as report_id from ae_reports where external_id = :rep_ext_id )) TO '/tmp/output/DIAttributions.csv' (format csv, delimiter ',', header true, FORCE_QUOTE *); 

--Genetic Attributions.
COPY (select name, attribution_code  from adverse_events  inner join ae_attributions on (adverse_events.id = ae_attributions.adverse_event_id) 
inner join ae_cause_types on ( ae_attributions.cause_type = ae_cause_types.code)
inner join ae_genetic_interventions on ( adverse_events.report_id =  ae_genetic_interventions.report_id )
where ae_cause_types.code = 'GI' and adverse_events.report_id in  ( select id as report_id from ae_reports where external_id = :rep_ext_id )) TO '/tmp/output/GIAttributions.csv' (format csv, delimiter ',', header true, FORCE_QUOTE *); 

-- Retrieve all Course Agent Attributions.
COPY (select agents.name, attribution_code  from adverse_events  inner join ae_attributions on (adverse_events.id = ae_attributions.adverse_event_id) 
inner join ae_cause_types on ( ae_attributions.cause_type = ae_cause_types.code)
inner join treatments on ( adverse_events.report_id = treatments.report_id)
inner join course_agents on ( course_agents.treatment_id = treatments.id)
inner join study_agents on ( course_agents.study_agent_id = study_agents.id )
inner join agents on ( study_agents.agent_id = agents.id)
where ae_cause_types.code = 'CA' and adverse_events.report_id in  ( select id as report_id from ae_reports where external_id = :rep_ext_id )) TO '/tmp/output/CAAttributions.csv' (format csv, delimiter ',', header true, FORCE_QUOTE *); 

-- Medical Device Attributions.
COPY (select name, attribution_code  from adverse_events  inner join ae_attributions on (adverse_events.id = ae_attributions.adverse_event_id) 
inner join ae_cause_types on ( ae_attributions.cause_type = ae_cause_types.code)
inner join ae_medical_devices on ( adverse_events.report_id =  ae_medical_devices.report_id )
where  ae_cause_types.code = 'DV' and adverse_events.report_id in  ( select id as report_id from ae_reports where external_id = :rep_ext_id )) TO '/tmp/output/DVAttributions.csv' (format csv, delimiter ',', header true, FORCE_QUOTE *); 

-- Radiation Intervention Attributions.
COPY (select name, attribution_code from adverse_events  inner join ae_attributions on (adverse_events.id = ae_attributions.adverse_event_id) 
inner join ae_cause_types on ( ae_attributions.cause_type = ae_cause_types.code)
inner join ae_radiation_interventions on ( adverse_events.report_id =  ae_radiation_interventions.report_id )
where ae_cause_types.code = 'RI' and  adverse_events.report_id in  ( select id as report_id from ae_reports where external_id = :rep_ext_id )) TO '/tmp/output/RIAttributions.csv' (format csv, delimiter ',', header true, FORCE_QUOTE *); 

-- Surgery Intervention Attributions.
COPY (select name, attribution_code from adverse_events  inner join ae_attributions on (adverse_events.id = ae_attributions.adverse_event_id) 
inner join ae_cause_types on ( ae_attributions.cause_type = ae_cause_types.code)
inner join ae_surgery_interventions on ( adverse_events.report_id =  ae_surgery_interventions.report_id )
where ae_cause_types.code = 'SI' and adverse_events.report_id in  ( select id as report_id from ae_reports where external_id = :rep_ext_id )) TO '/tmp/output/SIAttributions.csv' (format csv, delimiter ',', header true, FORCE_QUOTE *); 
 
-- Retrieve concomitant medications.
COPY (select agent_name,
  start_date_day,
  start_date_month,
  start_date_year,
  start_date_zone,
  end_date_day,
  end_date_month,
  end_date_year,
  still_taking_medications from concomitant_medications
where report_id in  ( select id as report_id from ae_reports where external_id = :rep_ext_id )) TO '/tmp/output/concomitant_medications.csv' (format csv, delimiter ',', header true, FORCE_QUOTE *); 

-- Retrieve all the fields for Course.
COPY (select  first_course_date,
  adverse_event_course_date,
  adverse_event_course_number,
  treatment_assignment_id,
  total_courses,
  treatment_description,
  inv_agent_adminstrd,
  adverse_event_course_dcode, 
  code, 
  description, 
  retired_indicator from treatments join treatment_assignment on ( treatments.treatment_assignment_id = treatment_assignment.id ) 
where treatments.report_id in  ( select id as report_id from ae_reports where external_id = :rep_ext_id )) TO '/tmp/output/course.csv' (format csv, delimiter ',', header true, FORCE_QUOTE *); 

--Retrieve all the fields for Dietary Interventions.
COPY (select other_interventions.name,ae_dietary_interventions.description from ae_dietary_interventions 
inner join other_interventions on ( ae_dietary_interventions.study_intervention_id = other_interventions.id)
where report_id in  ( select id as report_id from ae_reports where external_id = :rep_ext_id )) TO '/tmp/output/Dietary.csv' (format csv, delimiter ',', header true, FORCE_QUOTE *); 

-- Retrive disease histories.
COPY (select name, category, term_type, retired_indicator,diagnosis_day,diagnosis_month,diagnosis_year,other_primary_disease,other_primary_disease_site from disease_histories inner join anatomic_sites on (disease_histories.coded_primary_disease_site_id  = anatomic_sites.id )
inner join study_diseases on (disease_histories.study_disease_id = study_diseases.id)
where report_id in  ( select id as report_id from ae_reports where external_id = :rep_ext_id )) TO '/tmp/output/DH.csv' (format csv, delimiter ',', header true, FORCE_QUOTE *); 

 -- Retrieve all the fields for Genetic Interventions.
COPY (select other_interventions.name,ae_genetic_interventions.description from ae_genetic_interventions 
inner join other_interventions on ( ae_genetic_interventions.study_intervention_id = other_interventions.id)
where report_id in  ( select id as report_id from ae_reports where external_id = :rep_ext_id )) TO '/tmp/output/GI.csv' (format csv, delimiter ',', header true, FORCE_QUOTE *); 

-- Retrieve all the fields for Labs
COPY (select baseline_date, baseline_value, nadir_date, nadir_value, recovery_date, recovery_value, other, lab_term_id , site, lab_date, infectious_agent, baseline_zone, recovery_zone, nadir_zone, normal_range from ae_labs where report_id in  ( select id as report_id from ae_reports where external_id = :rep_ext_id )) TO '/tmp/output/Labs.csv' (format csv, delimiter ',', header true, FORCE_QUOTE *); 

-- Retrieve all the fields for Medical Devices.
COPY (select brand_name,  common_name, device_type, manufacturer_name, manufacturer_city, manufacturer_state, model_number, lot_number,  catalog_number, expiration_date, serial_number, other_number, device_operator_code, other_device_operator, implanted_date, explanted_date, device_reprocessed_code, reprocessor_name, reprocessor_address , evaluation_availability_code ,returned_date 
from ae_medical_devices where report_id in  ( select id as report_id from ae_reports where external_id = :rep_ext_id )) TO '/tmp/output/MedicalDevice.csv' (format csv, delimiter ',', header true, FORCE_QUOTE *); 


-- Retrieve meta static site details.
COPY (select name, category, diagnosis_day,diagnosis_month,diagnosis_year from metastatic_disease_sites 
inner join disease_histories on (disease_histories.id = metastatic_disease_sites.disease_history_id)
inner join anatomic_sites on ( anatomic_sites.id = metastatic_disease_sites.coded_site_id )
where report_id in  ( select id as report_id from ae_reports where external_id = :rep_ext_id )) TO '/tmp/output/MetastaticDisease.csv' (format csv, delimiter ',', header true, FORCE_QUOTE *); 

-- Other causes.
COPY (select cause_text from other_causes where report_id in  ( select id as report_id from ae_reports where external_id = :rep_ext_id )) TO '/tmp/output/Cause.csv' (format csv, delimiter ',', header true, FORCE_QUOTE *); 


-- Retrieve participant history.
COPY (select * from participant_histories where report_id in  ( select id as report_id from ae_reports where external_id = :rep_ext_id )) TO '/tmp/output/ParticipantHistory.csv' (format csv, delimiter ',', header true, FORCE_QUOTE *); 

--  Retrieve pre existing conditions.
COPY ( select  condition_text,
  meddra_llt_code,
  meddra_llt,
  meddra_hlgt,
  retired_indicator  from ae_pre_existing_conds 
inner join pre_existing_conditions on( ae_pre_existing_conds.pre_existing_condition_id = pre_existing_conditions.id )
where report_id in  ( select id as report_id from ae_reports where external_id = :rep_ext_id )) TO '/tmp/output/preexistingCond.csv' (format csv, delimiter ',', header true, FORCE_QUOTE *); 

-- Retrieve all the fields for Prior Therapies.
COPY (select therapy_text,start_date_day, start_date_month, start_date_month,end_date_day, end_date_day,end_date_year,therapy_type, meddra_code, meddra_term from ae_prior_therapies 
inner join prior_therapies on (prior_therapies.id = ae_prior_therapies.prior_therapy_id)
where report_id in  ( select id as report_id from ae_reports where external_id = :rep_ext_id )) TO '/tmp/output/PT.csv' (format csv, delimiter ',', header true, FORCE_QUOTE *); 


-- Retrieve all the fields for Radiation Interventions.
COPY (select radiation_administration_code,
   dosage,dosage_unit, 
  last_treatment_date,
  fraction_number,
  days_elapsed,
  adjustment,
  name,
description from ae_radiation_interventions
inner join other_interventions on ( ae_radiation_interventions.study_intervention_id = other_interventions.id)
 where report_id in  ( select id as report_id from ae_reports where external_id = :rep_ext_id )) TO '/tmp/output/RI.csv' (format csv, delimiter ',', header true, FORCE_QUOTE *); 
 
 

 -- Retrieve all the fields for Report Description.
 COPY (select event_description,
  present_status_code,
  recovery_date,
  retreated,
  date_removed_from_protocol,
  blind_broken,
  study_drug_interrupted,
  reduced_dose,
  reduced_date,
  days_not_given,
  autopsy_performed,
  cause_of_death,
  primary_treatment,
  treatment_time_hour,
  treatment_time_minute,
  treatment_time_zone,
  event_abate,
  event_reappear 
from ae_report_descriptions where report_id in  ( select id as report_id from ae_reports where external_id = :rep_ext_id )) TO '/tmp/output/RPD.csv' (format csv, delimiter ',', header true, FORCE_QUOTE *); 


-- Retrieve Reporter section information with contact mechanisms. The Role 'P' for Physician and 'R' for Reporter.
COPY (select ae_report_people.first_name, ae_report_people.middle_name,ae_report_people.last_name, ae_report_people.role,contact_mechanisms.type, contact_mechanisms.value  from ae_report_people 
inner join contact_mechanisms on ( ae_report_people.id = contact_mechanisms.person_id)
where report_id in  ( select id as report_id from ae_reports where external_id = :rep_ext_id )) TO '/tmp/output/Reporter.csv' (format csv, delimiter ',', header true, FORCE_QUOTE *); 

-- Reporting period , study, participant details.
COPY (select studies.short_title, participant_assignments.study_subject_identifier,participants.first_name,participants.last_name,ae_reporting_periods.cycle_number, ae_reporting_periods.start_date, ae_reporting_periods.end_date, ae_reporting_periods.external_id, treatment_assignment.code, treatment_assignment.description 
from ae_reporting_periods inner join ae_reports on ( ae_reports.reporting_period_id = ae_reporting_periods.id ) 
inner join treatment_assignment on (ae_reporting_periods.treatment_assignment_id = treatment_assignment.id )
inner join studies on ( treatment_assignment.study_id = studies.id )
inner join participant_assignments on (ae_reporting_periods.assignment_id = participant_assignments.id)
inner join participants on ( participant_assignments.participant_id = participants.id )
where ae_reports.id  in  ( select id as report_id from ae_reports where external_id = :rep_ext_id )) TO '/tmp/output/ReportingPeriod.csv' (format csv, delimiter ',', header true, FORCE_QUOTE *); 
 
 --Retrieve all the fields for Surgery Interventions.
COPY (select other_interventions.name,ae_surgery_interventions.description from ae_surgery_interventions 
inner join other_interventions on (ae_surgery_interventions.study_intervention_id = other_interventions.id)
inner join intervention_sites on (intervention_sites.id = ae_surgery_interventions.intervention_site_id)
where report_id in  ( select id as report_id from ae_reports where external_id = :rep_ext_id )) TO '/tmp/output/Surgery.csv' (format csv, delimiter ',', header true, FORCE_QUOTE *); 
