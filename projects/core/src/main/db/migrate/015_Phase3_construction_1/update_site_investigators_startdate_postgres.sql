update site_investigators si
set start_date = (select ae.time from audit_events ae 
				       where CAST(ae.object_id AS INTEGER) = si.id and 
				       ae.operation = 'CREATE' and 
				       ae.class_name = 'gov.nih.nci.cabig.caaers.domain.SiteInvestigator');
				       
update site_investigators set start_date = '2009-01-01' where start_date IS null;
				       
CREATE TABLE temp
(
  login_name text,
  start_date timestamp without time zone
);

insert into temp
(start_date,login_name)
select min(si.start_date) as start_date,cu.login_name as login_name 
from 
	site_investigators si, investigators i,csm_user cu 
where 
	si.investigator_id = i.id and
	cu.login_name = i.login_id 
Group by
	cu.login_name,si.start_date,i.id;	

update csm_user cu set start_date = (select min(start_date) from temp where cu.login_name = temp.login_name);
update csm_user set start_date = '2009-01-01' where start_date IS null;

drop table temp;