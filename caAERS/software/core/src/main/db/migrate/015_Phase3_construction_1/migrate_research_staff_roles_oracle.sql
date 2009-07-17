insert into site_research_staff_roles (id,role_code,site_research_staffs_id,start_date)
select seq_site_research_staff_roles_id.nextval, cg.group_name, srs.id, to_date('2008-01-01', 'yyyy/mm/dd')
from 
	csm_user cu,csm_user_group cug,csm_group cg,research_staffs rs,site_research_staffs srs
where
	cu.user_id = cug.user_id and
	cug.group_id IN (-4,-5,-13,-14,-7942,-7943) and 
	cug.group_id = cg.group_id and
	rs.login_id = cu.login_name and
	rs.email_address = cu.email_id and
	rs.id = srs.researchstaff_id
group by cu.user_id,cg.group_name,srs.id
order by cu.user_id