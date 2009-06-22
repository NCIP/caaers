--
-- Dumping data for table `csm_application`
--

INSERT INTO csm_application
  (application_id,application_name,application_description,declarative_flag,active_flag,update_date)
VALUES(1,'csm_upt','UPT Super Admin Application',0,0,null);

INSERT INTO csm_application
  (application_id,application_name,application_description,declarative_flag,active_flag,update_date)
VALUES(2,'caAERS','caAERS',null,1,'8/29/2006');


--
-- Table structure for table `csm_user`
--

-- Dumping data for table `csm_user`
--



INSERT INTO csm_user
  (user_id,login_name,first_name,last_name,organization,department,title,phone_number,password,email_id,start_date,end_date,update_date)
VALUES(1,'SYSTEM_ADMIN','SYSTEM_ADMIN','SYSTEM_ADMIN',null,null,null,null,'system_admin',null,null,null,'8/30/2006');

INSERT INTO csm_user
  (user_id,login_name,first_name,last_name,organization,department,title,phone_number,password,email_id,start_date,end_date,update_date)
VALUES(2,'study_cd1','Study','Coordinator1',null,null,null,null,'password',null,null,null,'8/30/2006');

INSERT INTO csm_user
  (user_id,login_name,first_name,last_name,organization,department,title,phone_number,password,email_id,start_date,end_date,update_date)
VALUES(3,'study_cd2','Study','Coordinator',null,null,null,null,'password',null,null,null,'8/30/2006');

commit;