class InsertCtepInd extends edu.northwestern.bioinformatics.bering.Migration {
    void up() {
       if (databaseMatches('oracle')) {
        execute("insert into investigational_new_drugs(id, ind_number,grid_id,version) values(seq_investigational_new_dru_id.nextval,-111,'IND111',0)")
       	execute("insert into ind_holders (id, dtype, org_id, drug_id) values(seq_ind_holders_id.nextval,'ORG', (select id from organizations where name = 'Cancer Therapy Evaluation Program'), (select id from investigational_new_drugs where ind_number = -111))")
       }else{
       	execute("insert into investigational_new_drugs(ind_number,grid_id,version) values(-111,'IND111',0)")
       	execute("insert into ind_holders (dtype, org_id, drug_id) values('ORG', (select id from organizations where name = 'Cancer Therapy Evaluation Program'), (select id from investigational_new_drugs where ind_number = -111))")
       }
    }

    void down() {
        execute("delete  from ind_holders where org_id = (select id from organizations where name = 'Cancer Therapy Evaluation Program')")
		execute("delete  from investigational_new_drugs where ind_number = -111") 
    }
}
