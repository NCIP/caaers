class CreateIndicies extends edu.northwestern.bioinformatics.bering.Migration {
    void up() {
 		execute('CREATE INDEX studies_short_title_idx ON studies (short_title)')
 		execute('CREATE INDEX identifiers_valtyp_idx ON identifiers (value)')
 		execute('CREATE INDEX participants_flname_idx ON participants (first_name)')
 		execute('CREATE INDEX participants_flname_idx2 ON participants (last_name)')
 		execute('CREATE INDEX participants_eg_idx1 ON participants (ethnicity)')
 		execute('CREATE INDEX participants_eg_idx2 ON participants (gender)')
 		execute('CREATE INDEX participants_dob_idx1 ON participants (birth_year)')
 		execute('CREATE INDEX participants_dob_idx2 ON participants (birth_month)') 
 		execute('CREATE INDEX organizations_namecode_idx1 ON organizations (name)')
 		execute('CREATE INDEX organizations_namecode_idx2 ON organizations (nci_institute_code)')
 		
    }

    void down() {
        dropTable('DROP INDEX studies_short_title_idx')
        dropTable('DROP INDEX identifiers_valtyp_idx')
        dropTable('DROP INDEX participants_flname_idx')
        dropTable('DROP INDEX participants_flname_idx2')
        dropTable('DROP INDEX participants_eg_idx1')
        dropTable('DROP INDEX participants_eg_idx2')
        dropTable('DROP INDEX participants_dob_idx1')
        dropTable('DROP INDEX participants_dob_idx2')
        dropTable('DROP INDEX organizations_namecode_idx1')
        dropTable('DROP INDEX organizations_namecode_idx2')
        
    }
}