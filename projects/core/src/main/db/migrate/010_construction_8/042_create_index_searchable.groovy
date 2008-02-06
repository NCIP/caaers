class CreateIndicies extends edu.northwestern.bioinformatics.bering.Migration {
    void up() {
 		execute('CREATE INDEX studies_short_title_idx ON studies (short_title)')
 		execute('CREATE INDEX identifiers_valtyp_idx ON identifiers (value, "type")')
 		execute('CREATE INDEX participants_flegdob_idx ON participants (first_name, last_name, ethnicity, gender, birth_year, birth_month, birth_day)')
 		execute('CREATE INDEX organizations_namecode_idx ON organizations (name, nci_institute_code)')
 		
    }

    void down() {
        dropTable('DROP INDEX studies_short_title_idx')
        dropTable('DROP INDEX identifiers_valtyp_idx')
        dropTable('DROP INDEX participants_flegdob_idx')
        dropTable('DROP INDEX organizations_namecode_idx')
    }
}