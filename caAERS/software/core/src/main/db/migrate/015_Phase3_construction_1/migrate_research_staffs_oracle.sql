--insert into 
--	site_research_staffs (id,researchstaff_id,site_id) 
--		select seq_srstaffs_id.nextval,id,site_id from research_staffs;

--update study_personnel stper 
--set site_research_staffs_id = 
--		(select srs.id from 
--			site_research_staffs srs where srs.researchstaff_id = stper.research_staffs_id);