/*
This script loads test participant and registration data 
Assumptions:
# All CTEP-ESYS studies are imported into caAERS
# NCI organizations are imported into caAERS
# 40 of the NCI organizations have been picked and used in the script. The same list is used in the AE load script. They must match.
# 5 participants are registered for each of the 40 study sites
# Study subject identifiers for each of the participant will be constructed in the format {studyid}.{siteid}.[0-4]
# First name of the participant will be set in the format 'F'+participant index [0-4]+'P'+@studyid+'S'+@siteid
# Last name of the participant will be set in the format 'F'+participant index [0-4]+'P'+@studyid+'S'+@siteid
What it does:
# Adds the 40 NCI organizations as study sites to the study
# Creates 5 participants for each site using the study subject identifier in the format {studyid}.{siteid}.[0-4]
Input:
- @studyst indicates the index in the list of studies from which to start load test partcipants
- @studyend - 1 indicates the index of the study list upto which test data to be loaded
Defaults:
- @sites will get the db id of the 40 NCI organizations from the list of the 40 NCI institute codes
*/
declare @studies, @studyid, @sites, @siteid, @dobs, @sex;
declare @studyst, @studyend;
declare @aecnt, @aevcnt;
declare @aevidR, @aeidR, @iidR, @paidR, @piidR, @pidR, @soidR, @sswcidR;
declare @aevid, @aeid, @iid, @paid, @piid, @pid, @soid, @sswcid;
declare @uuidR, @iuuid, @pauuid, @puuid, @souuid, @sswcuuid1, @sswcuuid2;
declare @ssid, @sexindex,@sexstr;
declare @i, @j, @k,@kstr;
declare @doby,@dobm,@dobd;
declare @fstr, @lstr;
set @studyst=0;
set @studyend=50;
--CTEP-ESYS identifier
set @studies=select stu_id, value from identifiers where system_name ='CTEP-ESYS' and value in ('5987','6040','6304','6413','6528','6620','6837','7080','7251','7309','7354','7358','7416','7427','7435','7444','7458','7540','7606','7627','7678','7759','7832','7834','7870','7888','7916','7958','7967','7968','7977','7997','7998','8029','8045','8046','8052','8054','8064','8076','8131','8147','8155','8167','8192','8210','8215','8217','8226','8231','8238','8242','8249','8258','8264','8269','8272','8273','8274','8277','8281','8282','8283','8296','8297','8298','8305','8309','8311','8316','8317','8321','8323','8324','8329','8331','8333','8335','8340','8342','8348','8351','8354','8357','8406','8412','8414','8417','8418','8420','8444','8445','8446','8449','8455','8456','8457','8461','8463','8468','8472','8474','8476','8484','8485','8489','8490','8491','8506','8508','8511','8515','8516','8518','8539','8556','8597','8598','8601','8602','8603','8604','8609','8610','8614','8620','8627','8628','8632','8639','8692','8695','8698','8703','8709','8727','8728','8729','8731','8732','8735','8739','8740','8747','8752','8760','8761','8762','8783','8784','8786','8788','8792','8799','8803','8804','8808','8810','8811','8813','8814','8818','8821','8822','8826','8828','8832','8834','8836','8842','8844','8846','8850','8854','8856','8860','8865','8866','8867','8868','8871','8872','8873','8875','8876','8877','8880','8890','8945','8972','8976','8977','8980','8983','8984','8985','8986','8992','8993','9012','9022','9030','9031','9041','9068','9076','9091','9101','9111','9119','9127','9174','9177','9374','AALL0434','AALL04B1','AALL04B2','AALL0631','AALL06B1','AALL06N1','AALL07P1','AALL08B1','AALL08B2','AALL10B1','AALL10B2','AALL1131','AALL12B1','AALL12B2','AALL12B3','AALL12B4','AALL12B5','AAML05P1','AAML0631','AAML08B1','AAML09B2','AAML1031','AAML10B1','AAML10B11','AAML10B14','AAML10B16','AAML10B17','AAML10B18','AAML10B19','AAML10B2','AAML10B3','AAML10B4','AAML10B5','AAML10B7','AAML10B8','AAML10B9','AAML11B1','AAML11B10','AAML11B11','AAML11B12','AAML11B13','AAML11B2','AAML11B3','AAML11B4','AAML11B6','AAML11B7','AAML11B8','AAML11B9','AAML12B1','AAML12B2','AAML12B3','AAML12B4','AAML12B5','AAML12B8','ABTC-0906','ABTC-1002','ABTC-1101','ABTR01B1','ABTR02B1','ABTR04B1','ABTR12B3','ACCL0431','ACCL0933','ACCL0934','ACCL1031','ACCL1032','ACCL1033','ACCRN07','ACNS0332','ACNS0333','ACNS0334','ACNS0821','ACNS0822','ACNS0831','ACNS0927','ACNS1021','ACNS1022','ACNS11B1','ACOSOG-Z1071','ACOSOG-Z1072','ACOSOG-Z3081','ACOSOG-Z4099','ACOSOG-Z6051','ACOSOG-Z9081','ACRIN-6684','ACRIN-6685','ACRIN-6690','ACRIN-6691','ADVL0911','ADVL0912','ADVL0918','ADVL0921','ADVL1011','ADVL1013','ADVL1014','ADVL1112','ADVL1114','ADVL1115','ADVL1121','AEPI05N1','AEPI07N1','AEPI10N1','AEWS0331','AEWS07B1','AEWS08B1','AEWS1031','AEWS11B1','AEWS11B2','AHEP0731','AHOD04B1','AHOD11B1','AHOD12B1','ALTE03N1','ALTE05N1','ALTE07C1','AMC-053','AMC-058','AMC-063','AMC-064','AMC-070','AMC-072','AMC-075','AMC-076','AMC-078','AMC-079','ANBL0032','ANBL00B1','ANBL00P3','ANBL06B1','ANBL07B1','ANBL08B1','ANBL09B1','ANBL09P1','ANBL1021','ANBL10B1','ANBL11B1','ANBL11B2','ANBL11B3','ANBL11B4','ANBL12B2','ANBL12B4','ANBL12B5','ANBL12B6','ANBL12B7','ANHL04B1','ANHL11B1','ANUR1131','ANZGOG-0902-GOG-0274','AOST06B1','AOST08B1','AOST10B1','AOST10B2','AOST10B4','AOST10B5','AOST11B1','ARAR0332','ARAR12B1','ARAR12B2','AREN0321','AREN03B1','AREN03B2','AREN04B1','AREN0532','AREN0533','AREN0534','AREN09B1','AREN10B1','AREN10B2','AREN11B1','AREN11B2','AREN11B3','AREN12B1','AREN12B2','AREN12B3','AREN12B4','AREN12B5','AREN12B6','ARET0321','ARST0531','ARST08P1','ARST0921','ARST11B1','ARST11B2','ARST11B4','ARST11B5','ARST12B1','ARST12B2','ARST12B3','ARST12B4','ARST12B5','BCDM 8235','BMT-CTN 0701','BMTCTN-0401','BMTCTN-0702','CALGB-100701','CALGB-100801','CALGB-10403','CALGB-10404','CALGB-10701','CALGB-10801','CALGB-11001','CALGB-11002','CALGB-140202','CALGB-140503','CALGB-150201','CALGB-150603','CALGB-150607','CALGB-150705-ICSC','CALGB-150802','CALGB-150803','CALGB-150806','CALGB-150807','CALGB-150809','CALGB-150901','CALGB-150905','CALGB-151003','CALGB-151004','CALGB-151006','CALGB-151102','CALGB-151107','CALGB-151111','CALGB-159905','CALGB-20202','CALGB-20203','CALGB-20206','CALGB-20501','CALGB-20502','CALGB-20801','CALGB-21001','CALGB-30607','CALGB-30610','CALGB-30801','CALGB-30901','CALGB-31102','CALGB-40603','CALGB-40903','CALGB-50303','CALGB-50604','CALGB-50801','CALGB-50901','CALGB-50904','CALGB-70305','CALGB-70806','CALGB-70807','CALGB-80701','CALGB-80702','CALGB-80802','CALGB-80803','CALGB-8461','CALGB-90203','CALGB-90601','CALGB-90802','CALGB-9741A-ICSC','CALGB-9862','D9902','E1199B-E2197C-ICSC','E1201T1','E1208','E1305','E1496T1','E1609','E1808','E1906','E1908','E1A10','E2108','E2208','E2408','E2410','E2496T1','E2607','E2805T1','E2809','E2810','E2903','E2905','E2906','E2997T2','E2A08','E3108','E3200T2','E3205','E3503T1','E3508','E3805','E3903','E3A05','E3F05','E4A08','E5508','E6508','E7208','GLNE010','GOG-0076HH','GOG-0127W','GOG-0131H','GOG-0170Q','GOG-0186G','GOG-0186H','GOG-0186I','GOG-0186J','GOG-0187','GOG-0212','GOG-0213','GOG-0214','GOG-0221','GOG-0227G','GOG-0229K','GOG-0229L','GOG-0233','GOG-0235','GOG-0237','GOG-0238','GOG-0241','GOG-0242','GOG-0249','GOG-0250','GOG-0258','GOG-0259','GOG-0261','GOG-0262','GOG-0263','GOG-0264','GOG-0265','GOG-0267','GOG-0268','GOG-0270','GOG-0271','GOG-0273','GOG-0280','GOG-8005','GOG-8008','GOG-8009','GOG-8010','GOG-8011','GOG-8012','GOG-8015','GOG-8199','GOG-9923','GOG-9924','GOG-9925','GOG-9926','GOG-9927','MA.17ICSC','MDA-2007-0914A','MDA-2008-0005','MDA-2009-0288','MDA-CCC-01-06','N0477','N0543','N0572','N0574','N057K','N0849','N0871','N0872','N0874','N0877','N0879','N0923','N093B','N0949','N1048','N107C','N1085','N1087','N1088','N2008-02','NABTT-0703','NCCTG N9831-ICSC','NCCTG-94-72-53','NCCTG-N07C4','NCCTG-N08C1','NCCTG-N08C9','NCCTG-N08CB','NCCTG-N09C6','NCCTG-N10C1','NCCTG-N10C2','NCCTG-N9831(C)-NCCTG-ICSC','NCIC CTG OV.21','NCIC-MA.32','NSABP-B-39','NSABP-B-43','NSABP-B-47','NSABP-B-49','NSABP-B28-ICSC-A','NSABP-B31-ICSC-A','NSABP-C07-CS1','NSABP-DMP-1','NSABP-MC083I','NSABP-NCIC-CTG-MA.32.F','NSABP-P-5','PBTC-025B','PBTC-026','PBTC-029','PBTC-032','PBTC-033','PBTC-N11','Q9403','RTOG-0433','RTOG-0436','RTOG-0524','RTOG-0534','RTOG-0539','RTOG-0622','RTOG-0631','RTOG-0712','RTOG-0724','RTOG-0813','RTOG-0815','RTOG-0834','RTOG-0837','RTOG-0839','RTOG-0848','RTOG-0920','RTOG-0924','RTOG-0925','RTOG-0926','RTOG-0929','RTOG-0933','RTOG-0937','RTOG-0938','RTOG-1005','RTOG-1008','RTOG-1010','RTOG-1012','RTOG-1014','RTOG-1016','RTOG-1102','RTOG-1106','RTOG-1114','RTOG-1115','S0000D','S0221A-ICSC','S0337','S0518','S0535','S0702','S0703','S0709','S0711','S0713','S0800','S0805','S0806','S0809','S0812','S0816','S0820','S0905','S0910','S0925','S0927','S0931','S1001','S1008','S1011','S1013','S1014','S1105','S1106','S1108','S1117','S1200','S1201','S8814A-ICSC','S9031-S9126-S9333-S9500-B','S9313A-ICSC','S9916-S0111-S9510','SCUSF-0402','SCUSF-0502','SCUSF-0703','SCUSF-0803','SCUSF-0806','SCUSF-0901','SWOG-8897-ICSC','SWOG-S9313B-ICSC','URCC-0701','URCC-09005','URCC-10055','WFU-08-03-06','WFU-08-08-09','WFU-97609','WFU-99211') order by value;
--adding to 40 sites controlled by @j
set @sites =  select id, nci_institute_code from organizations where nci_institute_code in ('12001','15001','15006','17001','19004','19006','21002','25015','25022','25039','25042','25051','25077','APM','APP','AQB','AR001','AR028','AR047','ARC','ARQ','CA139','CA143','CA144','CA145','CA154','CA161','CA162','CA172','CA185','CA202','CA220','CA230','CA232','CA238','GA045','GA063','GA068','GA076','HBV') order by nci_institute_code;
set @dobs = '(1967,01,31) (1974,03,12) (1973,05,22) (1966,04,21) (1969,03,14)';
set @sex = '("Male") ("Female")';
set @sexindex=0;
set @studies = CAST (@studies AS RECORD);
set @dobs = CAST (@dobs AS RECORD);
set @sex = CAST (@sex AS RECORD);
set @i =@studyst;
while @i<@studyend	
begin
	set @studyid = @studies[@i][0];
	set @studyid=cast(@studyid as string);
	set @j =0;
	--per site
	while @j < 40
	begin
		set @siteid=@sites[@j][0];
		set @siteid=cast(@siteid as string);
		print cast(@i as string)+ ' study id = '+ @studyid + ' ' + cast(@j as string) + ' site id= ' + @siteid ;
		set @k =0;
		--per participants
                while @k<5
                begin 
			if @k<1
			begin
			set @aecnt=5;
			set @aevcnt=36;
			end
			else
			begin
			set @aecnt=2;
			set @aevcnt=23;
			end
--add aevcnt(24 0r 37)
set @aevidR = SELECT pg_catalog.setval('audit_event_values_id_seq', nextval('audit_event_values_id_seq')+@aevcnt, true);
print @aevidR[0][0];
set @aevid = @aevidR[0][0] - @aevcnt -1;
--add aecnt (2 or 5)
set @aeidR = SELECT pg_catalog.setval('audit_events_id_seq', nextval('audit_events_id_seq') + @aecnt, true);
set @aeid = @aeidR[0][0] - @aecnt -1;
--add 1
set @iidR = SELECT pg_catalog.setval('identifiers_id_seq', nextval('identifiers_id_seq'), true);
set @iid = @iidR[0][0] - 1;
--add 1
set @paidR = SELECT pg_catalog.setval('participant_assignments_id_seq', nextval('participant_assignments_id_seq'), true);
set @paid = @paidR[0][0] - 1;
--add 1
set @piidR = SELECT pg_catalog.setval('participant_index_id_seq', nextval('participant_index_id_seq'), true);
set @piid = @piidR[0][0] - 1;
--add 1
set @pidR = SELECT pg_catalog.setval('participants_id_seq', nextval('participants_id_seq'), true);
set @pid = @pidR[0][0] - 1;
--do this only for 1st participant
		if @k<1
		begin
		--add 1
		set @soidR = SELECT pg_catalog.setval('study_organizations_id_seq', nextval('study_organizations_id_seq'), true);
		set @soid = @soidR[0][0] - 1;
		--add 2
		set @sswcidR = SELECT pg_catalog.setval('study_site_wf_cfgs_id_seq', nextval('study_site_wf_cfgs_id_seq')+1, true);
		set @sswcid = @sswcidR[0][0] - 2;
		end
--get uuids
set @uuidR= select uuid_generate_v4(), uuid_generate_v4(), uuid_generate_v4(), uuid_generate_v4(), uuid_generate_v4(), uuid_generate_v4();
set @iuuid=@uuidR[0][0];
set @pauuid=@uuidR[0][1];
set @puuid=@uuidR[0][2];
set @souuid=@uuidR[0][3];
set @sswcuuid1=@uuidR[0][4];
set @sswcuuid2=@uuidR[0][5];
--other variables
set @kstr = cast(@k as string);
set @ssid=@studyid + '.' + @siteid + '.' + cast(@k as string);
set @sexindex = (@sexindex + 1) % 2 ;
set @sexstr=@sex[@sexindex];
set @doby=@dobs[@k][0];
set @dobm=@dobs[@k][1];
set @dobd=@dobs[@k][2];
set @fstr='F'+@kstr+'P'+@studyid+'S'+@siteid;
set @lstr='L'+@kstr+'P'+@studyid+'S'+@siteid;
begin transaction;
--increment id, suffix of F&L, change grid_id
INSERT INTO participants (id, instituitional_patient_number, institution, study_participant_name, first_name, last_name, gender, ethnicity, race, version, middle_name, maiden_name, grid_id, load_status, birth_year, birth_month, birth_day, birth_zone) VALUES (@pid+1, NULL, NULL, NULL, '@fstr', '@lstr', '@sexstr', 'Not Hispanic or Latino', 'White', 0, '', '', '@puuid', 1, @doby, @dobm, @dobd, 0);
--increment id, suffix of value, use participant id from above, change grid_id, change organization_id, 
INSERT INTO identifiers (id, value, type, system_name, participant_id, version, primary_indicator, stu_id, grid_id, organization_id, discriminator_column) VALUES (@iid+1, '@ssid', 'MRN', NULL, @pid+1, 0, true, NULL, '@iuuid', @siteid, 1);
--increment id, use participant id from above
INSERT INTO participant_index (id, version, login_id, participant_id, grid_id, r_101, r_102, r_103, r_104, r_105, r_106, r_107, r_108, r_109, r_110, r_111, r_112, r_113, r_114, r_115, r_116, r_117, r_118, r_119, r_120, r_121, r_122, r_123, r_7942, r_7943) VALUES (@piid+1, 0, 'SYSTEM', @pid+1, NULL, false, false, false, false, false, false, false, false, false, false, false, false, true, true, false, true, true, true, true, false, false, true, true, false, false);
		if @k<1
		begin
		--increment id, use organization id(change to get org_id for the NCIcode), use study_id for study_identifier, use study_identifier 
		INSERT INTO study_organizations (id, site_id, study_id, study_identifier, version, role_code, irb_approval_date, start_date, end_date, grid_id, type, retired_indicator) VALUES (@soid+1, @siteid, @studyid, NULL, 1, NULL, NULL, '2013-03-19', NULL, '@souuid', 'SST', false);
		--increemnt id, change grid_id, use study_organization_id from above
		INSERT INTO study_site_wf_cfgs (id, version, name, grid_id, study_organization_id, workflow_cfg_id) VALUES (@sswcid+1, 0, 'reportingPeriod', '@sswcuuid1', @soid+1, 1);
		--increemnt id, change grid_id, use study_organization_id from above
		INSERT INTO study_site_wf_cfgs (id, version, name, grid_id, study_organization_id, workflow_cfg_id) VALUES (@sswcid+2, 0, 'report', '@sswcuuid2', @soid+1, 2);
		end
--increment id, use study_site_id, use participant id from above, change grid_id, increment suffix of SSI
INSERT INTO participant_assignments (id, study_site_id, participant_id, date_of_enrollment, version, grid_id, load_status, study_subject_identifier, first_course_date, baseline_performance) VALUES (@paid+1, @soid+1, @pid+1, '2013-03-18', 0, '@pauuid', 1, '@ssid', NULL, NULL);
--increment id, use participant id from above
INSERT INTO audit_events (id, ip_address, user_name, "time", class_name, object_id, operation, url, version) VALUES (@aeid+1, '127.0.0.1', 'SYSTEM', '2013-03-18 21:44:35.172', 'gov.nih.nci.cabig.caaers.domain.Participant', @pid+1, 'CREATE', '/services', 0);
--increment id, use identifiers id from above
INSERT INTO audit_events (id, ip_address, user_name, "time", class_name, object_id, operation, url, version) VALUES (@aeid+2, '127.0.0.1', 'SYSTEM', '2013-03-18 21:44:35.172', 'gov.nih.nci.cabig.caaers.domain.OrganizationAssignedIdentifier', @iid+1, 'CREATE', '/services', 0);
--increment id, use participant_assignments id from above
INSERT INTO audit_events (id, ip_address, user_name, "time", class_name, object_id, operation, url, version) VALUES (@aeid+3, '127.0.0.1', 'SYSTEM', '2013-03-18 21:44:35.172', 'gov.nih.nci.cabig.caaers.domain.StudyParticipantAssignment', @paid+1, 'CREATE', '/services', 0);
		if @k<1
		begin
		--increment id, use study_organization_id from above
		INSERT INTO audit_events (id, ip_address, user_name, "time", class_name, object_id, operation, url, version) VALUES (@aeid+4, '127.0.0.1', 'SYSTEM', '2013-03-19 13:23:23.881', 'gov.nih.nci.cabig.caaers.domain.StudySite', @soid+1, 'CREATE', '/services', 0);
		--increment id, use study_site_wf_cfgs_id from above
		INSERT INTO audit_events (id, ip_address, user_name, "time", class_name, object_id, operation, url, version) VALUES (@aeid+5, '127.0.0.1', 'SYSTEM', '2013-03-19 13:23:23.881', 'gov.nih.nci.cabig.caaers.domain.workflow.StudySiteWorkflowConfig', @sswcid+1, 'CREATE', '/services', 0);
		--increment id, use study_site_wf_cfgs_id from above
		INSERT INTO audit_events (id, ip_address, user_name, "time", class_name, object_id, operation, url, version) VALUES (@aeid+6, '127.0.0.1', 'SYSTEM', '2013-03-19 13:23:23.881', 'gov.nih.nci.cabig.caaers.domain.workflow.StudySiteWorkflowConfig', @sswcid+2, 'CREATE', '/services', 0);
		end
--use all modified values from above, increment id, use audit_events id from above
--participant
INSERT INTO audit_event_values (id, audit_event_id, attribute_name, previous_value, new_value, version) VALUES (@aevid+1, @aeid+1, 'gridId', NULL, '@puuid', 0);
INSERT INTO audit_event_values (id, audit_event_id, attribute_name, previous_value, new_value, version) VALUES (@aevid+2, @aeid+1, 'dateOfBirth.day', NULL, '@dobd', 0);
INSERT INTO audit_event_values (id, audit_event_id, attribute_name, previous_value, new_value, version) VALUES (@aevid+3, @aeid+1, 'dateOfBirth.month', NULL, '@dobm', 0);
INSERT INTO audit_event_values (id, audit_event_id, attribute_name, previous_value, new_value, version) VALUES (@aevid+4, @aeid+1, 'dateOfBirth.year', NULL, '@doby', 0);
INSERT INTO audit_event_values (id, audit_event_id, attribute_name, previous_value, new_value, version) VALUES (@aevid+5, @aeid+1, 'dateOfBirth.zone', NULL, '0', 0);
INSERT INTO audit_event_values (id, audit_event_id, attribute_name, previous_value, new_value, version) VALUES (@aevid+6, @aeid+1, 'ethnicity', NULL, 'Not Hispanic or Latino', 0);
INSERT INTO audit_event_values (id, audit_event_id, attribute_name, previous_value, new_value, version) VALUES (@aevid+7, @aeid+1, 'firstName', NULL, 'fstr', 0);
INSERT INTO audit_event_values (id, audit_event_id, attribute_name, previous_value, new_value, version) VALUES (@aevid+8, @aeid+1, 'gender', NULL, '@sexstr', 0);
INSERT INTO audit_event_values (id, audit_event_id, attribute_name, previous_value, new_value, version) VALUES (@aevid+9, @aeid+1, 'lastName', NULL, '@lstr', 0);
INSERT INTO audit_event_values (id, audit_event_id, attribute_name, previous_value, new_value, version) VALUES (@aevid+10, @aeid+1, 'loadStatus', NULL, '1', 0);
INSERT INTO audit_event_values (id, audit_event_id, attribute_name, previous_value, new_value, version) VALUES (@aevid+11, @aeid+1, 'maidenName', NULL, '', 0);
INSERT INTO audit_event_values (id, audit_event_id, attribute_name, previous_value, new_value, version) VALUES (@aevid+12, @aeid+1, 'middleName', NULL, '', 0);
INSERT INTO audit_event_values (id, audit_event_id, attribute_name, previous_value, new_value, version) VALUES (@aevid+13, @aeid+1, 'race', NULL, 'White', 0);
--identifier
INSERT INTO audit_event_values (id, audit_event_id, attribute_name, previous_value, new_value, version) VALUES (@aevid+14, @aeid+2, 'gridId', NULL, '@iuuid', 0);
INSERT INTO audit_event_values (id, audit_event_id, attribute_name, previous_value, new_value, version) VALUES (@aevid+15, @aeid+2, 'primaryIndicator', NULL, 'true', 0);
INSERT INTO audit_event_values (id, audit_event_id, attribute_name, previous_value, new_value, version) VALUES (@aevid+16, @aeid+2, 'type', NULL, 'MRN', 0);
INSERT INTO audit_event_values (id, audit_event_id, attribute_name, previous_value, new_value, version) VALUES (@aevid+17, @aeid+2, 'value', NULL, '@ssid', 0);
INSERT INTO audit_event_values (id, audit_event_id, attribute_name, previous_value, new_value, version) VALUES (@aevid+18, @aeid+2, 'organization', NULL, '@siteid', 0);
--study participant assignment
INSERT INTO audit_event_values (id, audit_event_id, attribute_name, previous_value, new_value, version) VALUES (@aevid+19, @aeid+4, 'gridId', NULL, '@pauuid', 0);
INSERT INTO audit_event_values (id, audit_event_id, attribute_name, previous_value, new_value, version) VALUES (@aevid+20, @aeid+4, 'dateOfEnrollment', NULL, 'Mon Mar 18 21:44:35 EDT 2013', 0);
INSERT INTO audit_event_values (id, audit_event_id, attribute_name, previous_value, new_value, version) VALUES (@aevid+21, @aeid+4, 'loadStatus', NULL, '1', 0);
INSERT INTO audit_event_values (id, audit_event_id, attribute_name, previous_value, new_value, version) VALUES (@aevid+22, @aeid+4, 'participant', NULL, @pid+1, 0);
INSERT INTO audit_event_values (id, audit_event_id, attribute_name, previous_value, new_value, version) VALUES (@aevid+23, @aeid+4, 'studySite', NULL, @soid+1, 0);
INSERT INTO audit_event_values (id, audit_event_id, attribute_name, previous_value, new_value, version) VALUES (@aevid+24, @aeid+4, 'studySubjectIdentifier', NULL, '@ssid', 0);
		if @k<1
		begin
		--study organization
		INSERT INTO audit_event_values (id, audit_event_id, attribute_name, previous_value, new_value, version) VALUES (@aevid+25, @aeid+3, 'gridId', NULL, '@souuid', 0);
		INSERT INTO audit_event_values (id, audit_event_id, attribute_name, previous_value, new_value, version) VALUES (@aevid+26, @aeid+3, 'retiredIndicator', NULL, 'false', 0);
		INSERT INTO audit_event_values (id, audit_event_id, attribute_name, previous_value, new_value, version) VALUES (@aevid+27, @aeid+3, 'organization', NULL, '@siteid', 0);
		INSERT INTO audit_event_values (id, audit_event_id, attribute_name, previous_value, new_value, version) VALUES (@aevid+28, @aeid+3, 'startDate', NULL, 'Tue Mar 19 13:23:23 EDT 2013', 0);
		INSERT INTO audit_event_values (id, audit_event_id, attribute_name, previous_value, new_value, version) VALUES (@aevid+29, @aeid+3, 'study', NULL, '@studyid', 0);
		--study_site_wf_cfgs 1
		INSERT INTO audit_event_values (id, audit_event_id, attribute_name, previous_value, new_value, version) VALUES (@aevid+30, @aeid+5, 'gridId', NULL, '@sswcuuid1', 0);
		INSERT INTO audit_event_values (id, audit_event_id, attribute_name, previous_value, new_value, version) VALUES (@aevid+31, @aeid+5, 'name', NULL, 'report', 0);
		INSERT INTO audit_event_values (id, audit_event_id, attribute_name, previous_value, new_value, version) VALUES (@aevid+32, @aeid+5, 'studySite', NULL, @soid+1, 0);
		INSERT INTO audit_event_values (id, audit_event_id, attribute_name, previous_value, new_value, version) VALUES (@aevid+33, @aeid+5, 'workflowConfig', NULL, '2', 0);
		--study_site_wf_cfgs 2
		INSERT INTO audit_event_values (id, audit_event_id, attribute_name, previous_value, new_value, version) VALUES (@aevid+34, @aeid+6, 'gridId', NULL, '@sswcuuid2', 0);
		INSERT INTO audit_event_values (id, audit_event_id, attribute_name, previous_value, new_value, version) VALUES (@aevid+35, @aeid+6, 'name', NULL, 'reportingPeriod', 0);
		INSERT INTO audit_event_values (id, audit_event_id, attribute_name, previous_value, new_value, version) VALUES (@aevid+36, @aeid+6, 'studySite', NULL, @soid+1, 0);
		INSERT INTO audit_event_values (id, audit_event_id, attribute_name, previous_value, new_value, version) VALUES (@aevid+37, @aeid+6, 'workflowConfig', NULL, '1', 0);
		end
end transaction;
			set @k=@k+1;
		end
		set @j = @j + 1;
	end
	set @i = @i +1;
end