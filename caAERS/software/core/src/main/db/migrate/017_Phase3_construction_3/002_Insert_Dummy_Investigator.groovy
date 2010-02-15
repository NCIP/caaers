class InsertDummyInvestigator extends edu.northwestern.bioinformatics.bering.Migration {
	void up() {
		if (databaseMatches('postgresql')){
			execute("insert into investigators (id,first_name,last_name,nci_identifier,email_address,phone_number,fax_number,allowed_to_login) values (-1111,'Dummy','Dummy','-1111','dummy@dummy.org','0000000000','0000000000','FALSE')")
			execute("insert into site_investigators (id,site_id,investigator_id,start_date) values (-1111,-1111,-1111,'2010-01-01')")
		}else if(databaseMatches('oracle')){
			execute("insert into investigators (id,first_name,last_name,nci_identifier,email_address,phone_number,fax_number,allowed_to_login) values (-1111,'Dummy','Dummy','-1111','dummy@dummy.org','0000000000','0000000000',0)")
			execute("insert into site_investigators (id,site_id,investigator_id,start_date) values (-1111,-1111,-1111,to_date('2010-01-01', 'yyyy/mm/dd'))")
		}
		
	}
	void down() {
		execute("delete from site_investigators where id = -1111")
		execute("delete from investigators where id = -1111")
	}
}