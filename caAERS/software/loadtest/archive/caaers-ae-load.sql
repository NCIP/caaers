/*
This script loads test AE data for one study
Assumptions:
# All CTEP-ESYS studies are imported into caAERS
# NCI organizations are imported into caAERS
# 40 of the NCI organizations are already added as study sites to the study
# 5 participants are registered for each of the 40 study sites
# Study subject identifiers for each of the participant are expected to be of the format {studyid}.{siteid}.[0-4]
What it does:
# Creates 10 treatment assignments for the study with the TAC code in the format 'LTI-TAC[1-10]'
# Creates 10 reporting periods for each study, one for each treatment assignment of the study
# Creates 20 AEs for each reporting period
Input:
- @studyid value will take the study ctep-esys identifier value
Defaults:
- @sites will get the db id of the 40 NCI organizations from the list of the 40 NCI institute codes
- study participant assignment id will be fetched using the constructed study subject identifier 
*/
declare @studyid, @studyidR, @sites, @siteid;
declare @trtassid;
declare @ssidpfx, @ssidR, @spaid;
declare @seqidR, @aerpiid, @aerpiid, @adveid, @adveiid, @aetermid, @aeocid, @aeid, @aevid;
declare @extidpfx;
declare @j;
--CTEP-ESYS identifier
set @studyid='8258';
set @studyidR=select stu_id from identifiers where system_name ='CTEP-ESYS' and value='@studyid';
set @studyid=@studyidR[0][0];
--adding to 40 sites controlled by @j
set @sites =  select id, nci_institute_code from organizations where nci_institute_code in ('12001','15001','15006','17001','19004','19006','21002','25015','25022','25039','25042','25051','25077','APM','APP','AQB','AR001','AR028','AR047','ARC','ARQ','CA139','CA143','CA144','CA145','CA154','CA161','CA162','CA172','CA185','CA202','CA220','CA230','CA232','CA238','GA045','GA063','GA068','GA076','HBV') order by nci_institute_code;
set @j=0;
begin transaction;
set @seqidR=SELECT pg_catalog.setval('treatment_assignment_id_seq', nextval('treatment_assignment_id_seq')+9, true),
pg_catalog.setval('audit_events_id_seq', nextval('audit_events_id_seq')+9, true),
pg_catalog.setval('audit_event_values_id_seq', nextval('audit_event_values_id_seq')+39, true);
set @trtassid = @seqidR[0][0] - 10;
set @aeid = @seqidR[0][1] -10;
set @aevid=@seqidR[0][2] -40;
insert into treatment_assignment (SELECT @trtassid+id, code, @studyid, dose_level_order, description, comments, "version", uuid_generate_v4(), retired_indicator, ctep_db_identifier FROM tmp_tacs order by id); 
insert into audit_events (SELECT @aeid+id, ip_address, user_name, "time", class_name, @trtassid+object_id, operation, url, "version" FROM tmp_tac_audit_events order by id);
insert into audit_event_values (SELECT @aevid+id, @aeid+audit_event_id, attribute_name, previous_value, new_value, "version" FROM tmp_tac_audit_event_values where attribute_name not in ('gridId', 'study') order by id);
insert into audit_event_values (SELECT @aevid+ttaev.id, @aeid+ttaev.audit_event_id, ttaev.attribute_name, ttaev.previous_value, o.grid_id, ttaev."version" FROM tmp_tac_audit_event_values ttaev, audit_events ae, treatment_assignment o where attribute_name='gridId' and ae.id=ttaev.audit_event_id+@aeid and o.id=ae.object_id order by ttaev.id);
insert into audit_event_values (SELECT @aevid+id, @aeid+audit_event_id, attribute_name, previous_value, '@studyid', "version" FROM tmp_tac_audit_event_values where attribute_name='study' order by id);
end transaction;
while @j<40
begin
set @siteid=@sites[@j][0];
begin transaction;
set @ssidpfx=cast(@studyid as string) + '.' + cast(@siteid as string)+'%';
set @ssidR = select id from participant_assignments where study_subject_identifier like '@ssidpfx' order by id asc limit 1;
set @spaid=@ssidR[0][0] -1;
set @seqidR = SELECT pg_catalog.setval('ae_reporting_periods_id_seq', nextval('ae_reporting_periods_id_seq')+49, true),
pg_catalog.setval('reportingperiod_index_id_seq', nextval('reportingperiod_index_id_seq')+49, true), 
pg_catalog.setval('adverse_events_id_seq', nextval('adverse_events_id_seq')+999, true),
pg_catalog.setval('adverseevent_index_id_seq', nextval('adverseevent_index_id_seq')+999, true),
pg_catalog.setval('ae_terms_id_seq', nextval('ae_terms_id_seq')+999, true),
pg_catalog.setval('outcomes_id_seq', nextval('outcomes_id_seq')+999, true),
pg_catalog.setval('audit_events_id_seq', nextval('audit_events_id_seq')+2999, true),
pg_catalog.setval('audit_event_values_id_seq', nextval('audit_event_values_id_seq')+19999, true);
set @aerpid = @seqidR[0][0] - 50;
set @aerpiid = @seqidR[0][1] -50;
set @adveid=@seqidR[0][2] -1000;
set @adveiid=@seqidR[0][3] -1000;
set @aetermid=@seqidR[0][4] -1000;
set @aeocid=@seqidR[0][5] -1000;
set @aeid=@seqidR[0][6] -3000;
set @aevid=@seqidR[0][7] -20000;
set @extidpfx=cast(@studyid as string) + '.' + cast(@siteid as string)+'.';
insert into ae_reporting_periods (SELECT @aerpid+id, "version", @spaid+assignment_id, start_date, end_date, description, cycle_number, uuid_generate_v4(), @trtassid+treatment_assignment_id, epoch_id, workflow_id, review_status_code, treatment_assignment_desc, retired_indicator, '@extidpfx'||external_id, old_ae_mapping FROM tmp_aerp order by id);
insert into reportingperiod_index(SELECT @aerpiid+id, "version", login_id, @aerpid+reportingperiod_id, uuid_generate_v4(), r_101, r_102, r_103, r_104, r_105, r_106, r_107, r_108, r_109, r_110, r_111, r_112, r_113, r_114, r_115, r_116, r_117, r_118, r_119, r_120, r_121, r_122, r_123, r_7942, r_7943 FROM tmp_rp_index order by id);
insert into adverse_events (SELECT @adveid+id, "version", details_for_other, grade_code, expected, hospitalization_code, comments, report_id, uuid_generate_v4(), list_index, attribution_summary_code, routine_report_id, routine_list_index, start_date, end_date, low_level_term_id, solicited, @aerpid+reporting_period_id, requires_reporting, event_time_hour, event_time_minute, event_time_zone, event_location, graded_date, signature, reported, retired_indicator, post_submission_updated_date, participant_at_risk, '@extidpfx'||external_id, other_specify FROM tmp_aes order by id);
insert into adverseevent_index (SELECT @adveiid+id, "version", login_id, @adveid+adverseevent_id, uuid_generate_v4(), r_101, r_102, r_103, r_104, r_105, r_106, r_107, r_108, r_109, r_110, r_111, r_112, r_113, r_114, r_115, r_116, r_117, r_118, r_119, r_120, r_121, r_122, r_123, r_7942, r_7943 FROM tmp_ae_index order by id);
insert into ae_terms (SELECT @aetermid+id, @adveid+adverse_event_id, term_id, term_type, "version", uuid_generate_v4() FROM tmp_ae_terms order by id);
insert into outcomes (SELECT @aeocid+id, incident_date, outcome_type_code, other, "version", uuid_generate_v4(), report_id, list_index, @adveid+adverse_event_id FROM tmp_outcomes order by id);
insert into audit_events (SELECT @aeid+id, ip_address, user_name, "time", class_name, @aetermid+object_id, operation, url, "version" FROM tmp_aeterm_audit_events order by id);
insert into audit_events (SELECT @aeid+id, ip_address, user_name, "time", class_name, @aeocid+object_id, operation, url, "version" FROM tmp_outcome_audit_events order by id);
insert into audit_events (SELECT @aeid+id, ip_address, user_name, "time", class_name, @adveid+object_id, operation, url, "version" FROM tmp_ae_audit_events order by id);
insert into audit_event_values (SELECT @aevid+id, @aeid+audit_event_id, attribute_name, previous_value, new_value, "version" FROM tmp_outcome_aev where attribute_name not in ('gridId') order by id);
insert into audit_event_values (SELECT @aevid+id, @aeid+audit_event_id, attribute_name, previous_value, new_value, "version" FROM tmp_aeterm_aev where attribute_name='term' order by id);
insert into audit_event_values (SELECT @aevid+id, @aeid+audit_event_id, attribute_name, previous_value, new_value, "version" FROM tmp_ae_aev where attribute_name not in ('gridId','adverseEventTerm','reportingPeriod') order by id);
insert into audit_event_values (SELECT @aevid+aev.id, @aeid+aev.audit_event_id, aev.attribute_name, aev.previous_value, o.grid_id, aev."version" FROM tmp_outcome_aev aev, audit_events ae, outcomes o where attribute_name='gridId' and ae.id=aev.audit_event_id+@aeid and o.id=ae.object_id order by aev.id);
insert into audit_event_values (SELECT @aevid+aev.id, @aeid+aev.audit_event_id, aev.attribute_name, aev.previous_value, o.grid_id, aev."version" FROM tmp_aeterm_aev aev, audit_events ae, ae_terms o where attribute_name='gridId' and ae.id=aev.audit_event_id+@aeid and o.id=ae.object_id order by aev.id);
insert into audit_event_values (SELECT @aevid+aev.id, @aeid+aev.audit_event_id, aev.attribute_name, aev.previous_value, o.grid_id, aev."version" FROM tmp_ae_aev aev, audit_events ae, adverse_events o where attribute_name='gridId' and ae.id=aev.audit_event_id+@aeid and o.id=ae.object_id order by aev.id);
insert into audit_event_values (SELECT @aevid+id, @aeid+audit_event_id, attribute_name, previous_value, cast(new_value as integer)+@adveid, "version" FROM tmp_aeterm_aev where attribute_name='adverseEvent' order by id);
insert into audit_event_values (SELECT @aevid+id, @aeid+audit_event_id, attribute_name, previous_value, cast(new_value as integer)+@aetermid, "version" FROM tmp_ae_aev where attribute_name='adverseEventTerm' order by id);
insert into audit_event_values (SELECT @aevid+id, @aeid+audit_event_id, attribute_name, previous_value, cast(new_value as integer)+@aerpid, "version" FROM tmp_ae_aev where attribute_name='reportingPeriod' order by id);
end transaction;
set @j=@j+1;
end